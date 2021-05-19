package com.betulsahin.filmkoleksiyonuapp.repository;

import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    //Searching Case Insensitive
    @Query("SELECT m FROM Movie m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Movie> findAllByKeyword(String keyword);

    @Query("SELECT m FROM Movie m ORDER BY m.releaseYear ASC")
    List<Movie> findAllByReleaseYearASC();

    @Query("SELECT m FROM Movie m ORDER BY m.releaseYear DESC")
    List<Movie> findAllByReleaseYearDESC();
}