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
public class Actor {
    @Id
    @SequenceGenerator(name="sequence_actor", allocationSize = 1)
    @GeneratedValue(generator = "sequence_actor", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    private String role;

    @ManyToMany(mappedBy = "movieCast")
    Set<Movie> movies = new HashSet<>();

    public Actor(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }
}
