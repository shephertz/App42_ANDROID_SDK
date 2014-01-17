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
public class LeaderboardAdapter extends BaseAdapter {

	private Activity activity;
    private ArrayList<String> name;
    private ArrayList<String> rank;
    private ArrayList<String> score;
	private LayoutInflater inflater = null;

	public LeaderboardAdapter(Activity a,ArrayList<String> name,ArrayList<String> rank,
			ArrayList<String> score) {
		activity = a;
	  this.rank=rank;
	  this.name=name;
	  this.score=score;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return name.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		public TextView name;
		public TextView rank;
		public TextView score;
	}

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