package com.rays.rEvent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.adapter.GetAllEventsAdapter;
import com.rays.rEvent.bean.GetAllEventsbean;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.utils.Constants;

public class AllEventsFragment extends android.support.v4.app.Fragment
		implements OnItemClickListener {
	public static ListView lstList;
	// private com.rays.rEvent.adapter.ImageAdapter mBaseAdapter;
	private ArrayList<Integer> mArray;
	private GetAllEventsbean GetAllEventsbean;
	public static ArrayList<GetAllEvents> mArraylst_GetAllEventsList,
			mArraylst_GetAllEventsListFilter;
	public static GetAllEventsAdapter mBaseAdapter;
	private String strtext;
	private Bundle mbundle;
	private View view;
	public static Context c;
	public static ArrayList<GetAllEvents> array_sort;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.i("Activity", "onAttach");

		c = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("Activity", "onCreateView");
		view = inflater.inflate(R.layout.fragment_main, container, false);

		mbundle = getArguments();
		array_sort = new ArrayList<GetAllEventsbean.GetAllEvents>();

		if (mbundle != null) {
			strtext = mbundle.getString("FROM");
		} else {
			strtext = "";
		}

		Log.i("TAG", "strtext : " + strtext + " & Data : " + mbundle);
		lstList = (ListView) view.findViewById(R.id.lstList);
		lstList.invalidate();
		lstList.setTextFilterEnabled(true);
		lstList.setOnItemClickListener(this);
		mArraylst_GetAllEventsList = new ArrayList<GetAllEventsbean.GetAllEvents>();
		mArraylst_GetAllEventsListFilter = new ArrayList<GetAllEventsbean.GetAllEvents>();
		// if (Constants.checkNetworkStatus(c)) {
		// getAllEvents("");
		// } else {
		// Constants.showMessageDialog(c, "rEvent",
		// "Internet Connection is not available.");
		// }

		// mBaseAdapter = new
		// com.rays.rEvent.adapter.ImageAdapter(c, /* prgmImages
		// *//* mArray */
		// com.rays.rEvent.utils.Constants.IMAGES);
		// mBaseAdapter.notifyDataSetChanged();
		// lstList.setAdapter(mBaseAdapter);
		// lstList.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		Log.i("Activity", "setUserVisibleHint");
		if (isVisibleToUser) {
			if (Constants.checkNetworkStatus(c)) {
				// Toast.makeText(c, "AllEventsFragment", Toast.LENGTH_SHORT)
				// .show();
				getAllEvents("");
			} else {
				Constants.showMessageDialog(c, "rEvent",
						"Internet Connection is not available.");
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Activity", "onCreate");
		// setHasOptionsMenu(true);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);

	}

	private void getAllEvents(String data) {
		// TODO Auto-generated method stub
		final LoginRequestTask LoginRequestTask1 = new LoginRequestTask(c,
				new GetAllEventsbean(), GetAllEventsbean.class);
		LoginRequestTask1.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {
				lstList.setVisibility(View.VISIBLE);

				Log.i("Dialog",
						"Dialog : "
								+ LoginRequestTask.mProgressDialog.isShowing());

				if (LoginRequestTask.mProgressDialog.isShowing()) {
					LoginRequestTask.mProgressDialog.dismiss();
				}

				GetAllEventsbean = (com.rays.rEvent.bean.GetAllEventsbean) object;
				if (!mArraylst_GetAllEventsList.isEmpty()) {
					mArraylst_GetAllEventsList.clear();
				}
				if (!mArraylst_GetAllEventsListFilter.isEmpty()) {
					mArraylst_GetAllEventsListFilter.clear();
				}

				mArraylst_GetAllEventsList
						.addAll(GetAllEventsbean.GetAllEventsList);

				for (int i = 0; i < mArraylst_GetAllEventsList.size(); i++) {
					Log.i("TAG",
							"Event Name : "
									+ mArraylst_GetAllEventsList.get(i).EVT_NAME);
					mArraylst_GetAllEventsList
							.get(i)
							.setURL("https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg");

				}

				Log.i("ALL", "ALL Size : " + mArraylst_GetAllEventsList.size());
				if (mbundle != null) {
					for (int j = 0; j < mArraylst_GetAllEventsList.size(); j++) {
						if (mArraylst_GetAllEventsList.get(j).EVT_NAME
								.toLowerCase().contains(strtext.toLowerCase())) {
							mArraylst_GetAllEventsListFilter
									.add(mArraylst_GetAllEventsList.get(j));
						}

					}
				}
				Log.i("TAG",
						"ARRAY SIZE : " + mArraylst_GetAllEventsList.size());
				// if (mbundle != null) {
				// mBaseAdapter = new GetAllEventsAdapter(c,
				// mArraylst_GetAllEventsListFilter);
				// } else {
				mBaseAdapter = new GetAllEventsAdapter(c,
						mArraylst_GetAllEventsList);
				// }
				// mBaseAdapter = new GetAllEventsAdapter(c,
				// mArraylst_GetAllEventsList);
				mBaseAdapter.notifyDataSetChanged();
				lstList.setAdapter(mBaseAdapter);

				// handleAboutMe(txt_pageContent);
			}

			@Override
			public void onResponseReceived(String response) {
			}

			@Override
			public void onErrorReceived(String error) {
			}
		});
		try {
			LoginRequestTask1.execute(Constants.WS_URL_GETALLEVENTS,
					Constants.CONTENT_TYPE_JSON, data).get(
					Constants.SERVICE_TIME_OUT, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

}
