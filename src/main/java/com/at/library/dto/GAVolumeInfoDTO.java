package com.at.library.dto;

import java.io.Serializable;
import java.util.HashMap;

public class GAVolumeInfoDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -251594787778525544L;

	private String publishedDate;
	
	private String description;
	
	private HashMap<String, String> imageLinks = new HashMap<String, String>();

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, String> getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(HashMap<String, String> imageLinks) {
		this.imageLinks = imageLinks;
	}

	@Override
	public String toString() {
		return "GAVolumeInfoDTO [publishedDate=" + publishedDate + ", description=" + description + ", imageLinks="
				+ imageLinks + "]";
	}
}
