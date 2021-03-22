package com.bluet.bring.getnewsrest.models;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class News {

	private String id;	
    private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private Instant publishedAt;
	private String content;	
	private Source source;

	public News() {

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public Instant getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Instant publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

/*
 * @Document public class News {
 * 
 * @Id private Long id; private String title; private String shortDescription;
 * private Instant createdAt; private String imgUrl; private String source;
 * private String tag; private String timeofReading; private String
 * relatedPosts; private String publicBy;
 * 
 * 
 * public String getTag() { return tag; }
 * 
 * public void setTag(String tag) { this.tag = tag; }
 * 
 * public News() {
 * 
 * }
 * 
 * public String getTitle() { return title; }
 * 
 * public void setTitle(String title) { this.title = title; }
 * 
 * public String getShortDescription() { return shortDescription; }
 * 
 * public void setShortDescription(String shortDescription) {
 * this.shortDescription = shortDescription; }
 * 
 * public Long getId() { return id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * 
 * 
 * public String getImgUrl() { return imgUrl; }
 * 
 * public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
 * 
 * public String getSource() { return source; }
 * 
 * public void setSource(String source) { this.source = source; }
 * 
 * public Instant getCreatedAt() { return createdAt; }
 * 
 * public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; } }
 * 
 */