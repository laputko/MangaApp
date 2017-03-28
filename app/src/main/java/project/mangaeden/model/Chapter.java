package project.mangaeden.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by admin on 28.03.2017.
 */

public class Chapter {
    private ArrayList<String> aka;
    @SerializedName("aka-alias")
    private ArrayList<String> akaAliases;
    private String alias;
    private String artist;
    @SerializedName("artist-kw")
    private ArrayList<String> artistsKw;

    public Chapter(ArrayList<String> aka, ArrayList<String> akaAliases, String alias, String artist, ArrayList<String> artistsKw) {
        this.aka = aka;
        this.akaAliases = akaAliases;
        this.alias = alias;
        this.artist = artist;
        this.artistsKw = artistsKw;
    }


    public ArrayList<String> getAka() {
        return aka;
    }

    public void setAka(ArrayList<String> aka) {
        this.aka = aka;
    }

    public ArrayList<String> getAkaAliases() {
        return akaAliases;
    }

    public void setAkaAliases(ArrayList<String> akaAliases) {
        this.akaAliases = akaAliases;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public ArrayList<String> getArtistsKw() {
        return artistsKw;
    }

    public void setArtistsKw(ArrayList<String> artistsKw) {
        this.artistsKw = artistsKw;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "aka=" + aka +
                ", akaAliases=" + akaAliases +
                ", alias='" + alias + '\'' +
                ", artist='" + artist + '\'' +
                ", artistsKw=" + artistsKw +
                '}';
    }
}
