package com.example.instagramapi.entities;

import java.io.Serializable;

public class InstagramRecentMediaImage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2627425377842744573L;
	private InstagramRecentMediaImageStandartRes standard_resolution;
	private String link;

	public InstagramRecentMediaImageStandartRes getStandard_resolution() {
		return standard_resolution;
	}

	public void setStandard_resolution(InstagramRecentMediaImageStandartRes standard_resolution) {
		this.standard_resolution = standard_resolution;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
