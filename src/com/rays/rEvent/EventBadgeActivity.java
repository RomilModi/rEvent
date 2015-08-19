package com.rays.rEvent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rays.rEven.view.TouchyWebView;
import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.adapter.EventBadgeAdapter;
import com.rays.rEvent.bean.BadgePDF;
import com.rays.rEvent.bean.BadgePDF.GetBadgePDFDetails;
import com.rays.rEvent.bean.EventBadgebean;
import com.rays.rEvent.bean.EventBadgebean.GetBadgeDetails;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.utils.Constants;

public class EventBadgeActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {

	private GetAllEvents GetAllEvents;
	private String title;
	private ImageView ActivityBadge_img_Badge;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private TouchyWebView ActivityEventBadge_WebView;
	private String txt_content;
	private EventBadgebean EventBadgebean;
	private ArrayList<GetBadgeDetails> mArraylstEventBadgeDetails;
	private ListView ActivityBadge_lst_Badge;
	private EventBadgeAdapter mBaseAdapter;
	private BadgePDF BadgePDF;
	private ArrayList<GetBadgePDFDetails> mArraylstBadgePDF;
	public static final int progress_bar_type = 0;
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_eventbadge);
		mArraylstBadgePDF = new ArrayList<BadgePDF.GetBadgePDFDetails>();
		ActivityBadge_lst_Badge = (ListView) findViewById(R.id.ActivityBadge_lst_Badge);
		ActivityBadge_lst_Badge.setOnItemClickListener(this);
		mArraylstEventBadgeDetails = new ArrayList<EventBadgebean.GetBadgeDetails>();
		// ActivityEventBadge_WebView = (TouchyWebView)
		// findViewById(R.id.ActivityEventBadge_WebView);
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image)
				.showImageOnLoading(R.drawable.ic_stub).build();

		mArraylstEventBadgeDetails = new ArrayList<EventBadgebean.GetBadgeDetails>();

		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);

		// ActivityBadge_img_Badge = (ImageView)
		// findViewById(R.id.ActivityBadge_img_Badge);

		GetAllEvents = (com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents) getIntent()
				.getSerializableExtra("AllEventObject");

		title = getIntent().getStringExtra("title");
		if (!PreferenceManager.getDefaultSharedPreferences(
				EventBadgeActivity.this).getBoolean("isLoggedIn", false)) {
			initTitleBar(title, View.GONE, View.GONE);
		} else {
			initTitleBar(title, View.GONE, View.VISIBLE);
		}

		JSONObject data = new JSONObject();

		try {
			data.put("Barcode", GetAllEvents.getBADGE_NO());
			getBadgeData(data.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getBadgeData(String data) {
		// TODO Auto-generated method stub
		Log.e("TAG", "Barcode : " + data.toString());
		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new EventBadgebean(), EventBadgebean.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {

				EventBadgebean = (EventBadgebean) object;
				mArraylstEventBadgeDetails
						.addAll(EventBadgebean.GetGetBadgeDetails);

				mBaseAdapter = new EventBadgeAdapter(EventBadgeActivity.this,
						mArraylstEventBadgeDetails);
				mBaseAdapter.notifyDataSetChanged();
				ActivityBadge_lst_Badge.setAdapter(mBaseAdapter);

				// for (int i = 0; i < mArraylstEventBadgeDetails.size(); i++) {
				// txt_content =
				// mArraylstEventBadgeDetails.get(i).ASSOCIATE_TABLE;
				// }
				//
				// ActivityEventBadge_WebView.setVerticalScrollBarEnabled(false);
				// ActivityEventBadge_WebView.setHorizontalScrollBarEnabled(false);
				// ActivityEventBadge_WebView.setScrollContainer(false);
				// ActivityEventBadge_WebView.setPadding(0, 0, 0, 0);
				// ActivityEventBadge_WebView.getSettings().setJavaScriptEnabled(
				// true);
				// txt_content = getHtmlData(EventBadgeActivity.this,
				// txt_content);
				// ActivityEventBadge_WebView.loadDataWithBaseURL(null,
				// txt_content, "text/html", "utf-8", "about:blank");
				// ImageSize targetSize = new ImageSize(130, 130); // result
				// Bitmap
				// // will be fit
				// // to this size
				// imageLoader
				// .loadImage(
				// "https://lh4.googleusercontent.com/-GztnWEIiMz8/URqukVCU7bI/AAAAAAAAAbs/jo2Hjv6MZ6M/s1024/Countryside.jpg",
				// targetSize, options,
				// new SimpleImageLoadingListener() {
				// @Override
				// public void onLoadingComplete(
				// String imageUri, View view,
				// Bitmap loadedImage) {
				// // Do whatever you want with Bitmap
				// ActivityBadge_img_Badge
				// .setImageBitmap(loadedImage);
				// }
				// });
				// handleAboutMe(txt_pageContent);
			}

			@Override
			public void onResponseReceived(String response) {
			}

			@Override
			public void onErrorReceived(String error) {
			}
		});
		try {
			LoginRequestTask.execute(Constants.WS_URL_GETBADGEDETAILS,
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

	private String getHtmlData(Context context, String data) {
		StringBuilder response = new StringBuilder();
		response.append("<html> <head>");
		response.append("<meta http-equiv='content-type' content='text/html;' charset='UTF-8'>");
		response.append("<style> @font-face { font-family: 'Shruti'; src: url('file:///android_asset/shruti.ttf'); } ");
		response.append(" body { font-family: 'Shruti'; } ");
		response.append("</style>");
		response.append("</head> <body>");
		response.append(data);
		response.append("</body> </html>");
		return response.toString();
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
					.getDefaultSharedPreferences(EventBadgeActivity.this)
					.edit().putBoolean("isLoggedIn", false).commit();
			((ImageView) findViewById(R.id.btn_logout))
					.setVisibility(View.GONE);
			break;

		default:
			break;
		}
		super.onClick(v);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		// Log.e("TAG", "TAG : " +
		// mArraylstEventBadgeDetails.get(position).Mesg);
		// if (mArraylstEventBadgeDetails.get(position).Flag
		// .equalsIgnoreCase("true")) {
		// GetBadgeDetails GetBadgeDetails = new EventBadgebean().new
		// GetBadgeDetails();
		// GetBadgeDetails.setREG_TITLE(mArraylstEventBadgeDetails
		// .get(position).REG_TITLE);
		// GetBadgeDetails.setREG_FIRST_NAME(mArraylstEventBadgeDetails
		// .get(position).REG_FIRST_NAME);
		// GetBadgeDetails.setREG_LAST_NAME(mArraylstEventBadgeDetails
		// .get(position).REG_LAST_NAME);
		// GetBadgeDetails.setREG_MIDDLE_NAME(mArraylstEventBadgeDetails
		// .get(position).REG_MIDDLE_NAME);
		// GetBadgeDetails.setREG_BADGE_NO(mArraylstEventBadgeDetails
		// .get(position).REG_BADGE_NO);
		// Intent in = new Intent(this, EventBadgeDetailsActivity.class);
		// in.putExtra("title", title);
		// in.putExtra("Object", GetBadgeDetails);
		// startActivity(in);
		// } else {
		// Constants.showMessageDialog(EventBadgeActivity.this, "rEvent",
		// mArraylstEventBadgeDetails.get(position).Mesg);
		// }

		JSONObject data = new JSONObject();

		try {
			data.put("Barcode", GetAllEvents.getBADGE_NO());
			getBadgePDFData(data.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getBadgePDFData(String data) {
		// TODO Auto-generated method stub
		Log.e("TAG", "Barcode : " + data.toString());
		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new BadgePDF(), BadgePDF.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@SuppressLint("SetJavaScriptEnabled")
			@Override
			public void onResponseReceived(Object object) {

				// EventbadgePDF = (com.rays.rEvent.bean.EventbadgePDF) object;
				// mArraylstPDF.addAll(EventbadgePDF.GetBadgePDF);
				BadgePDF = (com.rays.rEvent.bean.BadgePDF) object;
				mArraylstBadgePDF.addAll(BadgePDF.GetBadgePDFDetails);
				Log.i("TAG", "Table : " + mArraylstBadgePDF.get(0).CONTENT);
				String pdfURL = mArraylstBadgePDF.get(0).CONTENT;
				File f = new File(Environment.getExternalStorageDirectory()
						.getPath(), "rEvent" + "/Badge.pdf");
				if (f.exists()) {
					f.delete();
				}
				if (f.exists()) {
					/* do something */
					openPdfIntent(Environment.getExternalStorageDirectory()
							.getPath()
							+ File.separator
							+ "rEvent"
							+ "/Badge.pdf");
				} else {
					new DownloadFileFromURL().execute(pdfURL);
				}

				// try {
				// // String k =
				// // "<html><body> This is my Project </body></html>";
				// String html = getHtmlData(EventBadgeActivity.this,
				// mArraylstBadgePDF.get(0).CONTENT);
				// FileOutputStream file = new FileOutputStream(new File(
				// Environment.getExternalStorageDirectory().getPath()
				// + File.separator + "rEvent"
				// + File.separator + "Badge.pdf"));
				// Document document = new Document();
				// PdfWriter writer = PdfWriter.getInstance(document, file);
				// document.open();
				// ByteArrayInputStream is = new ByteArrayInputStream(html
				// .getBytes());
				// XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				// is);
				// document.close();
				// file.close();
				// } catch (Exception e) {
				// e.printStackTrace();
				// }

				// try {
				// // String html =
				// // "<html><body> This is my Project </body></html>";
				// String html = getHtmlData(EventBadgeActivity.this,
				// mArraylstBadgePDF.get(0).CONTENT);
				// FileOutputStream file = new FileOutputStream(new File(
				// Environment.getExternalStorageDirectory().getPath()
				// + File.separator + "rEvent"
				// + File.separator + "Badge.pdf"));
				// Document document = new Document(PageSize.A4);
				// PdfWriter.getInstance(document, file);
				// document.open();
				// HTMLWorker htmlWorker = new HTMLWorker(document);
				// htmlWorker.parse(new StringReader(html));
				// document.close();
				// file.close();
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				//
				// openPdfIntent(Environment.getExternalStorageDirectory()
				// .getPath()
				// + File.separator
				// + "rEvent"
				// + File.separator
				// + "Badge.pdf");

				// try {
				// // String html = "<html><head></head><body>" + ma
				// // + "</body></html>";
				//
				// String html = getHtmlData(EventBadgeDetailsActivity.this,
				// mArraylstBadgePDF.get(0).ASSOCIATE_TABLE);
				//
				// Document doc = new Document();
				// ByteArrayInputStream in = new ByteArrayInputStream(html
				// .getBytes());
				// PdfWriter pdf = null;
				// try {
				// pdf = PdfWriter.getInstance(doc, new FileOutputStream(
				// Environment.getExternalStorageDirectory()
				// .getPath()
				// + File.separator
				// + "rEvent"
				// + File.separator + "out.pdf"));
				// } catch (FileNotFoundException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (DocumentException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// doc.open();
				// try {
				// XMLWorkerHelper.getInstance().parseXHtml(pdf, doc, in);
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// doc.close();
				// try {
				// in.close();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// } catch (Exception e) {
				// System.out.println(e.getMessage());
				// }

				// EventBadgebean = (com.rays.rEvent.bean.EventBadgebean)
				// object;
				// mArraylstBadgeDetails.addAll(EventBadgebean.GetGetBadgeDetails);
				//
				// String flag = null, msg = null;
				// for (int i = 0; i < mArraylstBadgeDetails.size(); i++) {
				// txt_content = mArraylstBadgeDetails.get(i).BADGE_FORMAT;
				// flag = mArraylstBadgeDetails.get(i).Flag;
				// msg = mArraylstBadgeDetails.get(i).Mesg;
				// }
				//
				// if (flag.equalsIgnoreCase("true")) {
				// ActivityEventBadge_WebView
				// .setVerticalScrollBarEnabled(false);
				// ActivityEventBadge_WebView
				// .setHorizontalScrollBarEnabled(false);
				// ActivityEventBadge_WebView.setScrollContainer(false);
				// ActivityEventBadge_WebView.setPadding(0, 0, 0, 0);
				// ActivityEventBadge_WebView.getSettings()
				// .setJavaScriptEnabled(true);
				// txt_content = getHtmlData(EventBadgeDetailsActivity.this,
				// txt_content);
				// ActivityEventBadge_WebView.loadDataWithBaseURL(null,
				// txt_content, "text/html", "utf-8", "about:blank");
				// } else {
				// Constants.showMessageDialog(EventBadgeDetailsActivity.this,
				// "rEvent", msg);
				// }

			}

			@Override
			public void onResponseReceived(String response) {
			}

			@Override
			public void onErrorReceived(String error) {
			}
		});
		try {
			LoginRequestTask.execute(Constants.WS_URL_GETBADGEDETAIL,
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

	private void openPdfIntent(String path) {
		try {
			final Intent intent = new Intent(this, ActivityPDFFullScreen.class);
			intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, path);
			startActivity(intent);
		} catch (Exception e) {
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
				BufferedInputStream input = new BufferedInputStream(
						url.openStream(), 8192);

				File outputFile = new File(Environment
						.getExternalStorageDirectory().getPath(), "rEvent");
				if (!outputFile.exists()) {
					outputFile.mkdirs();
				}
				// Output stream to write file
				FileOutputStream output = new FileOutputStream(
						outputFile.toString() + File.separator + "Badge.pdf");

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
					+ File.separator + "rEvent" + "/Badge.pdf");
			// Displaying downloaded image into image view
			// Reading image path from sdcard

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
