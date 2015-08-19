package com.rays.rEvent.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rays.rEvent.R;
import com.rays.rEvent.bean.EventServicesbean.GetEventServices;

public class EventServicesAdapter extends BaseAdapter {

	private Context c;
	private ArrayList<GetEventServices> mArraylstServices;
	private LayoutInflater mlayout;

	public EventServicesAdapter(Context context,
			ArrayList<GetEventServices> mArraylstServices) {
		// TODO Auto-generated constructor stub
		this.c = context;
		this.mlayout = LayoutInflater.from(c);
		this.mArraylstServices = mArraylstServices;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArraylstServices.size();
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
			convertView = mlayout.inflate(R.layout.raw_services, null);
			viewHolder = new ViewHolderItem();
			viewHolder.rawActivityServices_txt_Name = (TextView) convertView
					.findViewById(R.id.rawActivityServices_txt_Name);
			viewHolder.rawActivityServices_LinearLayout = (LinearLayout) convertView
					.findViewById(R.id.rawActivityServices_LinearLayout);
			viewHolder.rawActivityServices_LinearLayoutDateTo = (LinearLayout) convertView
					.findViewById(R.id.rawActivityServices_LinearLayoutDateTo);
			viewHolder.rawActivityServices_LinearLayoutDateFrom = (LinearLayout) convertView
					.findViewById(R.id.rawActivityServices_LinearLayoutDateFrom);

			// viewHolder.rawActivityServices_txt_Services = (TextView)
			// convertView
			// .findViewById(R.id.rawActivityServices_txt_Services);

			// store the holder with the view.
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}
		String delim = "";
		viewHolder.rawActivityServices_txt_Name.setText(new StringBuilder()
				.append(mArraylstServices.get(position).REG_FIRST_NAME + " "
						+ mArraylstServices.get(position).REG_LAST_NAME));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mArraylstServices.get(position).GetEventServicesTaken
				.size(); i++) {
			LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			lparams.gravity = Gravity.CENTER;
			TextView tv = new TextView(c);
			tv.setLayoutParams(lparams);
			tv.setText(mArraylstServices.get(position).GetEventServicesTaken
					.get(i).SERVICE_NAME);
			tv.setGravity(Gravity.CENTER);
			tv.setPadding(7, 7, 7, 7);
			tv.setTextSize(c.getResources().getDimension(R.dimen.tab_size));
			viewHolder.rawActivityServices_LinearLayout.addView(tv);

			LayoutParams lparams_EventTo = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lparams_EventTo.gravity = Gravity.CENTER;
			TextView event_to = new TextView(c);
			event_to.setLayoutParams(lparams_EventTo);
			event_to.setTextSize(c.getResources().getDimension(R.dimen.tab_size));
			event_to.setText(new StringBuffer().append(mArraylstServices
					.get(position).GetEventServicesTaken.get(i).START_DATE
					+ " "
					+ mArraylstServices.get(position).GetEventServicesTaken
							.get(i).START_TIME));
			event_to.setGravity(Gravity.CENTER);
			event_to.setPadding(7, 7, 7, 7);
			viewHolder.rawActivityServices_LinearLayoutDateTo.addView(event_to);

			LayoutParams lparams_EventFrom = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lparams_EventFrom.gravity = Gravity.CENTER;
			TextView event_from = new TextView(c);
			event_from.setTextSize(c.getResources().getDimension(R.dimen.tab_size));
			event_from.setLayoutParams(lparams_EventFrom);
			event_from.setText(new StringBuffer().append(mArraylstServices
					.get(position).GetEventServicesTaken.get(i).START_DATE
					+ " "
					+ mArraylstServices.get(position).GetEventServicesTaken
							.get(i).START_TIME));
			event_from.setGravity(Gravity.CENTER);
			event_from.setPadding(7, 7, 7, 7);
			viewHolder.rawActivityServices_LinearLayoutDateFrom
					.addView(event_from);

			// sb.append(delim).append(
			// mArraylstServices.get(position).GetEventServicesTaken
			// .get(i).SERVICE_NAME);
			// delim = ",";
			// rowEdt.setText(mArrayLstScheduledAppointments.get(i).Name);
		}
		// viewHolder.rawActivityServices_txt_Services.setText(method(sb
		// .toString()));

		return convertView;
	}

	static class ViewHolderItem {
		TextView rawActivityServices_txt_Name/*
											 * ,
											 * rawActivityServices_txt_Services
											 */;
		LinearLayout rawActivityServices_LinearLayout,
				rawActivityServices_LinearLayoutDateTo,
				rawActivityServices_LinearLayoutDateFrom;
	}

	public String method(String str) {
		if (str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
}
