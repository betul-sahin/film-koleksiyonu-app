package com.betulsahin.filmkoleksiyonuapp.controller;

import com.betulsahin.filmkoleksiyonuapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    public String showMovieList(Model model){
        model.addAttribute("movies", movieService.getAll());
        return "index";
    }

}
