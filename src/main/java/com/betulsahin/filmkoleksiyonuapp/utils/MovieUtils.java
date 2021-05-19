package com.betulsahin.filmkoleksiyonuapp.utils;

import com.betulsahin.filmkoleksiyonuapp.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieUtils {
    private static List<Movie> movieList = new ArrayList<>();

    public static List<Movie> buildMovies(){
        if(movieList.isEmpty()){
            Movie movie1 = new Movie("Hababam Sınıfı","Hababam Sınıfı özeti",1900, "mp4", "TR");
            movieList.add(movie1);
            Movie movie2 = new Movie("Gladyatör","Gladyatör özeti",2000, "mp4", "EN");
            movieList.add(movie2);
        }

        return movieList;
    }
}
