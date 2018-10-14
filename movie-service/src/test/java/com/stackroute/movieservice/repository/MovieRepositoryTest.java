package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
    public class MovieRepositoryTest {

        @Autowired
        MovieRepository movieRepository;
        Movie movie;

        @Before
        public void setUp()
        {
           movie = new Movie();
           movie.setId(101);
           movie.setTitle("Rang De Basanti");
           movie.setComments("good movie");
           movie.setLanguage("Hindi");

        }
        @After
        public void after(){
            movieRepository.deleteAll();
        }
        @Test
        public void testInsertMovie(){
            movieRepository.insert(movie);
            Movie fetchMovie = movieRepository.findById(movie.getId()).get();
            Assert.assertEquals(101,fetchMovie.getId());

        }
        @Test(expected=NoSuchElementException.class)
        public void testInsertMovieFailure(){
            Movie testMovie = new Movie(10,"spiderman","English","4 star");
            movieRepository.insert(testMovie);
            Movie fetchMovie=movieRepository.findById(movie.getId()).get();
            Assert.assertNotSame(fetchMovie.getTitle(),testMovie.getTitle());
//            movieRepository.insert(movie);
//            Movie fetchMovie = movieRepository.findById(movie.getId()).get();
//            Assert.assertNotSame(movie,fetchMovie);
        }
        @Test
        public void testGetAllMovie(){
            List<Movie> listold = movieRepository.findAll();
            Movie movie1 = new Movie(111,"Gita Govindam","Telugu","3 star");
            Movie movie2 = new Movie(112,"Buommarillu","Telugu","4 star");
            movieRepository.insert(movie1);
            movieRepository.insert(movie2);
            List<Movie> listUpdated = movieRepository.findAll();
            Assert.assertEquals(listold.size()+2,listUpdated.size());
            movieRepository.delete(movie1);
            movieRepository.delete(movie2);
        }
        @Test
        public void deleteByIdTest(){
            List<Movie> listold = movieRepository.findAll();
            Movie movie;
            Movie movie1 = new Movie(11,"Holiday","hindi","5 star");
            movieRepository.insert(movie1);
            movieRepository.deleteById(movie1.getId());
            List<Movie> listnew = movieRepository.findAll();
            Assert.assertEquals(listold.size(),listnew.size());
        }

        @Test
        public void findByTitleTest(){
            Movie movie1 = new Movie(12,"pk","hindi","5 star");
            movieRepository.insert(movie1);
            Movie movieReturned=movieRepository.findByTitle("pk");
            Assert.assertEquals(movie1.getTitle(),movieReturned.getTitle());
        }
        @Test
        public void findByIdTest()throws NoSuchElementException{
            Movie movie1 = new Movie(121,"pk","hindi","5 star");
            movieRepository.insert(movie1);
            Movie movieReturned=movieRepository.findById(movie1.getId()).get();
            Assert.assertEquals(movie1.getTitle(),movieReturned.getTitle());
            movieRepository.delete(movie1);
        }
        @Test
        public void existsByIdTest(){
            Movie movie1 = new Movie(14,"dhadak","hindi","5 star");
            movieRepository.insert(movie1);
            Boolean result=movieRepository.existsById(movie1.getId());
            Assert.assertEquals(true,result);
            movieRepository.delete(movie1);
        }
        @Test
        public void deleteByIdFailureTest(){

            Movie movie;
            List<Movie> listold1= movieRepository.findAll();
            Movie movie1 = new Movie(13,"A wednesday","hindi","5 star");
            movieRepository.insert(movie1);
            //System.out.println(movieRepository.findById(movie1.getId()));
            movieRepository.deleteById(movie1.getId()+1);
            List<Movie> listnew1 = movieRepository.findAll();
            Assert.assertNotEquals(listold1.size(),listnew1.size());
        }
        @Test(expected = NoSuchElementException.class)
        public void updateSuccess(){
//            movieRepository.insert(movie);
            Movie movie1 = movieRepository.findById(movie.getId()).get();
            movie1.setComments("nice movie");
            movieRepository.insert(movie1);
            Assert.assertEquals("nice movie", movie1.getComments());
        }



    }
