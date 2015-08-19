package com.rays.rEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.bean.EventPDFbean;
import com.rays.rEvent.bean.EventPDFbean.GetEventPDF;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.utils.Constants;

public class EventPDF extends Activity {
	private EventPDFbean EventPDFbean;
	private GetAllEvents GetAllEvents;
	private ArrayList<GetEventPDF> mArraylstEventPDF;
	private static final String GOOGLE_DRIVE_PDF_READER_PREFIX = "http://drive.google.com/viewer?url=";
	private static final String PDF_MIME_TYPE = "application/pdf";
	private static final String HTML_MIME_TYPE = "text/html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdfreader);

		GetAllEvents = (com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents) getIntent()
				.getSerializableExtra("AllEventObject");
		mArraylstEventPDF = new ArrayList<EventPDFbean.GetEventPDF>();
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
				if (mArraylstEventPDF.get(0).Flag.equalsIgnoreCase("true")) {
					if (isPDFSupported(EventPDF.this)) {
						downloadAndOpenPDF(EventPDF.this, pdfURL);
					} else {
						// openPDFThroughGoogleDrive(PdfReaderActivity.this,
						// pdfDataModel.getUrl());
						// "https://play.google.com/store/apps/details?id=com.adobe.reader"
						finish();
						Intent browserIntent = new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader"
										+ ""));
						startActivity(browserIntent);
					}
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

	public void downloadAndOpenPDF(final Context context, final String pdfUrl) {
		// Get filename
		final String filename = pdfUrl.substring(pdfUrl.lastIndexOf("/") + 1);
		// The place where the downloaded PDF file will be put
		final File tempFile = new File(
				context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
				filename);
		if (tempFile.exists()) {
			// If we have downloaded the file before, just go ahead and show it.
			openPDF(context, Uri.fromFile(tempFile));
			return;
		}

		// Show progress dialog while downloading
		final ProgressDialog progress = ProgressDialog.show(context,
				context.getString(R.string.pdf_show_local_progress_title),
				context.getString(R.string.pdf_show_local_progress_content),
				true);

		// Create the download request
		DownloadManager.Request r = new DownloadManager.Request(
				Uri.parse(pdfUrl));
		r.setDestinationInExternalFilesDir(context,
				Environment.DIRECTORY_DOWNLOADS, filename);
		final DownloadManager dm = (DownloadManager) context
				.getSystemService(Context.DOWNLOAD_SERVICE);
		BroadcastReceiver onComplete = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				if (!progress.isShowing()) {
					return;
				}
				context.unregisterReceiver(this);

				progress.dismiss();
				long downloadId = intent.getLongExtra(
						DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				Cursor c = dm.query(new DownloadManager.Query()
						.setFilterById(downloadId));

				if (c.moveToFirst()) {
					int status = c.getInt(c
							.getColumnIndex(DownloadManager.COLUMN_STATUS));
					if (status == DownloadManager.STATUS_SUCCESSFUL) {
						openPDF(context, Uri.fromFile(tempFile));
					}
				}
				c.close();
			}
		};
		context.registerReceiver(onComplete, new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE));

		// Enqueue the request
		dm.enqueue(r);
	}

	public final void openPDF(Context context, Uri localUri) {
		finish();
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(localUri, PDF_MIME_TYPE);
		context.startActivity(i);
	}

	public static void openPDFThroughGoogleDrive(final Context context,
			final String pdfUrl) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse(GOOGLE_DRIVE_PDF_READER_PREFIX + pdfUrl),
				HTML_MIME_TYPE);
		context.startActivity(i);
	}

	public static boolean isPDFSupported(Context context) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		final File tempFile = new File(
				context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
				"test.pdf");
		i.setDataAndType(Uri.fromFile(tempFile), PDF_MIME_TYPE);
		return context.getPackageManager()
				.queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY)
				.size() > 0;
	}

}
