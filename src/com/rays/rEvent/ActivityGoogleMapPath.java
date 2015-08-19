package com.rays.rEvent;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.rays.rEvent.RequestTask.JSONParser;
import com.rays.rEvent.utils.GPSTracker;

public class ActivityGoogleMapPath extends BaseActivity {

	private GoogleMap mMap;
	LatLng latlang, latlangEnd;
	Polyline line;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_restaurants);

		double SourceLat = getIntent().getDoubleExtra("SourceLat", 0.0);
		double SourceLang = getIntent().getDoubleExtra("SourceLang", 0.0);
		double DestLat = getIntent().getDoubleExtra("DestLat", 0.0);
		double DestLang = getIntent().getDoubleExtra("DestLang", 0.0);
		String Name = getIntent().getStringExtra("Name");

		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			initTitleBar(Name, View.GONE, View.GONE);
		} else {
			initTitleBar(Name, View.GONE, View.VISIBLE);
		}
		latlang = new LatLng(SourceLat, SourceLang);
		latlangEnd = new LatLng(DestLat, DestLang);
		initCompo();

		String url = makeURL(SourceLat, SourceLang, DestLat, DestLang);
		Log.i("URL", "URL is : " + url + " & Current lat : "
				+ new GPSTracker(this).getLatitude() + " & Current long : "
				+ new GPSTracker(this).getLongitude());
		new connectAsyncTask(url).execute();

	}

	private void initCompo() {
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		// mMap.setMyLocationEnabled(true);
		// mMap.setTrafficEnabled(true);
		mMap.moveCamera(CameraUpdateFactory.newLatLng(latlang));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
	}

	public String makeURL(double sourcelat, double sourcelog, double destlat,
			double destlog) {
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.googleapis.com/maps/api/directions/json");
		urlString.append("?origin=");// from
		urlString.append(Double.toString(sourcelat));
		urlString.append(",");
		urlString.append(Double.toString(sourcelog));
		urlString.append("&destination=");// to
		urlString.append(Double.toString(destlat));
		urlString.append(",");
		urlString.append(Double.toString(destlog));
		urlString.append("&sensor=false&mode=driving&alternatives=true");
		return urlString.toString();
	}

	public void drawPath(String result) {
		if (line != null) {
			mMap.clear();
		}
		mMap.addMarker(new MarkerOptions().position(latlang).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.pin)));
		mMap.addMarker(new MarkerOptions().position(latlangEnd).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.pin)));
		try {
			// Tranform the string into a json object
			final JSONObject json = new JSONObject(result);
			JSONArray routeArray = json.getJSONArray("routes");
			JSONObject routes = routeArray.getJSONObject(0);
			JSONObject overviewPolylines = routes
					.getJSONObject("overview_polyline");
			String encodedString = overviewPolylines.getString("points");
			List<LatLng> list = decodePoly(encodedString);

			for (int z = 0; z < list.size() - 1; z++) {
				LatLng src = list.get(z);
				LatLng dest = list.get(z + 1);
				Polyline line = mMap.addPolyline(new PolylineOptions()
						.add(new LatLng(src.latitude, src.longitude),
								new LatLng(dest.latitude, dest.longitude))
						.width(7).color(Color.BLUE).geodesic(true));
			}

			// PolylineOptions options = new PolylineOptions().width(5)
			// .color(Color.BLUE).geodesic(true);
			// for (int z = 0; z < list.size(); z++) {
			// LatLng point = list.get(z);
			// options.add(point);
			// }
			// line = mMap.addPolyline(options);
		} catch (JSONException e) {

		}
	}

	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}

		return poly;
	}

	private class connectAsyncTask extends AsyncTask<Void, Void, String> {
		private ProgressDialog progressDialog;
		String url;

		connectAsyncTask(String urlPass) {
			url = urlPass;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(ActivityGoogleMapPath.this);
			progressDialog.setMessage("Fetching route, Please wait...");
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			JSONParser jParser = new JSONParser();
			String json = jParser.getJSONFromUrl(url);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.hide();
			Log.i("Result", "Result : " + result);
			if (result != null) {
				drawPath(result);
			}
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
					.getDefaultSharedPreferences(ActivityGoogleMapPath.this)
					.edit().putBoolean("isLoggedIn", false).commit();
			break;
		default:
			break;
		}

	}
}
