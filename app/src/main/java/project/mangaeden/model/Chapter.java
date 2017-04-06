package project.mangaeden.model;

public class Chapter {
    private int number;
    private int date;
    private String title;
    private String ID;

    public Chapter(int number, int date, String title, String ID) {
        this.number = number;
        this.date = date;
        this.title = title;
        this.ID = ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "number=" + number +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
