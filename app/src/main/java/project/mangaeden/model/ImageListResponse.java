package project.mangaeden.model;

import java.util.List;

public class ImageListResponse {
    private List<Image> images;

    public ImageListResponse(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ImageListResponse{" +
                "images=" + images +
                '}';
    }
}
