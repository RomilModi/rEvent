package com.rays.rEvent.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rays.rEvent.R;
import com.rays.rEvent.bean.Place;

public class EventNearByAdapter extends BaseAdapter {

	private Context c;
	private ArrayList<Place> mArraylstPlace;
	private LayoutInflater mlayout;
	private String places;

	public EventNearByAdapter(Context context, ArrayList<Place> mArraylstPlace,
			String places) {
		// TODO Auto-generated constructor stub
		this.c = context;
		this.mlayout = LayoutInflater.from(c);
		this.mArraylstPlace = mArraylstPlace;
		this.places = places;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArraylstPlace.size();
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
			convertView = mlayout.inflate(R.layout.row_nearby, null);
			viewHolder = new ViewHolderItem();
			viewHolder.rowNearby_txt_Name = (TextView) convertView
					.findViewById(R.id.rowNearby_txt_Name);

			viewHolder.rowNearby_txt_Address = (TextView) convertView
					.findViewById(R.id.rowNearby_txt_Address);
			viewHolder.rowNearby_txt_Rating = (TextView) convertView
					.findViewById(R.id.rowNearby_txt_Rating);
			viewHolder.RowNearby_RelativeLayout_Rating = (RelativeLayout) convertView
					.findViewById(R.id.RowNearby_RelativeLayout_Rating);

			// viewHolder.rawActivityServices_txt_Services = (TextView)
			// convertView
			// .findViewById(R.id.rawActivityServices_txt_Services);

			// store the holder with the view.
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}
		viewHolder.rowNearby_txt_Name.setSelected(true);
		viewHolder.rowNearby_txt_Name.setText(mArraylstPlace.get(position)
				.getName());

		viewHolder.rowNearby_txt_Address.setText(mArraylstPlace.get(position)
				.getVicinity());

		if (places.equalsIgnoreCase("Restaurant")
				|| places.equalsIgnoreCase("Cafe")) {
			viewHolder.RowNearby_RelativeLayout_Rating
					.setVisibility(View.VISIBLE);
			if (mArraylstPlace.get(position).getRating() == null) {
				viewHolder.RowNearby_RelativeLayout_Rating
						.setVisibility(View.GONE);
			} else {
				viewHolder.RowNearby_RelativeLayout_Rating
						.setVisibility(View.VISIBLE);
				viewHolder.rowNearby_txt_Rating.setText(mArraylstPlace.get(
						position).getRating());
			}
		} else {
			viewHolder.RowNearby_RelativeLayout_Rating.setVisibility(View.GONE);
		}

		return convertView;
	}

	static class ViewHolderItem {
		TextView rowNearby_txt_Name, rowNearby_txt_Address,
				rowNearby_txt_Rating;
		private RelativeLayout RowNearby_RelativeLayout_Rating;
	}
}
