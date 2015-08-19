package com.rays.rEvent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rays.rEvent.R;
import com.rays.rEvent.enums.HomeGrid;

public class HomeGridAdapter extends BaseAdapter {
	private Context context;

	public HomeGridAdapter(Context mContext) {
		this.context = mContext;
	}

	@Override
	public int getCount() {
		return HomeGrid.values().length;
	}

	@Override
	public Object getItem(int position) {
		return HomeGrid.getByIndex(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridView;

		if (null == convertView) {
			gridView = new View(context);
			gridView = inflater.inflate(R.layout.home_grid_item, null);

			HomeGrid currentItem = HomeGrid.getByIndex(position);
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
			imageView.setImageResource(currentItem.getDrawable());
			TextView txt_tabname = (TextView) gridView
					.findViewById(R.id.txt_tabname);
			txt_tabname.setText(currentItem.getName());
			
			

		} else {
			gridView = convertView;
		}
		return gridView;
	}

}
