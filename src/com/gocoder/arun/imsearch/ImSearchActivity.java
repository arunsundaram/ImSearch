package com.gocoder.arun.imsearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImSearchActivity extends Activity {
	EditText etsearch ;
	Button btnsearch;
	GridView imresults;
	Button btnmoreres;
	ArrayList<ImageResult> images = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageResultAdapter;
	private int start_index=0;
	private final int RESULTS_PER_PAGE = 8;
	private final int REQUESTCODE_SETTINGS = 1;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_search);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setupViews();
        imageResultAdapter = new ImageResultArrayAdapter(this, images);
        imresults.setAdapter(imageResultAdapter);
        imresults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long rowId) {
				Intent i = new Intent(getApplicationContext(),ImageDisplayActivity.class);
				ImageResult imageResult = images.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
		});

    }

    private void setupViews() {
    	etsearch = (EditText) findViewById(R.id.etSearch);
    	btnsearch = (Button) findViewById(R.id.btnSearch);
    	imresults = (GridView) findViewById(R.id.imresults);
    	btnmoreres = (Button) findViewById(R.id.more_results);
    	btnmoreres.setVisibility(1);
		
	}
    private String getRequest(){
    	String query = etsearch.getText().toString();
    	ImageSearchSettings settings = ImageSearchSettings.getInstance();
    	String req = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0";
    	req += "&q="+Uri.encode(query);
    	req +=  "&start="+start_index;
    	if(!settings.imageSize.isEmpty()){
    		req += "&imgsz="+Uri.encode(settings.imageSize);
    	}
    	if(!settings.colorFilter.isEmpty()){
    		req += "&imgcolor="+Uri.encode(settings.colorFilter);
    	}
    	if(!settings.imageType.isEmpty()){
    		req += "&imgtype="+Uri.encode(settings.imageType);
    	}
    	if(!settings.sitefilter.isEmpty()){
    		req += "&as_sitesearch="+Uri.encode(settings.sitefilter);
    	}
		return req;
    }
    
    
    private void doSearch(){
    	if(!etsearch.getText().toString().isEmpty()){
	    	AsyncHttpClient client = new AsyncHttpClient();
	    	client.get(getRequest(), new JsonHttpResponseHandler(){
	    		public void onSuccess(JSONObject response){
	    			JSONArray imageresultsjson = null;
	    			try {
						imageresultsjson = response.getJSONObject("responseData").getJSONArray("results");
						imageResultAdapter.addAll(ImageResult.fromJSONArray(imageresultsjson));
						btnmoreres.setVisibility(0);
					} catch (JSONException e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "Error retrieving results", Toast.LENGTH_LONG).show();
					}
	    		}
	    	});
    	}
	}
    
    public void onSearch(View v) {
    	if(etsearch.getText().toString().isEmpty()){
    		Toast.makeText(getApplicationContext(), "Please type a search query", Toast.LENGTH_SHORT).show();
    	}
    	else{
    		resetSearchContext();
	    	btnmoreres.setVisibility(1);
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(etsearch.getWindowToken(), 0);
	    	doSearch();
    	}
    }
    
    public void onSettingsClick(MenuItem mi){
    	Intent i = new Intent(this, SearchSettingsActivity.class);
    	startActivityForResult(i,REQUESTCODE_SETTINGS);
    	
    }
    public void onMoreResults(View v){
    	btnmoreres.setVisibility(1);
    	start_index += RESULTS_PER_PAGE;
    	doSearch();
    }
    private void resetSearchContext(){
    	images.clear();
    	start_index = 0;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	  if (resultCode == RESULT_OK && requestCode == REQUESTCODE_SETTINGS) {
 //   	     String name = data.getExtras().getString("name");
    		  resetSearchContext();
    		  btnmoreres.setVisibility(1);
    		  doSearch();
    	  }
   } 

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.im_search, menu);
        return true;
    }
    
}
