package com.marvel.catalog.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image thumbnail;

    @ManyToMany(mappedBy = "events", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Character> characters;

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

    public Set<Character> getCharacters() {
	return characters;
    }

    public void setCharacters(Set<Character> characters) {
	this.characters = characters;
    }
}
