/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shephertz.app42.iam.InAppManager;
import com.shephertz.app42.iam.model.App42Action;
import com.shephertz.app42.iam.model.InAppAction;
import com.shephertz.app42.iam.model.InAppData;
import com.shephertz.app42.iam.model.InterstitialData;
import com.shephertz.app42.iam.utils.InAppUiUtils;
import com.shephertz.app42.iam.utils.InAppUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class InterstitialDialog.
 *
 * @author Vishnu Garg
 */
@SuppressLint("NewApi")
public class InterstitialDialog extends Dialog implements InAppAction {
	
	/** The dialog view. */
	private RelativeLayout dialogView;
	
	/** The message data. */
	protected InterstitialData messageData;
	
	/** The is closing. */
	private boolean isClosing = false;
    
	/**
	 * Instantiates a new interstitial dialog.
	 *
	 * @param activity the activity
	 * @param fullscreen the fullscreen
	 * @param options the options
	 */
	public InterstitialDialog(Activity activity, boolean fullscreen,
			InAppData options) {
		super(activity, getTheme(activity));
		InAppUiUtils.init(activity);

		this.messageData = (InterstitialData) options;
		dialogView = new RelativeLayout(activity);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		dialogView.setBackgroundColor(0x00000000);
		dialogView.setLayoutParams(layoutParams);
		RelativeLayout view = createContainerView(activity, fullscreen);
		view.setId(108);
		dialogView.addView(view, view.getLayoutParams());
		CloseButton closeButton = createCloseButton(activity, fullscreen, view);
		dialogView.addView(closeButton, closeButton.getLayoutParams());
		setContentView(dialogView, dialogView.getLayoutParams());
		dialogView.setAnimation(createFadeInAnimation());
		if (!fullscreen) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			// getWindow().setDimAmount(0.7f);
		}
	}

	/**
	 * Creates the fade in animation.
	 *
	 * @return the animation
	 */
	private Animation createFadeInAnimation() {
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(350);
		return fadeIn;
	}

	/**
	 * Creates the fade out animation.
	 *
	 * @return the animation
	 */
	private Animation createFadeOutAnimation() {
		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setDuration(350);
		return fadeOut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Dialog#cancel()
	 */
	@Override
	public void cancel() {
		if (isClosing) {
			return;
		}
		isClosing = true;
		Animation animation = createFadeOutAnimation();
		animation.setAnimationListener(new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						InterstitialDialog.super.cancel();
					}
				}, 10);
			}
		});
		dialogView.startAnimation(animation);
	}

	/**
	 * Creates the close button.
	 *
	 * @param context the context
	 * @param fullscreen the fullscreen
	 * @param parent the parent
	 * @return the close button
	 */
	private CloseButton createCloseButton(Activity context, boolean fullscreen,
			View parent) {
		CloseButton closeButton = new CloseButton(context);
		closeButton.setId(103);
		RelativeLayout.LayoutParams closeLayout = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		if (fullscreen) {
			closeLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP,
					dialogView.getId());
			closeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
					dialogView.getId());
			closeLayout.setMargins(0, InAppUiUtils.dp5, InAppUiUtils.dp5, 0);
		} else {
			closeLayout.addRule(RelativeLayout.ALIGN_TOP, parent.getId());
			closeLayout.addRule(RelativeLayout.ALIGN_RIGHT, parent.getId());
			closeLayout.setMargins(0, -InAppUiUtils.dp7, -InAppUiUtils.dp7, 0);
		}
		closeButton.setLayoutParams(closeLayout);
		closeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				cancel();
				performCancelAction(messageData.getCampName(),
						messageData.getCancelAction());
			}
		});
		return closeButton;
	}

	/**
	 * Creates the container view.
	 *
	 * @param context the context
	 * @param fullscreen the fullscreen
	 * @return the relative layout
	 */
	private RelativeLayout createContainerView(Activity context,
			boolean fullscreen) {
		RelativeLayout view = new RelativeLayout(context);

		// Positions the dialog.
		RelativeLayout.LayoutParams layoutParams;
		layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				RelativeLayout.TRUE);
		view.setLayoutParams(layoutParams);
		ShapeDrawable footerBackground = new ShapeDrawable();
		footerBackground.setShape(createRoundRect(fullscreen ? 0
				: InAppUiUtils.dp20));
		footerBackground.getPaint().setColor(0x00000000);
		view.setBackgroundDrawable(footerBackground);

		ImageView image = createBackgroundImageView(context, fullscreen);
		view.addView(image, image.getLayoutParams());
		image.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (!isClosing) {
					cancel();
					performAccpetAction(messageData.getCampName(),
							messageData.getAction());
				}
			}
		});
		return view;
	}

	/**
	 * Creates the round rect.
	 *
	 * @param cornerRadius the corner radius
	 * @return the shape
	 */
	private Shape createRoundRect(int cornerRadius) {
		int c = cornerRadius;
		float[] outerRadii = new float[] { c, c, c, c, c, c, c, c };
		return new RoundRectShape(outerRadii, null, null);
	}

	/**
	 * Creates the background image view.
	 *
	 * @param context the context
	 * @param fullscreen the fullscreen
	 * @return the image view
	 */
	private ImageView createBackgroundImageView(Context context,
			boolean fullscreen) {
		BackgroundImageView view = new BackgroundImageView(context, fullscreen);
		view.setScaleType(ImageView.ScaleType.FIT_XY);
		int cornerRadius = 10;
		// view.setImageBitmap(messageData.getBackgroundImage());
		Bitmap bmp = InAppUtils.getImageBitMapFromCache(messageData
				.getBackgroundImage());
		view.setImageBitmap(bmp);
		ShapeDrawable footerBackground = new ShapeDrawable();
		footerBackground.setShape(createRoundRect(cornerRadius));
		// footerBackground.getPaint().setColor(messageData.getBackgroundColor());
		view.setBackgroundDrawable(footerBackground);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		view.setLayoutParams(layoutParams);
		return view;
	}

	/**
	 * Gets the theme.
	 *
	 * @param activity the activity
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
	 * @see com.app42.inAppUI.App42Dialog#performCancelAction(java.lang.String,
	 * com.app42.inAppData.Action)
	 */
	@Override
	public void performCancelAction(String campName, App42Action app42Action) {
		// TODO Auto-generated method stub
		InAppManager.performActions(app42Action);
        
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
		
	}
}
