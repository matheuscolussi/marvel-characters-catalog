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

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "image_id")
	private Image thumbnail;

	@ManyToMany(mappedBy = "comics")
	@JsonBackReference
	private List<Character> characters;

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

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

//	private GenericSummary series;
//	private GenericList creators;

//	@ManyToMany(mappedBy = "stories")
//	private Set<Story> stories;
//	
//	@ManyToMany(mappedBy = "events")
//	private Set<Event> events;

//	@ManyToMany(mappedBy = "comics")
//	private Set<TextObject> textObjects;

//	@OneToMany
//	@JoinColumn(name = "url_id")
//	private Set<Url> urls;

//	Comic {
//		id (int, optional): The unique ID of the comic resource.,
//		digitalId (int, optional): The ID of the digital comic representation of this comic. Will be 0 if the comic is not available digitally.,
//		title (string, optional): The canonical title of the comic.,
//		issueNumber (double, optional): The number of the issue in the series (will generally be 0 for collection formats).,
//		variantDescription (string, optional): If the issue is a variant (e.g. an alternate cover, second printing, or director’s cut), a text description of the variant.,
//		description (string, optional): The preferred description of the comic.,
//		modified (Date, optional): The date the resource was most recently modified.,
//		isbn (string, optional): The ISBN for the comic (generally only populated for collection formats).,
//		upc (string, optional): The UPC barcode number for the comic (generally only populated for periodical formats).,
//		diamondCode (string, optional): The Diamond code for the comic.,
//		ean (string, optional): The EAN barcode for the comic.,
//		issn (string, optional): The ISSN barcode for the comic.,
//		format (string, optional): The publication format of the comic e.g. comic, hardcover, trade paperback.,
//		pageCount (int, optional): The number of story pages in the comic.,
//		textObjects (Array[TextObject], optional): A set of descriptive text blurbs for the comic.,
//		resourceURI (string, optional): The canonical URL identifier for this resource.,
//		urls (Array[Url], optional): A set of public web site URLs for the resource.,
//		series (SeriesSummary, optional): A summary representation of the series to which this comic belongs.,
//		variants (Array[ComicSummary], optional): A list of variant issues for this comic (includes the "original" issue if the current issue is a variant).,
//		collections (Array[ComicSummary], optional): A list of collections which include this comic (will generally be empty if the comic's format is a collection).,
//		collectedIssues (Array[ComicSummary], optional): A list of issues collected in this comic (will generally be empty for periodical formats such as "comic" or "magazine").,
//		dates (Array[ComicDate], optional): A list of key dates for this comic.,
//		prices (Array[ComicPrice], optional): A list of prices for this comic.,
//		thumbnail (Image, optional): The representative image for this comic.,
//		images (Array[Image], optional): A list of promotional images associated with this comic.,
//		creators (CreatorList, optional): A resource list containing the creators associated with this comic.,
//		characters (CharacterList, optional): A resource list containing the characters which appear in this comic.,
//		stories (StoryList, optional): A resource list containing the stories which appear in this comic.,
//		events (EventList, optional): A resource list containing the events in which this comic appears.
//		}
}
