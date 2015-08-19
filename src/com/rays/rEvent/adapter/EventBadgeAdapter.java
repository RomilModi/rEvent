package com.rays.rEvent.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rays.rEvent.R;
import com.rays.rEvent.bean.EventBadgebean.GetBadgeDetails;

public class EventBadgeAdapter extends BaseAdapter {

	private Context c;
	private ArrayList<GetBadgeDetails> mArraylstbadge;
	private LayoutInflater mlayout;

	public EventBadgeAdapter(Context context,
			ArrayList<GetBadgeDetails> mArraylstbadge) {
		// TODO Auto-generated constructor stub
		this.c = context;
		this.mlayout = LayoutInflater.from(c);
		this.mArraylstbadge = mArraylstbadge;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArraylstbadge.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolderItem viewHolder;
		if (convertView == null) {
			convertView = mlayout.inflate(R.layout.raw_event_badge, null);
			viewHolder = new ViewHolderItem();
			viewHolder.raw_ActivityBadge_txt_Name = (TextView) convertView
					.findViewById(R.id.raw_ActivityBadge_txt_Name);

			// viewHolder.rawActivityServices_txt_Services = (TextView)
			// convertView
			// .findViewById(R.id.rawActivityServices_txt_Services);

			// store the holder with the view.
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}
		viewHolder.raw_ActivityBadge_txt_Name.setText(new StringBuilder()
				.append(mArraylstbadge.get(position).REG_FIRST_NAME + " "
						+ mArraylstbadge.get(position).REG_LAST_NAME));

		return convertView;
	}

	static class ViewHolderItem {
		TextView raw_ActivityBadge_txt_Name;
	}

}
