package com.rays.rEvent.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rays.rEvent.EventDashBoardActivity;
import com.rays.rEvent.R;
import com.rays.rEvent.bean.GetAllEventsbean;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;

public class GetMyEventsAdapter extends BaseAdapter implements Filterable {
	Context context;
	// int[] imageId;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ArrayList<GetAllEvents> mArraylstAllEvents;
	private Filter planetFilter;
	// private String[] IMAGES;
	// private ArrayList<GetAllEvents> listpicOrigin;
	private ArrayList<GetAllEvents> origPlanetList;

	public GetMyEventsAdapter(Context c,/* int[] prgmImages *//*
															 * ArrayList<Integer>
															 * mArray
															 */
			ArrayList<GetAllEvents> mArraylstAllEvents) {
		// TODO Auto-generated constructor stub
		this.context = c;
		// imageId = prgmImages;
		// this.mArraylst = mArray;
		// this.IMAGES = IMAGES;
		this.mArraylstAllEvents = mArraylstAllEvents;
		this.origPlanetList = mArraylstAllEvents;
		// this.listpicOrigin = new ArrayList<GetAllEvents>();
		// this.listpicOrigin.addAll(mArraylstAllEvents);
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image)
				.showImageOnLoading(R.drawable.ic_stub).build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArraylstAllEvents.size();
	}

	@Override
	public int getViewTypeCount() {
		if (mArraylstAllEvents.size() > 1) {
			return 2;
		} else {
			return 1;
		}

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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		final GetAllEvents All = new GetAllEventsbean().new GetAllEvents();
		All.setHEADER_CONTENT(mArraylstAllEvents.get(position).HEADER_CONTENT);
		All.setFOOTER_CONTENT(mArraylstAllEvents.get(position).FOOTER_CONTENT);
		All.setEVT_ID(mArraylstAllEvents.get(position).EVT_ID);
		All.setEVT_NAME(mArraylstAllEvents.get(position).EVT_NAME);
		All.setEVT_SHORT_NAME(mArraylstAllEvents.get(position).EVT_SHORT_NAME);
		All.setEVT_START_DATE(mArraylstAllEvents.get(position).EVT_START_DATE);
		All.setEVT_END_DATE(mArraylstAllEvents.get(position).EVT_END_DATE);
		All.setEVT_START_TIME(mArraylstAllEvents.get(position).EVT_START_TIME);
		All.setEVT_END_TIME(mArraylstAllEvents.get(position).EVT_END_TIME);
		All.setEVT_VENUE(mArraylstAllEvents.get(position).EVT_VENUE);
		All.setEVT_CITY(mArraylstAllEvents.get(position).EVT_CITY);
		All.setEVT_STATE(mArraylstAllEvents.get(position).EVT_STATE);
		All.setEVT_WEB_URL(mArraylstAllEvents.get(position).EVT_WEB_URL);
		All.setEVT_EMAIL_ADDRESS(mArraylstAllEvents.get(position).EVT_EMAIL_ADDRESS);
		All.setEVT_IS_CURRENT(mArraylstAllEvents.get(position).EVT_IS_CURRENT);
		All.setEVT_COUNTRY(mArraylstAllEvents.get(position).EVT_COUNTRY);
		All.setEVT_CONTACT_PERSON(mArraylstAllEvents.get(position).EVT_CONTACT_PERSON);
		All.setEVT_CONTACT_NUMBER(mArraylstAllEvents.get(position).EVT_CONTACT_NUMBER);
		All.setEVT_ZIP_CODE(mArraylstAllEvents.get(position).EVT_ZIP_CODE);
		All.setEVT_BADGE_ALLOW(mArraylstAllEvents.get(position).EVT_BADGE_ALLOW);
		All.setEVT_BADGE_FORMAT(mArraylstAllEvents.get(position).EVT_BADGE_FORMAT);
		All.setEVT_CREATED_USER_ID(mArraylstAllEvents.get(position).EVT_CREATED_USER_ID);
		All.setEVT_CREATED_DATE(mArraylstAllEvents.get(position).EVT_CREATED_DATE);
		All.setEVT_LAST_CHANGED_USER_ID(mArraylstAllEvents.get(position).EVT_LAST_CHANGED_USER_ID);
		All.setEVT_LAST_CHANGED_DATE(mArraylstAllEvents.get(position).EVT_LAST_CHANGED_DATE);
		All.setEVT_RECORD_STATUS_FLAG(mArraylstAllEvents.get(position).EVT_RECORD_STATUS_FLAG);
		All.setEVT_RECORD_STATUS_DATE(mArraylstAllEvents.get(position).EVT_RECORD_STATUS_DATE);
		All.setEVT_TRANSACTION_GUID(mArraylstAllEvents.get(position).EVT_TRANSACTION_GUID);
		All.setEVT_RECORD_TIMESTAMP(mArraylstAllEvents.get(position).EVT_RECORD_TIMESTAMP);
		All.setEVT_QC_FLAG_DONE(mArraylstAllEvents.get(position).EVT_QC_FLAG_DONE);
		All.setREG_ID(mArraylstAllEvents.get(position).REG_ID);
		All.setBADGE_NO(mArraylstAllEvents.get(position).BADGE_NO);
		All.setFlag(mArraylstAllEvents.get(position).Flag);
		All.setMesg(mArraylstAllEvents.get(position).Mesg);
		if (position % 2 == 0) {
			// convertView.findViewById(R.id.chatLayoutImageLeft).setVisibility(
			// View.INVISIBLE);
			// convertView.findViewById(R.id.chatLayoutImageRight).setVisibility(
			// View.VISIBLE);

			viewHolder.TextView_EventNameRight.setSelected(true);
			viewHolder.TextView_EventNameRight.setText(mArraylstAllEvents
					.get(position).EVT_NAME);
			viewHolder.LinearLayoutRight.setVisibility(View.INVISIBLE);

			// imageLoader.displayImage("drawable://" + mArraylst.get(position),
			// viewHolder.imageView_chat_right, options);
			imageLoader.displayImage(
					mArraylstAllEvents.get(position).EVT_LOGO_PATH,
					viewHolder.imageView_chat_right, options);

			// viewHolder.imageView_chat_right.setImageResource(imageId[position]);
			// viewHolder.imageView_chat_right.setImageResource(imageId[position]);

			viewHolder.LinearLayoutLeft
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							callActivity(All);
							// Toast.makeText(context,
							// mArraylstAllEvents.get(position).EVT_NAME,
							// Toast.LENGTH_LONG).show();
						}
					});
		} else if (position % 2 == 1) {
			// convertView.findViewById(R.id.chatLayoutImageLeft).setVisibility(
			// View.VISIBLE);
			// convertView.findViewById(R.id.chatLayoutImageRight).setVisibility(
			// View.INVISIBLE);
			viewHolder.TextView_EventNameLeft.setSelected(true);
			viewHolder.TextView_EventNameLeft.setText(mArraylstAllEvents
					.get(position).EVT_NAME);
			viewHolder.LinearLayoutLeft.setVisibility(View.INVISIBLE);

			// imageLoader.displayImage("drawable://" + mArraylst.get(position),
			// viewHolder.imageView_chat_left, options);
			imageLoader.displayImage(
					mArraylstAllEvents.get(position).EVT_LOGO_PATH,
					viewHolder.imageView_chat_left, options);
			// viewHolder.imageView_chat_left.setImageResource(imageId[position]);
			// viewHolder.imageView_chat_left.setImageResource(imageId[position]);

			viewHolder.LinearLayoutRight
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							callActivity(All);
							// Toast.makeText(context,
							// mArraylstAllEvents.get(position).EVT_NAME,
							// Toast.LENGTH_LONG).show();
						}
					});

		}
		return convertView;
	}

	protected void callActivity(Object obj) {
		// TODO Auto-generated method stub
		Intent in = new Intent(context, EventDashBoardActivity.class);
		in.putExtra("AllEventObject", (GetAllEvents) obj);
		context.startActivity(in);
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

	public ArrayList<GetAllEvents> filter(String charText) {
		charText = charText.toLowerCase();
		mArraylstAllEvents.clear();
		if (charText.length() == 0) {
			mArraylstAllEvents.addAll(origPlanetList);
		} else {
			for (GetAllEvents pic : origPlanetList) {
				if (pic.EVT_NAME.toLowerCase().contains(charText)) {
					mArraylstAllEvents.add(pic);
				}
			}
		}
		notifyDataSetChanged();
		return mArraylstAllEvents;
	}

	public void resetData() {
		mArraylstAllEvents = origPlanetList;
	}

	/*
	 * We create our filter
	 */

	@Override
	public Filter getFilter() {
		if (planetFilter == null)
			planetFilter = new PlanetFilter();

		return planetFilter;
	}

	private class PlanetFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = origPlanetList;
				results.count = origPlanetList.size();
			} else {
				// We perform filtering operation
				List<GetAllEvents> nPlanetList = new ArrayList<GetAllEvents>();

				for (GetAllEvents p : mArraylstAllEvents) {
					// if (p.EVT_NAME.toLowerCase().contains(constraint)) {
					// mArraylstAllEvents.add(p);
					// }
					if (p.EVT_NAME.toUpperCase().contains(
							constraint.toString().toUpperCase()))
						nPlanetList.add(p);
				}

				results.values = nPlanetList;
				results.count = nPlanetList.size();

			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {
				mArraylstAllEvents = (ArrayList<GetAllEvents>) results.values;
				notifyDataSetChanged();
			}

		}

	}

}
