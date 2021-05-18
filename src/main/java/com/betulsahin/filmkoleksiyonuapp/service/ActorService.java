package com.betulsahin.filmkoleksiyonuapp.service;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public Actor save(Actor actor){
        Actor savedActor = actorRepository.save(actor);
        return savedActor;
    }

    public Optional<Actor> getById(long id){
        return actorRepository.findById(id);
    }

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    public void delete(Actor actor) {
        actorRepository.delete(actor);
    }
}
