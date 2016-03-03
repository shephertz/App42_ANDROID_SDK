/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.ma.sample;

import org.json.JSONObject;

import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.App42Response;

// TODO: Auto-generated Javadoc
/**
 * The Class App42Event.
 *
 * @author Vishnu Garg
 */
public class App42Event {

	/** The event listener. */
	private App42EventListener eventListener;
	
	/**
	 * Instantiates a new app42 event.
	 *
	 * @param eventListener the event listener
	 */
	public App42Event(App42EventListener eventListener) {
		super();
		this.eventListener = eventListener;  
	}


	/**
 * Start activity event.
 *
 * @param activtyEventName the activty event name
 * @param properties the properties
 */
	public void startActivityEvent(final String activtyEventName,JSONObject properties) {
		App42API.buildEventService().startActivity(activtyEventName,
				properties, new App42CallBack() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						eventListener.onResult((App42Response) response);
						
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}


	/**
 * Track event.
 *
 * @param trackEventName the track event name
 * @param properties the properties
 */
public void trackEvent(final String trackEventName,JSONObject properties) {
		App42API.buildEventService().trackEvent(trackEventName,
				properties, new App42CallBack() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						eventListener.onResult((App42Response) response);
					
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	/**
	 * End activity event.
	 *
	 * @param activtyEventName the activty event name
	 * @param properties the properties
	 */
	public void endActivityEvent(final String activtyEventName,JSONObject properties) {

		App42API.buildEventService().endActivity(activtyEventName,
				properties, new App42CallBack() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						eventListener.onResult((App42Response) response);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	/**
	 * Update user props.
	 *
	 * @param newProperties the new properties
	 */
	public void updateUserProps(JSONObject newProperties) {
		App42API.buildEventService().updateLoggedInUserProperties(
				newProperties, new App42CallBack() {
					@Override
					public void onSuccess(Object response) {

						eventListener.onResult((App42Response) response);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	/**
	 * Sets the user props.
	 *
	 * @param newProperties the new user props
	 */
	public void setUserProps(JSONObject newProperties) {
		App42API.buildEventService().setLoggedInUserProperties(newProperties,
				new App42CallBack() {
					@Override
					public void onSuccess(Object response) {

						eventListener.onResult((App42Response) response);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	/**
	 * The listener interface for receiving app42Event events.
	 * The class that is interested in processing a app42Event
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addApp42EventListener<code> method. When
	 * the app42Event event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see App42EventEvent
	 */
	public interface App42EventListener {
		
		/**
		 * On result.
		 *
		 * @param response the response
		 */
		public void onResult(App42Response response);

		/**
		 * On error.
		 *
		 * @param e the e
		 */
		public void onError(Exception e);
	}
}
