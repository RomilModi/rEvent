package com.rays.rEvent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.adapter.GetMyEventsAdapter;
import com.rays.rEvent.bean.GetAllEventsbean;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.utils.Constants;

public class MyEventsFregment extends Fragment implements OnItemClickListener {

	public static ListView lstList_AllMyEvents;
	// private com.rays.rEvent.adapter.ImageAdapter mBaseAdapter;
	private ArrayList<Integer> mArray;
	private GetAllEventsbean GetAllEventsbean;
	public static ArrayList<GetAllEvents> mArraylst_GetAllEventsList;
	public static GetMyEventsAdapter mBaseAdapter;
	private String strtext;
	private Bundle mbundle;
	public static View view;
	public static Context c;
	public static ArrayList<GetAllEvents> array_sort;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mArraylst_GetAllEventsList = new ArrayList<GetAllEventsbean.GetAllEvents>();
		Log.e("TAG1", "onAttach : " + activity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.activity_my_events, container, false);
		lstList_AllMyEvents = (ListView) view
				.findViewById(R.id.lstList_AllMyEvents);
		c = getActivity();
		array_sort = new ArrayList<GetAllEventsbean.GetAllEvents>();
		Log.e("TAG1", "onCreateView : " + getActivity());
		// Log.e("MyEventsFregment", "isRemembered : "
		// + PreferenceManager.getDefaultSharedPreferences(getActivity())
		// .getBoolean("isLoggedIn", false)
		// + " & Count : "
		// + PreferenceManager.getDefaultSharedPreferences(getActivity())
		// .getInt("Count", 0));
		// if (PreferenceManager.getDefaultSharedPreferences(getActivity())
		// .getInt("Count", 0) == 0) {
		// view = inflater.inflate(R.layout.activity_my_events, container,
		// false);
		// } else {

		// if (PreferenceManager.getDefaultSharedPreferences(getActivity())
		// .getBoolean("isLoggedIn", false)) {
		// view = inflater.inflate(R.layout.activity_my_events, container,
		// false);
		// mArraylst_GetAllEventsList = new
		// ArrayList<GetAllEventsbean.GetAllEvents>();
		// mArraylst_GetAllEventsListFilter = new
		// ArrayList<GetAllEventsbean.GetAllEvents>();
		// lstList_AllMyEvents = (ListView) view
		// .findViewById(R.id.lstList_AllMyEvents);
		// lstList_AllMyEvents.invalidate();
		// lstList_AllMyEvents.setTextFilterEnabled(true);
		// lstList_AllMyEvents.setOnItemClickListener(this);
		// if (Constants.checkNetworkStatus(getActivity())) {
		// JSONObject data = new JSONObject();
		// try {
		// data.put("Email", PreferenceManager
		// .getDefaultSharedPreferences(getActivity())
		// .getString("Username", "0"));
		// Log.i("DATA", "data : " + data);
		// getMyEvents(data.toString());
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// } else {
		// Constants.showMessageDialog(getActivity(), "rEvent",
		// "Internet Connection is not available.");
		// }
		// } else {
		// Intent intent = new Intent(getActivity(), ActivityLogin.class);
		// startActivity(intent);
		// }
		// }

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("TAG1", "onCreate : " + getActivity());
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("TAG1",
				"onResume : "
						+ getUserVisibleHint()
						+ " &  PreferenceManager : "
						+ PreferenceManager.getDefaultSharedPreferences(c)
								.getBoolean("Manage", false)
						+ " & Temp1 : "
						+ PreferenceManager.getDefaultSharedPreferences(c)
								.getBoolean("Temp1", false));

		if (!getUserVisibleHint()) {
			return;
		}

		if (PreferenceManager.getDefaultSharedPreferences(c).getBoolean(
				"Temp1", false)) {
			getActivity().finish();
			return;
		}
		if (PreferenceManager.getDefaultSharedPreferences(c).getBoolean(
				"isLoggedIn", false)) {
			// Toast.makeText(c, "MyEventsFragment", Toast.LENGTH_SHORT).show();

			if (!mArraylst_GetAllEventsList.isEmpty()) {
				mArraylst_GetAllEventsList.clear();
			}
			lstList_AllMyEvents.invalidate();
			lstList_AllMyEvents.setVisibility(View.GONE);
			lstList_AllMyEvents.setTextFilterEnabled(true);
			lstList_AllMyEvents.setOnItemClickListener(this);

			if (Constants.checkNetworkStatus(c)) {
				JSONObject data = new JSONObject();
				try {
					data.put("MemberID",
							PreferenceManager.getDefaultSharedPreferences(c)
									.getString("MemberId", "0"));
					Log.i("DATA", "data : " + data);
					getMyEvents(data.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				Constants.showMessageDialog(c, "rEvent",
						"Internet Connection is not available.");
			}
		} else {
			Log.e("TAG1", "Else Redirecting to Login");
			Intent intent = new Intent(c, ActivityLogin.class);
			startActivity(intent);

		}

	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		Log.i("TAG1", "setUserVisibleHint " + " & Log : " + isVisibleToUser
				+ " & getActivity : " + c + " & isResumed : " + isResumed());
		if (isVisibleToUser && isResumed()) {
			Log.i("isLoggedIn", "isLoggedIn : "
					+ PreferenceManager.getDefaultSharedPreferences(c)
							.getBoolean("isLoggedIn", false));
			onResume();
		}
	}

	// public void MyEvents() {
	// // TODO Auto-generated method stub
	// mArraylst_GetAllEventsList = new
	// ArrayList<GetAllEventsbean.GetAllEvents>();
	// mArraylst_GetAllEventsListFilter = new
	// ArrayList<GetAllEventsbean.GetAllEvents>();
	// LayoutInflater inflater = LayoutInflater.from(getActivity());
	// view = inflater.inflate(R.layout.activity_my_events, null, false);
	//
	// lstList_AllMyEvents = (ListView) view
	// .findViewById(R.id.lstList_AllMyEvents);
	// lstList_AllMyEvents.invalidate();
	// lstList_AllMyEvents.setTextFilterEnabled(true);
	// lstList_AllMyEvents.setOnItemClickListener(this);
	// if (Constants.checkNetworkStatus(getActivity())) {
	// JSONObject data = new JSONObject();
	// try {
	// data.put(
	// "Email",
	// PreferenceManager.getDefaultSharedPreferences(
	// getActivity()).getString("Username", "0"));
	// Log.i("DATA", "data : " + data);
	// getMyEvents(data.toString());
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// } else {
	// Constants.showMessageDialog(getActivity(), "rEvent",
	// "Internet Connection is not available.");
	// }
	// }

	private void getMyEvents(String data) {
		// TODO Auto-generated method stub
		LoginRequestTask LoginRequestTask1 = new LoginRequestTask(c,
				new GetAllEventsbean(), GetAllEventsbean.class);
		LoginRequestTask1.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {

				if (LoginRequestTask.mProgressDialog.isShowing()) {
					LoginRequestTask.mProgressDialog.dismiss();
				}

				PreferenceManager.getDefaultSharedPreferences(c).edit()
						.putBoolean("Manage", true).commit();
				lstList_AllMyEvents.setVisibility(View.VISIBLE);
				GetAllEventsbean = (com.rays.rEvent.bean.GetAllEventsbean) object;
				if (!mArraylst_GetAllEventsList.isEmpty()) {
					mArraylst_GetAllEventsList.clear();
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

				Log.i("ALL", "My Size : " + mArraylst_GetAllEventsList.size());

				Log.i("TAG",
						"ARRAY SIZE : " + mArraylst_GetAllEventsList.size());
				mBaseAdapter = new GetMyEventsAdapter(c,
						mArraylst_GetAllEventsList);
				// mBaseAdapter = new GetAllEventsAdapter(getActivity(),
				// mArraylst_GetAllEventsList);
				mBaseAdapter.notifyDataSetChanged();
				lstList_AllMyEvents.setAdapter(mBaseAdapter);

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
			LoginRequestTask1.execute(Constants.WS_URL_GETCOMPANYWISEEVENTS,
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
