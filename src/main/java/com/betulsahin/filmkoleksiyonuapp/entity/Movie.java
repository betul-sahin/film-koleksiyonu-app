package com.betulsahin.filmkoleksiyonuapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @SequenceGenerator(name="sequence_movie", allocationSize = 1)
    @GeneratedValue(generator="sequence_movie", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name="release_year")
    private int releaseYear;

    private String media;

    private String language;

    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    public Set<Category> movieCategories;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    public Set<Actor> movieCast;

    public Movie(String name, String description, int releaseYear, String media, String language, Category... category) {
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
        this.media = media;
        this.language = language;
        this.movieCategories = Stream.of(category).collect(Collectors.toSet());
        this.movieCategories.forEach(x -> x.getMovies().add(this));
    }

}
