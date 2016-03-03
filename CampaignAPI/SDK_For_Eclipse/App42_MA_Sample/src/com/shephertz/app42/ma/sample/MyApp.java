/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.ma.sample;

import android.provider.Settings;

import com.shephertz.app42.iam.App42CampaignAPI;
import com.shephertz.app42.iam.ui.InAppListener;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42Log;
import com.shephertz.app42.paas.sdk.android.app.App42Application;

// TODO: Auto-generated Javadoc
/**
 * The Class MyApp.
 */
public class MyApp extends App42Application {
	
	/* (non-Javadoc)
	 * @see com.shephertz.app42.paas.sdk.android.app.App42Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		//My app Keys
		 App42API.initialize(
		   		 this,
		   		 "Your API Key",
		   		 "Your Secret Key");
		   		App42Log.setDebug(true);
		   		String userId=Settings.Secure.getString(
					getContentResolver(), "android_id");
		   		
		   		//Can change userId here
		   		App42API.setLoggedInUser(userId);
		   		App42CampaignAPI.initWithListener(new InAppListener(), 5);
		   		App42API.enableAppStateEventTracking(true);	
		   		App42API.enableCrashEventHandler(true);
	}
}