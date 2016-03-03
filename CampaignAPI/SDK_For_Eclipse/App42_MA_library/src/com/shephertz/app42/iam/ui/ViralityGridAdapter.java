/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shephertz.app42.ma.sample.R;
import com.shephertz.app42.virality.model.SocialItem;

/**
 * The Class ViralityGridAdapter.
 *
 * @author Vishnu
 */
public class ViralityGridAdapter extends ArrayAdapter<SocialItem> {
	
	/** The context. */
	private Context context;
	
	/** The layout resource id. */
	private int layoutResourceId;

	/** The social items. */
	private ArrayList<SocialItem> socialItems = new ArrayList<SocialItem>();

	/**
	 * Instantiates a new virality grid adapter.
	 *
	 * @param context the context
	 * @param layoutResourceId the layout resource id
	 * @param socialActions the social actions
	 */
	public ViralityGridAdapter(Context context, int layoutResourceId,
			ArrayList<SocialItem> socialActions) {
		super(context, layoutResourceId, socialActions);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.socialItems = socialActions;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View row, ViewGroup parent) {
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.appName = (TextView) row.findViewById(R.id.app42_virality_item_text);
			holder.icon = (ImageView) row.findViewById(R.id.app42_virality_item_icon);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		SocialItem item = socialItems.get(position);
		holder.appName.setText(item.getSocialApp().toString());
		Drawable icon;
		try {
			icon = context.getPackageManager().getApplicationIcon(item.getSocialApp().getPackage());
			holder.icon.setImageDrawable(icon);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return row;
	}

	/**
	 * The Class ViewHolder.
	 */
	class ViewHolder {
		
		/** The app name. */
		TextView appName;
		
		/** The icon. */
		ImageView icon;
	}
}