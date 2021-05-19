package com.betulsahin.filmkoleksiyonuapp.controller;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.entity.ActorRole;
import com.betulsahin.filmkoleksiyonuapp.entity.Category;
import com.betulsahin.filmkoleksiyonuapp.entity.Movie;
import com.betulsahin.filmkoleksiyonuapp.service.ActorService;
import com.betulsahin.filmkoleksiyonuapp.service.CategoryService;
import com.betulsahin.filmkoleksiyonuapp.service.MovieService;
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

    private static final String INVALID_ID = "Invalid id: %s";

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

        Actor actor1 = new Actor("Russell Crowe", ActorRole.BASROL.name());
        actor1.setMovie(movie2);
        actorService.save(actor1);

        Actor actor2 = new Actor("Joaquin Phoenix", ActorRole.YARDIMCIOYUNCU.name());
        actor2.setMovie(movie2);
        actorService.save(actor2);

        Actor actor3 = new Actor("Connie Nielsen", ActorRole.KONUKOYUNCU.name());
        actor3.setMovie(movie2);
        actorService.save(actor3);
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
        Actor searchedActor = actorService
                .getByKeyword(keyword)
                .get();
        Movie searchedMovie = searchedActor.getMovie();
        List<Movie> movies = Arrays.asList(searchedMovie);

        List<Category> categories = categoryService.getAll();

        model.addAttribute("movies", movies);
        model.addAttribute("categories", categories);
        return "index";
    }

    @PostMapping("/movie/category/search")
    public String searchCategory(@RequestParam String keyword, Model model){
        Category searchedCategory = categoryService
                .getByKeyword(keyword)
                .get();
        Set<Movie> searchedMovies = searchedCategory.getMovies();
        List<Movie> movies = searchedMovies.stream().collect(Collectors.toList());

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
