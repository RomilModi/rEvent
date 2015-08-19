package com.rays.rEvent;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;
import com.rays.rEvent.RequestTask.PlacesService;
import com.rays.rEvent.adapter.EventNearByAdapter;
import com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents;
import com.rays.rEvent.bean.Place;
import com.rays.rEvent.utils.GPSTracker;

public class EventNearByRestaurantsActivity extends BaseActivity implements
		OnItemClickListener {
	private final String TAG = getClass().getSimpleName();
	private GoogleMap mMap;
	private String[] places;
	private LocationManager locationManager;
	// private Location loc;
	private HashMap<Marker, Place> eventMarkerMap;
	private GetAllEvents GetAllEvents;
	private ListView ActivityEventNearby_lst_Nearby;
	private EventNearByAdapter mBaseAdapter;
	private ImageView CustomActionbarSignout;
	private Spinner CustomSpinner;
	private TextView CustomActionbarTitle;
	private android.app.ActionBar actionBar;
	private ArrayList<Place> mArraylstPlace;
	private GPSTracker gps;
	private String strType;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_event_nearby);
		mArraylstPlace = new ArrayList<Place>();
		gps = new GPSTracker(this);
		ActivityEventNearby_lst_Nearby = (ListView) findViewById(R.id.ActivityEventNearby_lst_Nearby);
		ActivityEventNearby_lst_Nearby.setOnItemClickListener(this);
		String title = getIntent().getStringExtra("title");
		GetAllEvents = (com.rays.rEvent.bean.GetAllEventsbean.GetAllEvents) getIntent()
				.getSerializableExtra("AllEventObject");
		Log.e("title", "title : " + title);
		// if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
		// "isLoggedIn", false)) {
		// initTitleBar(title, View.GONE, View.VISIBLE);
		// } else {
		// initTitleBar(title, View.GONE, View.VISIBLE);
		// }

		eventMarkerMap = new HashMap<Marker, Place>();
		// initCompo();
		places = getResources().getStringArray(R.array.places);
		currentLocation();

		actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		// final ActionBar actionBar = getActionBar();
		// actionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.custom_nearby_actionbar, null);

		CustomActionbarTitle = (TextView) v
				.findViewById(R.id.CustomActionbarTitle);

		CustomActionbarTitle.setText(title);

		CustomSpinner = (Spinner) v.findViewById(R.id.CustomSpinner);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_item, places);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		CustomSpinner.setAdapter(dataAdapter);

		CustomSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// if (loc != null) {
				// mMap.clear();
				strType = places[position].toLowerCase();
				if (gps.canGetLocation()) {
					new GetPlaces(EventNearByRestaurantsActivity.this,
							places[position].toLowerCase().replace("-", "_")
									.replace(" ", "_")).execute();
				} else {
					gps.showSettingsAlert();
				}

				// }
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		CustomActionbarSignout = (ImageView) v
				.findViewById(R.id.CustomActionbarSignout);

		CustomActionbarSignout = (ImageView) v
				.findViewById(R.id.CustomActionbarSignout);

		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			CustomActionbarSignout.setVisibility(View.VISIBLE);
		}

		CustomActionbarSignout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//
				// AllEventsFragment m = new AllEventsFragment();
				// m.setUserVisibleHint(true);

				// actionBar.setSelectedNavigationItem(0);
				Intent in1 = new Intent(EventNearByRestaurantsActivity.this,
						MainActivity.class);
				startActivity(in1);
				finish();
				PreferenceManager
						.getDefaultSharedPreferences(
								EventNearByRestaurantsActivity.this).edit()
						.putBoolean("isLoggedIn", false).commit();

				CustomActionbarSignout.setVisibility(View.GONE);

			}
		});
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(v);

		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// actionBar.setListNavigationCallbacks(ArrayAdapter.createFromResource(
		// this, R.array.places, android.R.layout.simple_list_item_1),
		// new ActionBar.OnNavigationListener() {
		//
		// @Override
		// public boolean onNavigationItemSelected(int itemPosition,
		// long itemId) {
		// Log.e(TAG,
		// places[itemPosition].toLowerCase().replace("-",
		// "_"));
		// if (loc != null) {
		// // mMap.clear();
		// new GetPlaces(EventNearByRestaurantsActivity.this,
		// places[itemPosition].toLowerCase()
		// .replace("-", "_")
		// .replace(" ", "_")).execute();
		// }
		// return true;
		// }
		//
		// });
		// final ActionBar actionBar = getActionBar();
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// actionBar.setListNavigationCallbacks(ArrayAdapter.createFromResource(
		// this, R.array.places, android.R.layout.simple_list_item_1),
		// new ActionBar.OnNavigationListener() {
		//
		// @Override
		// public boolean onNavigationItemSelected(int itemPosition,
		// long itemId) {
		// Log.e(TAG,
		// places[itemPosition].toLowerCase().replace("-",
		// "_"));
		// if (loc != null) {
		// mMap.clear();
		// new GetPlaces(EventNearByRestaurantsActivity.this,
		// places[itemPosition].toLowerCase()
		// .replace("-", "_")
		// .replace(" ", "_")).execute();
		// }
		// return true;
		// }
		//
		// });

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
					.getDefaultSharedPreferences(
							EventNearByRestaurantsActivity.this).edit()
					.putBoolean("isLoggedIn", false).commit();
			break;
		default:
			break;
		}

	}

	private class GetPlaces extends AsyncTask<Void, Void, ArrayList<Place>> {

		private ProgressDialog dialog;
		private Context context;
		private String places;

		public GetPlaces(Context context, String places) {
			this.context = context;
			this.places = places;
		}

		@Override
		protected void onPostExecute(final ArrayList<Place> result) {
			super.onPostExecute(result);
			if (dialog.isShowing()) {
				dialog.dismiss();
			}

			if (!mArraylstPlace.isEmpty()) {
				mArraylstPlace.clear();
			}
			mArraylstPlace.addAll(result);

			mBaseAdapter = new EventNearByAdapter(context, result, places);
			mBaseAdapter.notifyDataSetChanged();
			ActivityEventNearby_lst_Nearby.setAdapter(mBaseAdapter);

			// for (int i = 0; i < result.size(); i++) {
			// Marker m = mMap.addMarker(new MarkerOptions()
			// .title(result.get(i).getName())
			// .position(
			// new LatLng(result.get(i).getLatitude(), result
			// .get(i).getLongitude()))
			// .icon(BitmapDescriptorFactory
			// .fromResource(R.drawable.pin))
			// .snippet(result.get(i).getVicinity()));
			// eventMarkerMap.put(m, result.get(i));
			// }
			// CameraPosition cameraPosition = new CameraPosition.Builder()
			// .target(new LatLng(result.get(0).getLatitude(), result.get(
			// 0).getLongitude())) // Sets the center of the map to
			// // Mountain View
			// .zoom(16) // Sets the zoom
			// .tilt(30) // Sets the tilt of the camera to 30 degrees
			// .build(); // Creates a CameraPosition from the builder
			// mMap.animateCamera(CameraUpdateFactory
			// .newCameraPosition(cameraPosition));
			// mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener()
			// {
			// @Override
			// public void onInfoWindowClick(Marker arg0) {
			// // Intent intent = new Intent(getBaseContext(),
			// // MainActivity.class);
			//
			// arg0.hideInfoWindow();
			//
			// Place reference = eventMarkerMap.get(arg0);
			//
			// Place p = new Place();
			// p.setId(reference.getId());
			// p.setIcon(reference.getIcon());
			// p.setLatitude(reference.getLatitude());
			// p.setLongitude(reference.getLongitude());
			// p.setName(reference.getName());
			// p.setRating(reference.getRating());
			// p.setVicinity(reference.getVicinity());
			//
			// Intent in = new Intent(EventNearByRestaurantsActivity.this,
			// RestaurentDetailsActivity.class);
			// in.putExtra("PlaceObject", p);
			// startActivity(in);
			//
			// // Toast.makeText(EventNearByRestaurantsActivity.this,
			// // reference.getName(), Toast.LENGTH_LONG).show();
			//
			// // intent.putExtra("reference", reference);
			//
			// // Starting the Place Details Activity
			// }
			// });
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			runOnUiThread(new Runnable() {
				public void run() {
					// runs on UI thread
					dialog = new ProgressDialog(context);
					dialog.setCancelable(false);
					dialog.setMessage("Loading..");
					dialog.isIndeterminate();
					dialog.show();
				}
			});

		}

		// AIzaSyCRV9EcvnPK8-m_rZwLrWQfu1zFIFK19Vo
		@Override
		protected ArrayList<Place> doInBackground(Void... arg0) {
			PlacesService service = new PlacesService(
					"AIzaSyCMFxsY_KZzRos81kAmjbYPggTfb17GVuA");

			// ArrayList<Place> findPlaces =
			// service.findPlaces(loc.getLatitude(), // 28.632808
			// loc.getLongitude(), places); // 77.218276

			ArrayList<Place> findPlaces = service.findPlaces(gps.getLatitude(), // 28.632808
					gps.getLongitude(), places);
			Log.i("LATLONG",
					"Lat is : " + gps.getLatitude() + " & Long : "
							+ gps.getLongitude() + " & Places Array : "
							+ findPlaces.size());
			for (int i = 0; i < findPlaces.size(); i++) {

				Place placeDetail = findPlaces.get(i);
				Log.e(TAG, "places : " + placeDetail.getName());
			}
			return findPlaces;
		}

	}

	private void initCompo() {
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
	}

	private void currentLocation() {
		// LocationResult locationResult = new LocationResult() {
		// @Override
		// public void gotLocation(Location location) {
		// // Got the location!
		// locationManager = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);
		//
		// String provider = locationManager.getBestProvider(
		// new Criteria(), false);
		//
		// // Location location =
		// // locationManager.getLastKnownLocation(provider);
		// // Location location = getLastKnownLocation();
		//
		// Log.e("TAG", "Location : " + location + " & Loc : " + loc);
		//
		// // if (location == null) {
		// // locationManager.requestLocationUpdates(provider, 0, 0,
		// // listener);
		// // } else {
		// // loc = location;
		// new GetPlaces(EventNearByRestaurantsActivity.this, places[0]
		// .toLowerCase().replace("-", "_")).execute();
		// Log.e(TAG, "location : " + location);
		// // }
		// }
		//
		// };
		// MyLocation myLocation = new MyLocation();
		// myLocation.getLocation(this, locationResult);
		//
		// if (!myLocation.getLocation(this, locationResult)) {
		// startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		// }

		// if (gps.canGetLocation()) {
		// new GetPlaces(EventNearByRestaurantsActivity.this, places[0]
		// .toLowerCase().replace("-", "_")).execute();
		// } else {
		// gps.showSettingsAlert();
		// }

		// Log.e(TAG, "location : " + location);
		// Log.i("TAG",
		// "Location : " + myLocation.getLocation(this, locationResult));

	}

	// private LocationListener listener = new LocationListener() {
	//
	// @Override
	// public void onStatusChanged(String provider, int status, Bundle extras) {
	//
	// }
	//
	// @Override
	// public void onProviderEnabled(String provider) {
	//
	// }
	//
	// @Override
	// public void onProviderDisabled(String provider) {
	//
	// }
	//
	// @Override
	// public void onLocationChanged(Location location) {
	// Log.e(TAG, "location update : " + location);
	// loc = location;
	// locationManager.removeUpdates(listener);
	// }
	// };

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.e("TAG",
				"Lat : " + gps.getLatitude() + " & Lang : "
						+ gps.getLongitude());
		Intent in = new Intent(this, ActivityGoogleMapPath.class);
		in.putExtra("SourceLat", gps.getLatitude());
		in.putExtra("SourceLang", gps.getLongitude());
		in.putExtra("DestLat", mArraylstPlace.get(position).getLatitude());
		in.putExtra("DestLang", mArraylstPlace.get(position).getLongitude());
		in.putExtra("Name", mArraylstPlace.get(position).getName());
		startActivity(in);
	}

}
