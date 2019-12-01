package com.marvel.catalog.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image thumbnail;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "character_comic", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "comic_id"))
    @JsonManagedReference
    private Set<Comic> comics;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "character_story", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "story_id"))
    @JsonManagedReference
    private Set<Story> stories;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "character_event", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    @JsonManagedReference
    private Set<Event> events;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "character_serie", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "serie_id"))
    @JsonManagedReference
    private Set<Serie> series;

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

    public Set<Comic> getComics() {
	return comics;
    }

    public void setComics(Set<Comic> comics) {
	this.comics = comics;
    }

    public Set<Story> getStories() {
	return stories;
    }

    public void setStories(Set<Story> stories) {
	this.stories = stories;
    }

    public Set<Event> getEvents() {
	return events;
    }

    public void setEvents(Set<Event> events) {
	this.events = events;
    }

    public Set<Serie> getSeries() {
	return series;
    }

    public void setSeries(Set<Serie> series) {
	this.series = series;
    }
}
