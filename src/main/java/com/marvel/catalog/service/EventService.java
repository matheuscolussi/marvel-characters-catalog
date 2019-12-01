package com.marvel.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marvel.catalog.model.Event;
import com.marvel.catalog.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> findAll() {
	return eventRepository.findAll();
    }

    public Optional<Event> findById(int id) {
	return eventRepository.findById(id);
    }

    public Set<Event> findAllByTitle(String title) {
	return eventRepository.findAllByTitle(title);
    }

    public Event findByTitle(String title) {
	return eventRepository.findByTitle(title);
    }
}
