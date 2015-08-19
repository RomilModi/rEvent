package com.rays.rEvent;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rays.rEvent.bean.Place;

public class RestaurentDetailsActivity extends BaseActivity {

	private Place p;
	private TextView ActivityRestaurantDetails_txt_Name,
			ActivityRestaurantDetails_txt_Address;
	private RatingBar ActivityRestaurantDetails_Ratingbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.restaurent_details);
		ActivityRestaurantDetails_txt_Name = (TextView) findViewById(R.id.ActivityRestaurantDetails_txt_Name);
		ActivityRestaurantDetails_txt_Address = (TextView) findViewById(R.id.ActivityRestaurantDetails_txt_Address);
		ActivityRestaurantDetails_Ratingbar = (RatingBar) findViewById(R.id.ActivityRestaurantDetails_Ratingbar);
		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			initTitleBar("Restaurant Details", View.GONE, View.GONE);
		} else {
			initTitleBar("Restaurant Details", View.GONE, View.VISIBLE);
		}
		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);
		p = (Place) getIntent().getSerializableExtra("PlaceObject");
		ActivityRestaurantDetails_Ratingbar.setNumStars(5);
		Log.e("NAME", "NAME is : " + p.getRating() + " & " + p.getVicinity()
				+ " & : " + p.getRating());
		ActivityRestaurantDetails_txt_Name.setText(p.getName());
		ActivityRestaurantDetails_txt_Address.setText(p.getVicinity());
		if (p.getRating() == null) {
			ActivityRestaurantDetails_Ratingbar.setProgress(0);
		} else {
			ActivityRestaurantDetails_Ratingbar.setRating(Float.parseFloat(p
					.getRating()));
		}
		ActivityRestaurantDetails_Ratingbar.setStepSize((float) 0.5);
		ActivityRestaurantDetails_Ratingbar.setEnabled(false);
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
					.getDefaultSharedPreferences(RestaurentDetailsActivity.this)
					.edit().putBoolean("isLoggedIn", false).commit();
			break;
		default:
			break;
		}

	}
}
