package com.betulsahin.filmkoleksiyonuapp.service;

import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import com.betulsahin.filmkoleksiyonuapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
