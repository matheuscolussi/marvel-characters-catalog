package com.marvel.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marvel.catalog.repository.CharacterRepository;

public class CharacterService {

	@Autowired
	CharacterRepository characterRepository;

	public List<com.marvel.catalog.model.Character> findAllByName(String name) {
		return characterRepository.findAllByName(name);
	}

	public com.marvel.catalog.model.Character findByName(String name) {
		return characterRepository.findByName(name);
	}
}
