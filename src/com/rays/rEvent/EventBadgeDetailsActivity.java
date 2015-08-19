package com.rays.rEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.rays.rEven.view.TouchyWebView;
import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.bean.BadgePDF;
import com.rays.rEvent.bean.BadgePDF.GetBadgePDFDetails;
import com.rays.rEvent.bean.EventBadgebean;
import com.rays.rEvent.bean.EventBadgebean.GetBadgeDetails;
import com.rays.rEvent.bean.EventbadgePDF;
import com.rays.rEvent.bean.EventbadgePDF.GetBadgePDF;
import com.rays.rEvent.utils.Constants;

public class EventBadgeDetailsActivity extends BaseActivity implements
		OnClickListener {

	private GetBadgeDetails GetBadgeDetails;
	private EventBadgebean EventBadgebean;
	private EventbadgePDF EventbadgePDF;
	private BadgePDF BadgePDF;
	private ArrayList<GetBadgePDFDetails> mArraylstBadgePDF;
	private ArrayList<GetBadgePDF> mArraylstPDF;
	private String title, txt_content;
	private TouchyWebView ActivityEventBadge_WebView;
	private ArrayList<com.rays.rEvent.bean.EventBadgebean.GetBadgeDetails> mArraylstBadgeDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.badge_details);

		mArraylstBadgePDF = new ArrayList<BadgePDF.GetBadgePDFDetails>();
		mArraylstPDF = new ArrayList<EventbadgePDF.GetBadgePDF>();
		mArraylstBadgeDetails = new ArrayList<EventBadgebean.GetBadgeDetails>();
		GetBadgeDetails = (com.rays.rEvent.bean.EventBadgebean.GetBadgeDetails) getIntent()
				.getSerializableExtra("Object");

		ActivityEventBadge_WebView = (TouchyWebView) findViewById(R.id.ActivityEventBadge_WebView);
		title = getIntent().getStringExtra("title");

		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			initTitleBar(title, View.GONE, View.GONE);
		} else {
			initTitleBar(title, View.GONE, View.VISIBLE);
		}
		((ImageView) findViewById(R.id.btn_home)).setOnClickListener(this);
		((ImageView) findViewById(R.id.btn_logout)).setOnClickListener(this);
		JSONObject data = new JSONObject();

		try {
			data.put("Barcode", GetBadgeDetails.getREG_BADGE_NO());
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

				if (mArraylstBadgePDF.get(0).Flag.equalsIgnoreCase("True")) {
					try {
						// String k =
						// "<html><body> This is my Project </body></html>";
						String html = getHtmlData(
								EventBadgeDetailsActivity.this,
								mArraylstBadgePDF.get(0).CONTENT);
						FileOutputStream file = new FileOutputStream(new File(
								Environment.getExternalStorageDirectory()
										.getPath()
										+ File.separator
										+ "rEvent"
										+ File.separator + "Badge.pdf"));
						Document document = new Document();
						PdfWriter.getInstance(document, file);
						document.open();
						HTMLWorker htmlWorker = new HTMLWorker(document);
						htmlWorker.parse(new StringReader(html));
						document.close();
						file.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					openPdfIntent(Environment.getExternalStorageDirectory()
							.getPath()
							+ File.separator
							+ "rEvent"
							+ File.separator + "Badge.pdf");
				} else {
					Constants.showMessageDialog(EventBadgeDetailsActivity.this,
							"rEvent", mArraylstBadgePDF.get(0).Mesg);
				}

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
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
					.getDefaultSharedPreferences(EventBadgeDetailsActivity.this)
					.edit().putBoolean("isLoggedIn", false).commit();
			break;

		default:
			break;
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
}
