package com.marvel.catalog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.catalog.model.Character;
import com.marvel.catalog.model.Comic;
import com.marvel.catalog.model.Event;
import com.marvel.catalog.model.GenericDataContainer;
import com.marvel.catalog.model.GenericWrapper;
import com.marvel.catalog.model.Serie;
import com.marvel.catalog.model.Story;
import com.marvel.catalog.repository.CharacterRepository;
import com.marvel.catalog.repository.ComicRepository;
import com.marvel.catalog.repository.EventRepository;
import com.marvel.catalog.repository.SerieRepository;
import com.marvel.catalog.repository.StoryRepository;
import com.marvel.catalog.utils.Utils;

@RestController
@RequestMapping("/v1/public")
public class AppController {

	@Autowired
	CharacterRepository characterRepository;

	@Autowired
	ComicRepository comicRepository;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	SerieRepository serieRepository;

	@Autowired
	StoryRepository storyRepository;

	@GetMapping("/")
	public String getDefaultURI() {
		return "Server is up!!";
	}

	@GetMapping(path = "/characters")
	public ResponseEntity<Model> getCharacters(Model model,
			@RequestParam(required = false, value = "name") Optional<String> name,
			@RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "comics") Optional<Integer> comics,
			@RequestParam(required = false, value = "series") Optional<Integer> series,
			@RequestParam(required = false, value = "events") Optional<Integer> events,
			@RequestParam(required = false, value = "stories") Optional<Integer> stories,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {

		List<Character> listOfCharacters = characterRepository.findAll();

		if (null != listOfCharacters && listOfCharacters.size() > 0) {
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Character> data = Utils.createGenericDataContainer(listOfCharacters);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/characters/{characterId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getCharactersById(Model model, @PathVariable int characterId) {
		Optional<Character> character = characterRepository.findById(characterId);
		if (character.isPresent()) {
			List<Character> listOfCharacters = new ArrayList<Character>();
			listOfCharacters.add(character.get());
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Character> data = Utils.createGenericDataContainer(listOfCharacters);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);

		} else {
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/characters/{characterId}/comics", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getComicsByCharacterId(Model model, @PathVariable int characterId,
			@RequestParam(required = false, value = "format") Optional<String> format,
			@RequestParam(required = false, value = "formatType") Optional<String> formatType,
			@RequestParam(required = false, value = "noVariants") Optional<Boolean> noVariants,
			@RequestParam(required = false, value = "dateDescriptor") Optional<String> dateDescriptor,
			@RequestParam(required = false, value = "dateRange") Optional<Integer> dateRange,
			@RequestParam(required = false, value = "title") Optional<String> title,
			@RequestParam(required = false, value = "titleStartsWith") Optional<String> titleStartsWith,
			@RequestParam(required = false, value = "startYear") Optional<Integer> startYear,
			@RequestParam(required = false, value = "issueNumber") Optional<Integer> issueNumber,
			@RequestParam(required = false, value = "diamondCode") Optional<String> diamondCode,
			@RequestParam(required = false, value = "digitalId") Optional<Integer> digitalId,
			@RequestParam(required = false, value = "upc") Optional<String> upc,
			@RequestParam(required = false, value = "isbn") Optional<String> isbn,
			@RequestParam(required = false, value = "ean") Optional<String> ean,
			@RequestParam(required = false, value = "issn") Optional<String> issn,
			@RequestParam(required = false, value = "hasDigitalIssue") Optional<Boolean> hasDigitalIssue,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "creators") Optional<Integer> creators,
			@RequestParam(required = false, value = "series") Optional<Integer> series,
			@RequestParam(required = false, value = "events") Optional<Integer> events,
			@RequestParam(required = false, value = "stories") Optional<Integer> stories,
			@RequestParam(required = false, value = "sharedAppearances") Optional<Integer> sharedAppearances,
			@RequestParam(required = false, value = "collaborators") Optional<Integer> collaborators,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<String> offset) {

		Optional<Character> character = characterRepository.findById(characterId);
		if (character.isPresent()) {
			List<Comic> listOfComics = character.get().getComics();
			GenericWrapper<Comic> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Comic> data = Utils.createGenericDataContainer(listOfComics);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Comic> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/characters/{characterId}/events", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getEventsByCharacterId(Model model, @PathVariable int characterId,
			@RequestParam(required = false, value = "name") Optional<String> name,
			@RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "creators") Optional<Integer> creators,
			@RequestParam(required = false, value = "series") Optional<Integer> series,
			@RequestParam(required = false, value = "comics") Optional<Integer> comics,
			@RequestParam(required = false, value = "stories") Optional<Integer> stories,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {
		Optional<Character> character = characterRepository.findById(characterId);
		if (character.isPresent()) {
			List<Event> listOfEvents = character.get().getEvents();
			GenericWrapper<Event> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Event> data = Utils.createGenericDataContainer(listOfEvents);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Event> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/characters/{characterId}/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getSeriesByCharacterId(Model model, @PathVariable int characterId,
			@RequestParam(required = false, value = "title") Optional<String> title,
			@RequestParam(required = false, value = "titleStartsWith") Optional<String> titleStartsWith,
			@RequestParam(required = false, value = "startYear") Optional<Integer> startYear,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "comics") Optional<Integer> comics,
			@RequestParam(required = false, value = "stories") Optional<Integer> stories,
			@RequestParam(required = false, value = "events") Optional<Integer> events,
			@RequestParam(required = false, value = "creators") Optional<Integer> creators,
			@RequestParam(required = false, value = "seriesType") Optional<String> seriesType,
			@RequestParam(required = false, value = "contains") Optional<String> contains,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {
		Optional<Character> character = characterRepository.findById(characterId);
		if (character.isPresent()) {
			List<Serie> listOfSeries = character.get().getSeries();
			GenericWrapper<Serie> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Serie> data = Utils.createGenericDataContainer(listOfSeries);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Serie> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/characters/{characterId}/stories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getStoriesByCharacterId(Model model, @PathVariable int characterId,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "comics") Optional<Integer> comics,
			@RequestParam(required = false, value = "series") Optional<Integer> series,
			@RequestParam(required = false, value = "events") Optional<Integer> events,
			@RequestParam(required = false, value = "creators") Optional<Integer> creators,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {
		Optional<Character> character = characterRepository.findById(characterId);
		if (character.isPresent()) {
			List<Story> listOfStories = character.get().getStories();
			GenericWrapper<Story> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Story> data = Utils.createGenericDataContainer(listOfStories);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Story> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/comics/{comicId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getCharactersByComicId(Model model, @PathVariable int comicId,
			@RequestParam(required = false, value = "name") Optional<String> name,
			@RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "series") Optional<Integer> series,
			@RequestParam(required = false, value = "events") Optional<Integer> events,
			@RequestParam(required = false, value = "stories") Optional<Integer> stories,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {
		Optional<Comic> comic = comicRepository.findById(comicId);
		if (comic.isPresent()) {
			List<Character> listOfCharacters = comic.get().getCharacters();
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Character> data = Utils.createGenericDataContainer(listOfCharacters);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/events/{eventId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getCharactersByEventId(Model model, @PathVariable int eventId,
			@RequestParam(required = false, value = "name") Optional<String> name,
			@RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "comics") Optional<Integer> comics,
			@RequestParam(required = false, value = "series") Optional<Integer> series,
			@RequestParam(required = false, value = "stories") Optional<Integer> stories,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {
		Optional<Event> event = eventRepository.findById(eventId);
		if (event.isPresent()) {
			List<Character> listOfCharacters = event.get().getCharacters();
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Character> data = Utils.createGenericDataContainer(listOfCharacters);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/series/{serieId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getCharactersBySerieId(Model model, @PathVariable int serieId,
			@RequestParam(required = false, value = "name") Optional<String> name,
			@RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "comics") Optional<Integer> comics,
			@RequestParam(required = false, value = "events") Optional<Integer> events,
			@RequestParam(required = false, value = "stories") Optional<Integer> stories,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {
		Optional<Serie> serie = serieRepository.findById(serieId);
		if (serie.isPresent()) {
			List<Character> listOfCharacters = serie.get().getCharacters();
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Character> data = Utils.createGenericDataContainer(listOfCharacters);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/stories/{storyId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> getCharactersByStoryId(Model model, @PathVariable int storyId,
			@RequestParam(required = false, value = "name") Optional<String> name,
			@RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
			@RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
			@RequestParam(required = false, value = "comics") Optional<Integer> comics,
			@RequestParam(required = false, value = "series") Optional<Integer> series,
			@RequestParam(required = false, value = "events") Optional<Integer> events,
			@RequestParam(required = false, value = "limit") Optional<Integer> limit,
			@RequestParam(required = false, value = "offset") Optional<Integer> offset) {
		Optional<Story> story = storyRepository.findById(storyId);
		if (story.isPresent()) {
			List<Character> listOfCharacters = story.get().getCharacters();
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
			GenericDataContainer<Character> data = Utils.createGenericDataContainer(listOfCharacters);
			response.setData(data);
			model.addAttribute(response);
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		} else {
			GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
			model.addAttribute(response);
			return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
	}

}