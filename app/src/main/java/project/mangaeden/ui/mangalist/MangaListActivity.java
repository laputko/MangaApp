package project.mangaeden.ui.mangalist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import project.mangaeden.MangaEdenApp;
import project.mangaeden.R;
import project.mangaeden.api.MangaApi;
import project.mangaeden.model.Manga;
import project.mangaeden.model.MangaListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangaListActivity extends AppCompatActivity {
    private MangaAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_list);
        adapter = new MangaAdapter(new ArrayList<Manga>(), new MangaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Manga manga) {
                Toast.makeText(MangaListActivity.this, manga.getID(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMangaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        MangaEdenApp.getMangaApi().getMangaList(0, 0, 25).enqueue(new Callback<MangaListResponse>() {
            @Override
            public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                adapter.mangaList.clear();
                adapter.mangaList.addAll(response.body().getMangas());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MangaListResponse> call, Throwable t) {

            }
        });
    }
}
