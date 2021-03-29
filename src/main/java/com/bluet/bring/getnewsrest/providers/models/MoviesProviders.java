package com.bluet.bring.getnewsrest.providers.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class MoviesProviders {

	@Id
	@Field(name="_id")
	private long id;
	private String name;
	private String logoUrl ;
	
	public MoviesProviders() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPath() {
		return logoUrl;
	}

	public void setImgPath(String logoUrl) {
		this.logoUrl = logoUrl;
	}
}
