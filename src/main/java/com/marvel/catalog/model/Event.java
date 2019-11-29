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
@Table(name = "EVENT")
public class Event {
	@Id
	private int id;
	private String title;
	private String description;
	private String resourceURI;
	private Date modified;
	private Date start;
	private Date end;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "image_id")
	private Image thumbnail;

	@ManyToMany(mappedBy = "events")
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

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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
//	private GenericList series;
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
//	public Date getModified() {
//		return modified;
//	}
//
//	public void setModified(Date modified) {
//		this.modified = modified;
//	}
//
//	public Date getStart() {
//		return start;
//	}
//
//	public void setStart(Date start) {
//		this.start = start;
//	}
//
//	public Date getEnd() {
//		return end;
//	}
//
//	public void setEnd(Date end) {
//		this.end = end;
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
//	public GenericList getSeries() {
//		return series;
//	}
//
//	public void setSeries(GenericList series) {
//		this.series = series;
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

//	Event {
//		id (int, optional): The unique ID of the event resource.,
//		title (string, optional): The title of the event.,
//		description (string, optional): A description of the event.,
//		resourceURI (string, optional): The canonical URL identifier for this resource.,
//		urls (Array[Url], optional): A set of public web site URLs for the event.,
//		modified (Date, optional): The date the resource was most recently modified.,
//		start (Date, optional): The date of publication of the first issue in this event.,
//		end (Date, optional): The date of publication of the last issue in this event.,
//		thumbnail (Image, optional): The representative image for this event.,
//		comics (ComicList, optional): A resource list containing the comics in this event.,
//		stories (StoryList, optional): A resource list containing the stories in this event.,
//		series (SeriesList, optional): A resource list containing the series in this event.,
//		characters (CharacterList, optional): A resource list containing the characters which appear in this event.,
//		creators (CreatorList, optional): A resource list containing creators whose work appears in this event.,
//		next (EventSummary, optional): A summary representation of the event which follows this event.,
//		previous (EventSummary, optional): A summary representation of the event which preceded this event.
//		}
}
