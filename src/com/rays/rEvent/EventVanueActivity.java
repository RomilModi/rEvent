package com.rays.rEvent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.bean.EventVanuebean;
import com.rays.rEvent.bean.EventVanuebean.GetEventVanue;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.utils.Constants;

public class EventVanueActivity extends BaseActivity {
	private GetAllEvents GetAllEvents;
	private TextView ActivityEventVanue_txt_EventName,
			ActivityEventVanue_txt_EventFrom, ActivityEventVanue_txt_EventTo,
			ActivityEventVanue_txt_EventVanue,
			ActivityEventVanue_txt_EventCity,
			ActivityEventVanue_txt_EventState,
			ActivityEventVanue_txt_EventZipCode,
			ActivityEventVanue_txt_EventCountry,
			ActivityEventVanue_txt_EventEmail,
			ActivityEventVanue_txt_EventContactPerson,
			ActivityEventVanue_txt_EventContactNumber;
	private EventVanuebean EventVanuebean;
	private ArrayList<GetEventVanue> mArrayLstEventVanue;
	private ImageView ActivityVanueInfo_img_EventLogo;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_vanue_information);
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image)
				.showImageOnLoading(R.drawable.ic_stub).build();

		String title = getIntent().getStringExtra("title");
		GetAllEvents = (com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents) getIntent()
				.getSerializableExtra("AllEventObject");
		Log.e("title", "title : " + title);
		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			initTitleBar(title, View.GONE, View.GONE);
		} else {
			initTitleBar(title, View.GONE, View.VISIBLE);
		}
		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);
		ActivityVanueInfo_img_EventLogo = (ImageView) findViewById(R.id.ActivityVanueInfo_img_EventLogo);
		ActivityEventVanue_txt_EventName = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventName);
		ActivityEventVanue_txt_EventFrom = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventFrom);
		ActivityEventVanue_txt_EventTo = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventTo);
		ActivityEventVanue_txt_EventVanue = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventVanue);
		ActivityEventVanue_txt_EventCity = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventCity);
		ActivityEventVanue_txt_EventState = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventState);
		ActivityEventVanue_txt_EventZipCode = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventZipCode);
		ActivityEventVanue_txt_EventCountry = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventCountry);
		ActivityEventVanue_txt_EventEmail = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventEmail);
		ActivityEventVanue_txt_EventContactPerson = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventContactPerson);
		ActivityEventVanue_txt_EventContactNumber = (TextView) findViewById(R.id.ActivityEventVanue_txt_EventContactNumber);

		mArrayLstEventVanue = new ArrayList<EventVanuebean.GetEventVanue>();
		JSONObject data = new JSONObject();
		try {
			data.put("EVT_ID", GetAllEvents.getEVT_ID());
			getVanueInfo(data.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getVanueInfo(String data) {
		// TODO Auto-generated method stub
		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new EventVanuebean(), EventVanuebean.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {
				EventVanuebean = (EventVanuebean) object;

				mArrayLstEventVanue.addAll(EventVanuebean.GetEventVanue);

				ActivityEventVanue_txt_EventName.setText(mArrayLstEventVanue
						.get(0).EVT_NAME);
				ActivityEventVanue_txt_EventFrom.setText(mArrayLstEventVanue
						.get(0).EVT_START_DATE);
				ActivityEventVanue_txt_EventTo.setText(mArrayLstEventVanue
						.get(0).EVT_END_DATE);
				ActivityEventVanue_txt_EventVanue.setText(mArrayLstEventVanue
						.get(0).EVT_VENUE);
				ActivityEventVanue_txt_EventCity.setText(mArrayLstEventVanue
						.get(0).EVT_CITY);
				ActivityEventVanue_txt_EventState.setText(mArrayLstEventVanue
						.get(0).EVT_STATE);
				ActivityEventVanue_txt_EventZipCode.setText(mArrayLstEventVanue
						.get(0).EVT_ZIP_CODE);
				ActivityEventVanue_txt_EventCountry.setText(mArrayLstEventVanue
						.get(0).EVT_COUNTRY);
				ActivityEventVanue_txt_EventEmail.setText(mArrayLstEventVanue
						.get(0).EVT_EMAIL_ADDRESS);
				ActivityEventVanue_txt_EventContactPerson
						.setText(mArrayLstEventVanue.get(0).EVT_CONTACT_PERSON);
				ActivityEventVanue_txt_EventContactNumber
						.setText(mArrayLstEventVanue.get(0).EVT_CONTACT_NUMBER);
				ImageSize targetSize = new ImageSize(130, 130); // result Bitmap
																// will be fit
																// to this size
				imageLoader.loadImage(mArrayLstEventVanue.get(0).EVT_LOGO_PATH,
						targetSize, options, new SimpleImageLoadingListener() {
							@Override
							public void onLoadingComplete(String imageUri,
									View view, Bitmap loadedImage) {
								// Do whatever you want with Bitmap
								ActivityVanueInfo_img_EventLogo
										.setImageBitmap(loadedImage);
							}
						});
			}

			@Override
			public void onResponseReceived(String response) {
			}

			@Override
			public void onErrorReceived(String error) {
			}
		});
		try {
			LoginRequestTask.execute(Constants.WS_URL_GETVANUEDETAILS,
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
					.getDefaultSharedPreferences(EventVanueActivity.this)
					.edit().putBoolean("isLoggedIn", false).commit();
			break;
		default:
			break;
		}

	}
}
