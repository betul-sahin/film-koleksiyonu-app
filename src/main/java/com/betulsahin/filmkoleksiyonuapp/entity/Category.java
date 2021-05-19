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
public class Category {
    @Id
    @SequenceGenerator(name="sequence_category", allocationSize = 1)
    @GeneratedValue(generator = "sequence_category", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToMany(mappedBy = "movieCategories", cascade = CascadeType.ALL)
    Set<Movie> movies = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }
}
