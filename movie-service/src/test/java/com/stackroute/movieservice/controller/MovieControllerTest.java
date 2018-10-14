package com.stackroute.movieservice.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Movie movie;
    @MockBean
    private MovieService movieService;
//    @MockBean
//    private UserService userService;
    @InjectMocks
    private MovieController movieController;

    private List<Movie> list =null;
    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = new Movie(5,"DDLJ","hindi","All time movie");
        list = new ArrayList();
        list.add(movie);
    }
    @Test
    public void insertMovie() throws Exception {
        when(movieService.insertMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void insertMovieFailure() throws Exception {
        when(movieService.insertMovie(any())).thenThrow(MovieAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie/")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllMovie() throws Exception {
        when(movieService.getAllMovie()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void searchMovieSuccess() throws Exception {
        when(movieService.searchMovieByName(movie.getTitle())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/DDLJ")
                .content(movie.getTitle())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void searchMovieFailure() throws Exception {
        when(movieService.searchMovieByName(movie.getTitle())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/DDLJ")
                .content(movie.getTitle())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        when(movieService.deleteMovie(movie.getId())).thenReturn("Successfully deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/6"))
                //.contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testDeleteFailure() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/645667")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
//        @Test
//        public void testdeleteMoviefailure() throws Exception {
//            when(movieService.deleteMovie(movie.getId())).thenThrow(MovieNotFoundException.class);
//            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/60"))
//                    //.contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
//                    .andExpect(MockMvcResultMatchers.status().isConflict())
//                    .andDo(MockMvcResultHandlers.print());
//    }
    @Test
    public void updateSuccess() throws Exception {
        when(movieService.updateComments(6,"bad movie")).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie/6").content(asJsonString(movie))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateFailure() throws Exception {
        when(movieService.updateComments(anyInt(),anyString())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie/7").content(asJsonString(movie))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

//                .andEx(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());





    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}
