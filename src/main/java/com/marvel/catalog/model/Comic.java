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
@Table(name = "COMIC")
public class Comic {

    @Id
    private int id;
    private int digitalId;
    private String title;
    private double issueNumber;
    private String variantDescription;
    private String description;
    private Date modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private int pageCount;
    private String resourceURI;

    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image thumbnail;

    @ManyToMany(mappedBy = "comics", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Character> characters;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getDigitalId() {
	return digitalId;
    }

    public void setDigitalId(int digitalId) {
	this.digitalId = digitalId;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public double getIssueNumber() {
	return issueNumber;
    }

    public void setIssueNumber(double issueNumber) {
	this.issueNumber = issueNumber;
    }

    public String getVariantDescription() {
	return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
	this.variantDescription = variantDescription;
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

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public String getUpc() {
	return upc;
    }

    public void setUpc(String upc) {
	this.upc = upc;
    }

    public String getDiamondCode() {
	return diamondCode;
    }

    public void setDiamondCode(String diamondCode) {
	this.diamondCode = diamondCode;
    }

    public String getEan() {
	return ean;
    }

    public void setEan(String ean) {
	this.ean = ean;
    }

    public String getIssn() {
	return issn;
    }

    public void setIssn(String issn) {
	this.issn = issn;
    }

    public String getFormat() {
	return format;
    }

    public void setFormat(String format) {
	this.format = format;
    }

    public int getPageCount() {
	return pageCount;
    }

    public void setPageCount(int pageCount) {
	this.pageCount = pageCount;
    }

    public String getResourceURI() {
	return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
	this.resourceURI = resourceURI;
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
