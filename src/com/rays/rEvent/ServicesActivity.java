package com.rays.rEvent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.adapter.EventServicesAdapter;
import com.rays.rEvent.bean.EventServicesbean;
import com.rays.rEvent.bean.EventServicesbean.GetEventServices;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.utils.Constants;

public class ServicesActivity extends BaseActivity {
	private GetAllEvents GetAllEvents;
	private String title;
	private EventServicesbean EventServicesbean;
	private ArrayList<GetEventServices> mArraylst_GetEventServices;
	private ListView lstList_Services;
	private EventServicesAdapter mBaseAdapter;
	private TextView ActivityEventServices_txt_EventName,
	ActivityEventServices_txt_EventTo, ActivityEventServices_txt_EventFrom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_services);
		lstList_Services = (ListView) findViewById(R.id.lstList_Services);

		mArraylst_GetEventServices = new ArrayList<GetEventServices>();
		GetAllEvents = (com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents) getIntent()
				.getSerializableExtra("AllEventObject");

		title = getIntent().getStringExtra("title");
		ActivityEventServices_txt_EventName = (TextView) findViewById(R.id.ActivityEventServices_txt_EventName);
		ActivityEventServices_txt_EventTo = (TextView) findViewById(R.id.ActivityEventServices_txt_EventTo);
		ActivityEventServices_txt_EventFrom = (TextView) findViewById(R.id.ActivityEventServices_txt_EventFrom);
		ActivityEventServices_txt_EventName.setText(GetAllEvents.EVT_NAME);
		ActivityEventServices_txt_EventTo.setText(GetAllEvents.EVT_START_DATE);
		ActivityEventServices_txt_EventFrom.setText(GetAllEvents.EVT_END_DATE);
		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			initTitleBar(title, View.GONE, View.GONE);
		} else {
			initTitleBar(title, View.GONE, View.VISIBLE);
		}
		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);

		JSONObject data = new JSONObject();
		try {
			data.put("Barcode", GetAllEvents.getBADGE_NO());
			getServicesData(data.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_home:
			Intent in = new Intent(this, MainActivity.class);
			startActivity(in);
			finish();
			break;
		case R.id.btn_logout:
			Intent in1 = new Intent(this, MainActivity.class);
			startActivity(in1);
			finish();
			PreferenceManager
					.getDefaultSharedPreferences(ServicesActivity.this).edit()
					.putBoolean("isLoggedIn", false).commit();
			break;
		default:
			break;
		}

	}

	private void getServicesData(String data) {
		// TODO Auto-generated method stub
		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new EventServicesbean(), EventServicesbean.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {

				EventServicesbean = (EventServicesbean) object;
				mArraylst_GetEventServices
						.addAll(EventServicesbean.GetEventServices);

				mBaseAdapter = new EventServicesAdapter(ServicesActivity.this,
						mArraylst_GetEventServices);
				mBaseAdapter.notifyDataSetChanged();
				lstList_Services.setAdapter(mBaseAdapter);
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
			LoginRequestTask.execute(Constants.WS_URL_GETSERVICES,
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
}
