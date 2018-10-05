package com.example.instagramapi.entities;

import java.io.Serializable;
import java.util.List;

public class InstagramRecentMedia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3900879292598129419L;
	private List<InstagramRecentMediaData> data;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<InstagramRecentMediaData> getData() {
		return data;
	}

	public void setData(List<InstagramRecentMediaData> data) {
		this.data = data;
	}
}
