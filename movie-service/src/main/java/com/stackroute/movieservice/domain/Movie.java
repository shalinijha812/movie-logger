package com.stackroute.movieservice.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class Movie {

    @Id
    private int id;
    private String title;
    private String language;
    private String comments;

    public Movie(int id, String title, String language, String comments) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.comments = comments;
    }

    public Movie() {
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
