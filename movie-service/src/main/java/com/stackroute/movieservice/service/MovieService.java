package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {
    public Movie insertMovie(Movie movie) throws MovieAlreadyExistsException;
    //public void viewInfo();
    public Movie updateComments(int id,String comments) throws MovieNotFoundException;
    public String deleteMovie(int id) throws MovieNotFoundException;
    public List<Movie> getAllMovie();
    public Movie searchMovieByName(String name) throws MovieNotFoundException;

}
