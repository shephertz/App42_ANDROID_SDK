package com.app42.api.test;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.user.User;

public class MainActivity extends Activity implements
		AsyncApp42ServiceApi.App42ServiceListener {

	private AsyncApp42ServiceApi asyncService;

	private SharedPreferences mPrefs;
	private ProgressDialog progressDialog;
	// private String userName=null;

	private String userName = "userNameAks" + new Date().getTime();
	private String password = "pswd";
	private String email = "userNameAks" + new Date().getTime() + "@gmail.com";

	/** Called when the activity is first created. **/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mPrefs = getSharedPreferences(Constants.TicTacToePref, MODE_PRIVATE);
		asyncService = AsyncApp42ServiceApi.instance();
	}

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void onStart() {
		super.onStart();
	}

	public void onSigninClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "signing in..");
		progressDialog.setCancelable(true);
		asyncService.authenticateUser(userName, password, this);
	}

	public void onNextClick(View view) {
		Intent mainIntent = new Intent(this, SecondActivity.class);
		mainIntent.putExtra(Constants.IntentUserName, userName);
		this.startActivity(mainIntent);
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
	protected void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onCreationFailed(App42Exception exception) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		// Toast.makeText(this, "User creation failed.", Toast.LENGTH_SHORT)
		// .show();
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
		AlertDialog.Builder alertbox = new AlertDialog.Builder(
				MainActivity.this);
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
