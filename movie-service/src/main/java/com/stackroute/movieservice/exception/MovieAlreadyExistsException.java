package com.stackroute.movieservice.exception;

public class MovieAlreadyExistsException extends Exception {
    private String MovieAlreadymessage;
    public MovieAlreadyExistsException(){};

    public MovieAlreadyExistsException(String MovieAlreadymessage){super(MovieAlreadymessage);
    this.MovieAlreadymessage=MovieAlreadymessage;
    }
}
