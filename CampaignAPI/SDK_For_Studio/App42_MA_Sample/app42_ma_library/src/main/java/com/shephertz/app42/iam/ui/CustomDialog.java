/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shephertz.app42.iam.InAppManager;
import com.shephertz.app42.iam.model.App42Action;
import com.shephertz.app42.iam.model.CustomDialogData;
import com.shephertz.app42.iam.model.InAppAction;
import com.shephertz.app42.iam.model.InAppData;
import com.shephertz.app42.iam.model.TextViewProps;
import com.shephertz.app42.iam.utils.InAppDefines.CrossStatus;
import com.shephertz.app42.iam.utils.InAppDefines.LayoutGravity;
import com.shephertz.app42.iam.utils.InAppDefines.LayoutType;
import com.shephertz.app42.iam.utils.InAppUtils;

import org.json.JSONObject;
import com.shephertz.app42.ma.library.R;

/**
 * The Class CustomDialog.
 *
 * @author Vishnu
 */
public class CustomDialog  implements InAppAction {

	/** The message data. */
	protected CustomDialogData messageData;
	
	/** The display. */
	private Display display;
	
	/** The display height. */
	private int displayHeight;
	
	/** The display width. */
	private int displayWidth;

	/**
	 * Instantiates a new custom dialog.
	 *
	 * @param activity the activity
	 * @param inAppData the in app data
	 */
	public CustomDialog(Activity activity, InAppData inAppData) {
		final Dialog dialog = new Dialog(activity,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
		WindowManager.LayoutParams layoutParams = dialog.getWindow()
		.getAttributes();

		display = activity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		displayWidth = size.x;
		displayHeight = size.y;
		this.messageData = (CustomDialogData) inAppData;

        LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(
				getLayoutType(messageData.getLayoutType()), null);
		
		TextView title = (TextView) layout.findViewById(R.id.title_text);
		TextView message = (TextView) layout.findViewById(R.id.message_text1);
		updateTextView(messageData.getTitle(), title);
		updateTextView(messageData.getMessageLine1(), message);
		ImageView iconImage=(ImageView)layout.findViewById(R.id.dialog_icon);
		if(messageData.getLayoutType()!=LayoutType.NoImage){
			
			buildIconImage(iconImage);
		}
		else{
			iconImage.setVisibility(View.GONE);
		}
		setImageBackgorund(layout, activity);
		layoutParams.width = displayWidth;
		layoutParams.height = getLayoutHeight(messageData.getLayoutGravity());
        layoutParams.gravity = getLayoutGravity(messageData.getLayoutGravity());
		//layoutParams.dimAmount = 0.6f;
		if (messageData.getLayoutGravity() == LayoutGravity.Center) {
//			int gap=InAppUiUtils.pxToDp(activity, 20);
//			layout.setPadding(gap, 0, gap, 0);
			layoutParams.width = displayWidth*9/10;
			TextView message2 = (TextView) layout
					.findViewById(R.id.message_text2);
			TextView message3 = (TextView) layout
					.findViewById(R.id.message_text3);
			updateTextView(messageData.getMessageLine3(), message3);
			updateTextView(messageData.getMessageLine2(), message2);
		}
		
		
		dialog.getWindow().setAttributes(layoutParams);
		//dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		/////getWindow().setAttributes(layoutParams);
		//layout.setLayoutParams(layoutParams);
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		dialog.setContentView(layout);
		dialog.show();
		
		ImageButton crossButton=(ImageButton)layout.findViewById(R.id.close_button);
		enableCrossFunctionality(crossButton, messageData.getCrossStatus(), dialog, messageData.getAutoTimeOut());
		
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(dialog.isShowing())
					dialog.dismiss();
					performAccpetAction(messageData.getCampName(), messageData.getAction());
			}
		});
		
		// Event For Analytics comment it if not required
		App42Action action = new App42Action("trackEvent", "CampaignViewed_"
				+ inAppData.getCampName(), new JSONObject());
		InAppManager.performActions(action);
	}
	
	/**
	 * Builds the icon image.
	 *
	 * @param iconImage the icon image
	 */
	private void buildIconImage(ImageView iconImage){
		if(!InAppUtils.isNullBlank(messageData.getIconImage())){
			Bitmap iconBitmap = InAppUtils.getImageBitMapFromCache(messageData
					.getIconImage());
			if(iconBitmap!=null){
				iconImage.setImageBitmap(iconBitmap);
				iconImage.setVisibility(View.VISIBLE);
			}
			else
			
				iconImage.setVisibility(View.GONE);
		}
		else{
			iconImage.setVisibility(View.GONE);
			//iconImage.setVisibility(View.VISIBLE);
		}
			
	}
	
	/**
	 * Sets the image backgorund.
	 *
	 * @param layout the layout
	 * @param activity the activity
	 */
	private void setImageBackgorund(View layout,Activity activity){
		if(!InAppUtils.isNullBlank(messageData.getBackGroundImage())){
			Bitmap iconBitmap = InAppUtils.getImageBitMapFromCache(messageData
					.getBackGroundImage());
			if(iconBitmap
					!=null){
				
//			if(messageData.isRoundCorner())
//				iconBitmap=InAppUiUtils.getRoundedShape(iconBitmap, displayWidth);
			if(iconBitmap!=null){
				Drawable drawable = new BitmapDrawable(activity.getResources(), iconBitmap);
				layout.setBackground(drawable);
			}
			}
			else
				layout.setBackgroundColor(messageData.getBackgroundColor());
		}
		else
			layout.setBackgroundColor(messageData.getBackgroundColor());
		
	}
	
	/**
	 * Enable cross functionality.
	 *
	 * @param crossButton the cross button
	 * @param crossStatus the cross status
	 * @param dialog the dialog
	 * @param timeOut the time out
	 */
	private void enableCrossFunctionality(ImageButton crossButton,CrossStatus crossStatus,final Dialog dialog,int timeOut){
		if(crossStatus==CrossStatus.Forever)
			return;
		if(crossStatus==CrossStatus.Manual){
			crossButton.setVisibility(View.VISIBLE);
			crossButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(dialog.isShowing())
					dialog.dismiss();
					performCancelAction(messageData.getCampName(), messageData.getCancelAction());
				}
			});
		}
		else if(crossStatus==CrossStatus.Automate){
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					if(dialog.isShowing())
						dialog.dismiss();
					performCancelAction(messageData.getCampName(), messageData.getCancelAction());
				}
			}, timeOut*1000);
			
		}
		
	}

	/**
	 * Gets the layout type.
	 *
	 * @param layoutType the layout type
	 * @return the layout type
	 */
	private int getLayoutType(LayoutType layoutType) {
		switch (layoutType) {
		case LeftImage:
			return R.layout.left_popup;
		case RightImage:
			return R.layout.right_popup;
		case CenterImage:
			return R.layout.center_popup;
		case NoImage:
			return R.layout.center_popup;
		default:
			return R.layout.center_popup;
		}
	}

	/**
	 * Gets the layout gravity.
	 *
	 * @param gravity the gravity
	 * @return the layout gravity
	 */
	private int getLayoutGravity(LayoutGravity gravity) {
		switch (gravity) {
		case Top:
			return Gravity.TOP;
		case Center:
			return Gravity.CENTER;
		case Bottom:
			return Gravity.BOTTOM;
		default:
			return Gravity.CENTER;
		}
	}

	/**
	 * Gets the layout height.
	 *
	 * @param gravity the gravity
	 * @return the layout height
	 */
	private int getLayoutHeight(LayoutGravity gravity) {
		switch (gravity) {
		case Top:
			return (displayHeight) / 5;
		case Center:
//			return (displayHeight) /2;
            // TODO: 1/28/2017 Changes in height if layout gravity is center and layout type is left or right or no image
            if (messageData.getLayoutType() == (LayoutType.LeftImage) || messageData.getLayoutType() == LayoutType.RightImage ||
                    messageData.getLayoutType() == LayoutType.NoImage){
            return (displayHeight) / 4;
        } else{
            return (displayHeight) / 3;
        }
		case Bottom:
			return (displayHeight) / 4;
		default:
			return (displayHeight) /2;
		}
	}

	/**
	 * Update text view.
	 *
	 * @param props the props
	 * @param view the view
	 */
	private void updateTextView(TextViewProps props, TextView view) {
		if (props == null || InAppUtils.isNullBlank(props.getText()))
			return;
		view.setText(props.getText());
		view.setTextSize(TypedValue.COMPLEX_UNIT_SP, props.getSize());
		view.setTextColor(props.getColor());
		view.setVisibility(View.VISIBLE);
	}

	/**
	 * Gets the theme.
	 * 
	 * @param activity
	 *            the activity
	 * @return the theme
	 */
	private static int getTheme(Activity activity) {
		boolean full = (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
		if (full) {
			return android.R.style.Theme_Translucent_NoTitleBar_Fullscreen;
		} else {
			return android.R.style.Theme_Translucent_NoTitleBar;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shephertz.app42.iam.model.InAppAction#performCancelAction(java.lang
	 * .String, com.shephertz.app42.iam.model.App42Action)
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
	 * @see
	 * com.shephertz.app42.iam.model.InAppAction#performAccpetAction(java.lang
	 * .String, com.shephertz.app42.iam.model.App42Action)
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
