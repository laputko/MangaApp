package project.mangaeden.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import project.mangaeden.MangaEdenApp;
import project.mangaeden.R;
import project.mangaeden.model.ImageListResponse;
import project.mangaeden.model.MangaFullDescription;
import project.mangaeden.model.MangaListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnGetMangas = (Button) findViewById(R.id.btnGetMangas);
        btnGetMangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MangaEdenApp.getMangaApi().getMangaList(0, 0, 25).enqueue(new Callback<MangaListResponse>() {
                    @Override
                    public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                        Log.d("Mangas",response.body().getMangas().toString());
                    }

                    @Override
                    public void onFailure(Call<MangaListResponse> call, Throwable t) {
                        Log.d("Mangas",t.getMessage());
                    }
                });
            }
        });

        //54430be945b9ef3a6d5818cc
        Button btnGetMangaFull = (Button) findViewById(R.id.btnGetMangaFull);
        btnGetMangaFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MangaEdenApp.getMangaApi().getMangaFull("54430be945b9ef3a6d5818cc").enqueue(new Callback<MangaFullDescription>() {
                    @Override
                    public void onResponse(Call<MangaFullDescription> call, Response<MangaFullDescription> response) {
                        Log.d("MangaDescription", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<MangaFullDescription> call, Throwable t) {
                        Log.d("MangaDescription",t.getMessage());
                    }
                });
            }
        });

        //5473d04345b9ef823ed0addb
        Button btnGetImages = (Button) findViewById(R.id.btnGetImages);
        btnGetImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MangaEdenApp.getMangaApi().getChapterImageList("5473d04345b9ef823ed0addb").enqueue(new Callback<ImageListResponse>() {
                    @Override
                    public void onResponse(Call<ImageListResponse> call, Response<ImageListResponse> response) {
                        Log.d("ImageList", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ImageListResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
