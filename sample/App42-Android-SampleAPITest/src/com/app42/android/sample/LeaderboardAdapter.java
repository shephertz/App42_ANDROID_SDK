/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.app42.android.sample;

import java.util.ArrayList;

import com.app42.api.test.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * The Class LeaderboardAdapter.
 */
public class LeaderboardAdapter extends BaseAdapter {

	/** The activity. */
	private Activity activity;
    
    /** The name. */
    private ArrayList<String> name;
    
    /** The rank. */
    private ArrayList<String> rank;
    
    /** The score. */
    private ArrayList<String> score;
	
	/** The inflater. */
	private LayoutInflater inflater = null;

	/**
	 * Instantiates a new leaderboard adapter.
	 *
	 * @param a the a
	 * @param name the name
	 * @param rank the rank
	 * @param score the score
	 */
	public LeaderboardAdapter(Activity a,ArrayList<String> name,ArrayList<String> rank,
			ArrayList<String> score) {
		activity = a;
	  this.rank=rank;
	  this.name=name;
	  this.score=score;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {
		return name.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	public Object getItem(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	public long getItemId(int position) {
		return position;
	}

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder {
		
		/** The name. */
		public TextView name;
		
		/** The rank. */
		public TextView rank;
		
		/** The score. */
		public TextView score;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;

		if (convertView == null) {		
			vi = inflater.inflate(R.layout.item_leader_board , null);
			holder = new ViewHolder();
			holder.rank = (TextView) vi.findViewById(R.id.rank);
			holder.name = (TextView) vi.findViewById(R.id.name);
			holder.score = (TextView) vi.findViewById(R.id.score);
			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		holder.rank.setText(rank.get(position) );
		holder.name.setText(name.get(position));
		holder.score.setText(score.get(position));
		

		return vi;
	}
}