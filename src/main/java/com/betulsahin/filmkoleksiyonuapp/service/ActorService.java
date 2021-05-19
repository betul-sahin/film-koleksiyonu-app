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

    public Optional<Actor> getById(long id){
        return actorRepository.findById(id);
    }

    public Optional<Actor> getByKeyword(String keyword){
        return actorRepository.findByKeyword(keyword);
    }

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    public Actor save(Actor actor){
        Actor savedActor = actorRepository.save(actor);
        return savedActor;
    }

    public void saveAll(List<Actor> actors){
        actorRepository.saveAll(actors);
    }

    public void delete(Actor actor) {
        actorRepository.delete(actor);
    }

    public void deleteAll(){
        actorRepository.deleteAll();
    }
}
