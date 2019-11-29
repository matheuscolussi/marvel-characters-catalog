package com.marvel.catalog.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface CharacterRepository extends JpaRepository<com.marvel.catalog.model.Character, Integer> {

	com.marvel.catalog.model.Character findByName(String name);

	List<com.marvel.catalog.model.Character> findAllByName(String name);
}
