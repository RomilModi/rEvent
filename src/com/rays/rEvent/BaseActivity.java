package com.rays.rEvent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public void initTitleBar(String titleText, int isHomeBtnVisible,
			int isLogoutBtnVisible) {

		((TextView) findViewById(R.id.txt_title)).setText(titleText);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"shruti.ttf");
		((TextView) findViewById(R.id.txt_title)).setTypeface(custom_font);
		// findViewById(R.id.btn_back).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout))
				.setVisibility(isLogoutBtnVisible);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);

		((ImageView) findViewById(R.id.btn_home))
				.setVisibility(isHomeBtnVisible);
		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.btn_back:
		// Log.i("FINISH", "FINISH");
		// finish();
		// break;
		case R.id.btn_logout:
			Intent in = new Intent(this, MainActivity.class);
			startActivity(in);
			finish();
			break;
		case R.id.btn_home:
			Intent in1 = new Intent(this, MainActivity.class);
			startActivity(in1);
			finish();
			break;
		}
	}

}
