package com.betulsahin.filmkoleksiyonuapp.service;

import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private static final String NO_FOUND = "No Found row with this keyword: %s";

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getById(long id){
        return categoryRepository.findById(id);
    }

    public Category getByKeyword(String keyword){
        return categoryRepository.findByKeyword(keyword).orElseThrow(
                ()->new IllegalArgumentException(String.format(NO_FOUND, keyword)));
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void saveAll(List<Category> categories){
        categoryRepository.saveAll(categories);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
