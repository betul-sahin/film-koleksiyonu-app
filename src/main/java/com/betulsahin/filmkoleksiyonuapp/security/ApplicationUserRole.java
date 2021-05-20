package com.betulsahin.filmkoleksiyonuapp.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.betulsahin.filmkoleksiyonuapp.security.ApplicationUserPermission.*;

@AllArgsConstructor
@Getter
public enum ApplicationUserRole {
    USER(Sets.newHashSet(MOVIE_CREATE, MOVIE_UPDATE, ACTOR_CREATE, ACTOR_UPDATE)),
    ADMIN(Sets.newHashSet(MOVIE_CREATE, MOVIE_UPDATE, MOVIE_DELETE, ACTOR_CREATE, ACTOR_UPDATE, ACTOR_DELETE));

    private final Set<ApplicationUserPermission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
