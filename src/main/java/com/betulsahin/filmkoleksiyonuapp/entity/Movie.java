package com.betulsahin.filmkoleksiyonuapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @SequenceGenerator(name="sequence_movie", allocationSize = 1)
    @GeneratedValue(generator="sequence_movie", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name="release_year")
    private int releaseYear;

    private String media;

    private String language;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> movieCategories = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id"))
    Set<Actor> movieCast = new HashSet<>();

    public Movie(String name, String description, int releaseYear, String media, String language) {
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
        this.media = media;
        this.language = language;
    }
}
