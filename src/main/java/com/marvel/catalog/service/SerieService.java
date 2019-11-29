package com.marvel.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marvel.catalog.repository.SerieRepository;

public class SerieService {

	@Autowired
	SerieRepository serieRepository;

	public List<com.marvel.catalog.model.Serie> findAllByTitle(String title) {
		return serieRepository.findAllByTitle(title);
	}

	public com.marvel.catalog.model.Serie findByTitle(String title) {
		return serieRepository.findByTitle(title);
	}
}
