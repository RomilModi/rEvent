package com.rays.rEvent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.adapter.HomeGridAdapter;
import com.rays.rEvent.bean.EventPDFbean;
import com.rays.rEvent.bean.EventPDFbean.GetEventPDF;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.enums.HomeGrid;
import com.rays.rEvent.utils.Constants;
import com.rays.rEvent.utils.PDFTools;

public class EventDashBoardActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener {

	private Intent in;
	private boolean phone_status;
	private HomeGridAdapter mBaseAdapterHomeGrid;
	private ImageView betileft;
	private GetAllEvents GetAllEvents;
	private WebView webView;
	private EventPDFbean EventPDFbean;
	private ArrayList<GetEventPDF> mArraylstEventPDF;
	public static final int progress_bar_type = 0;
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_event_dashboard);
		// getSIMStatus();
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		Log.e("YAB", "SIM : " + tm.getSimState());
		// The phone has SIM card
		// No SIM card on the phone
		phone_status = tm.getSimState() != TelephonyManager.SIM_STATE_UNKNOWN;

		GetAllEvents = (com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents) getIntent()
				.getSerializableExtra("AllEventObject");
		Log.e("YAB", "SIM : " + tm.getSimState() + " & Number : "
				+ GetAllEvents.EVT_CONTACT_NUMBER);
		mArraylstEventPDF = new ArrayList<EventPDFbean.GetEventPDF>();
		mBaseAdapterHomeGrid = new HomeGridAdapter(this);
		((GridView) findViewById(R.id.activityhome_gridview))
				.setAdapter(mBaseAdapterHomeGrid);
		((GridView) findViewById(R.id.activityhome_gridview))
				.setOnItemClickListener(this);
		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			initTitleBar("rEvent", View.VISIBLE, View.GONE);
		} else {
			initTitleBar("rEvent", View.VISIBLE, View.VISIBLE);
		}
		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);
	}

	private void getSIMStatus() {
		// TODO Auto-generated method stub
		TelephonyManager telMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		int simState = telMgr.getSimState();
		switch (simState) {
		case TelephonyManager.SIM_STATE_ABSENT:
			// do something
			Log.i("Absent", "Absent");
			break;
		case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
			Log.i("LOCKED", "LOCKED");
			// do something
			break;
		case TelephonyManager.SIM_STATE_PIN_REQUIRED:
			Log.i("PIN_REQUIRED", "PIN_REQUIRED");
			// do something
			break;
		case TelephonyManager.SIM_STATE_PUK_REQUIRED:
			Log.i("PUK_REQUIRED", "PUK_REQUIRED");
			// do something
			break;
		case TelephonyManager.SIM_STATE_READY:
			Log.i("STATE_READY", "STATE_READY");
			// do something
			break;
		case TelephonyManager.SIM_STATE_UNKNOWN:
			Log.i("STATE_UNKNOWN", "STATE_UNKNOWN");
			phone_status = false;
			// do something
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		HomeGrid selected = HomeGrid.getByIndex(position);
		// Toast.makeText(getApplicationContext(), selected.getName(),
		// Toast.LENGTH_SHORT).show();
		performOperation(selected);
	}

	private void performOperation(HomeGrid selected) {
		// TODO Auto-generated method stub
		if (HomeGrid.PDF.equals(selected)) {

			JSONObject data = new JSONObject();
			try {

				data.put("Barcode", GetAllEvents.getBADGE_NO());
				Log.i("BADGENO", "Badge No : " + GetAllEvents.getBADGE_NO());
				getEventPDF(data.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (HomeGrid.SUPPORT.equals(selected)) {
			if (phone_status) {

				getCall(GetAllEvents.EVT_CONTACT_NUMBER);
			} else {
				Constants.showMessageDialog(this,
						getResources().getString(R.string.app_name),
						"Phone Call is not allowed in your Device.");
			}

		} else {
			Intent intent = new Intent(EventDashBoardActivity.this,
					selected.getKlass());
			intent.putExtra("title",
					getResources().getString(selected.getName()));
			intent.putExtra("from", getResources()
					.getString(selected.getName()));
			intent.putExtra("AllEventObject", GetAllEvents);
			startActivity(intent);
		}

	}

	private void getCall(String number) {
		// TODO Auto-generated method stub
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + number));
		startActivity(callIntent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
					.getDefaultSharedPreferences(EventDashBoardActivity.this)
					.edit().putBoolean("isLoggedIn", false).commit();
			break;
		default:
			break;
		}
	}

	private void getEventPDF(String data) {
		// TODO Auto-generated method stub

		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new EventPDFbean(), EventPDFbean.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {

				EventPDFbean = (com.rays.rEvent.bean.EventPDFbean) object;
				mArraylstEventPDF.addAll(EventPDFbean.GetLoginDetails);
				String pdfURL = mArraylstEventPDF.get(0).URL;
				// PDFTools.downloadAndOpenPDF(EventDashBoardActivity.this,
				// pdfURL);
				File f = new File(Environment.getExternalStorageDirectory()
						.getPath(), "rEvent" + "/rEvent.pdf");
				if (f.exists()) {
					f.delete();
				}
				if (f.exists()) {
					/* do something */
					openPdfIntent(Environment.getExternalStorageDirectory()
							.getPath()
							+ File.separator
							+ "rEvent"
							+ "/rEvent.pdf");
				} else {
					new DownloadFileFromURL().execute(pdfURL);
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
			LoginRequestTask.execute(Constants.WS_URL_GETRECEIPT,
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

	class DownloadFileFromURL extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Bar Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(progress_bar_type);
		}

		/**
		 * Downloading file in background thread
		 * */
		@Override
		protected String doInBackground(String... f_url) {
			int count;
			try {
				URL url = new URL(f_url[0]);
				URLConnection conection = url.openConnection();
				conection.connect();
				// getting file length
				int lenghtOfFile = conection.getContentLength();

				// input stream to read file - with 8k buffer
				InputStream input = new BufferedInputStream(url.openStream(),
						8192);

				File outputFile = new File(Environment
						.getExternalStorageDirectory().getPath(), "rEvent");
				if (!outputFile.exists()) {
					outputFile.mkdirs();
				}
				// Output stream to write file
				OutputStream output = new FileOutputStream(
						outputFile.toString() + File.separator + "rEvent.pdf");

				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					// publishing the progress....
					// After this onProgressUpdate will be called
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));

					// writing data to file
					output.write(data, 0, count);
				}

				// flushing output
				output.flush();

				// closing streams
				output.close();
				input.close();

			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
			}

			return null;
		}

		/**
		 * Updating progress bar
		 * */
		protected void onProgressUpdate(String... progress) {
			// setting progress percentage
			pDialog.setProgress(Integer.parseInt(progress[0]));
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after the file was downloaded
			dismissDialog(progress_bar_type);
			openPdfIntent(Environment.getExternalStorageDirectory().getPath()
					+ File.separator + "rEvent" + "/rEvent.pdf");
			// Displaying downloaded image into image view
			// Reading image path from sdcard

		}

	}

	private void openPdfIntent(String path) {
		try {
			final Intent intent = new Intent(this, ActivityPDFFullScreen.class);
			intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, path);
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class Callback extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return (false);
		}
	}

	/**
	 * Showing Dialog
	 * */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case progress_bar_type:
			pDialog = new ProgressDialog(this);
			pDialog.setMessage("Downloading file. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setMax(100);
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.setCancelable(true);
			pDialog.show();
			return pDialog;
		default:
			return null;
		}
	}
}
