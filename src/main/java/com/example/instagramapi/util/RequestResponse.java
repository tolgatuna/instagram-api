package com.example.instagramapi.util;

public class RequestResponse {
	
	String responseString;
	public RequestResponse(String responseAsString) {
		this.responseString = responseAsString;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
}
