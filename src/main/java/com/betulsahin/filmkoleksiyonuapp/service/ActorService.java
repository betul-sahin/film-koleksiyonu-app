package com.betulsahin.filmkoleksiyonuapp.service;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActorService {
    private static final String INVALID_ID = "Invalid id: %s";
    private static final String NO_FOUND = "No Found row with this keyword: %s";

    private ActorRepository actorRepository;

    public Actor getById(long id){
        return actorRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException(String.format(INVALID_ID, id)));
    }

    public Actor getByKeyword(String keyword){
        return actorRepository.findByKeyword(keyword).orElseThrow(
                ()->new IllegalArgumentException(String.format(NO_FOUND, keyword)));
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
