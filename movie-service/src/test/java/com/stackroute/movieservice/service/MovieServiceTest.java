package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

   Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie(6,"Raaz","hindi","2 star");
//        user.setLastName("John");
//        user.setId(101);
//        user.setFirstName("Jenny");
//        user.setAge(10);
        list = new ArrayList<>();
        list.add(movie);


    }
    @Test
    public void insertMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.insert((Movie) any())).thenReturn(movie);
        Movie insertdMovie = movieService.insertMovie(movie);
        Assert.assertEquals(movie,insertdMovie);

        //verify here verifies that userRepository insert method is only called once
        verify(movieRepository,times(1)).insert(movie);

    }
    @Test(expected = MovieAlreadyExistsException.class)
    public void insertMovieTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.insert((Movie) any())).thenReturn(null);
        Movie insertdMovie = movieService.insertMovie(movie);
        System.out.println("insertdMovie" + insertdMovie);
        Assert.assertEquals(movie, insertdMovie);
    }
    @Test(expected= NoSuchElementException.class)
   public void updateMovieSuccessTest()throws MovieAlreadyExistsException,MovieNotFoundException {
        //movieRepository.insert(movie);
        when(movieRepository.existsById(movie.getId())).thenReturn(true);
        when(movieRepository.findById(movie.getId()).get()).thenReturn(movie);
        when(movieRepository.insert((Movie) any())).thenReturn(movie);
        Movie insertdMovie = movieService.insertMovie(movie);
        //movieRepository.insert(movie);
        Movie updatedMovie = movieService.updateComments(movie.getId(), "bad movie");
        Assert.assertEquals(movie.getComments(), updatedMovie.getComments());
        verify(movieRepository,times(1)).insert(movie);
    }

    @Test(expected=MovieNotFoundException.class)
    public void updateMovieFailureTest()throws MovieAlreadyExistsException,MovieNotFoundException {
        //movieRepository.insert(movie);
        when(movieRepository.existsById(movie.getId())).thenReturn(false);
        Movie updatedMovie = movieService.updateComments(movie.getId(), "bad movie");
//        when(movieRepository.findById(movie.getId()).get()).thenReturn(movie);
//
        //Movie insertdMovie = movieService.insertMovie(movie);
        //movieRepository.insert(movie);

//        Assert.assertEquals(movie.getComments(), updatedMovie.getComments());
//        verify(movieRepository,times(1)).insert(movie);
    }


//        updatedMovie.setComments("1 star movie");
//        Movie updatedInsertdMovie = movieRepository.insert(updatedMovie);
//        Assert.assertEquals("1 star movie",updatedInsertdMovie.getComments());






//    }
    @Test
    public void getallMovieTest()
   {

        movieRepository.insert(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movielist = movieService.getAllMovie();
        Assert.assertEquals(list,movielist);

        //add verify
    }
    @Test
    public void  deleteMovieSuccess()throws MovieNotFoundException,MovieAlreadyExistsException {
        movieRepository.insert(movie);

        when(movieRepository.existsById(movie.getId())).thenReturn(true);
        String expectedmsg = movieService.deleteMovie(movie.getId());
        Assert.assertEquals(expectedmsg, "Succesfully deleted");
        verify(movieRepository, times(1)).deleteById(movie.getId());
    }
    @Test(expected=MovieNotFoundException.class)
    public void  deleteMovieFailure()throws MovieNotFoundException {
        //movieRepository.insert(movie);
        when(movieRepository.existsById(movie.getId())).thenReturn(false);
        movieService.deleteMovie(movie.getId());
    }
     @Test
    public void searchMovieSuccess()throws MovieNotFoundException{
        when(movieRepository.existsById(movie.getId())).thenReturn(true);
         when(movieRepository.findByTitle(movie.getTitle())).thenReturn(movie);
         Movie searchedMovie=movieService.searchMovieByName(movie.getTitle());
    }
    @Test(expected =MovieNotFoundException.class)
    public void searchMovieFailure()throws MovieNotFoundException{
        when(movieRepository.existsById(movie.getId())).thenReturn(false);
        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(null);
        Movie searchedMovie=movieService.searchMovieByName(movie.getTitle());
    }//movieRepository.deleteById(insertdMovie.getId());

//       Movie movie2 = new Movie(6,"Raaz","hindi","2 star");
//        movieRepository.insert(movie2);
//           String expectedmsg = movieService.deleteMovie(movie.getId());
//            Assert.assertEquals(expectedmsg, "Succesfully deleted");







}
