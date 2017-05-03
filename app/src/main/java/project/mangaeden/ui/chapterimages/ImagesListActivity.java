package project.mangaeden.ui.chapterimages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import project.mangaeden.MangaEdenApp;
import project.mangaeden.R;
import project.mangaeden.model.Image;
import project.mangaeden.model.ImageListResponse;
import project.mangaeden.model.Manga;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImagesListActivity extends AppCompatActivity {

    public static final String CHAPTER_ID = "ChapterID";
    public static final String CHAPTER_TITTLE = "ChapterTittle";

    private ImageAdapter adapter;
    private RecyclerView recyclerView;
    ArrayList<Image> list = new ArrayList<>();

    private String chapterID;

    public static Intent newIntent(Context context, String chapterID, String chapterTittle) {
        Intent intent = new Intent(context, ImagesListActivity.class);
        intent.putExtra(CHAPTER_ID, chapterID);
        intent.putExtra(CHAPTER_TITTLE, chapterTittle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        String title = getIntent().getExtras().getString(CHAPTER_TITTLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        adapter = new ImageAdapter(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Image image) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rcImageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        chapterID = getIntent().getExtras().getString(CHAPTER_ID);

        MangaEdenApp.getMangaApi().getChapterImageList(chapterID).enqueue(new Callback<ImageListResponse>() {
            @Override
            public void onResponse(Call<ImageListResponse> call, Response<ImageListResponse> response) {
                list = response.body().getImages();
                addDataToAdapter(list);
            }

            @Override
            public void onFailure(Call<ImageListResponse> call, Throwable t) {

            }
        });
    }

    private void addDataToAdapter(ArrayList<Image> list) {
        adapter.imageList.clear();                                                          //Очищаем
        adapter.imageList.addAll(list);                                                     //Добавляем все
        adapter.notifyDataSetChanged();                                                     //Обновляем список
    }
}

