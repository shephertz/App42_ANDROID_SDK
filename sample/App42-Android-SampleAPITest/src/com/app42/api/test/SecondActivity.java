package com.app42.api.test;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.storage.Storage;

public class SecondActivity extends Activity implements
AsyncApp42ServiceApi.App42StorageServiceListener {

	private AsyncApp42ServiceApi asyncService;

	private ProgressDialog progressDialog;
	private String dbName = "queryTest";
	private String collectionName = "CollectioN";
	private String json = "{\"Name\":\"Nick\"}";
	private String newJson = "{\"Name\":\"Nick Pheonix\"}";
	private String docId = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//mPrefs = getSharedPreferences(Constants.TicTacToePref, MODE_PRIVATE);
		asyncService = AsyncApp42ServiceApi.instance();
	}
	
	public void onNextClick(View view) {
		Intent mainIntent = new Intent(this, ThirdActivity.class);
		this.startActivity(mainIntent);
	}
	
	public void onInsertClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Inserting..");
		progressDialog.setCancelable(true);
		asyncService.insertJSONDoc(dbName, collectionName, json, this);
	}
	
	
	public void onFindClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Searching..");
		progressDialog.setCancelable(true);
		asyncService.findDocByDocId(dbName, collectionName, docId, this);
	}
	
	public void onUpdateClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Updating..");
		progressDialog.setCancelable(true);
		asyncService.updateDocByKeyValue(dbName, collectionName, "Name", "Nick", newJson,  this);
	}
	
	@Override
	public void onDocumentInserted(Storage response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		String getJson = response.getJsonDocList().get(0).getJsonDoc();
		try{
		JSONObject json = new JSONObject(getJson);
		createAlertDialog("Document Inserted for key 'Name' with value: "+ json.get("Name"));
		 docId = response.getJsonDocList().get(0).getDocId();
		
		}catch(JSONException ex){
			
		}
		
	}

	@Override
	public void onInsertionFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : "+ ex.getMessage());
	}

	@Override
	public void onFindDocFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : "+ ex.getMessage());
	}

	@Override
	public void onFindDocSuccess(Storage response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Document SuccessFully Fetched : "+ response.getJsonDocList().get(0).getJsonDoc());
	}

	@Override
	public void onUpdateDocSuccess(Storage response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Document SuccessFully Updated : "+ response.getJsonDocList().get(0).getJsonDoc());
	}

	@Override
	public void onUpdateDocFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : "+ ex.getMessage());
	}

	public void createAlertDialog(String msg) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(
				SecondActivity.this);
		alertbox.setTitle("Response Message");
		alertbox.setMessage(msg);
		alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			// do something when the button is clicked
			public void onClick(DialogInterface arg0, int arg1) 
			{
			}
		});
		alertbox.show();
	}

}
