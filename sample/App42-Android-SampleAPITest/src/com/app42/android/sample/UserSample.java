package com.app42.android.sample;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app42.api.test.R;
import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.user.User;

public class UserSample extends Activity implements
AsyncApp42ServiceApi.App42UserServiceListener {
	
	private AsyncApp42ServiceApi asyncService;
	private ProgressDialog progressDialog;
	private String userName = "userNameAks" + new Date().getTime();
	private String password = "pswd";
	private String email = "userNameAks" + new Date().getTime() + "@gmail.com";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		asyncService = AsyncApp42ServiceApi.instance();
	}

	public void onPreviousClicked(View view) {
		Intent mainIntent = new Intent(this, MainActivity.class);
		this.startActivity(mainIntent);
	}
	
	public void onNextClicked(View view) {
		Intent mainIntent = new Intent(this, StorageSample.class);
		this.startActivity(mainIntent);
	}
	
	public void onSigninClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "signing in..");
		progressDialog.setCancelable(true);
		asyncService.authenticateUser(userName, password, this);
	}

	public void onRegisterClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "registering..");
		progressDialog.setCancelable(true);
		asyncService.createUser(userName, password, email, this);
	}

	public void onGetUserClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Getting..");
		progressDialog.setCancelable(true);
		asyncService.getUser(userName, this);
	}

	@Override
	public void onUserCreated(User user) {
		progressDialog.dismiss();
		createAlertDialog("User Created :    UserName -> "
				+ user.getUserName().toString() + "  Email ID -> "
				+ user.getEmail());
	}

	@Override
	public void onUserAuthenticated(User response) {
		progressDialog.dismiss();
		createAlertDialog("SuccessFully Authenticated : UserName Is -> "
				+ response.getUserName().toString());
	}

	@Override
	public void onCreationFailed(App42Exception exception) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog(exception.getMessage());
	}

	@Override
	public void onAuthenticationFailed(App42Exception exception) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog(exception.getMessage());
	}

	/*
	 * used to create alert dialog.
	 */
	public void createAlertDialog(String msg) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(UserSample.this);
		alertbox.setTitle("Response Message");
		alertbox.setMessage(msg);
		alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			// do something when the button is clicked
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	@Override
	public void onGetUserSuccess(User response) {
		// TODO Auto-generated method stub
		String userName = response.getUserName();
		String email = response.getEmail();
		progressDialog.dismiss();
		createAlertDialog("User Name Is : " + userName + "EmailId Is : "
				+ email);
	}

	@Override
	public void onGetUserFailed(App42Exception exception) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog(exception.getMessage());
	}

}