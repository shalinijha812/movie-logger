package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@Qualifier("implementation1")
public class MovieServiceImpl implements MovieService{
    @Value("${movieservice.service.exceptionmsg1}")
    private String exceptionmsg1;
    @Value("${movieservice.service.exceptionmsg2}")
    private String exceptionmsg2;
    @Value("${movieservice.service.successmsg}")
    private String successmsg;
    private MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie insertMovie(Movie movie) throws MovieAlreadyExistsException {
        if (movieRepository.existsById(movie.getId())) {
            throw new MovieAlreadyExistsException(exceptionmsg1);
        }

        Movie insertdMovie = movieRepository.insert(movie);
        if (insertdMovie == null) {
            throw new MovieAlreadyExistsException(exceptionmsg1);
        }
        return insertdMovie;
    }

//            else
//                throw new MovieAlreadyExistsException();
//        }
//        catch(MovieAlreadyExistsException e)
//        {
//            e.printStackTrace();
//        }
//        return movie;


//    @Override
//    public void viewInfo() {
//        List<Movie> movieList=(List<Movie>)movieRepository.findAll();
//        for(Movie movie:movieList)
//        {
//            System.out.println(movie);
//        }
//
//       // return  (List<Movie>)movieRepository.findAll();
//    }

    @Override
    public Movie updateComments(int id,String comments) throws MovieNotFoundException
    {
           if (movieRepository.existsById(id)) {
               Movie updatedMovie = movieRepository.findById(id).get();
               updatedMovie.setComments(comments);
               Movie updatedInsertdMovie = movieRepository.insert(updatedMovie);
               return updatedInsertdMovie;

           }
           else
               throw new MovieNotFoundException(exceptionmsg2);


    }

    @Override
    public String deleteMovie(int id) throws MovieNotFoundException {
            if (movieRepository.existsById(id))
            {
                movieRepository.deleteById(id);
                return successmsg;
            } else
                throw new MovieNotFoundException(exceptionmsg2);
    }




    @Override
    public List<Movie> getAllMovie() {
        return  (List<Movie>)movieRepository.findAll();
    }

    @Override
    public Movie searchMovieByName(String name) throws MovieNotFoundException{
            if (movieRepository.findByTitle(name) != null) {
                Movie searchedMovie = movieRepository.findByTitle(name);

                return searchedMovie;
            } else
                throw new MovieNotFoundException(exceptionmsg2);

    }
}
