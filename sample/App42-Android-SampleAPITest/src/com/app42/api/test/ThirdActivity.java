package com.app42.api.test;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.upload.Upload;
import com.shephertz.app42.paas.sdk.android.upload.UploadFileType;

public class ThirdActivity extends Activity implements
AsyncApp42ServiceApi.App42UploadServiceListener {

	private AsyncApp42ServiceApi asyncService;

	private ProgressDialog progressDialog;
	
	private String fileName = "IMAGE" + new Date().getTime();
	private String filePath = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcR1feTB7e8V1gANFr-K97h2thKR2461kOtO8xSob5j5PsS24d3y6A";
	private String Description = "This Is IMAGE File";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_layout);
		asyncService = AsyncApp42ServiceApi.instance();
	}
	
	public void onNextClick(View view) {
		Intent mainIntent = new Intent(this, FourthActivity.class);
	//	mainIntent.putExtra(Constants.IntentUserName, userName);
		this.startActivity(mainIntent);
	}
	
	public void onUploadFileClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Please Wait.. Uploading...");
		progressDialog.setCancelable(true);
		asyncService.uploadPic(fileName, "http://10.0.2.2/ch.png", UploadFileType.IMAGE, Description, this);
	}
	
	
	public void onGetFilesClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Getting..");
		progressDialog.setCancelable(true);
		asyncService.getPics(this);
	}
	
	@Override
	public void onUploadPicSuccess(Upload response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("SUccessFully Uploaded : "+ response);
	}


	@Override
	public void onUploadPicFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : "+ ex.getMessage());
	}


	@Override
	public void onGetPicsSuccess(Upload response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("SuccessFully Fetched : "+ response);
	}


	@Override
	public void onGetPicsFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : "+ ex.getMessage());
	}
	
	public void createAlertDialog(String msg) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(
				ThirdActivity.this);
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
