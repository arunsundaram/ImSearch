package com.gocoder.arun.imsearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable{
	private static final long serialVersionUID = 2037707570733729497L;
	private String fullUrl;
	private String thumbUrl;
	
	public ImageResult(JSONObject json) {
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	
	@Override
	public String toString() {
		return  thumbUrl;
	}
	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray imageresultsjson) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for(int i=0;i<imageresultsjson.length();i++){
			try {
				results.add(new ImageResult(imageresultsjson.getJSONObject(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;
	}
	

}
