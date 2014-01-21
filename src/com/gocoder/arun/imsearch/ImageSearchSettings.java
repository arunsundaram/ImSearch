package com.gocoder.arun.imsearch;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageSearchSettings implements Serializable{

	private static final long serialVersionUID = -764327439985079877L;
	private static ImageSearchSettings instance = null;
	private ArrayList<String> imageSizeList ;
	private ArrayList<String> imageTypeList ;
	private ArrayList<String> colorFilterList ;
	

	public String imageSize;
	public String colorFilter;
	public String imageType;
	public String sitefilter;
	
	private ImageSearchSettings() {
		imageSize = "";
		colorFilter = "";
		imageType = "";
		sitefilter = "";
		imageSizeList = new ArrayList<String>();
		imageSizeList.add("select");
		imageSizeList.add("small");
		imageSizeList.add("medium");
		imageSizeList.add("large");
		imageSizeList.add("xlarge");
		imageSizeList.add("xxlarge");
		imageSizeList.add("huge");
		imageSizeList.add("icon");
		imageTypeList = new ArrayList<String>();
		imageTypeList.add("select");
		imageTypeList.add("face");
		imageTypeList.add("photo");
		imageTypeList.add("clipart");
		imageTypeList.add("lineart");
		colorFilterList = new ArrayList<String>();
		colorFilterList.add("select");
		colorFilterList.add("blue");
		colorFilterList.add("black");
		colorFilterList.add("brown");
		colorFilterList.add("gray");
		colorFilterList.add("green");
		colorFilterList.add("orange");
		colorFilterList.add("pink");
		colorFilterList.add("purple");
		colorFilterList.add("red");
		colorFilterList.add("teal");
		colorFilterList.add("white");
		colorFilterList.add("yellow");
	}
	
	public static ImageSearchSettings getInstance(){
		if(instance == null){
			instance = new ImageSearchSettings();
		}
		return instance;
	}

	public ArrayList<String> getImageSizeList() {
		return imageSizeList;
	}

	public ArrayList<String> getImageTypeList() {
		return imageTypeList;
	}

	public ArrayList<String> getColorFilterList() {
		return colorFilterList;
	}

}
