package com.betulsahin.filmkoleksiyonuapp.controller;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.entity.ActorRole;
import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.service.ActorService;
import com.betulsahin.filmkoleksiyonuapp.service.CategoryService;
import com.betulsahin.filmkoleksiyonuapp.service.MovieService;
import com.betulsahin.filmkoleksiyonuapp.utils.ActorUtils;
import com.betulsahin.filmkoleksiyonuapp.utils.CategoryUtils;
import com.betulsahin.filmkoleksiyonuapp.utils.MovieUtils;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void init(){
        categoryService.deleteAll();;
        movieService.deleteAll();
        actorService.deleteAll();

        List<Movie> movies = MovieUtils.buildMovies();
        movieService.saveAll(movies);

        List<Actor> actors = ActorUtils.buildActors();
        actors.get(0).setMovie(movies.get(1));
        actors.get(1).setMovie(movies.get(1));
        actors.get(2).setMovie(movies.get(1));
        actorService.saveAll(actors);

        List<Category> categories = CategoryUtils.buildCategories();
        categories.get(1).setMovie(movies.get(0));
        categories.get(6).setMovie(movies.get(0));
        categories.get(3).setMovie(movies.get(1));
        categories.get(2).setMovie(movies.get(1));
        categoryService.saveAll(categories);
    }

    @GetMapping
    public String home(Model model){
        List<Movie> movies = movieService.getAll();
        List<Category> categories = categoryService.getAll();

        model.addAttribute("movies", movies);
        model.addAttribute("categories", categories);
        return "index";
    }

    @PostMapping("/movie/filter")
    public String filterMovie(@RequestParam String filter, Model model){
        List<Movie> movies = movieService.getAllByFilter(filter);
        List<Category> categories = categoryService.getAll();

        model.addAttribute("movies", movies);
        model.addAttribute("categories", categories);
        return "index";
    }

    @PostMapping("/movie/name/search")
    public String searchMovie(@RequestParam String keyword, Model model){
        List<Movie> movies = movieService.getAllByKeyword(keyword);
        List<Category> categories = categoryService.getAll();

        model.addAttribute("movies", movies);
        model.addAttribute("categories", categories);
        return "index";
    }

    @PostMapping("/movie/actor/search")
    public String searchActor(@RequestParam String keyword, Model model){
        Actor searchedActor = actorService.getByKeyword(keyword);

        Movie searchedMovie = searchedActor.getMovie();
        List<Movie> movies = Arrays.asList(searchedMovie);

        List<Category> categories = categoryService.getAll();

        model.addAttribute("movies", movies);
        model.addAttribute("categories", categories);
        return "index";
    }

    @PostMapping("/movie/category/search")
    public String searchCategory(@RequestParam String keyword, Model model){
        Category searchedCategory = categoryService.getByKeyword(keyword);

        Movie searchedMovies = searchedCategory.getMovie();
        List<Movie> movies = Arrays.asList(searchedMovies);

        List<Category> categories = categoryService.getAll();

        model.addAttribute("movies", movies);
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/movies")
    public String showMovieList(Model model){
        List<Movie> movies = movieService.getAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movie/add")
    public String showAddMovieForm(ModelMap map){
        Movie movie = new Movie();
        List<Category> categories = categoryService.getAll();

        map.addAttribute("movie", movie);
        map.addAttribute("categories", categories);
        return "add-movie";
    }

    @PostMapping("/movie/save")
    public String saveMovieForm(@Valid Movie movie, BindingResult result) {
        if(result.hasErrors()){
            return "add-movie";
        }

        movieService.save(movie);
        return "redirect:/";
    }

    @GetMapping("/movie/edit/{id}")
    public String showUpdateMovieForm(@PathVariable long id, ModelMap map){
        Movie movie = movieService.getById(id);
        List<Category> categories = categoryService.getAll();

        map.addAttribute("movie", movie);
        map.addAttribute("categories", categories);
        return "update-movie";
    }

    @PostMapping("/movie/update/{id}")
    public String updateMovie(@PathVariable long id, @Valid Movie movie, BindingResult result){
        if(result.hasErrors()){
            movie.setId(id);
            return "update-movie";
        }

        movieService.save(movie);
        return "redirect:/";
    }

    @GetMapping("/movie/delete/{id}")
    public String deleteMovie(@PathVariable long id){
        Movie movie = movieService.getById(id);
        movieService.delete(movie);

        return "redirect:/";
    }

    @GetMapping("/actors")
    public String showActors(Model model){
        List<Actor> actors = actorService.getAll();
        model.addAttribute("actors", actors);

        return "actors";
    }

    @GetMapping("/actor/add")
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

    @PostMapping("/actor/save")
    public String saveActor(@Valid Actor actor, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-actor";
        }

        actorService.save(actor);
        List<Actor> actors = actorService.getAll();
        model.addAttribute("actors", actors);
        return "actors";
    }

    @GetMapping("/actor/edit/{id}")
    public String showUpdateActorForm(@PathVariable long id, ModelMap map){
        Actor actor = actorService.getById(id);

        List<ActorRole> actorRoles = new ArrayList<>();
        actorRoles.add(ActorRole.BASROL);
        actorRoles.add(ActorRole.YARDIMCIOYUNCU);
        actorRoles.add(ActorRole.KONUKOYUNCU);

        List<Movie> movies = movieService.getAll();

        map.addAttribute("actor", actor);
        map.addAttribute("actorRoles", actorRoles);
        map.addAttribute("movies", movies);
        return "update-actor";
    }

    @PostMapping("/actor/update/{id}")
    public String updateActor(@PathVariable long id, @Valid Actor actor, BindingResult result){
        if(result.hasErrors()){
            actor.setId(id);
            return "update-actor";
        }

        actorService.save(actor);
        return "redirect:/actors";
    }

    @GetMapping("/actor/delete/{id}")
    public String deleteActor(@PathVariable long id){
        Actor actor = actorService.getById(id);
        actorService.delete(actor);

        return "redirect:/actors";
    }
}
