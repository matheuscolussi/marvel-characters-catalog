package com.marvel.catalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/public")
public class MainController {
	
	@GetMapping(value = "/")
	public String getDefaultResponse() {
		return "Environment is UP!";
	}

	@GetMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCharacters() {
		return new ResponseEntity<>("Working as designed!", HttpStatus.OK);
	}

	@GetMapping(value = "/characters/{characterId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCharactersById(@PathVariable int characterId) {
		return new ResponseEntity<>("Working as designed! Character ID: " + characterId, HttpStatus.OK);
	}
}
