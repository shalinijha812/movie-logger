package com.stackroute.movieservice.controller;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie/")
public class MovieController {

    private MovieService movieService;
    Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping()
    public ResponseEntity<?> insertMovie(@RequestBody Movie movie) {
        ResponseEntity responseEntity;
        try {
            Movie insertdMovie = movieService.insertMovie(movie);
            responseEntity = new ResponseEntity<Movie>(insertdMovie, HttpStatus.CREATED);
            logger.info("The movie is saved to the database successfully!!!");
        }
        catch(MovieAlreadyExistsException e)
        {

            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            logger.error("This movie is already there in the database...Try another one!!");

        }
        return responseEntity;
    }
//    @PostMapping("movie/list")
//    public  ResponseEntity<?> insertMoreMovies(@RequestBody List<Movie> listMovies) {
//        for(int i=0;i<listMovies.size();i++) {
//            Movie insertdMovie = movieService.insertMovie(listMovies.get(i));
//        }
//        ResponseEntity responseEntity = new ResponseEntity<List<Movie >>(movieService.getAllMovie(), HttpStatus.OK);
//
//        return responseEntity;
//
//    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") int id ,@RequestBody String comments) {
        ResponseEntity responseEntity;
        try {

            Movie updatedMovie = movieService.updateComments(id, comments);
            responseEntity = new ResponseEntity<Movie>(updatedMovie, HttpStatus.CREATED);
            logger.info("The required movie is updated with new comments.....");
        }
        catch(MovieNotFoundException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            logger.error("This movie is not there in the database...Try another one!!");
        }
        return responseEntity;
    }


//    public boolean deleteMovie(@PathVariable("id") int id)
//    {
//        List<Movie> movieList;
//        movieList=movieService.deleteMovie(id);
//        return true;
//    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") int id) {
//        List<Movie> movieList;
        ResponseEntity responseEntity;

        try {
            String message = movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
            logger.info("The movie is deleted successfully....");
        }
        catch(MovieNotFoundException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            logger.error("This movie is already there in the database...Try another one!!");
        }
        return responseEntity;
    }
    @GetMapping()
    public ResponseEntity<?> getAllMovie()
    {
        List<Movie> movieList;
        movieList=movieService.getAllMovie();
        ResponseEntity responseEntity=new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
        logger.info("The required list is shown!!All cool!!");

        return responseEntity;
    }
    @GetMapping("{title}")
    public ResponseEntity<?>  searchByTitle(@PathVariable("title") String title)
    {
        ResponseEntity responseEntity;
        try{
            Movie searchedMovie=movieService.searchMovieByName(title);
            responseEntity = new ResponseEntity<Movie>(searchedMovie, HttpStatus.OK);
            logger.info("The required movie is found...System is working perfectly!!");


        }
        catch(MovieNotFoundException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            logger.error("This movie is already there in the database...Try another one!!");
        }

        return responseEntity;

    }


}
