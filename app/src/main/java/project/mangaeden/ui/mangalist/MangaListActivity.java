package project.mangaeden.ui.mangalist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import project.mangaeden.MangaEdenApp;
import project.mangaeden.R;
import project.mangaeden.api.MangaApi;
import project.mangaeden.model.Manga;
import project.mangaeden.model.MangaListResponse;
import project.mangaeden.ui.mangadescription.MangaFullDescriptionActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangaListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private MangaAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private TextView tvNoData;
    ArrayList<Manga> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_list);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMangaList();
            }
        });

        //По нажатию, переходим в новую активность
        adapter = new MangaAdapter(new ArrayList<Manga>(), new MangaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Manga manga) {
                startActivity(MangaFullDescriptionActivity.newIntent(MangaListActivity.this, manga.getID(), manga.getTitle()));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMangaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        tvNoData = (TextView) findViewById(R.id.tvNoData);

        list = new ArrayList<>();

        getMangaList();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manga_list, menu);                                         //Когда вызываем преобразуй xml файл в menu
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private void getMangaList() {
        //Формируем список манг
        MangaEdenApp.getMangaApi().getMangaList(0).enqueue(new Callback<MangaListResponse>() {
            @Override
            public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                tvNoData.setVisibility(View.GONE);
                list = response.body().getMangas();
                Collections.sort(list);
                addDataToAdapter(list.subList(0, 25));
                onStopRefresh();
            }

            @Override
            public void onFailure(Call<MangaListResponse> call, Throwable t) {
                onStopRefresh();
                tvNoData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void addDataToAdapter(List<Manga> list) {
        adapter.mangaList.clear();                                                          //Очищаем
        adapter.mangaList.addAll(list);                                                     //Добавляем все
        adapter.notifyDataSetChanged();                                                     //Обновляем список
    }

    private void onStopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        tvNoData.setVisibility(View.GONE);
        if (newText.isEmpty()) {
            addDataToAdapter(list.subList(0, 25));
        } else if (!list.isEmpty()) {

            List<Manga> resultList = new ArrayList<>();
            for (Manga manga : list) {
                if (manga.getTitle().toLowerCase().startsWith(newText.toLowerCase())) {
                    resultList.add(manga);
                }
            }
            addDataToAdapter(resultList);
            if (resultList.isEmpty()) tvNoData.setVisibility(View.VISIBLE);
        }

        return true;
    }
}
