package com.betulsahin.filmkoleksiyonuapp.repository;

import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
