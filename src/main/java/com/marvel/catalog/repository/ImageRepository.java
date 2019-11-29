package com.marvel.catalog.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ImageRepository extends JpaRepository<com.marvel.catalog.model.Image, Integer> {

	com.marvel.catalog.model.Image findByPath(String path);

	List<com.marvel.catalog.model.Image> findAllByPath(String path);

	com.marvel.catalog.model.Image findByExtension(String extension);

	List<com.marvel.catalog.model.Image> findAllByExtension(String extension);

}
