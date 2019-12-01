package com.marvel.catalog.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marvel.catalog.model.Character;
import com.marvel.catalog.repository.CharacterRepository;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    public List<Character> findAll() {
	return characterRepository.findAll();
    }

    public Optional<Character> findById(int id) {
	return characterRepository.findById(id);
    }

    public Set<Character> findAllByName(String name) {
	return characterRepository.findAllByName(name);
    }

    public Character findByName(String name) {
	return characterRepository.findByName(name);
    }

    public Set<Character> findAllByOptionalParameters(String name, String nameStartsWith, Date modifiedSince) {
	return characterRepository.findAllByOptionalParameters(name, nameStartsWith, modifiedSince);
    }
}
