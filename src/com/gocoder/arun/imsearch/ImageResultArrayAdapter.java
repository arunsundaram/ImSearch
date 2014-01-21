package com.gocoder.arun.imsearch;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.image_result_item,images);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = this.getItem(position);
		SmartImageView smartImage ;
		if(convertView == null){
			LayoutInflater inflator = LayoutInflater.from(getContext());
			smartImage = (SmartImageView) inflator.inflate(R.layout.image_result_item,parent, false);
		}
		else{
			smartImage = (SmartImageView) convertView;
			smartImage.setImageResource(android.R.color.transparent);
		}
		smartImage.setImageUrl(imageInfo.getThumbUrl());
		return smartImage;
	}
	
	

}
