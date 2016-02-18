/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.ma.sample;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shephertz.app42.iam.App42CampaignAPI;
import com.shephertz.app42.iam.ui.ViralityActivity;
import com.shephertz.app42.iam.utils.InAppPreferences;
import com.shephertz.app42.ma.sample.App42Event.App42EventListener;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.App42Response;
import com.shephertz.app42.paas.sdk.android.event.RewardsPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class SampleActivity.
 */
public class ViralitySample extends Activity implements App42EventListener {

	/** The result tv. */
	private TextView resultTv;

	/** The edit user id. */
	private EditText editEventNAme;
	
	/** The edit camp name. */
	private EditText editCampName;


	/** The app42 event. */
	private App42Event app42Event;
	
	/** The my rewards. */
	private ArrayList<RewardsPoint> myRewards;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		setContentView(R.layout.virality_test);
		resultTv = (TextView) findViewById(R.id.result);
		editEventNAme = (EditText) findViewById(R.id.editActivtyEventName);
		editCampName = (EditText) findViewById(R.id.editCampaignName);
		app42Event = new App42Event(this);
	
  }

	
	/**
	 * On track event.
	 * 
	 * @param view
	 *            the view
	 * @throws JSONException
	 *             the JSON exception
	 */
	public void onTrackEvent(View view) throws JSONException {

		JSONObject json = new JSONObject();
		json.put("company", "shePhertz");
		resultTv.setText("Tracking Event....");
		String eventName = editEventNAme.getText().toString();
		if (eventName == null || eventName.equals(""))
			Toast.makeText(this, "Event Name Should not be blank",
					Toast.LENGTH_SHORT).show();
		else
			app42Event.trackEvent(eventName, json);

	}
	
	/**
	 * On next.
	 *
	 * @param view the view
	 */
	public void onNext(View view){
//		Intent intent=new Intent(this,EventActivity.class);
//		startActivity(intent);
		InAppPreferences.instance().saveReferralDetails("ghe");
	}

	/**
	 * On start activity.
	 * 
	 * @param view
	 *            the view
	 */
	public void onShareApp(View view) {
		if (App42CampaignAPI.isViralityAvailable()) {
			Intent intent = new Intent(this, ViralityActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Not Campign Available", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * On get active camp.
	 *
	 * @param view the view
	 */
	public void onGetActiveCamp(View view) {
      final ArrayList<String> list=App42CampaignAPI.getActiveViralityCampaigns();
      runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// System.out.println(response.toString());
				for(String str:list)
				resultTv.setText("Camp : " + str+"\n");
			}
		});
      
	}
	
	/**
	 * Do install.
	 *
	 * @param view the view
	 */
	public void doInstall(View view){
		App42CampaignAPI.doReferRewardAction();
	}

	/**
	 * On redeem rewards.
	 *
	 * @param view the view
	 */
	public void onRedeemRewards(View view) {
		
		if(myRewards!=null&&myRewards.size()!=0){
			RewardsPoint point=myRewards.get(0);
			App42CampaignAPI.redeemRewardForUser(App42API.getLoggedInUser(), point.getRewardPoints(), point.getRewardUnit(), point.getCampaignName(), new App42CallBack() {
				
				@Override
				public void onSuccess(Object response) {
					// TODO Auto-generated method stub
					onResult((App42Response) response);
				}
				
				@Override
				public void onException(Exception ex) {
					// TODO Auto-generated method stub
					onError(ex);
				}
			});
		}
		else{
			resultTv.setText("No Rewards To Redeen : \n");
		}
     
	}

	/**
	 * On end activity.
	 * 
	 * @param view
	 *            the view
	 */
	public void onGetUserRewards(View view) {
		String campName = editCampName.getText().toString();
		if (campName == null || campName.equals(""))
			Toast.makeText(this, "Campaign Name Should not be blank",
					Toast.LENGTH_SHORT).show();
		else{
		App42CampaignAPI.getRewardOfUser(App42API.getLoggedInUser(), campName, new App42CallBack() {
			
			@Override
			public void onSuccess(Object response) {
				// TODO Auto-generated method stub
				ArrayList<RewardsPoint> app42Response=(ArrayList<RewardsPoint>) response;
				showReward(app42Response);
			}
			
			@Override
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
				onError(ex);
			}
		});
		}
	}
	
	/**
	 * Show reward.
	 *
	 * @param app42Response the app42 response
	 */
	private void showReward(final ArrayList<RewardsPoint> app42Response){
		myRewards=app42Response;
		  runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// System.out.println(response.toString());
					for(RewardsPoint str:app42Response)
					resultTv.setText(str.getStrResponse());
				}
			});
	}

	/**
	 * On result.
	 * 
	 * @param response
	 *            the response
	 */
	public void onResult(final App42Response response) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// System.out.println(response.toString());
				resultTv.setText("App42Response : " + response);
			}
		});

	}

	/**
	 * On error.
	 * 
	 * @param e
	 *            the e
	 */
	public void onError(final Exception e) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resultTv.setText(e.getMessage());
			}
		});
	}
}
