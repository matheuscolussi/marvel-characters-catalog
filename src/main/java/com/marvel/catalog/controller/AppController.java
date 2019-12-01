package com.marvel.catalog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.marvel.catalog.messages.Msg;
import com.marvel.catalog.model.Character;
import com.marvel.catalog.model.Comic;
import com.marvel.catalog.model.Event;
import com.marvel.catalog.model.GenericDataContainer;
import com.marvel.catalog.model.GenericWrapper;
import com.marvel.catalog.model.Serie;
import com.marvel.catalog.model.Story;
import com.marvel.catalog.service.CharacterService;
import com.marvel.catalog.service.ComicService;
import com.marvel.catalog.service.EventService;
import com.marvel.catalog.service.SerieService;
import com.marvel.catalog.service.StoryService;
import com.marvel.catalog.utils.Utils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/public")
public class AppController {

    @Autowired
    CharacterService characterService;

    @Autowired
    ComicService comicService;

    @Autowired
    EventService eventService;

    @Autowired
    SerieService serieService;

    @Autowired
    StoryService storyService;

    @GetMapping("/")
    public String getDefaultURI() {
	return "Server is up!!";
    }

    @ApiOperation(value = "List of characters", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of characters retrieved successfully!."),
	    @ApiResponse(code = 404, message = "Character(s) not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/characters")
    public ResponseEntity<Model> getCharacters(Model model,
	    @RequestParam(required = false, value = "name") Optional<String> name,
	    @RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {

	if (name.isPresent() || nameStartsWith.isPresent() || modifiedSince.isPresent() || limit.isPresent()) {
	    Set<Character> setOfCharacters = characterService.findAllByOptionalParameters(
		    (name.isPresent() ? name.get() : null), (nameStartsWith.isPresent() ? nameStartsWith.get() : null),
		    (modifiedSince.isPresent() ? modifiedSince.get() : null));

	    if (!setOfCharacters.isEmpty()) {

		if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		    model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		    model.addAttribute(response);
		    return new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}

		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
		GenericDataContainer<Character> data = null;
		if (limit.isPresent()) {
		    int toIndex = limit.get().intValue() >= setOfCharacters.size() ? setOfCharacters.size()
			    : limit.get().intValue();
		    List<Character> temporaryList = new ArrayList<Character>(setOfCharacters);
		    data = Utils.createGenericDataContainer(
			    new HashSet<Character>(new HashSet<Character>(temporaryList.subList(0, toIndex))));
		} else {
		    data = Utils.createGenericDataContainer(setOfCharacters);
		}
		response.setData(data);
		model.addAttribute(response);
		return new ResponseEntity<Model>(model, HttpStatus.OK);
	    } else {
		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	    }

	} else {
	    List<Character> listOfCharacters = characterService.findAll();

	    if (!listOfCharacters.isEmpty()) {
		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
		GenericDataContainer<Character> data = Utils
			.createGenericDataContainer(listOfCharacters.stream().collect(Collectors.toSet()));
		response.setData(data);
		model.addAttribute(response);
		return new ResponseEntity<Model>(model, HttpStatus.OK);
	    } else {
		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	    }
	}
    }

    @ApiOperation(value = "Retrieves character by passing its identifier", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Character retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Character(s) not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/characters/{characterId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getCharactersById(Model model, @PathVariable int characterId) {
	Optional<Character> character = characterService.findById(characterId);
	if (character.isPresent()) {
	    List<Character> listOfCharacters = new ArrayList<Character>();
	    listOfCharacters.add(character.get());
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Character> data = Utils
		    .createGenericDataContainer(listOfCharacters.stream().collect(Collectors.toSet()));
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves comics for one given character", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of comics retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Comic(s) not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/characters/{characterId}/comics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getComicsByCharacterId(Model model, @PathVariable int characterId,
	    @RequestParam(required = false, value = "title") Optional<String> title,
	    @RequestParam(required = false, value = "titleStartsWith") Optional<String> titleStartsWith,
	    @RequestParam(required = false, value = "format") Optional<String> format,
	    @RequestParam(required = false, value = "issueNumber") Optional<Integer> issueNumber,
	    @RequestParam(required = false, value = "diamondCode") Optional<String> diamondCode,
	    @RequestParam(required = false, value = "digitalId") Optional<Integer> digitalId,
	    @RequestParam(required = false, value = "upc") Optional<String> upc,
	    @RequestParam(required = false, value = "isbn") Optional<String> isbn,
	    @RequestParam(required = false, value = "ean") Optional<String> ean,
	    @RequestParam(required = false, value = "issn") Optional<String> issn,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {

	Optional<Character> character = characterService.findById(characterId);
	Set<Comic> temporarySetOfComics = character.isPresent() ? character.get().getComics() : new HashSet<Comic>();

	Set<Comic> setOfComicsAfterFiltering = new HashSet<Comic>();

	for (Comic comic : temporarySetOfComics) {
	    if (comic.getTitle().equalsIgnoreCase(title.isPresent() ? title.get() : comic.getTitle())
		    && comic.getTitle()
			    .startsWith(titleStartsWith.isPresent() ? titleStartsWith.get() : comic.getTitle())
		    && comic.getFormat().equalsIgnoreCase(format.isPresent() ? format.get() : comic.getFormat())
		    && comic.getIssueNumber() == (issueNumber.isPresent() ? issueNumber.get().doubleValue()
			    : comic.getIssueNumber())
		    && comic.getDiamondCode()
			    .equalsIgnoreCase(diamondCode.isPresent() ? diamondCode.get() : comic.getDiamondCode())
		    && comic.getDigitalId() == (digitalId.isPresent() ? digitalId.get().intValue()
			    : comic.getDigitalId())
		    && comic.getUpc().equalsIgnoreCase(upc.isPresent() ? upc.get() : comic.getUpc())
		    && comic.getIsbn().equalsIgnoreCase(isbn.isPresent() ? isbn.get() : comic.getIsbn())
		    && comic.getEan().equalsIgnoreCase(ean.isPresent() ? ean.get() : comic.getEan())
		    && comic.getIssn().equalsIgnoreCase(issn.isPresent() ? issn.get() : comic.getIssn())
		    && comic.getModified()
			    .compareTo(modifiedSince.isPresent() ? modifiedSince.get() : comic.getModified()) >= 0) {
		setOfComicsAfterFiltering.add(comic);
	    }
	}

	if (!setOfComicsAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Comic> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }

	    GenericWrapper<Comic> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Comic> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfComicsAfterFiltering.size()
			? setOfComicsAfterFiltering.size()
			: limit.get().intValue();

		List<Comic> temporaryList = new ArrayList<Comic>(setOfComicsAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Comic>(new HashSet<Comic>(temporaryList.subList(0, toIndex))));
	    } else {
		data = Utils.createGenericDataContainer(setOfComicsAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Comic> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves events for one given character", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of events retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Event(s) not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/characters/{characterId}/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getEventsByCharacterId(Model model, @PathVariable int characterId,
	    @RequestParam(required = false, value = "title") Optional<String> title,
	    @RequestParam(required = false, value = "titleStartsWith") Optional<String> titleStartsWith,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {
	Optional<Character> character = characterService.findById(characterId);
	Set<Event> temporarySetOfEvents = character.isPresent() ? character.get().getEvents() : new HashSet<Event>();

	Set<Event> setOfEventsAfterFiltering = new HashSet<Event>();

	for (Event event : temporarySetOfEvents) {
	    if (event.getTitle().equalsIgnoreCase(title.isPresent() ? title.get() : event.getTitle())
		    && event.getTitle()
			    .startsWith(titleStartsWith.isPresent() ? titleStartsWith.get() : event.getTitle())
		    && event.getModified()
			    .compareTo(modifiedSince.isPresent() ? modifiedSince.get() : event.getModified()) >= 0) {
		setOfEventsAfterFiltering.add(event);
	    }
	}

	if (!setOfEventsAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Event> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }

	    GenericWrapper<Event> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Event> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfEventsAfterFiltering.size()
			? setOfEventsAfterFiltering.size()
			: limit.get().intValue();

		List<Event> temporaryList = new ArrayList<Event>(setOfEventsAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Event>(new HashSet<Event>(temporaryList.subList(0, toIndex))));

	    } else {
		data = Utils.createGenericDataContainer(setOfEventsAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Event> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves series for one given character", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of series retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Serie(s) not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/characters/{characterId}/series", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getSeriesByCharacterId(Model model, @PathVariable int characterId,
	    @RequestParam(required = false, value = "title") Optional<String> title,
	    @RequestParam(required = false, value = "titleStartsWith") Optional<String> titleStartsWith,
	    @RequestParam(required = false, value = "startYear") Optional<Integer> startYear,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {
	Optional<Character> character = characterService.findById(characterId);
	Set<Serie> temporarySetOfSeries = character.isPresent() ? character.get().getSeries() : new HashSet<Serie>();

	Set<Serie> setOfSeriesAfterFiltering = new HashSet<Serie>();

	for (Serie serie : temporarySetOfSeries) {
	    if (serie.getTitle().equalsIgnoreCase(title.isPresent() ? title.get() : serie.getTitle())
		    && serie.getTitle()
			    .startsWith(titleStartsWith.isPresent() ? titleStartsWith.get() : serie.getTitle())
		    && serie.getStartYear() == (startYear.isPresent() ? startYear.get().intValue()
			    : serie.getStartYear())
		    && serie.getModified()
			    .compareTo(modifiedSince.isPresent() ? modifiedSince.get() : serie.getModified()) >= 0) {
		setOfSeriesAfterFiltering.add(serie);
	    }
	}

	if (!setOfSeriesAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Serie> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }

	    GenericWrapper<Serie> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Serie> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfSeriesAfterFiltering.size()
			? setOfSeriesAfterFiltering.size()
			: limit.get().intValue();
		List<Serie> temporaryList = new ArrayList<Serie>(setOfSeriesAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Serie>(new HashSet<Serie>(temporaryList.subList(0, toIndex))));
	    } else {
		data = Utils.createGenericDataContainer(setOfSeriesAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Serie> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves stories for one given character", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of stories retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Stories not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/characters/{characterId}/stories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getStoriesByCharacterId(Model model, @PathVariable int characterId,
	    @RequestParam(required = false, value = "title") Optional<String> title,
	    @RequestParam(required = false, value = "titleStartsWith") Optional<String> titleStartsWith,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {
	Optional<Character> character = characterService.findById(characterId);
	Set<Story> temporarySetOfStories = character.isPresent() ? character.get().getStories() : new HashSet<Story>();

	Set<Story> setOfStoriesAfterFiltering = new HashSet<Story>();

	for (Story story : temporarySetOfStories) {
	    if (story.getTitle().equalsIgnoreCase(title.isPresent() ? title.get() : story.getTitle())
		    && story.getTitle()
			    .startsWith(titleStartsWith.isPresent() ? titleStartsWith.get() : story.getTitle())
		    && story.getModified()
			    .compareTo(modifiedSince.isPresent() ? modifiedSince.get() : story.getModified()) >= 0) {
		setOfStoriesAfterFiltering.add(story);
	    }
	}

	if (!setOfStoriesAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Story> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }

	    GenericWrapper<Story> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Story> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfStoriesAfterFiltering.size()
			? setOfStoriesAfterFiltering.size()
			: limit.get().intValue();
		List<Story> temporaryList = new ArrayList<Story>(setOfStoriesAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Story>(new HashSet<Story>(temporaryList.subList(0, toIndex))));
	    } else {
		data = Utils.createGenericDataContainer(setOfStoriesAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Story> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves list of characters for one given comic", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of characters retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Characters not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/comics/{comicId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getCharactersByComicId(Model model, @PathVariable int comicId,
	    @RequestParam(required = false, value = "name") Optional<String> name,
	    @RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {

	Optional<Comic> comic = comicService.findById(comicId);
	Set<Character> temporarySetOfCharacters = comic.isPresent() ? comic.get().getCharacters()
		: new HashSet<Character>();

	Set<Character> setOfCharactersAfterFiltering = new HashSet<Character>();

	for (Character character : temporarySetOfCharacters) {
	    if (character.getName().equalsIgnoreCase(name.isPresent() ? name.get() : character.getName())
		    && character.getName()
			    .startsWith(nameStartsWith.isPresent() ? nameStartsWith.get() : character.getName())
		    && character.getModified().compareTo(
			    modifiedSince.isPresent() ? modifiedSince.get() : character.getModified()) >= 0) {
		setOfCharactersAfterFiltering.add(character);
	    }
	}

	if (!setOfCharactersAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Character> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfCharactersAfterFiltering.size()
			? setOfCharactersAfterFiltering.size()
			: limit.get().intValue();
		List<Character> temporaryList = new ArrayList<Character>(setOfCharactersAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Character>(new HashSet<Character>(temporaryList.subList(0, toIndex))));
	    } else {
		data = Utils.createGenericDataContainer(setOfCharactersAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves list of characters for one given event", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of characters retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Characters not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/events/{eventId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getCharactersByEventId(Model model, @PathVariable int eventId,
	    @RequestParam(required = false, value = "name") Optional<String> name,
	    @RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {
	Optional<Event> event = eventService.findById(eventId);
	Set<Character> temporarySetOfCharacters = event.isPresent() ? event.get().getCharacters()
		: new HashSet<Character>();
	Set<Character> setOfCharactersAfterFiltering = new HashSet<Character>();

	for (Character character : temporarySetOfCharacters) {
	    if (character.getName().equalsIgnoreCase(name.isPresent() ? name.get() : character.getName())
		    && character.getName()
			    .startsWith(nameStartsWith.isPresent() ? nameStartsWith.get() : character.getName())
		    && character.getModified().compareTo(
			    modifiedSince.isPresent() ? modifiedSince.get() : character.getModified()) >= 0) {
		setOfCharactersAfterFiltering.add(character);
	    }
	}

	if (!setOfCharactersAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Character> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfCharactersAfterFiltering.size()
			? setOfCharactersAfterFiltering.size()
			: limit.get().intValue();
		List<Character> temporaryList = new ArrayList<Character>(setOfCharactersAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Character>(new HashSet<Character>(temporaryList.subList(0, toIndex))));
	    } else {
		data = Utils.createGenericDataContainer(setOfCharactersAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves list of characters for one given serie", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of characters retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Characters not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/series/{serieId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getCharactersBySerieId(Model model, @PathVariable int serieId,
	    @RequestParam(required = false, value = "name") Optional<String> name,
	    @RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {
	Optional<Serie> serie = serieService.findById(serieId);
	Set<Character> temporarySetOfCharacters = serie.isPresent() ? serie.get().getCharacters()
		: new HashSet<Character>();

	Set<Character> setOfCharactersAfterFiltering = new HashSet<Character>();

	for (Character character : temporarySetOfCharacters) {
	    if (character.getName().equalsIgnoreCase(name.isPresent() ? name.get() : character.getName())
		    && character.getName()
			    .startsWith(nameStartsWith.isPresent() ? nameStartsWith.get() : character.getName())
		    && character.getModified().compareTo(
			    modifiedSince.isPresent() ? modifiedSince.get() : character.getModified()) >= 0) {
		setOfCharactersAfterFiltering.add(character);
	    }
	}

	if (!setOfCharactersAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Character> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfCharactersAfterFiltering.size()
			? setOfCharactersAfterFiltering.size()
			: limit.get().intValue();
		List<Character> temporaryList = new ArrayList<Character>(setOfCharactersAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Character>(new HashSet<Character>(temporaryList.subList(0, toIndex))));
	    } else {
		data = Utils.createGenericDataContainer(setOfCharactersAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

    @ApiOperation(value = "Retrieves list of characters for one given story", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of characters retrieved successfully!"),
	    @ApiResponse(code = 404, message = "Characters not found"),
	    @ApiResponse(code = 409, message = "Limit parameter passed has wrong value. It should be set greater than 1 and less than 100"), })
    @GetMapping(path = "/stories/{storyId}/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getCharactersByStoryId(Model model, @PathVariable int storyId,
	    @RequestParam(required = false, value = "name") Optional<String> name,
	    @RequestParam(required = false, value = "nameStartsWith") Optional<String> nameStartsWith,
	    @RequestParam(required = false, value = "modifiedSince") Optional<Date> modifiedSince,
	    @RequestParam(required = false, value = "limit") Optional<Integer> limit) {
	Optional<Story> story = storyService.findById(storyId);
	Set<Character> temporarySetOfCharacters = story.isPresent() ? story.get().getCharacters()
		: new HashSet<Character>();

	Set<Character> setOfCharactersAfterFiltering = new HashSet<Character>();

	for (Character character : temporarySetOfCharacters) {
	    if (character.getName().equalsIgnoreCase(name.isPresent() ? name.get() : character.getName())
		    && character.getName()
			    .startsWith(nameStartsWith.isPresent() ? nameStartsWith.get() : character.getName())
		    && character.getModified().compareTo(
			    modifiedSince.isPresent() ? modifiedSince.get() : character.getModified()) >= 0) {
		setOfCharactersAfterFiltering.add(character);
	    }
	}

	if (!setOfCharactersAfterFiltering.isEmpty()) {
	    if (limit.isPresent() && (limit.get().intValue() == 0 || limit.get().intValue() > 100)) {
		GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.CONFLICT);
		model.addAttribute("errorDescription", Msg.LIMIT_MSG_ERROR);
		model.addAttribute(response);
		return new ResponseEntity<>(model, HttpStatus.CONFLICT);
	    }
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.OK);
	    GenericDataContainer<Character> data = null;
	    if (limit.isPresent()) {
		int toIndex = limit.get().intValue() >= setOfCharactersAfterFiltering.size()
			? setOfCharactersAfterFiltering.size()
			: limit.get().intValue();
		List<Character> temporaryList = new ArrayList<Character>(setOfCharactersAfterFiltering);
		data = Utils.createGenericDataContainer(
			new HashSet<Character>(new HashSet<Character>(temporaryList.subList(0, toIndex))));
	    } else {
		data = Utils.createGenericDataContainer(setOfCharactersAfterFiltering);
	    }
	    response.setData(data);
	    model.addAttribute(response);
	    return new ResponseEntity<Model>(model, HttpStatus.OK);
	} else {
	    GenericWrapper<Character> response = Utils.createGenericWrapper(HttpStatus.NOT_FOUND);
	    model.addAttribute(response);
	    return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
	}
    }

}