package com.marvel.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marvel.catalog.repository.ComicRepository;

public class ComicService {

	@Autowired
	ComicRepository comicRepository;

	public List<com.marvel.catalog.model.Comic> findAllByTitle(String title) {
		return comicRepository.findAllByTitle(title);
	}

	public com.marvel.catalog.model.Comic findByTitle(String title) {
		return comicRepository.findByTitle(title);
	}
}
