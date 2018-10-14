package com.stackroute.movieservice.configuration;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class DataPreLoadConfig implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    MovieRepository movieRepository;

    @Autowired
    public DataPreLoadConfig(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Movie movie = new Movie(4, "Titanic", "English", "Ultimate movie");
        movieRepository.insert(movie);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Movie movie = new Movie(1, "3idiots", "hindi", "best movie");
        movieRepository.insert(movie);
        movie = new Movie(2, "Guru", "hindi", "avg movie");
        movieRepository.insert(movie);
        movie = new Movie(3, "Dangal", "hindi", "awesome movie");
        movieRepository.insert(movie);
    }
}
