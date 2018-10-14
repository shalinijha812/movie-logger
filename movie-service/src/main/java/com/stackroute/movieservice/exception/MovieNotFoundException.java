package com.stackroute.movieservice.exception;

public class MovieNotFoundException extends Exception{
    private String movieNotFoundMessage;
    public MovieNotFoundException(){};

    public MovieNotFoundException(String movieNotFoundMessage) {
        super(movieNotFoundMessage);
        this.movieNotFoundMessage = movieNotFoundMessage;
    }
}
