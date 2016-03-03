/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
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

/**
 * The Class UserSample.
 */
public class UserSample extends Activity implements
AsyncApp42ServiceApi.App42UserServiceListener {
	
	/** The async service. */
	private AsyncApp42ServiceApi asyncService;
	
	/** The progress dialog. */
	private ProgressDialog progressDialog;
	
	/** The user name. */
	private String userName = "userNameAks" + new Date().getTime();
	
	/** The password. */
	private String password = "pswd";
	
	/** The email. */
	private String email = "userNameAks" + new Date().getTime() + "@gmail.com";
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		asyncService = AsyncApp42ServiceApi.instance(this);
	}

	/**
	 * On previous clicked.
	 *
	 * @param view the view
	 */
	public void onPreviousClicked(View view) {
		Intent mainIntent = new Intent(this, MainActivity.class);
		this.startActivity(mainIntent);
	}
	
	/**
	 * On next clicked.
	 *
	 * @param view the view
	 */
	public void onNextClicked(View view) {
		Intent mainIntent = new Intent(this, StorageSample.class);
		this.startActivity(mainIntent);
	}
	
	/**
	 * On signin clicked.
	 *
	 * @param view the view
	 */
	public void onSigninClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "signing in..");
		progressDialog.setCancelable(true);
		asyncService.authenticateUser(userName, password, this);
	}

	/**
	 * On register clicked.
	 *
	 * @param view the view
	 */
	public void onRegisterClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "registering..");
		progressDialog.setCancelable(true);
		asyncService.createUser(userName, password, email, this);
	}

	/**
	 * On get user clicked.
	 *
	 * @param view the view
	 */
	public void onGetUserClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Getting..");
		progressDialog.setCancelable(true);
		asyncService.getUser(userName, this);
	}

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42UserServiceListener#onUserCreated(com.shephertz.app42.paas.sdk.android.user.User)
	 */
	@Override
	public void onUserCreated(User user) {
		progressDialog.dismiss();
		createAlertDialog("User Created :    UserName -> "
				+ user.getUserName().toString() + "  Email ID -> "
				+ user.getEmail());
	}

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42UserServiceListener#onUserAuthenticated(com.shephertz.app42.paas.sdk.android.user.User)
	 */
	@Override
	public void onUserAuthenticated(User response) {
		progressDialog.dismiss();
		createAlertDialog("SuccessFully Authenticated : UserName Is -> "
				+ response.getUserName().toString());
	}

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42UserServiceListener#onCreationFailed(com.shephertz.app42.paas.sdk.android.App42Exception)
	 */
	@Override
	public void onCreationFailed(App42Exception exception) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog(exception.getMessage());
	}

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42UserServiceListener#onAuthenticationFailed(com.shephertz.app42.paas.sdk.android.App42Exception)
	 */
	@Override
	public void onAuthenticationFailed(App42Exception exception) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog(exception.getMessage());
	}

	/**
	 * Creates the alert dialog.
	 *
	 * @param msg the msg
	 */
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

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42UserServiceListener#onGetUserSuccess(com.shephertz.app42.paas.sdk.android.user.User)
	 */
	@Override
	public void onGetUserSuccess(User response) {
		// TODO Auto-generated method stub
		String userName = response.getUserName();
		String email = response.getEmail();
		progressDialog.dismiss();
		createAlertDialog("User Name Is : " + userName + "EmailId Is : "
				+ email);
	}

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42UserServiceListener#onGetUserFailed(com.shephertz.app42.paas.sdk.android.App42Exception)
	 */
	@Override
	public void onGetUserFailed(App42Exception exception) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog(exception.getMessage());
	}

}