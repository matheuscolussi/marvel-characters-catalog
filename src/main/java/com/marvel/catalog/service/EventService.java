package com.marvel.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marvel.catalog.repository.EventRepository;

public class EventService {

	@Autowired
	EventRepository eventRepository;

	public List<com.marvel.catalog.model.Event> findAllByTitle(String title) {
		return eventRepository.findAllByTitle(title);
	}

	public com.marvel.catalog.model.Event findByTitle(String title) {
		return eventRepository.findByTitle(title);
	}
}
