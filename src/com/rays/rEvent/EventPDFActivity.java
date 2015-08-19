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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.bean.EventPDFbean;
import com.rays.rEvent.bean.EventPDFbean.GetEventPDF;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.utils.Constants;

public class EventPDFActivity extends BaseActivity {

	private GetAllEvents GetAllEvents;
	private EventPDFbean EventPDFbean;
	private ArrayList<GetEventPDF> mArraylstEventPDF;
	public static final int progress_bar_type = 0;
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pdf);
		String title = getIntent().getStringExtra("title");
		Log.e("title", "title : " + title);
		mArraylstEventPDF = new ArrayList<EventPDFbean.GetEventPDF>();
		GetAllEvents = (com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents) getIntent()
				.getSerializableExtra("AllEventObject");
		initTitleBar(title, View.GONE, View.VISIBLE);

		JSONObject data = new JSONObject();
		try {
			data.put("Barcode", GetAllEvents.getBADGE_NO());
			getEventPDF(data.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				String pdfURL = /* "http://dl.dropboxusercontent.com/u/37098169/Course%20Brochures/AND101.pdf" */mArraylstEventPDF
						.get(0).URL;
				// webView.loadUrl("https://docs.google.com/gview?embedded=true&url="
				// + pdfURL);
				// webView.loadUrl(pdfURL);

				File f = new File(Environment.getExternalStorageDirectory()
						.getPath(), "rEvent" + "/rEvent.pdf");
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
			LoginRequestTask.execute(Constants.WS_URL_GETEVENTPDF,
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
