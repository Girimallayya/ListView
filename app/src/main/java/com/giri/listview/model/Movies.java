package com.giri.listview.model;

import java.util.List;

/**
 * Created by Giri on 13-Feb-18.
 */

public class Movies {

    private String title;
    private String image;
    private String rating;
    private String releaseyear;
    private List<String> genre;

    public Movies() {

    }

    public Movies(String title, String image, String rating, String releaseyear) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseyear = releaseyear;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(String releaseyear) {
        this.releaseyear = releaseyear;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating='" + rating + '\'' +
                ", releaseyear='" + releaseyear + '\'' +
                ", genre=" + genre +
                '}';
    }

}
