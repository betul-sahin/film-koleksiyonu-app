package com.betulsahin.filmkoleksiyonuapp.utils;

import com.betulsahin.filmkoleksiyonuapp.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryUtils {

    private static List<Category> categoryList = new ArrayList<>();

    public static List<Category> buildCategories(){
        if(categoryList.isEmpty()){
            Category categoryAile = new Category("Aile");
            categoryList.add(categoryAile);

            Category categoryDram = new Category("Dram");
            categoryList.add(categoryDram);

            Category categoryAksiyon = new Category("Aksiyon");
            categoryList.add(categoryAksiyon);

            Category categoryPolisiye = new Category("Polisiye");
            categoryList.add(categoryPolisiye);

            Category categoryKorku = new Category("Korku");
            categoryList.add(categoryKorku);

            Category categoryRomantik = new Category("Romantik");
            categoryList.add(categoryRomantik);

            Category categoryKomedi = new Category("Komedi");
            categoryList.add(categoryKomedi);
        }

        return categoryList;
    }
}
