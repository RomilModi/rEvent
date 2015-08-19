package com.rays.rEvent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ActivityContactUs extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		String from = getIntent().getStringExtra("from");
		if (from.equalsIgnoreCase("Contact Us")) {
			setContentView(R.layout.contact_us_activity);
			initTitleBar(from, View.GONE, View.VISIBLE);
		} else {
			setContentView(R.layout.about_us_activity);
			initTitleBar(from, View.GONE, View.VISIBLE);
		}
	}
}
