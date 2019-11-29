package com.marvel.catalog.startup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.marvel.catalog.model.Character;
import com.marvel.catalog.model.Image;
import com.marvel.catalog.model.Serie;
import com.marvel.catalog.repository.CharacterRepository;
import com.marvel.catalog.repository.ComicRepository;
import com.marvel.catalog.repository.EventRepository;
import com.marvel.catalog.repository.ImageRepository;
import com.marvel.catalog.repository.SerieRepository;
import com.marvel.catalog.repository.StoryRepository;

@Component
public class AppStartUp implements ApplicationListener<ContextRefreshedEvent> {

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

	@Autowired
	ImageRepository imageRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initializeData();
	}

	private void initializeData() {
		Character character1 = new Character();
		character1.setId(1);
		character1.setName("Character 1");
		character1.setDescription("Description of Character 1!");
		character1.setModified(new Date());
		character1.setResourseURI("ResourseURI of Character 1");

		Image img_character1 = new Image();
		img_character1.setId(1);
		img_character1.setExtension("JPEG");
		img_character1.setPath("/static/resources/img_character1");
		img_character1.setCharacter(character1);
		character1.setThumbnail(img_character1);

		Serie serie1 = new Serie();
		List<Character> listOfCharacters1 = new ArrayList<Character>();
		listOfCharacters1.add(character1);
		serie1.setId(1);
		serie1.setCharacters(listOfCharacters1);
		serie1.setTitle("Title of Serie 1");
		serie1.setDescription("Description of Serie 1");
		serie1.setStartYear(1990);
		serie1.setEndYear(2000);
		serie1.setModified(new Date());
		serie1.setRating("5 Stars");
		serie1.setResourceURI("ResourceURI of Serie 1");
		Image img_serie1 = new Image();
		img_serie1.setId(2);
		img_serie1.setExtension("JPEG");
		img_serie1.setPath("/static/resources/img_serie1");
		img_serie1.setSerie(serie1);
		serie1.setThumbnail(img_serie1);

		Serie serie2 = new Serie();
		List<Character> listOfCharacters2 = new ArrayList<Character>();
		listOfCharacters2.add(character1);
		serie2.setId(2);
		serie2.setCharacters(listOfCharacters2);
		serie2.setTitle("Title of Serie 2");
		serie2.setDescription("Description of Serie 2");
		serie2.setStartYear(2001);
		serie2.setEndYear(2012);
		serie2.setModified(new Date());
		serie2.setRating("4 Stars");
		serie2.setResourceURI("ResourceURI of Serie 2");
		Image img_serie2 = new Image();
		img_serie2.setId(3);
		img_serie2.setExtension("JPEG");
		img_serie2.setPath("/static/resources/img_serie2");
		img_serie2.setSerie(serie2);
		serie2.setThumbnail(img_serie2);

		Serie serie3 = new Serie();
		List<Character> listOfCharacters3 = new ArrayList<Character>();
		listOfCharacters3.add(character1);
		serie3.setId(3);
		serie3.setCharacters(listOfCharacters3);
		serie3.setTitle("Title of Serie 3");
		serie3.setDescription("Description of Serie 3");
		serie3.setStartYear(1995);
		serie3.setEndYear(1999);
		serie3.setModified(new Date());
		serie3.setRating("3 Stars");
		serie3.setResourceURI("ResourceURI of Serie 3");
		Image img_serie3 = new Image();
		img_serie3.setId(4);
		img_serie3.setExtension("JPEG");
		img_serie3.setPath("/static/resources/img_serie3");
		img_serie3.setSerie(serie3);
		serie3.setThumbnail(img_serie3);

		List<Serie> setOfSeries1 = new ArrayList<Serie>();
		setOfSeries1.add(serie1);
		setOfSeries1.add(serie2);
		setOfSeries1.add(serie3);
		character1.setSeries(setOfSeries1);

		characterRepository.save(character1);
	}
}
