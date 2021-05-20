package com.betulsahin.filmkoleksiyonuapp.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationUserPermission {
    MOVIE_CREATE("movie:create"),
    MOVIE_UPDATE("movie:update"),
    MOVIE_DELETE("movie:delete"),
    ACTOR_CREATE("actor:create"),
    ACTOR_UPDATE("actor:update"),
    ACTOR_DELETE("actor:delete");

    private final String permission;
}
