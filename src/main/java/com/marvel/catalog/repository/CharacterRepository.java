package com.marvel.catalog.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.context.WebApplicationContext;

import com.marvel.catalog.model.Character;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    Character findByName(String name);

    Set<Character> findAllByName(String name);

    @Query("SELECT c FROM Character c WHERE (:name is null OR c.name = :name)"
	    + " AND (:nameStartsWith is null OR c.name LIKE :nameStartsWith%)"
	    + " AND (:modifiedSince is null OR c.modified >= :modifiedSince)")
    Set<Character> findAllByOptionalParameters(@Param("name") String name,
	    @Param("nameStartsWith") String nameStartsWith, @Param("modifiedSince") Date modifiedSince);
}
