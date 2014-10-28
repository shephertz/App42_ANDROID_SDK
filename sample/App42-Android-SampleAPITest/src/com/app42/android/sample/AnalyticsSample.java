package com.app42.android.sample;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import com.app42.android.sample.AsyncApp42ServiceApi.App42SampleCallbackListener;
import com.app42.api.test.R;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.App42Response;
import com.shephertz.app42.paas.sdk.android.event.EventService;

public class AnalyticsSample extends Activity implements App42SampleCallbackListener{
	private TextView resultTv;
	private String userName;
	private String trackEventName;
	private String activtyEventName;
	private String IsFirstTime = "isFirstTime";
	private String EventName = "trackEvent";
	private String AppUserName = "appUserName";
	private String EventNameActivity = "activtyEvent";
	private SharedPreferences mApp42Preference;
	private static final String PrefName = "sampleAnalytics";
	private EditText editUsername, editEventNAme, editActivtyName;
	private AsyncApp42ServiceApi app42Serveice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		setContentView(R.layout.analytics_sample);
		resultTv = (TextView) findViewById(R.id.textView1);
		mApp42Preference = getSharedPreferences(PrefName,
				android.content.Context.MODE_PRIVATE);
		app42Serveice=AsyncApp42ServiceApi.instance(this);
		intializeViews();
		if (isSaved()) {
			fetchDetails();
		} else {
			SaveFetchDetails();
		}
	
		setUserName();
	}

	/**
	 * 
	 */
	private void setUserName() {
		App42API.setLoggedInUser(userName);
	}

	/**
	 * 
	 */
	private void intializeViews() {
		editUsername = (EditText) findViewById(R.id.editUser);
		editEventNAme = (EditText) findViewById(R.id.editTrackEventName);
		editActivtyName = (EditText) findViewById(R.id.editActivtyEventName);
	}

	/**
	 * 
	 */
	private void fetchDetails() {
		userName = mApp42Preference.getString(AppUserName, "Ajay");
		trackEventName = mApp42Preference.getString(EventName, "hurdleClear");
		activtyEventName = mApp42Preference.getString(EventNameActivity,
				"Level2");
		editUsername.setText(userName);
		editEventNAme.setText(trackEventName);
		editActivtyName.setText(activtyEventName);
	}

	/**
	 * 
	 */
	private void SaveFetchDetails() {
		userName = editUsername.getText().toString();
		trackEventName = editEventNAme.getText().toString();
		activtyEventName = editActivtyName.getText().toString();
		SharedPreferences.Editor localEditor = mApp42Preference.edit();
		localEditor.putString(AppUserName, userName);
		localEditor.putString(EventName, trackEventName);
		localEditor.putString(EventNameActivity, activtyEventName);
		localEditor.putBoolean(IsFirstTime, true);
		localEditor.commit();
	}

	/**
	 * @return
	 */
	private boolean isSaved() {
		return mApp42Preference.getBoolean(IsFirstTime, false);
	}
	/**
	 * @param view
	 */
	public void onSaveDetails(View view) {
		SaveFetchDetails();
		setUserName();
	}

	/**
	 * @param view
	 */
	public void onSetProperties(View view) {
		resultTv.setText("Setting UserProperties....");
		app42Serveice.setUserProperties(getUserProperties(), this);
	}

	/**
	 * @param view
	 */
	public void onUpdateProperties(View view) {
		resultTv.setText("Updating UserProperties....");
		app42Serveice.updateUserProperties(getUpdatedUserProperties(), this);
	}

	/**
	 * @param view
	 */
	public void onTrackEvent(View view) {
		resultTv.setText("Tracking Event....");
		app42Serveice.trackEvent(trackEventName, getEventProperties(), this);
	}

	/**
	 * @param view
	 */
	public void onStartActivity(View view) {
		resultTv.setText("Tracking Level Start....");
		app42Serveice.trackStartLevel(activtyEventName, getLevelProperties(), this);
	}

	/**
	 * @param view
	 */
	public void onEndActivity(View view) {
		resultTv.setText("Tracking Lavel End....");
		app42Serveice.trackEndLevel(activtyEventName, getLevelProperties(), this);
	}

	/**
	 * @return
	 */
	private JSONObject getUpdatedUserProperties() {
		JSONObject userProps = new JSONObject();
		try {
			userProps.put("socialPlatform", "Twitter");
			userProps.put("subscribeNewsLetter", "Yes");
			userProps.put("polled", "Yes");
			userProps.put("appSubscription", "Free");
			userProps.put("referredUsers", 50);
			userProps.put("searchPerformed", 75);
			userProps.put("gamePoints", 500);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userProps;
	}

	/**
	 * @return
	 */
	private JSONObject getUserProperties() {
		JSONObject userProps = new JSONObject();
		try {
			userProps.put("revenue", 10000);
			userProps.put("age", 25);
			userProps.put("browser", "firefox");
			userProps.put("subscriptionType", "Free");
			userProps.put("socialPlatform", "Facebook");
			userProps.put("networkType", "Wifi");
			userProps.put("ViewedSales", "Yes");
			userProps.put("subscribeNewsLetter", "No");
			userProps.put("polled", "No");
			userProps.put("gameType", "Action");
			userProps.put("appSubscription", "Paid");
			userProps.put("referredUsers", 20);
			userProps.put("searchPerformed", 45);
			userProps.put("gamePoints", 100);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userProps;
	}

	/**
	 * @return
	 */
	private JSONObject getLevelProperties() {
		JSONObject levelProps = new JSONObject();
		try {
			levelProps.put("displayName", "Tiwariji");
			levelProps.put("country", "in");
			levelProps.put("longtitude", 28.123);
			levelProps.put("lattitude", -76.123);
			levelProps.put("levelPoints", 100);
			levelProps.put("levelRewardPoints", 50);
			levelProps.put("levelHigScore", 1000);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return levelProps;
	}
	/**
	 * @return
	 */
	private JSONObject getEventProperties() {
		JSONObject eventProps = new JSONObject();
		try {
			eventProps.put("hurdleType", "Rock");
			eventProps.put("noOfHurdlesCleared", 8);
			eventProps.put("noOfEnemykilled", 10);
			eventProps.put("lifeCosumed", 5);
			eventProps.put("lifeRemaining", 5);
			eventProps.put("bonusGet", 50);
			eventProps.put("levelCompleted", "45%");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eventProps;
	}

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42SampleCallbackListener#onApp42Result(com.shephertz.app42.paas.sdk.android.App42Response)
	 */
	@Override
	public void onApp42Result(final App42Response response) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resultTv.setText("App42Response : "
						+ response.isResponseSuccess());
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.app42.android.sample.AsyncApp42ServiceApi.App42SampleCallbackListener#onApp42Error(java.lang.Exception)
	 */
	@Override
	public void onApp42Error(final Exception e) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resultTv.setText(e.getMessage());
			}
		});
	}
}
