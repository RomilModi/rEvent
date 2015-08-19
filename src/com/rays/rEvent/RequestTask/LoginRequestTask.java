package com.rays.rEvent.RequestTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.rays.rEvent.utils.Constants;

public class LoginRequestTask extends AsyncTask<String, Integer, Object> {
	public Context mCon;
	private AsyncCallListener listener;
	private String errorMessage;
	private boolean isError;
	private String response, errorMsg, res;
	// private Loginbean Loginbean;
	public static ProgressDialog mProgressDialog;
	private Object Obj;
	private Class<?> mClass;

	public LoginRequestTask(Context mContext, Object Obj, Class<?> mClass) {
		this.mCon = mContext;
		this.Obj = Obj;
		this.mClass = mClass;
		mProgressDialog = new ProgressDialog(mCon);
		mProgressDialog.setMessage("Please wait...");
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (mProgressDialog != null && !mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}
	}

	@Override
	protected Object doInBackground(String... params) {
		return SignIn(params[0], params[1], params[2]);
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		Log.i("mProgressDialog", "mProgressDialog : " + mProgressDialog);
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		if (listener != null) {
			if (isError) {
				Log.e("", "In Asnyc call->errorMessage:" + errorMessage);
				listener.onErrorReceived(errorMessage);
			} else {
				// listener.onResponseReceived(Loginbean);
				if (res != null) {
					listener.onResponseReceived(Obj);
				}

			}
		}
	}

	public void setAsyncCallListener(AsyncCallListener listener) {
		this.listener = listener;
	}

	private Object SignIn(String url, String jsonContentType, String data) {
		RestFulWebservice ws = new RestFulWebservice();
		try {
			String response = ws.executeWS(url, jsonContentType, data);

			Log.i("Response", "Response : " + response);
			res = response;
			// JSONObject jsonObj = new JSONObject(response);
			if (response != null) {
				Gson gson = new Gson();
				Obj = gson.fromJson(response.toString(), mClass);
			} else {
				((Activity) mCon).runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Constants.showMessageDialog(mCon, "VMS",
								"Service is not Available.");
					}
				});

			}

			// Obj = gson.fromJson(response.toString(), Loginbean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
