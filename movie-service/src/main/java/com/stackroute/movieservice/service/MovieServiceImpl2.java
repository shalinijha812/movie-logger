//package com.stackroute.movieservice.service;
//
//import com.stackroute.movieservice.domain.Movie;
//import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
//import com.stackroute.movieservice.exception.MovieNotFoundException;
//import com.stackroute.movieservice.repository.MovieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Qualifier("implementation2")
//
//public class MovieServiceImpl2 {
//    public class MovieServiceImpl implements MovieService{
//
//        private MovieRepository movieRepository;
//        @Autowired
//        public MovieServiceImpl(MovieRepository movieRepository) {
//            this.movieRepository = movieRepository;
//        }
//
//        @Override
//        public Movie insertMovie(Movie movie) throws MovieAlreadyExistsException {
//            if (movieRepository.existsById(movie.getId())) {
//                throw new MovieAlreadyExistsException("movie already exists");
//            }
//
//            Movie insertdMovie = movieRepository.insert(movie);
//            if (insertdMovie == null) {
//                throw new MovieAlreadyExistsException("movie already exists");
//            }
//            return insertdMovie;
//        }
//
////            else
////                throw new MovieAlreadyExistsException();
////        }
////        catch(MovieAlreadyExistsException e)
////        {
////            e.printStackTrace();
////        }
////        return movie;
//
//
////    @Override
////    public void viewInfo() {
////        List<Movie> movieList=(List<Movie>)movieRepository.findAll();
////        for(Movie movie:movieList)
////        {
////            System.out.println(movie);
////        }
////
////       // return  (List<Movie>)movieRepository.findAll();
////    }
//
//        @Override
//        public Movie updateComments(int id,String comments) throws MovieNotFoundException
//        {
//            if (movieRepository.existsById(id)) {
//                Movie updatedMovie = movieRepository.findById(id).get();
//                updatedMovie.setComments(comments);
//                Movie updatedInsertdMovie = movieRepository.insert(updatedMovie);
//                return updatedInsertdMovie;
//
//            }
//            else
//                throw new MovieNotFoundException("Movie not found");
//
//
//        }
//
//        @Override
//        public String deleteMovie(int id) throws MovieNotFoundException {
//            if (movieRepository.existsById(id)) {
//                movieRepository.deleteById(id);
//                return "Succesfully deleted";
//            } else
//                throw new MovieNotFoundException("Movie Not Found");
//        }
//
//
//
//
//        @Override
//        public List<Movie> getAllMovie() {
//            return  (List<Movie>)movieRepository.findAll();
//        }
//
//        @Override
//        public Movie searchMovieByName(String name) throws MovieNotFoundException{
//            if (movieRepository.findByTitle(name) != null) {
//                Movie searchedMovie = movieRepository.findByTitle(name);
//
//                return searchedMovie;
//            } else
//                throw new MovieNotFoundException("Movie Not Found");
//
//        }
//    }
//
//}
