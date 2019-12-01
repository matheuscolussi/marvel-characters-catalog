package com.marvel.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marvel.catalog.model.Serie;
import com.marvel.catalog.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    SerieRepository serieRepository;

    public List<Serie> findAll() {
	return serieRepository.findAll();
    }

    public Optional<Serie> findById(int id) {
	return serieRepository.findById(id);
    }

    public Set<Serie> findAllByTitle(String title) {
	return serieRepository.findAllByTitle(title);
    }

    public Serie findByTitle(String title) {
	return serieRepository.findByTitle(title);
    }
}
