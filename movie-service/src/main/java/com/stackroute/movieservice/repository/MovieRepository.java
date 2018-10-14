package com.stackroute.movieservice.repository;


import com.stackroute.movieservice.domain.Movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface MovieRepository extends MongoRepository<Movie,Integer> {
   // @Query("S")
    public Movie findByTitle(String movieTitle);

    }
