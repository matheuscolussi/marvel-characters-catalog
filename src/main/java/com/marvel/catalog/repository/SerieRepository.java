package com.marvel.catalog.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface SerieRepository extends JpaRepository<com.marvel.catalog.model.Serie, Integer> {

	List<com.marvel.catalog.model.Serie> findAllByTitle(String title);

	com.marvel.catalog.model.Serie findByTitle(String title);
}
