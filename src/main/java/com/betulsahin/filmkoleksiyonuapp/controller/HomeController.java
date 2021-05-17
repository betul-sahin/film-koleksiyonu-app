package com.betulsahin.filmkoleksiyonuapp.controller;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String showMovieList(Model model){
        model.addAttribute("movies", movieService.getAll());
        return "index";
    }

    @GetMapping("/addMovie")
    public String showAddMovieForm(ModelMap map){
        Movie movie = new Movie();
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        List<Actor> actors = new ArrayList<>();
        actors.add(new Actor());

        map.addAttribute("movie", movie);
        map.addAttribute("actors", actors);
        map.addAttribute("categories", categories);
        return "add-movie";
    }
}
