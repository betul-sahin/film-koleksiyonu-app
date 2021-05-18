package com.betulsahin.filmkoleksiyonuapp.controller;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.entity.ActorRole;
import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.service.ActorService;
import com.betulsahin.filmkoleksiyonuapp.service.CategoryService;
import com.betulsahin.filmkoleksiyonuapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private static final String INVALID_ID = "Invalid id: %s";

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void init(){
        Category categoryAile = new Category("Aile");
        categoryService.save(categoryAile);

        Category categoryDram = new Category("Dram");
        categoryDram = categoryService.save(categoryDram);

        Category categoryAksiyon = new Category("Aksiyon");
        categoryAksiyon = categoryService.save(categoryAksiyon);

        Category categoryPolisiye = new Category("Polisiye");
        categoryPolisiye = categoryService.save(categoryPolisiye);

        Category categoryKorku = new Category("Korku");
        categoryService.save(categoryKorku);

        Category categoryRomantik = new Category("Romantik");
        categoryService.save(categoryRomantik);

        Category categoryKomedi = new Category("Komedi");
        categoryKomedi = categoryService.save(categoryKomedi);

        Movie movie1 = new Movie("Hababam Sınıfı","Hababam Sınıfı özeti",1900, "mp4", "TR", categoryDram, categoryKomedi);
        movieService.save(movie1);
        Movie movie2 = new Movie("Gladyatör","Gladyatör özeti",2000, "mp4", "EN", categoryPolisiye, categoryAksiyon);
        movieService.save(movie2);
    }

    @GetMapping
    public String home(ModelMap map){
        List<Movie> movies = movieService.getAll();
        map.addAttribute("movies", movies);

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
        List<Actor> actors = actorService.getAll();
        List<Category> categories = categoryService.getAll();

        map.addAttribute("movie", movie);
        map.addAttribute("actors", actors);
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
    public String showUpdateForm(@PathVariable long id, Model model){
        Movie movie = movieService.getById(id).orElseThrow(
                ()->new IllegalArgumentException(String.format(INVALID_ID, id)));
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

    @GetMapping("/movie/delete/{id}")
    public String deleteMovie(@PathVariable long id){
        Movie movie = movieService.getById(id).orElseThrow(
                ()->new IllegalArgumentException(String.format(INVALID_ID, id)));
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
        Actor actor = actorService.getById(id).orElseThrow(
                ()->new IllegalArgumentException(String.format(INVALID_ID, id)));

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
        Actor actor = actorService.getById(id)
                .orElseThrow(()->new IllegalArgumentException(String.format(INVALID_ID, id)));
        actorService.delete(actor);

        return "redirect:/actors";
    }
}
