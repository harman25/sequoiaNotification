package com.hack.notificationapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DealListAdapter extends BaseAdapter {

	Context context;
	List<String> deals;

	public DealListAdapter(Context context, List<String> offerList) {
		this.context = context;
		this.deals = offerList;
	}

	@Override
	public int getCount() {
		return deals.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inf = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.list_child, parent, false);
		}

		TextView usernameText = (TextView) convertView
				.findViewById(R.id.textview_feedname);
		usernameText.setText(deals.get(position));

		return convertView;
	}

}
