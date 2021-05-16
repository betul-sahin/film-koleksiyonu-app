package com.betulsahin.filmkoleksiyonuapp.repository;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
