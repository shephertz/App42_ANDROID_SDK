/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.shephertz.app42.iam.App42CampaignAPI;
import com.shephertz.app42.ma.sample.R;
import com.shephertz.app42.virality.App42ViralityGridView;
import com.shephertz.app42.virality.ViralityUtil;
import com.shephertz.app42.virality.model.SocialItem;
import com.shephertz.app42.virality.model.ViralityCampaign;



/**
 * The Class ViralityActivity.
 *
 * @author Vishnu
 */
public class ViralityActivity extends Activity{
    
    /** The grid view. */
    private App42ViralityGridView gridView;
	
	/** The campaign. */
	private ViralityCampaign campaign;
	
	/** The No social app msg. */
	private final String NoSocialAppMsg="No Social App founds";
	
	/** The social items. */
	private ArrayList<SocialItem> socialItems;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		setContentView(R.layout.virality_screen);
		final TextView offerTitle=(TextView)findViewById(R.id.app42_virality_title);
		final TextView offerDescription=(TextView)findViewById(R.id.app42_virality_desc);
		final TextView referralUrlLink=(TextView)findViewById(R.id.app42_referral_link_url);
		final TextView noSocialApp=(TextView)findViewById(R.id.app42_no_social_app);
		campaign=App42CampaignAPI.getActiveViralityCampaign(ViralityActivity.this);
		if(campaign==null||campaign.getSocilaWidgetList()==null||campaign.getSocilaWidgetList().size()==0){
			noSocialApp.setText(NoSocialAppMsg);
		noSocialApp.setVisibility(View.VISIBLE);
		}
		else{
			offerDescription.setText(ViralityUtil.getRewardDescription(campaign.getRewardRules(),campaign.getRewardUnit()));
			referralUrlLink.setText(campaign.getLinkUrl());
			offerTitle.setText(campaign.getCampiagnName());
		gridView=(App42ViralityGridView)findViewById(R.id.app42_virality_gridView);
		showSocialGridView(campaign);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int index, long id) {
				App42CampaignAPI.startActivity(campaign.getSocilaWidgetList().get(index), ViralityActivity.this, campaign.getLinkUrl(),campaign.getCampiagnName());
			
			}
		});
		}
	}
	

	/**
	 * Show social grid view.
	 *
	 * @param campaign the campaign
	 */
	private void showSocialGridView(ViralityCampaign campaign){
		socialItems=campaign.getSocilaWidgetList();
		ViralityGridAdapter adapter=new ViralityGridAdapter(this, R.layout.virality_row, socialItems);
		gridView.setAdapter(adapter);
	}
}
