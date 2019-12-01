package com.marvel.catalog.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "IMAGE")
public class Image {

    @Id
    private int id;
    private String path;
    private String extension;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Character character;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Comic comic;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Event event;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Serie serie;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Story story;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public String getExtension() {
	return extension;
    }

    public void setExtension(String extension) {
	this.extension = extension;
    }

    public Character getCharacter() {
	return character;
    }

    public void setCharacter(Character character) {
	this.character = character;
    }

    public Comic getComic() {
	return comic;
    }

    public void setComic(Comic comic) {
	this.comic = comic;
    }

    public Event getEvent() {
	return event;
    }

    public void setEvent(Event event) {
	this.event = event;
    }

    public Serie getSerie() {
	return serie;
    }

    public void setSerie(Serie serie) {
	this.serie = serie;
    }

    public Story getStory() {
	return story;
    }

    public void setStory(Story story) {
	this.story = story;
    }
}
