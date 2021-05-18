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

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ActorRepository actorRepository;

    public Movie save(Movie movie){
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie;
    }

    public Optional<Movie> getById(long id){
        return movieRepository.findById(id);
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }
}
