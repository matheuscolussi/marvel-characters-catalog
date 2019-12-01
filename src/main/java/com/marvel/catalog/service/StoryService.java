package com.marvel.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marvel.catalog.model.Story;
import com.marvel.catalog.repository.StoryRepository;

@Service
public class StoryService {

    @Autowired
    StoryRepository storyRepository;

    public List<Story> findAll() {
	return storyRepository.findAll();
    }

    public Optional<Story> findById(int id) {
	return storyRepository.findById(id);
    }

    public Set<Story> findAllByTitle(String title) {
	return storyRepository.findAllByTitle(title);
    }

    public Story findByTitle(String title) {
	return storyRepository.findByTitle(title);
    }
}
