package com.marvel.catalog.repository;

import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.WebApplicationContext;

import com.marvel.catalog.model.Image;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByPath(String path);

    Set<Image> findAllByPath(String path);

    Image findByExtension(String extension);

    Set<Image> findAllByExtension(String extension);

}
