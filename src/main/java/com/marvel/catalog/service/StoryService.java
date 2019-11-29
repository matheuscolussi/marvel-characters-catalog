package com.marvel.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marvel.catalog.repository.StoryRepository;

public class StoryService {

	@Autowired
	StoryRepository storyRepository;

	public List<com.marvel.catalog.model.Story> findAllByTitle(String title) {
		return storyRepository.findAllByTitle(title);
	}

	public com.marvel.catalog.model.Story findByTitle(String title) {
		return storyRepository.findByTitle(title);
	}
}
