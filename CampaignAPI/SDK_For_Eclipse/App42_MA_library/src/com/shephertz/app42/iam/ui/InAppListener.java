/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;

import com.shephertz.app42.iam.App42InAppListener;
import com.shephertz.app42.iam.model.CustomDialogData;
import com.shephertz.app42.iam.model.InAppData;
import com.shephertz.app42.iam.model.InterstitialData;
import com.shephertz.app42.iam.utils.InAppDefines.InAppType;
import com.shephertz.app42.iam.utils.InAppUtils;
import com.shephertz.app42.paas.sdk.android.App42Log;
import com.shephertz.app42.paas.sdk.android.app.App42Application;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving inApp events. The class that is
 * interested in processing a inApp event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addInAppListener<code> method. When
 * the inApp event occurs, that object's appropriate
 * method is invoked.
 * 
 * @author Vishnu
 */
public class InAppListener implements App42InAppListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shephertz.app42.iam.App42InAppListener#onInAppReceived(com.shephertz
	 * .app42.iam.data.InAppData, com.shephertz.app42.iam.utils.InAppType)
	 */
	@Override
	public void onInAppReceived(final InAppData inAppData,
			final InAppType inAppType) {
		// TODO Auto-generated method stub
		final Activity currentActivity = App42Application.getCurrentActivity();
		currentActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (inAppType == InAppType.Alert) {
					new SimpleDialog(currentActivity, false, inAppData);
				} else if (inAppType == InAppType.Confirmation) {
					new SimpleDialog(currentActivity, true, inAppData);
				} else if (inAppType == InAppType.Survey) {
					new SurveyDialog(currentActivity)
							.showSurveyDialog(inAppData);
				} else if (inAppType == InAppType.Interstitial) {
					InterstitialData customData = (InterstitialData) inAppData;
					if (InAppUtils.isImageAvailable(customData
							.getBackgroundImage())) {
						InterstitialDialog interstitialDialog = new InterstitialDialog(
								currentActivity, true, inAppData);
						interstitialDialog.show();
					}
				} else if (inAppType == InAppType.Custom) {

					CustomDialogData messageData = (CustomDialogData) inAppData;
					if (!InAppUtils.isNullBlank(messageData
							.getBackGroundImage())) {
						Bitmap iconBitmap = InAppUtils
								.getImageBitMapFromCache(messageData
										.getBackGroundImage());
						if (iconBitmap != null) {
							new CustomDialog(currentActivity, inAppData);
						}
						else
							new CustomDialog(currentActivity, inAppData);
					} else {
						new CustomDialog(currentActivity, inAppData);
					}

				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shephertz.app42.iam.App42InAppListener#onCouponRedeemedFailed(java
	 * .lang.String, org.json.JSONObject)
	 */
	@Override
	public void onCouponRedeemedFailed(String couponCode,
			JSONObject couponProperties) {
		// TODO Auto-generated method stub
		App42Log.debug("InApp Redeeme Failed" + couponCode
				+ couponProperties.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shephertz.app42.iam.App42InAppListener#onCouponRedeemedSucceeded(
	 * java.lang.String, org.json.JSONObject)
	 */
	@Override
	public void onCouponRedeemedSucceeded(String couponCode,
			JSONObject couponProperties) {
		// TODO Auto-generated method stub
		App42Log.debug("InApp Redeeme Failed" + couponCode
				+ couponProperties.toString());
	}

}
