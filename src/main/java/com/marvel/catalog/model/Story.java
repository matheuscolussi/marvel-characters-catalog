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
@Table(name = "STORY")
public class Story {

	@Id
	private int id;
	private String title;
	private String description;
	private String resourceURI;
	private String type;
	private Date modified;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "image_id")
	private Image thumbnail;

	@ManyToMany(mappedBy = "stories")
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
//	private GenericSummary originalissue;

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
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
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
//	public GenericSummary getOriginalissue() {
//		return originalissue;
//	}
//
//	public void setOriginalissue(GenericSummary originalissue) {
//		this.originalissue = originalissue;
//	}

//	Story {
//		id (int, optional): The unique ID of the story resource.,
//		title (string, optional): The story title.,
//		description (string, optional): A short description of the story.,
//		resourceURI (string, optional): The canonical URL identifier for this resource. ,
//		type (string, optional): The story type e.g. interior story, cover, text story.,
//		modified (Date, optional): The date the resource was most recently modified.,
//		thumbnail (Image, optional): The representative image for this story.,
//		comics (ComicList, optional): A resource list containing comics in which this story takes place.,
//		series (SeriesList, optional): A resource list containing series in which this story appears.,
//		events (EventList, optional): A resource list of the events in which this story appears.,
//		characters (CharacterList, optional): A resource list of characters which appear in this story.,
//		creators (CreatorList, optional): A resource list of creators who worked on this story.,
//		originalissue (ComicSummary, optional): A summary representation of the issue in which this story was originally published.
//		}
}
