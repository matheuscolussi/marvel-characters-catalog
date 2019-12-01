package com.marvel.catalog.startup;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.marvel.catalog.model.Character;
import com.marvel.catalog.model.Comic;
import com.marvel.catalog.model.Event;
import com.marvel.catalog.model.Image;
import com.marvel.catalog.model.Serie;
import com.marvel.catalog.model.Story;
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
	initializeDataForSpiderMan();
	initializeDataForHulk();
    }

    private Date createDate(int daysToAdd, int monthsToAdd, int yearsToAdd) {
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
	gregorianCalendar.add(Calendar.MONTH, monthsToAdd);
	gregorianCalendar.add(Calendar.YEAR, yearsToAdd);
	return gregorianCalendar.getTime();
    }

    private void initializeDataForSpiderMan() {
	Character character1 = new Character();
	character1.setId(1);
	character1.setName("Spider-Man");
	character1.setDescription("Description of Character 1!");
	character1.setModified(createDate(0, -10, 0));
	character1.setResourseURI("ResourseURI of Character 1");

	Image img1_character1 = new Image();
	img1_character1.setId(1);
	img1_character1.setExtension("JPEG");
	img1_character1.setPath("/static/resources/img1_character1");
	img1_character1.setCharacter(character1);
	character1.setThumbnail(img1_character1);

	Serie serie1 = new Serie();
	Set<Character> setOfCharacters1 = new HashSet<Character>();
	setOfCharacters1.add(character1);
	serie1.setId(1);
	serie1.setCharacters(setOfCharacters1);
	serie1.setTitle("Title of Serie 1");
	serie1.setDescription("Description of Serie 1");
	serie1.setStartYear(1990);
	serie1.setEndYear(2000);
	serie1.setModified(createDate(0, -9, 0));
	serie1.setRating("5 Stars");
	serie1.setResourceURI("ResourceURI of Serie 1");

	Image img2_serie1 = new Image();
	img2_serie1.setId(2);
	img2_serie1.setExtension("JPEG");
	img2_serie1.setPath("/static/resources/img2_serie1");
	img2_serie1.setSerie(serie1);
	serie1.setThumbnail(img2_serie1);

	Serie serie2 = new Serie();
	Set<Character> setOfCharacters2 = new HashSet<Character>();
	setOfCharacters2.add(character1);
	serie2.setId(2);
	serie2.setCharacters(setOfCharacters2);
	serie2.setTitle("Title of Serie 2");
	serie2.setDescription("Description of Serie 2");
	serie2.setStartYear(2001);
	serie2.setEndYear(2012);
	serie2.setModified(createDate(1, -8, 0));
	serie2.setRating("4 Stars");
	serie2.setResourceURI("ResourceURI of Serie 2");

	Image img3_serie2 = new Image();
	img3_serie2.setId(3);
	img3_serie2.setExtension("JPEG");
	img3_serie2.setPath("/static/resources/img3_serie2");
	img3_serie2.setSerie(serie2);
	serie2.setThumbnail(img3_serie2);

	Serie serie3 = new Serie();
	Set<Character> setOfCharacters3 = new HashSet<Character>();
	setOfCharacters3.add(character1);
	serie3.setId(3);
	serie3.setCharacters(setOfCharacters3);
	serie3.setTitle("Title of Serie 3");
	serie3.setDescription("Description of Serie 3");
	serie3.setStartYear(1995);
	serie3.setEndYear(1999);
	serie3.setModified(createDate(2, -7, 0));
	serie3.setRating("3 Stars");
	serie3.setResourceURI("ResourceURI of Serie 3");

	Image img4_serie3 = new Image();
	img4_serie3.setId(4);
	img4_serie3.setExtension("JPEG");
	img4_serie3.setPath("/static/resources/img4_serie3");
	img4_serie3.setSerie(serie3);
	serie3.setThumbnail(img4_serie3);

	Comic comic1 = new Comic();
	comic1.setId(1);
	comic1.setDigitalId(10000);
	comic1.setDescription("Description of Comic 1");
	comic1.setDiamondCode("Diamond Code of Comic 1");
	comic1.setEan("EAN of Comic 1");
	comic1.setFormat("Format of Comic 1");
	comic1.setIsbn("ISBN of Comic 1");
	comic1.setIssn("ISSN of Comic 1");
	comic1.setIssueNumber(111111);
	comic1.setModified(createDate(20, -15, 0));
	comic1.setPageCount(200);
	comic1.setResourceURI("ResourceURI of Comic 1");
	comic1.setTitle("Title of Comic 1");
	comic1.setUpc("UPC of Comic 1");
	comic1.setVariantDescription("Variant Description of Comic 1");

	Image img5_comic1 = new Image();
	img5_comic1.setId(5);
	img5_comic1.setExtension("JPEG");
	img5_comic1.setPath("/static/resources/img5_comic1");
	img5_comic1.setComic(comic1);
	comic1.setThumbnail(img5_comic1);

	Comic comic2 = new Comic();
	comic2.setId(2);
	comic2.setDigitalId(20000);
	comic2.setDescription("Description of Comic 2");
	comic2.setDiamondCode("Diamond Code of Comic 2");
	comic2.setEan("EAN of Comic 2");
	comic2.setFormat("Format of Comic 2");
	comic2.setIsbn("ISBN of Comic 2");
	comic2.setIssn("ISSN of Comic 2");
	comic2.setIssueNumber(222222);
	comic2.setModified(createDate(12, -3, 0));
	comic2.setPageCount(200);
	comic2.setResourceURI("ResourceURI of Comic 2");
	comic2.setTitle("Title of Comic 2");
	comic2.setUpc("UPC of Comic 2");
	comic2.setVariantDescription("Variant Description of Comic 2");

	Image img6_comic2 = new Image();
	img6_comic2.setId(6);
	img6_comic2.setExtension("JPEG");
	img6_comic2.setPath("/static/resources/img6_comic2");
	img6_comic2.setComic(comic2);
	comic2.setThumbnail(img6_comic2);

	Story story1 = new Story();
	story1.setId(1);
	story1.setTitle("Title of Story 1");
	story1.setDescription("Description of Story 1");
	story1.setModified(createDate(25, -1, 0));
	story1.setResourceURI("Resource URI of Story 1");
	story1.setType("Type of Story 1");

	Image img7_story1 = new Image();
	img7_story1.setId(7);
	img7_story1.setExtension("JPEG");
	img7_story1.setPath("/static/resources/img7_story1");
	img7_story1.setStory(story1);
	story1.setThumbnail(img7_story1);

	Story story2 = new Story();
	story2.setId(2);
	story2.setTitle("Title of Story 2");
	story2.setDescription("Description of Story 2");
	story2.setModified(createDate(10, -10, 0));
	story2.setResourceURI("Resource URI of Story 2");
	story2.setType("Type of Story 2");

	Image img8_story2 = new Image();
	img8_story2.setId(8);
	img8_story2.setExtension("JPEG");
	img8_story2.setPath("/static/resources/img8_story2");
	img8_story2.setStory(story2);
	story2.setThumbnail(img8_story2);

	Story story3 = new Story();
	story3.setId(3);
	story3.setTitle("Title of Story 3");
	story3.setDescription("Description of Story 3");
	story3.setModified(createDate(7, -4, 0));
	story3.setResourceURI("Resource URI of Story 3");
	story3.setType("Type of Story 3");

	Image img9_story3 = new Image();
	img9_story3.setId(9);
	img9_story3.setExtension("JPEG");
	img9_story3.setPath("/static/resources/img9_story3");
	img9_story3.setStory(story3);
	story3.setThumbnail(img9_story3);

	Story story4 = new Story();
	story4.setId(4);
	story4.setTitle("Title of Story 4");
	story4.setDescription("Description of Story 4");
	story4.setModified(createDate(30, -20, 0));
	story4.setResourceURI("Resource URI of Story 4");
	story4.setType("Type of Story 4");

	Image img10_story4 = new Image();
	img10_story4.setId(10);
	img10_story4.setExtension("JPEG");
	img10_story4.setPath("/static/resources/img10_story4");
	img10_story4.setStory(story4);
	story4.setThumbnail(img10_story4);

	Event event1 = new Event();
	event1.setId(1);
	event1.setTitle("Title of Event 1");
	event1.setDescription("Description of Title 1");
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, -12);
	event1.setStart(gregorianCalendar.getTime());
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, 12);
	event1.setEnd(gregorianCalendar.getTime());
	event1.setModified(createDate(9, -15, 0));
	event1.setResourceURI("ResourceURI of Event 1");

	Image img11_event1 = new Image();
	img11_event1.setId(11);
	img11_event1.setExtension("JPEG");
	img11_event1.setPath("/static/resources/img11_event1");
	img11_event1.setEvent(event1);
	event1.setThumbnail(img11_event1);

	Event event2 = new Event();
	event2.setId(2);
	event2.setTitle("Title of Event 2");
	event2.setDescription("Description of Title 2");
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, -24);
	event2.setStart(gregorianCalendar.getTime());
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, 24);
	event2.setEnd(gregorianCalendar.getTime());
	event2.setModified(createDate(19, -2, 0));
	event2.setResourceURI("ResourceURI of Event 2");

	Image img12_event2 = new Image();
	img12_event2.setId(12);
	img12_event2.setExtension("JPEG");
	img12_event2.setPath("/static/resources/img12_event2");
	img12_event2.setEvent(event2);
	event2.setThumbnail(img12_event2);

	Event event3 = new Event();
	event3.setId(3);
	event3.setTitle("Title of Event 3");
	event3.setDescription("Description of Title 3");
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, -36);
	event3.setStart(gregorianCalendar.getTime());
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, 36);
	event3.setEnd(gregorianCalendar.getTime());
	event3.setModified(createDate(0, -1, 0));
	event3.setResourceURI("ResourceURI of Event 3");

	Image img13_event3 = new Image();
	img13_event3.setId(13);
	img13_event3.setExtension("JPEG");
	img13_event3.setPath("/static/resources/img13_event3");
	img13_event3.setEvent(event3);
	event3.setThumbnail(img13_event3);

	Set<Serie> setOfSeries1 = new HashSet<Serie>();
	setOfSeries1.add(serie1);
	setOfSeries1.add(serie2);
	setOfSeries1.add(serie3);
	character1.setSeries(setOfSeries1);

	Set<Comic> setOfComics1 = new HashSet<Comic>();
	setOfComics1.add(comic1);
	setOfComics1.add(comic2);
	character1.setComics(setOfComics1);

	Set<Story> setOfStories1 = new HashSet<Story>();
	setOfStories1.add(story1);
	setOfStories1.add(story2);
	setOfStories1.add(story3);
	setOfStories1.add(story4);
	character1.setStories(setOfStories1);

	Set<Event> setOfEvents1 = new HashSet<Event>();
	setOfEvents1.add(event1);
	setOfEvents1.add(event2);
	setOfEvents1.add(event3);
	character1.setEvents(setOfEvents1);

	characterRepository.save(character1);
    }

    private void initializeDataForHulk() {
	Character character2 = new Character();
	character2.setId(2);
	character2.setName("Hulk");
	character2.setDescription("Description of Character 2!");
	character2.setModified(createDate(1, -10, 0));
	character2.setResourseURI("ResourseURI of Character 2");

	Image img14_character2 = new Image();
	img14_character2.setId(14);
	img14_character2.setExtension("JPEG");
	img14_character2.setPath("/static/resources/img14_character2");
	img14_character2.setCharacter(character2);
	character2.setThumbnail(img14_character2);

	Serie serie4 = new Serie();
	Set<Character> setOfCharacters1 = new HashSet<Character>();
	setOfCharacters1.add(character2);
	serie4.setId(4);
	serie4.setCharacters(setOfCharacters1);
	serie4.setTitle("Title of Serie 4");
	serie4.setDescription("Description of Serie 4");
	serie4.setStartYear(1990);
	serie4.setEndYear(2000);
	serie4.setModified(createDate(1, -10, 0));
	serie4.setRating("5 Stars");
	serie4.setResourceURI("ResourceURI of Serie 4");

	Image img15_serie4 = new Image();
	img15_serie4.setId(15);
	img15_serie4.setExtension("JPEG");
	img15_serie4.setPath("/static/resources/img15_serie4");
	img15_serie4.setSerie(serie4);
	serie4.setThumbnail(img15_serie4);

	Serie serie5 = new Serie();
	Set<Character> setOfCharacters2 = new HashSet<Character>();
	setOfCharacters2.add(character2);
	serie5.setId(5);
	serie5.setCharacters(setOfCharacters2);
	serie5.setTitle("Title of Serie 5");
	serie5.setDescription("Description of Serie 5");
	serie5.setStartYear(2001);
	serie5.setEndYear(2012);
	serie5.setModified(createDate(2, -9, 0));
	serie5.setRating("4 Stars");
	serie5.setResourceURI("ResourceURI of Serie 5");

	Image img16_serie5 = new Image();
	img16_serie5.setId(16);
	img16_serie5.setExtension("JPEG");
	img16_serie5.setPath("/static/resources/img16_serie5");
	img16_serie5.setSerie(serie5);
	serie5.setThumbnail(img16_serie5);

	Serie serie6 = new Serie();
	Set<Character> setOfCharacters3 = new HashSet<Character>();
	setOfCharacters3.add(character2);
	serie6.setId(6);
	serie6.setCharacters(setOfCharacters3);
	serie6.setTitle("Title of Serie 6");
	serie6.setDescription("Description of Serie 6");
	serie6.setStartYear(1995);
	serie6.setEndYear(2000);
	serie6.setModified(createDate(2, -8, 0));
	serie6.setRating("3 Stars");
	serie6.setResourceURI("ResourceURI of Serie 6");

	Image img17_serie6 = new Image();
	img17_serie6.setId(17);
	img17_serie6.setExtension("JPEG");
	img17_serie6.setPath("/static/resources/img17_serie6");
	img17_serie6.setSerie(serie6);
	serie6.setThumbnail(img17_serie6);

	Comic comic3 = new Comic();
	comic3.setId(3);
	comic3.setDigitalId(20000);
	comic3.setDescription("Description of Comic 3");
	comic3.setDiamondCode("Diamond Code of Comic 3");
	comic3.setEan("EAN of Comic 3");
	comic3.setFormat("Format of Comic 3");
	comic3.setIsbn("ISBN of Comic 3");
	comic3.setIssn("ISSN of Comic 3");
	comic3.setIssueNumber(111111);
	comic3.setModified(createDate(19, -15, 0));
	comic3.setPageCount(200);
	comic3.setResourceURI("ResourceURI of Comic 3");
	comic3.setTitle("Title of Comic 3");
	comic3.setUpc("UPC of Comic 3");
	comic3.setVariantDescription("Variant Description of Comic 3");

	Image img18_comic3 = new Image();
	img18_comic3.setId(18);
	img18_comic3.setExtension("JPEG");
	img18_comic3.setPath("/static/resources/img18_comic3");
	img18_comic3.setComic(comic3);
	comic3.setThumbnail(img18_comic3);

	Comic comic4 = new Comic();
	comic4.setId(4);
	comic4.setDigitalId(30000);
	comic4.setDescription("Description of Comic 4");
	comic4.setDiamondCode("Diamond Code of Comic 4");
	comic4.setEan("EAN of Comic 4");
	comic4.setFormat("Format of Comic 4");
	comic4.setIsbn("ISBN of Comic 4");
	comic4.setIssn("ISSN of Comic 4");
	comic4.setIssueNumber(222222);
	comic4.setModified(createDate(10, -3, 0));
	comic4.setPageCount(200);
	comic4.setResourceURI("ResourceURI of Comic 4");
	comic4.setTitle("Title of Comic 4");
	comic4.setUpc("UPC of Comic 4");
	comic4.setVariantDescription("Variant Description of Comic 4");

	Image img19_comic4 = new Image();
	img19_comic4.setId(19);
	img19_comic4.setExtension("JPEG");
	img19_comic4.setPath("/static/resources/img19_comic4");
	img19_comic4.setComic(comic4);
	comic4.setThumbnail(img19_comic4);

	Story story5 = new Story();
	story5.setId(5);
	story5.setTitle("Title of Story 5");
	story5.setDescription("Description of Story 5");
	story5.setModified(createDate(10, -1, 0));
	story5.setResourceURI("Resource URI of Story 5");
	story5.setType("Type of Story 5");

	Image img20_story5 = new Image();
	img20_story5.setId(20);
	img20_story5.setExtension("JPEG");
	img20_story5.setPath("/static/resources/img20_story5");
	img20_story5.setStory(story5);
	story5.setThumbnail(img20_story5);

	Story story6 = new Story();
	story6.setId(6);
	story6.setTitle("Title of Story 6");
	story6.setDescription("Description of Story 6");
	story6.setModified(createDate(8, -8, 0));
	story6.setResourceURI("Resource URI of Story 6");
	story6.setType("Type of Story 6");

	Image img21_story6 = new Image();
	img21_story6.setId(21);
	img21_story6.setExtension("JPEG");
	img21_story6.setPath("/static/resources/img21_story6");
	img21_story6.setStory(story6);
	story6.setThumbnail(img21_story6);

	Story story7 = new Story();
	story7.setId(7);
	story7.setTitle("Title of Story 7");
	story7.setDescription("Description of Story 7");
	story7.setModified(createDate(6, -3, 0));
	story7.setResourceURI("Resource URI of Story 7");
	story7.setType("Type of Story 7");

	Image img22_story7 = new Image();
	img22_story7.setId(22);
	img22_story7.setExtension("JPEG");
	img22_story7.setPath("/static/resources/img21_story7");
	img22_story7.setStory(story7);
	story7.setThumbnail(img22_story7);

	Story story8 = new Story();
	story8.setId(8);
	story8.setTitle("Title of Story 8");
	story8.setDescription("Description of Story 8");
	story8.setModified(createDate(29, -2, 0));
	story8.setResourceURI("Resource URI of Story 8");
	story8.setType("Type of Story 8");

	Image img23_story8 = new Image();
	img23_story8.setId(23);
	img23_story8.setExtension("JPEG");
	img23_story8.setPath("/static/resources/img22_story8");
	img23_story8.setStory(story8);
	story8.setThumbnail(img23_story8);

	Event event4 = new Event();
	event4.setId(4);
	event4.setTitle("Title of Event 4");
	event4.setDescription("Description of Title 4");
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, -24);
	event4.setStart(gregorianCalendar.getTime());
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, 24);
	event4.setEnd(gregorianCalendar.getTime());
	event4.setModified(createDate(7, -13, 0));
	event4.setResourceURI("ResourceURI of Event 4");

	Image img24_event4 = new Image();
	img24_event4.setId(24);
	img24_event4.setExtension("JPEG");
	img24_event4.setPath("/static/resources/img23_event4");
	img24_event4.setEvent(event4);
	event4.setThumbnail(img24_event4);

	Event event5 = new Event();
	event5.setId(5);
	event5.setTitle("Title of Event 5");
	event5.setDescription("Description of Title 5");
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, -18);
	event5.setStart(gregorianCalendar.getTime());
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, 18);
	event5.setEnd(gregorianCalendar.getTime());
	event5.setModified(createDate(18, -7, 0));
	event5.setResourceURI("ResourceURI of Event 5");

	Image img25_event5 = new Image();
	img25_event5.setId(25);
	img25_event5.setExtension("JPEG");
	img25_event5.setPath("/static/resources/img24_event5");
	img25_event5.setEvent(event5);
	event5.setThumbnail(img25_event5);

	Event event6 = new Event();
	event6.setId(6);
	event6.setTitle("Title of Event 6");
	event6.setDescription("Description of Title 6");
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, -8);
	event6.setStart(gregorianCalendar.getTime());
	gregorianCalendar.setTime(new Date());
	gregorianCalendar.add(Calendar.MONTH, 8);
	event6.setEnd(gregorianCalendar.getTime());
	event6.setModified(createDate(9, -1, 0));
	event6.setResourceURI("ResourceURI of Event 6");

	Image img26_event6 = new Image();
	img26_event6.setId(26);
	img26_event6.setExtension("JPEG");
	img26_event6.setPath("/static/resources/img25_event6");
	img26_event6.setEvent(event6);
	event6.setThumbnail(img26_event6);

	Set<Serie> setOfSeries1 = new HashSet<Serie>();
	setOfSeries1.add(serie4);
	setOfSeries1.add(serie5);
	setOfSeries1.add(serie6);
	character2.setSeries(setOfSeries1);

	Set<Comic> setOfComics1 = new HashSet<Comic>();
	setOfComics1.add(comic3);
	setOfComics1.add(comic4);
	character2.setComics(setOfComics1);

	Set<Story> setOfStories1 = new HashSet<Story>();
	setOfStories1.add(story5);
	setOfStories1.add(story6);
	setOfStories1.add(story7);
	setOfStories1.add(story8);
	character2.setStories(setOfStories1);

	Set<Event> setOfEvents1 = new HashSet<Event>();
	setOfEvents1.add(event4);
	setOfEvents1.add(event5);
	setOfEvents1.add(event6);
	character2.setEvents(setOfEvents1);

	characterRepository.save(character2);
    }
}
