package com.betulsahin.filmkoleksiyonuapp.service;

import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.repository.ActorRepository;
import com.betulsahin.filmkoleksiyonuapp.repository.CategoryRepository;
import com.betulsahin.filmkoleksiyonuapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private static final String INVALID_ID = "Invalid id: %s";

    @Autowired
    private MovieRepository movieRepository;

    public Movie getById(long id){
        return movieRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException(String.format(INVALID_ID, id)));
    }

    public List<Movie> getAllByKeyword(String keyword){
        return movieRepository.findAllByKeyword(keyword);
    }

    public List<Movie> getAllByFilter(String filter) {
        List<Movie> movies = null;
        if(filter.equals("ASC")){
            movies = movieRepository.findAllByReleaseYearASC();
        }
        else if(filter.equals("DESC")){
            movies = movieRepository.findAllByReleaseYearDESC();
        }
        return movies;
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public void saveAll(List<Movie> movies){
        movieRepository.saveAll(movies);
    }

    public void delete(Movie movie){
        movieRepository.delete(movie);
    }

    public void deleteAll() {
        movieRepository.deleteAll();
    }
}
