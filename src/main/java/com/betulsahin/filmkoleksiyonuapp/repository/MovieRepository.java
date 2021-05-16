package com.betulsahin.filmkoleksiyonuapp.repository;

import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
