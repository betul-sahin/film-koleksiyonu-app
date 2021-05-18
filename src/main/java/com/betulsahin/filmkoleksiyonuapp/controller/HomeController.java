package com.betulsahin.filmkoleksiyonuapp.controller;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.entity.ActorRole;
import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.service.ActorService;
import com.betulsahin.filmkoleksiyonuapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private static final String INVALID_MOVIE_ID = "Invalid id: %s";
    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

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
        List<Actor> actors = actorService.getAll();

        map.addAttribute("movie", movie);
        map.addAttribute("actors", actors);
        map.addAttribute("categories", categories);
        return "add-movie";
    }

    @PostMapping("/saveMovie")
    public String saveMovieForm(@Valid Movie movie, BindingResult result) {
        if(result.hasErrors()){
            return "add-movie";
        }

        movieService.save(movie);
        return "redirect:/";
    }

    @GetMapping("/movie/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model){
        Movie movie = movieService.getById(id).orElseThrow(
                ()->new IllegalArgumentException(String.format(INVALID_MOVIE_ID, id)));
        model.addAttribute("movie", movie);

        return "update-movie";
    }

    @PostMapping("/movie/update/{id}")
    public String updateMovie(@PathVariable long id, @Valid Movie movie,BindingResult result){
        if(result.hasErrors()){
            movie.setId(id);
            return "update-movie";
        }

        movieService.save(movie);
        return "redirect:/";
    }

    @GetMapping("/addActor")
    public String showActorForm(ModelMap map){
        Actor actor = new Actor();
        List<ActorRole> actorRoles = new ArrayList<>();
        actorRoles.add(ActorRole.BASROL);
        actorRoles.add(ActorRole.YARDIMCIOYUNCU);
        actorRoles.add(ActorRole.KONUKOYUNCU);

        List<Movie> movies = movieService.getAll();

        map.addAttribute("actor", actor);
        map.addAttribute("actorRoles", actorRoles);
        map.addAttribute("movies", movies);
        return "add-actor";
    }

    @PostMapping("/saveActor")
    public String saveActor(@Valid Actor actor, BindingResult result){
        if(result.hasErrors()){
            return "add-actor";
        }

        actorService.save(actor);
        return "redirect:/";
    }
}
