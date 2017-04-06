package project.mangaeden.api;

import android.Manifest;

import project.mangaeden.model.ChaptersListResponse;
import project.mangaeden.model.ImageListResponse;
import project.mangaeden.model.MangaListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface MangaApi {

    @GET("api/list/{lang}/")
    Call<MangaListResponse> getMangaList(@Path("lang") int lang);

    @GET("api/manga/{mangaId}/")
    Call<ChaptersListResponse> getChapterList(@Path("mangaId") String mangaId);

    @GET("api/chapter/{chapterId}/")
    Call<ImageListResponse> getChapterImageList(@Path("chapterId") String chapterId);
}

