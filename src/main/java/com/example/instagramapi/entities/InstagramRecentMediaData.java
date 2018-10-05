package com.example.instagramapi.entities;

import java.io.Serializable;

public class InstagramRecentMediaData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3790928049041595196L;
	private InstagramRecentMediaImage images;
	private String link;
	
	public InstagramRecentMediaImage getImages() {
		return images;
	}
	public void setImages(InstagramRecentMediaImage images) {
		this.images = images;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
