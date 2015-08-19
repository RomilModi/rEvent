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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.bean.UserProfilebean;
import com.rays.rEvent.bean.UserProfilebean.GetUserProfile;
import com.rays.rEvent.utils.Constants;

public class UserProfileActivity extends BaseActivity {

	private GetAllEvents GetAllEvents;
	private String title = "Profile";
	private TextView ActivityuserProfile_txt_Name,
			ActivityuserProfile_txt_gender, ActivityUserProfile_txt_DOB,
			ActivityUserProfile_txt_Address, ActivityuserProfile_txt_Mobile,
			ActivityuserProfile_txt_Email, ActivityUserProfile_txt_Town,
			ActivityUserProfile_txt_Country;
	private UserProfilebean UserProfilebean;
	private ArrayList<GetUserProfile> mArraylstUserProfile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.userprofile_activity);

		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			initTitleBar(title, View.GONE, View.GONE);
		} else {
			initTitleBar(title, View.GONE, View.VISIBLE);
		}
		mArraylstUserProfile = new ArrayList<UserProfilebean.GetUserProfile>();
		ActivityuserProfile_txt_Name = (TextView) findViewById(R.id.ActivityuserProfile_txt_Name);
		ActivityuserProfile_txt_gender = (TextView) findViewById(R.id.ActivityuserProfile_txt_gender);
		ActivityUserProfile_txt_DOB = (TextView) findViewById(R.id.ActivityUserProfile_txt_DOB);
		ActivityUserProfile_txt_Address = (TextView) findViewById(R.id.ActivityUserProfile_txt_Address);
		ActivityuserProfile_txt_Mobile = (TextView) findViewById(R.id.ActivityuserProfile_txt_Mobile);
		ActivityuserProfile_txt_Email = (TextView) findViewById(R.id.ActivityuserProfile_txt_Email);
		ActivityUserProfile_txt_Town = (TextView) findViewById(R.id.ActivityUserProfile_txt_Town);
		ActivityUserProfile_txt_Country = (TextView) findViewById(R.id.ActivityUserProfile_txt_Country);
		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);
		JSONObject data = new JSONObject();
		try {
			data.put(
					"Email",
					PreferenceManager.getDefaultSharedPreferences(
							UserProfileActivity.this).getString("Username", ""));
			Log.i("DATA", "Data is : " + data.toString());
			getUserProfileData(data.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getUserProfileData(String data) {
		// TODO Auto-generated method stub
		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new UserProfilebean(), UserProfilebean.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {

				UserProfilebean = (UserProfilebean) object;
				mArraylstUserProfile
						.addAll(UserProfilebean.GetUserProfileDetails);
				// handleAboutMe(txt_pageContent);

				for (int i = 0; i < mArraylstUserProfile.size(); i++) {
					Log.e("TAG", "Title :"
							+ mArraylstUserProfile.get(i).MEM_TITLE);
				}
				for (int i = 0; i < mArraylstUserProfile.size(); i++) {

					String MEM_TITLE, MEM_FIRST_NAME, MEM_LAST_NAME, MEM_GENDER, MEM_DOB, MEM_ADDRESS1, MEM_ADDRESS2, MEM_MOBILE, MEM_EMAIL, MEM_TOWN, MEM_COUNTRY;

					if (mArraylstUserProfile.get(i).MEM_TITLE == null) {
						MEM_TITLE = "";
					} else {
						MEM_TITLE = mArraylstUserProfile.get(i).MEM_TITLE;
					}

					if (mArraylstUserProfile.get(i).MEM_FIRST_NAME == null) {
						MEM_FIRST_NAME = "";
					} else {
						MEM_FIRST_NAME = mArraylstUserProfile.get(i).MEM_FIRST_NAME;
					}
					if (mArraylstUserProfile.get(i).MEM_LAST_NAME == null) {
						MEM_LAST_NAME = "";
					} else {
						MEM_LAST_NAME = mArraylstUserProfile.get(i).MEM_LAST_NAME;
					}
					if (mArraylstUserProfile.get(i).MEM_GENDER == null) {
						MEM_GENDER = "";
					} else {
						MEM_GENDER = mArraylstUserProfile.get(i).MEM_GENDER;
					}
					if (mArraylstUserProfile.get(i).MEM_DOB == null) {
						MEM_DOB = "";
					} else {
						MEM_DOB = mArraylstUserProfile.get(i).MEM_DOB;
					}
					if (mArraylstUserProfile.get(i).MEM_ADDRESS1 == null) {
						MEM_ADDRESS1 = "";
					} else {
						MEM_ADDRESS1 = mArraylstUserProfile.get(i).MEM_ADDRESS1;
					}
					if (mArraylstUserProfile.get(i).MEM_ADDRESS2 == null) {
						MEM_ADDRESS2 = "";
					} else {
						MEM_ADDRESS2 = mArraylstUserProfile.get(i).MEM_ADDRESS2;
					}

					if (mArraylstUserProfile.get(i).MEM_MOBILE == null) {
						MEM_MOBILE = "";
					} else {
						MEM_MOBILE = mArraylstUserProfile.get(i).MEM_MOBILE;
					}
					if (mArraylstUserProfile.get(i).MEM_EMAIL == null) {
						MEM_EMAIL = "";
					} else {
						MEM_EMAIL = mArraylstUserProfile.get(i).MEM_EMAIL;
					}
					if (mArraylstUserProfile.get(i).MEM_TOWN == null) {
						MEM_TOWN = "";
					} else {
						MEM_TOWN = mArraylstUserProfile.get(i).MEM_TOWN;
					}
					if (mArraylstUserProfile.get(i).MEM_COUNTRY == null) {
						MEM_COUNTRY = "";
					} else {
						MEM_COUNTRY = mArraylstUserProfile.get(i).MEM_COUNTRY;
					}

					ActivityuserProfile_txt_Name.setText(new StringBuilder()
							.append(MEM_TITLE + " " + MEM_FIRST_NAME + " "
									+ MEM_LAST_NAME));
					ActivityuserProfile_txt_gender.setText(MEM_GENDER);
					ActivityUserProfile_txt_DOB.setText(MEM_DOB);
					ActivityUserProfile_txt_Address.setText(MEM_ADDRESS1 + " "
							+ MEM_ADDRESS2);
					ActivityuserProfile_txt_Mobile.setText(MEM_MOBILE);
					ActivityuserProfile_txt_Email.setText(MEM_EMAIL);
					ActivityUserProfile_txt_Town.setText(MEM_TOWN);
					ActivityUserProfile_txt_Country.setText(MEM_COUNTRY);
				}

			}

			@Override
			public void onResponseReceived(String response) {
			}

			@Override
			public void onErrorReceived(String error) {
			}
		});
		try {
			LoginRequestTask.execute(Constants.WS_URL_GETUSERPROFILE,
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
					.getDefaultSharedPreferences(UserProfileActivity.this)
					.edit().putBoolean("isLoggedIn", false).commit();
			break;
		default:
			break;
		}

	}
}
