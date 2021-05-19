package com.betulsahin.filmkoleksiyonuapp.utils;

import com.betulsahin.filmkoleksiyonuapp.entity.Actor;
import com.betulsahin.filmkoleksiyonuapp.entity.ActorRole;

import java.util.ArrayList;
import java.util.List;

public class ActorUtils {
    private static List<Actor> actorList = new ArrayList<>();

    public static List<Actor> buildActors(){
        if(actorList.isEmpty()){
            Actor actor1 = new Actor("Russell Crowe", ActorRole.BASROL.name());
            actorList.add(actor1);

            Actor actor2 = new Actor("Joaquin Phoenix", ActorRole.YARDIMCIOYUNCU.name());
            actorList.add(actor2);

            Actor actor3 = new Actor("Connie Nielsen", ActorRole.KONUKOYUNCU.name());
            actorList.add(actor3);
        }

        return actorList;
    }
}
