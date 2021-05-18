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
    private long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    private String role;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable = false)
    public Movie movie;

    public Actor(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }
}
