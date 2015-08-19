package com.rays.rEvent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rays.rEvent.R;

public class ImageAdapter extends BaseAdapter {
	Context context;
	// int[] imageId;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	// private ArrayList<Integer> mArraylst;
	private String[] IMAGES;

	public ImageAdapter(Context c,/* int[] prgmImages *//* ArrayList<Integer> mArray */
			String[] IMAGES) {
		// TODO Auto-generated constructor stub
		this.context = c;
		// imageId = prgmImages;
		// this.mArraylst = mArray;
		this.IMAGES = IMAGES;
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.ic_core_overflow)
				.showImageOnFail(R.drawable.ic_core_overflow)
				.showImageOnLoading(R.drawable.ic_core_overflow).build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return IMAGES.length;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if (position % 2 == 0) {
			return 0;
		} else {
			return 1;
		}
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
		ViewHolder viewHolder;
		if (convertView == null) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_chat, null);
			viewHolder = new ViewHolder();

			viewHolder.TextView_EventNameLeft = (TextView) convertView
					.findViewById(R.id.TextView_EventNameLeft);
			viewHolder.TextView_EventNameRight = (TextView) convertView
					.findViewById(R.id.TextView_EventNameRight);
			viewHolder.imageView_chat_left = (ImageView) convertView
					.findViewById(R.id.imageView_chat_left);
			viewHolder.imageView_chat_right = (ImageView) convertView
					.findViewById(R.id.imageView_chat_right);
			viewHolder.LinearLayoutRight = (LinearLayout) convertView
					.findViewById(R.id.LinearLayoutRight);
			viewHolder.LinearLayoutLeft = (LinearLayout) convertView
					.findViewById(R.id.LinearLayoutLeft);
			// store the holder with the view.
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// inflater = (LayoutInflater) context
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// View rowView = inflater.inflate(R.layout.row_chat, null, true);
		//
		// ImageView imageView_chat_left = (ImageView) rowView
		// .findViewById(R.id.imageView_chat_left);
		// ImageView imageView_chat_right = (ImageView) rowView
		// .findViewById(R.id.imageView_chat_right);

		Log.e("TAG", "Modulo : " + position % 2 + " & Pos : " + position);

		if (position % 2 == 0) {
			// convertView.findViewById(R.id.chatLayoutImageLeft).setVisibility(
			// View.INVISIBLE);
			// convertView.findViewById(R.id.chatLayoutImageRight).setVisibility(
			// View.VISIBLE);

			viewHolder.TextView_EventNameRight.setSelected(true);

			viewHolder.LinearLayoutRight.setVisibility(View.INVISIBLE);

			// imageLoader.displayImage("drawable://" + mArraylst.get(position),
			// viewHolder.imageView_chat_right, options);
			imageLoader.displayImage(IMAGES[position],
					viewHolder.imageView_chat_right, options);

			// viewHolder.imageView_chat_right.setImageResource(imageId[position]);
			// viewHolder.imageView_chat_right.setImageResource(imageId[position]);

		} else if (position % 2 == 1) {
			// convertView.findViewById(R.id.chatLayoutImageLeft).setVisibility(
			// View.VISIBLE);
			// convertView.findViewById(R.id.chatLayoutImageRight).setVisibility(
			// View.INVISIBLE);
			viewHolder.TextView_EventNameLeft.setSelected(true);
			viewHolder.LinearLayoutLeft.setVisibility(View.INVISIBLE);

			// imageLoader.displayImage("drawable://" + mArraylst.get(position),
			// viewHolder.imageView_chat_left, options);
			imageLoader.displayImage(IMAGES[position],
					viewHolder.imageView_chat_left, options);
			// viewHolder.imageView_chat_left.setImageResource(imageId[position]);
			// viewHolder.imageView_chat_left.setImageResource(imageId[position]);

		}
		return convertView;
	}

	public class ViewHolder {
		TextView TextView_EventNameLeft, TextView_EventNameRight;
		ImageView imageView_chat_left, imageView_chat_right;
		private LinearLayout LinearLayoutRight, LinearLayoutLeft;
	}
	// View v = convertView;
	// int type = getItemViewType(position);
	// if (v == null) {
	// // Inflate the layout according to the view type
	// LayoutInflater inflater = (LayoutInflater) context
	// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// if (type == 0) {
	// // Inflate the layout with image
	// v = inflater.inflate(R.layout.row_chat_image_right, parent,
	// false);
	// } else {
	// v = inflater.inflate(R.layout.row_chat_image_left, parent,
	// false);
	// }
	// }
	// //
	// if (type == 0) {
	// ImageView img = (ImageView) v
	// .findViewById(R.id.imageView_chat_right);
	// img.setImageResource(imageId[position]);
	// } else {
	// ImageView img = (ImageView) v
	// .findViewById(R.id.imageView_chat_left);
	// img.setImageResource(imageId[position]);
	// }
	// return v;
}
