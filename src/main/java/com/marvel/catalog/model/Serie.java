package com.marvel.catalog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SERIE")
public class Serie {

	@Id
	private int id;
	private String title;
	private String description;
	private String resourceURI;
	private int startYear;
	private int endYear;
	private String rating;
	private Date modified;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "image_id")
	private Image thumbnail;

	@ManyToMany(mappedBy = "series")
	@JsonBackReference
	private List<Character> characters;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

//	private GenericList comics;
//	private GenericList stories;
//	private GenericList events;
//	private GenericList characters;
//	private GenericList creators;
//	private GenericSummary next;
//	private GenericSummary previous;

//	@OneToMany
//	@JoinColumn(name = "url_id")
//	private List<Url> urls;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getResourceURI() {
//		return resourceURI;
//	}
//
//	public void setResourceURI(String resourceURI) {
//		this.resourceURI = resourceURI;
//	}
//
//	public int getStartYear() {
//		return startYear;
//	}
//
//	public void setStartYear(int startYear) {
//		this.startYear = startYear;
//	}
//
//	public int getEndYear() {
//		return endYear;
//	}
//
//	public void setEndYear(int endYear) {
//		this.endYear = endYear;
//	}
//
//	public String getRating() {
//		return rating;
//	}
//
//	public void setRating(String rating) {
//		this.rating = rating;
//	}
//
//	public Date getModified() {
//		return modified;
//	}
//
//	public void setModified(Date modified) {
//		this.modified = modified;
//	}
//
//	public Image getThumbnail() {
//		return thumbnail;
//	}
//
//	public void setThumbnail(Image thumbnail) {
//		this.thumbnail = thumbnail;
//	}
//
//	public GenericList getComics() {
//		return comics;
//	}
//
//	public void setComics(GenericList comics) {
//		this.comics = comics;
//	}
//
//	public GenericList getStories() {
//		return stories;
//	}
//
//	public void setStories(GenericList stories) {
//		this.stories = stories;
//	}
//
//	public GenericList getEvents() {
//		return events;
//	}
//
//	public void setEvents(GenericList events) {
//		this.events = events;
//	}
//
//	public GenericList getCharacters() {
//		return characters;
//	}
//
//	public void setCharacters(GenericList characters) {
//		this.characters = characters;
//	}
//
//	public GenericList getCreators() {
//		return creators;
//	}
//
//	public void setCreators(GenericList creators) {
//		this.creators = creators;
//	}
//
//	public GenericSummary getNext() {
//		return next;
//	}
//
//	public void setNext(GenericSummary next) {
//		this.next = next;
//	}
//
//	public GenericSummary getPrevious() {
//		return previous;
//	}
//
//	public void setPrevious(GenericSummary previous) {
//		this.previous = previous;
//	}
//
//	public List<Url> getUrls() {
//		return urls;
//	}
//
//	public void setUrls(List<Url> urls) {
//		this.urls = urls;
//	}

//	Series {
//		id (int, optional): The unique ID of the series resource.,
//		title (string, optional): The canonical title of the series.,
//		description (string, optional): A description of the series.,
//		resourceURI (string, optional): The canonical URL identifier for this resource.,
//		urls (Array[Url], optional): A set of public web site URLs for the resource.,
//		startYear (int, optional): The first year of publication for the series.,
//		endYear (int, optional): The last year of publication for the series (conventionally, 2099 for ongoing series) .,
//		rating (string, optional): The age-appropriateness rating for the series.,
//		modified (Date, optional): The date the resource was most recently modified.,
//		thumbnail (Image, optional): The representative image for this series.,
//		comics (ComicList, optional): A resource list containing comics in this series.,
//		stories (StoryList, optional): A resource list containing stories which occur in comics in this series.,
//		events (EventList, optional): A resource list containing events which take place in comics in this series.,
//		characters (CharacterList, optional): A resource list containing characters which appear in comics in this series.,
//		creators (CreatorList, optional): A resource list of creators whose work appears in comics in this series.,
//		next (SeriesSummary, optional): A summary representation of the series which follows this series.,
//		previous (SeriesSummary, optional): A summary representation of the series which preceded this series.
//		}
}
