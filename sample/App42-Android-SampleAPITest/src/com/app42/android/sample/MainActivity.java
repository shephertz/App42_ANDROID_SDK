/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.app42.android.sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.app42.api.test.R;




/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity  {
	
	/** The user name. */
	private String userName = "userNameAks" + new Date().getTime();
	

	/**
	 *  Called when the activity is first created. *
	 *
	 * @param savedInstanceState the saved instance state
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	public void onStart() {
		super.onStart();
	}

	/**
	 * On user clicked.
	 *
	 * @param view the view
	 */
	public void onUserClicked(View view) {
		Intent mainIntent = new Intent(this, UserSample.class);
		mainIntent.putExtra(Constants.IntentUserName, userName);
		this.startActivity(mainIntent);
	}

	/**
	 * On storage clicked.
	 *
	 * @param view the view
	 */
	public void onStorageClicked(View view) {
		Intent mainIntent = new Intent(this, StorageSample.class);
		mainIntent.putExtra(Constants.IntentUserName, userName);
		this.startActivity(mainIntent);
	}

	/**
	 * On upload clicked.
	 *
	 * @param view the view
	 */
	public void onUploadClicked(View view) {
		Intent mainIntent = new Intent(this, UploadSample.class);
		mainIntent.putExtra(Constants.IntentUserName, userName);
		this.startActivity(mainIntent);
	}
	
	/**
	 * On leaderboard clicked.
	 *
	 * @param view the view
	 */
	public void onLeaderboardClicked(View view) {
		Intent mainIntent = new Intent(this, LeaderboardSample.class);
		mainIntent.putExtra(Constants.IntentUserName, userName);
		this.startActivity(mainIntent);
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
	 
 	/**
 	 * Gets the bitmap from assets.
 	 *
 	 * @return the bitmap from assets
 	 */
 	public Bitmap getBitmapFromAssets() {
         AssetManager assetManager = getAssets();
         InputStream istr;
         try {
                 istr = assetManager.open("ch.png");
                 Bitmap bitmap = BitmapFactory.decodeStream(istr);
                 return bitmap;
         } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
                 return null;
         }

 }
}
