package com.marvel.catalog.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.context.WebApplicationContext;

import com.marvel.catalog.model.Comic;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ComicRepository extends JpaRepository<Comic, Integer> {

    Set<Comic> findAllByTitle(String title);

    Comic findByTitle(String title);

    @Query("SELECT c FROM Comic c WHERE (:title is null OR c.title = :title)"
	    + " AND (:titleStartsWith is null OR c.title LIKE :titleStartsWith%)"
	    + " AND (:modifiedSince is null OR c.modified >= :modifiedSince)")
    Set<Comic> findAllByOptionalParameters(@Param("title") String title,
	    @Param("titleStartsWith") String titleStartsWith, @Param("modifiedSince") Date modifiedSince);
}
