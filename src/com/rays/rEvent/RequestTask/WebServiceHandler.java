package com.rays.rEvent.RequestTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WebServiceHandler extends AsyncTask<Object, Void, JSONObject> {
	private ProgressDialog barProgressDialog;

	private Context mcontext;

	public WebServiceHandler() {

	}

	public WebServiceHandler(Context c) {
		mcontext = c;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (null != mcontext) {
			barProgressDialog = ProgressDialog.show(mcontext, "",
					"Please wait ...", true);
		}
	}

	@Override
	protected JSONObject doInBackground(Object... params) {
		JSONObject returnVal = null;
		RestFulWebservice ws = new RestFulWebservice();
		try {
			String response = ws.executeWS(params[0].toString(),
					params[1].toString(), params[2].toString());
			Log.i("Response", "Response is : " + response);
			returnVal = new JSONObject(response);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		try {
			super.onPostExecute(result);
			if (null != mcontext) {
				barProgressDialog.dismiss();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
