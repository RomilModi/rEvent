package com.rays.rEvent.RequestTask;

public interface AsyncCallListener {

	void onResponseReceived(String response);

	void onResponseReceived(Object object);

	void onErrorReceived(String error);
}
