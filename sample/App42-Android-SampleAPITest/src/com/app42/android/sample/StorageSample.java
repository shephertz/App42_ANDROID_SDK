package com.app42.android.sample;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app42.api.test.R;
import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.storage.Storage;

public class StorageSample extends Activity implements
AsyncApp42ServiceApi.App42StorageServiceListener {

	private AsyncApp42ServiceApi asyncService;

	private ProgressDialog progressDialog;
	private String docId = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storage);
		asyncService = AsyncApp42ServiceApi.instance();
	}
	
	public void onPreviousClicked(View view) {
		Intent mainIntent = new Intent(this, UserSample.class);
		this.startActivity(mainIntent);
	}
	
	public void onNextClicked(View view) {
		Intent mainIntent = new Intent(this, UploadSample.class);
		this.startActivity(mainIntent);
	}
	
	public void onInsertClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Inserting..");
		progressDialog.setCancelable(true);
		JSONObject jsonToSave = new JSONObject();
		try {
			jsonToSave.put("Name", "Nick");
			jsonToSave.put("Age", 23);
			jsonToSave.put("Company", "Shephertz");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asyncService.insertJSONDoc(Constants.App42DBName, Constants.CollectionName, jsonToSave, this);
	}
	
	
	public void onFindClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Searching..");
		progressDialog.setCancelable(true);
		asyncService.findDocByDocId(Constants.App42DBName, Constants.CollectionName, docId, this);
	}
	
	public void onUpdateClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Updating..");
		progressDialog.setCancelable(true);
		JSONObject jsonToUpdate = new JSONObject();
		try {
			jsonToUpdate.put("Name", "Nick Pheonix");
			jsonToUpdate.put("Age", 24);
			jsonToUpdate.put("Company", "Shephertz");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asyncService.updateDocByKeyValue(Constants.App42DBName, Constants.CollectionName, "Name", "Nick", jsonToUpdate,  this);
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
				StorageSample.this);
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
