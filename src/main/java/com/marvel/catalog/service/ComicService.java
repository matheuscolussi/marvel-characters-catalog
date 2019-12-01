package com.marvel.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marvel.catalog.model.Comic;
import com.marvel.catalog.repository.ComicRepository;

@Service
public class ComicService {

    @Autowired
    ComicRepository comicRepository;
    
    public List<Comic> findAll() {
	return comicRepository.findAll();
    }

    public Optional<Comic> findById(int id) {
	return comicRepository.findById(id);
    }

    public Set<Comic> findAllByTitle(String title) {
	return comicRepository.findAllByTitle(title);
    }

    public Comic findByTitle(String title) {
	return comicRepository.findByTitle(title);
    }
}
