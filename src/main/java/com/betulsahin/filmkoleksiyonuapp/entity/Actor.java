package com.betulsahin.filmkoleksiyonuapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Actor {
    @Id
    @SequenceGenerator(name="sequence_actor", allocationSize = 1)
    @GeneratedValue(generator = "sequence_actor", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    private String role;

    @ManyToMany(mappedBy = "movieCast", cascade = CascadeType.REMOVE)
    Set<Movie> movies = new HashSet<>();
}