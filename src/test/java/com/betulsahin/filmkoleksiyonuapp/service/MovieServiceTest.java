package com.betulsahin.filmkoleksiyonuapp.service;

import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService underTest;

    @Mock
    private MovieRepository movieRepository;

    @Test
    void itShouldReturnMovieIfFound_givenId() {
        //given
        Movie movie = new Movie();
        movie.setId(1);

        //when
        when(movieRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(movie));

        //then
        Movie result = underTest.getById(1);
        assertEquals(result.getId(), movie.getId());

        verify(movieRepository).findById(movie.getId());
    }

    @Test
    public void itShouldThrowExceptionIfMovieNoFound_givenId() {
        //given
        //when
        //then
        assertThatThrownBy(() -> underTest.getById(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid id: 1");
    }

    @Test
    void itShouldReturnAllMovie() {
        //given
        Movie movie = new Movie(
                "Hababam Sınıfı",
                "Hababam Sınıfı özeti",
                1900,
                "mp4",
                "TR");
        List<Movie> movies = Arrays.asList(movie);

        //when
        when(movieRepository.findAll())
                .thenReturn(movies);

        //then
        List<Movie> results = underTest.getAll();

        assertEquals(results.size(), 1);

        verify(movieRepository).findAll();
    }

    @Test
    public void itShouldReturnMovie_whenSaveMovie() {
        //given
        Movie movie = new Movie(
                "Hababam Sınıfı",
                "Hababam Sınıfı özeti",
                1900,
                "mp4",
                "TR");

        //when
        when(movieRepository.save(ArgumentMatchers.any(Movie.class)))
                .thenReturn(mock(Movie.class));

        //then
        Movie result = underTest.save(movie);
        assertEquals(result.getId(), movie.getId());

        verify(movieRepository).save(movie);
    }

    @Test
    public void itShouldSaveAllMovie() {
        //given
        Movie movie = new Movie(
                "Hababam Sınıfı",
                "Hababam Sınıfı özeti",
                1900,
                "mp4",
                "TR");
        List<Movie> movies = Arrays.asList(movie);

        //when
        when(movieRepository.saveAll(ArgumentMatchers.any(List.class)))
                .thenReturn(mock(List.class));

        //then
        underTest.saveAll(movies);

        verify(movieRepository).saveAll(movies);
    }
}