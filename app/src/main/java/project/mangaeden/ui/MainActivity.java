package project.mangaeden.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import project.mangaeden.R;
import project.mangaeden.api.MangaApi;
import project.mangaeden.model.Chapter;
import project.mangaeden.model.ChapterDeserializer;
import project.mangaeden.model.Image;
import project.mangaeden.model.ImageDeserializer;
import project.mangaeden.model.ImageListResponse;
import project.mangaeden.model.Manga;
import project.mangaeden.model.MangaListResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> arr = new ArrayList<>();
        arr.add("Action");
        arr.add("Drama");

        final Manga newManga = new Manga("Dream", arr, 5, "123123", "123123", 123123, 1, "Dream");
//Кнопка 1
        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(newManga.toString());
            }
        });

//Кнопка 2
        Button btnGetMangas = (Button) findViewById(R.id.btnGetMangas);
        btnGetMangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//Конвертер для Image
        Gson gson1 = new GsonBuilder()
                .registerTypeAdapter(Image.class, new ImageDeserializer())
                .create();

//Конвертер для Chapter
        Gson gson2 = new GsonBuilder()
                .registerTypeAdapter(Chapter.class, new ChapterDeserializer())
                .create();

//Логирование
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("https://www.mangaeden.com/") //Базовая часть адреса
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson1)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://www.mangaeden.com/") //Базовая часть адреса
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson2)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();

//Создаем объект, при помощи которого будем выполнять запросы
        MangaApi mangaApi = retrofit1.create(MangaApi.class);
    }
}
