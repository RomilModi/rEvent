package com.rays.rEven.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class TouchyWebView extends WebView {

	public TouchyWebView(Context context) {
		super(context);
	}

	public TouchyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TouchyWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		requestDisallowInterceptTouchEvent(true);
		return super.onTouchEvent(event);
	}
}
