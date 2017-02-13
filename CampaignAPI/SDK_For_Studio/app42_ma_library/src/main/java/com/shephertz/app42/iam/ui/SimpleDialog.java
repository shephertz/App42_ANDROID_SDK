/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.shephertz.app42.iam.InAppManager;
import com.shephertz.app42.iam.model.AlertData;
import com.shephertz.app42.iam.model.App42Action;
import com.shephertz.app42.iam.model.InAppAction;
import com.shephertz.app42.iam.model.InAppData;
import com.shephertz.app42.iam.utils.InAppUiUtils;
import org.json.JSONObject;
// TODO: Auto-generated Javadoc
/**
 * The Class SimpleDialog.
 *
 * @author Vishnu
 */
public class SimpleDialog implements InAppAction {

	/**
	 * Instantiates a new simple dialog.
	 *
	 * @param activity the activity
	 * @param isConfirm the is confirm
	 * @param inAppData the in app data
	 */
	public SimpleDialog(Activity activity, boolean isConfirm,
	InAppData inAppData) {

		// TODO Auto-generated constructor stubs
		if(activity==null)
			return;
		if (isConfirm)
			createConfirmAlert(activity, (AlertData) inAppData);
		else
			createAlert(activity, (AlertData) inAppData);
	}

	/**
	 * Creates the alert.
	 *
	 * @param activity the activity
	 * @param alertInfo the alert info
	 */
	private void createAlert(Activity activity, final AlertData alertInfo) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				activity);
		String message=alertInfo.getMessage();
		if(alertInfo.getCouponCode()!=null&&!alertInfo.getCouponCode().equals(""))
			message+=alertInfo.getCouponCode();
		alertDialogBuilder
				.setCustomTitle(getCustomTitle(activity, alertInfo.getTitle()))
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton(alertInfo.getAcceptBtntext(),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								performAccpetAction(alertInfo.getCampName(),
										alertInfo.getAction());
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
		
		// Event For Analytics comment it if not required
		App42Action action = new App42Action("trackEvent", "CampaignViewed_"
				+ alertInfo.getCampName(), new JSONObject());
		InAppManager.performActions(action);
	}

	/**
	 * Gets the custom title.
	 *
	 * @param activity the activity
	 * @param titleText the title text
	 * @return the custom title
	 */
	private TextView getCustomTitle(Activity activity, String titleText) {
		TextView title = new TextView(activity);
		title.setText(titleText);
		int padd=InAppUiUtils.dpToPx(activity, 10);
		title.setPadding(0, padd, 0, padd);
		title.setBackgroundColor(Color.DKGRAY);
		title.setGravity(Gravity.CENTER);
		title.setTextColor(Color.WHITE);
		title.setTextSize(InAppUiUtils.dpToPx(activity, 14));
		return title;
	}

	/**
	 * Creates the confirm alert.
	 *
	 * @param activity the activity
	 * @param alertInfo the alert info
	 */
	private void createConfirmAlert(Activity activity, final AlertData alertInfo) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				activity);
		alertDialogBuilder
				.setCustomTitle(getCustomTitle(activity, alertInfo.getTitle()))
				.setMessage(alertInfo.getMessage())
				.setCancelable(false)
				.setPositiveButton(alertInfo.getAcceptBtntext(),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								performAccpetAction(alertInfo.getCampName(),
										alertInfo.getAction());
							}
						})
				.setNegativeButton(alertInfo.getCancelBtnText(),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								performCancelAction(alertInfo.getCampName(),
										alertInfo.getCancelAction());
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
		
		// Event For Analytics comment it if not required
		App42Action action = new App42Action("trackEvent", "CampaignViewed_"
				+ alertInfo.getCampName(), new JSONObject());
		InAppManager.performActions(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app42.inAppUI.App42Dialog#performCancelAction(java.lang.String,
	 * com.app42.inAppData.Action)
	 */
	@Override
	public void performCancelAction(String campName, App42Action app42Action) {
		// TODO Auto-generated method stub
		InAppManager.performActions(app42Action);
		
		// Event For Analytics comment it if not required
				App42Action action = new App42Action("trackEvent", "CampaignSkip_"
						+ campName, new JSONObject());
				InAppManager.performActions(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app42.inAppUI.App42Dialog#performAccpetAction(java.lang.String,
	 * com.app42.inAppData.Action)
	 */
	@Override
	public void performAccpetAction(String campName, App42Action app42Action) {
		// TODO Auto-generated method stub
		InAppManager.performActions(app42Action);
		
			//Event For Analytics comment it if not required
		App42Action action = new App42Action("trackEvent", "CampaignSubmit_"
				+ campName, new JSONObject());
		InAppManager.performActions(action);

	}

}
