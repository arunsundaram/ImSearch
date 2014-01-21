package com.gocoder.arun.imsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchSettingsActivity extends Activity {
	Spinner etImageSize;
	Spinner etImageType;
	Spinner etColorFilter;
	EditText etSiteFilter;
	ArrayAdapter<String> imageSizeAdapter;
	ArrayAdapter<String> imageTypeAdapter;
	ArrayAdapter<String> colorFilterAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_settings);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setupViews();
		setFilters();
	}

	private void setFilters() {
		ImageSearchSettings settings = ImageSearchSettings.getInstance();
		int pos;
		if (!settings.imageSize.isEmpty()){
			pos = settings.getImageSizeList().indexOf(settings.imageSize);
			etImageSize.setSelection(pos);
		}
		if(!settings.imageType.isEmpty()){
			pos = settings.getImageTypeList().indexOf(settings.imageType);
			etImageType.setSelection(pos);
		}
		if(!settings.colorFilter.isEmpty()){
			pos = settings.getColorFilterList().indexOf(settings.colorFilter);
			etColorFilter.setSelection(pos);
		}
		if(!settings.sitefilter.isEmpty()){
			etSiteFilter.setText(settings.sitefilter);
		}
	}
	private void updateFilters(){
		String imageType = String.valueOf(etImageType.getSelectedItem());
		String imageSize = String.valueOf(etImageSize.getSelectedItem());
		String colorFilter =  String.valueOf(etColorFilter.getSelectedItem());
		ImageSearchSettings settings = ImageSearchSettings.getInstance();
		settings.imageType = imageType.equals("select")? "" : imageType;
		settings.imageSize =  imageSize.equals("select")? "" : imageSize;
		settings.colorFilter =  colorFilter.equals("select")? "" : colorFilter;
		settings.sitefilter = etSiteFilter.getText().toString();
	}

	private void setupViews() {
		etImageSize = (Spinner) findViewById(R.id.val_imagesize);
		etImageType = (Spinner) findViewById(R.id.val_imagetype);
		etColorFilter = (Spinner) findViewById(R.id.val_colorfilter);
		etSiteFilter = (EditText) findViewById(R.id.val_sitefilter);
		ImageSearchSettings settings = ImageSearchSettings.getInstance();
		imageSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,settings.getImageSizeList());
		imageSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		etImageSize.setAdapter(imageSizeAdapter);
		imageTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,settings.getImageTypeList());
		imageTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		etImageType.setAdapter(imageTypeAdapter);
		colorFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,settings.getColorFilterList());
		colorFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		etColorFilter.setAdapter(colorFilterAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_settings, menu);
		return true;
	}
	
	public void onSaveSettings(View v){
		updateFilters();
		Intent data = new Intent();
		setResult(RESULT_OK, data);
		finish();
	}

}
