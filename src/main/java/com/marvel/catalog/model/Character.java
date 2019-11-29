package com.marvel.catalog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CHARACTER")
public class Character {

	@Id
	private int id;
	private String name;
	private String description;
	private Date modified;
	private String resourseURI;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "image_id")
	private Image thumbnail;

	@ManyToMany (cascade = {CascadeType.ALL})
	@JoinTable(name = "character_comic", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "comic_id"))
	@JsonManagedReference
	private List<Comic> comics;

	@ManyToMany (cascade = {CascadeType.ALL})
	@JoinTable(name = "character_story", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "story_id"))
	@JsonManagedReference
	private List<Story> stories;

	@ManyToMany (cascade = {CascadeType.ALL})
	@JoinTable(name = "character_event", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	@JsonManagedReference
	private List<Event> events;

	@ManyToMany (cascade = {CascadeType.ALL})
	@JoinTable(name = "character_serie", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "serie_id"))
	@JsonManagedReference
	private List<Serie> series;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getResourseURI() {
		return resourseURI;
	}

	public void setResourseURI(String resourseURI) {
		this.resourseURI = resourseURI;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<Comic> getComics() {
		return comics;
	}

	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

//	@OneToMany
//	@JoinColumn(name = "url_id")
//	private List<Url> urls;

//	Character {
//		id (int, optional): The unique ID of the character resource.,
//		name (string, optional): The name of the character.,
//		description (string, optional): A short bio or description of the character.,
//		modified (Date, optional): The date the resource was most recently modified.,
//		resourceURI (string, optional): The canonical URL identifier for this resource.,
//		urls (Array[Url], optional): A set of public web site URLs for the resource.,
//		thumbnail (Image, optional): The representative image for this character.,
//		comics (ComicList, optional): A resource list containing comics which feature this character.,
//		stories (StoryList, optional): A resource list of stories in which this character appears.,
//		events (EventList, optional): A resource list of events in which this character appears.,
//		series (SeriesList, optional): A resource list of series in which this character appears.
//		}
}
