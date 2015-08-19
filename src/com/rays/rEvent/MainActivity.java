package com.rays.rEvent;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rays.rEvent.utils.Constants;

public class MainActivity extends FragmentActivity implements TabListener {

	ViewPager viewPager;
	TapFragmentManager1 fragmentManger1;
	TapFragmentManager fragmentManger;
	android.app.ActionBar actionBar;
	private EditText txtEventName;
	String[] tabsName = { "All Events", "My Events" };
	DialogInterface.OnClickListener closeButtonListener;
	DialogInterface.OnClickListener settingsButtonListener;
	String tabName;
	private int Count;
	public static EditText editText;
	private ImageView img_filter, img_user_profile, img_logout;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		PreferenceManager.getDefaultSharedPreferences(this).edit()
				.putBoolean("Manage", false).commit();
		getActionBar().setIcon(
				new ColorDrawable(getResources().getColor(
						android.R.color.transparent)));
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.custom_actionbar, null);
		PreferenceManager.getDefaultSharedPreferences(this).edit()
				.putBoolean("Temp1", false).commit();
		img_filter = (ImageView) v.findViewById(R.id.img_filter);
		img_filter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		img_user_profile = (ImageView) v.findViewById(R.id.img_user_profile);
		img_user_profile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent in;
				if (PreferenceManager.getDefaultSharedPreferences(
						MainActivity.this).getBoolean("isLoggedIn", false)) {
					in = new Intent(MainActivity.this,
							UserProfileActivity.class);
					in.putExtra("title", "Profile");
				} else {
					PreferenceManager
							.getDefaultSharedPreferences(MainActivity.this)
							.edit().putBoolean("UserProfile", true).commit();
					in = new Intent(MainActivity.this, ActivityLogin.class);
				}
				startActivity(in);

			}
		});
		img_logout = (ImageView) v.findViewById(R.id.img_logout);
		img_logout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//
				// AllEventsFragment m = new AllEventsFragment();
				// m.setUserVisibleHint(true);

				// actionBar.setSelectedNavigationItem(0);

				viewPager.setCurrentItem(0);
				PreferenceManager
						.getDefaultSharedPreferences(MainActivity.this).edit()
						.putBoolean("isLoggedIn", false).commit();

				img_logout.setVisibility(View.GONE);

			}
		});

		actionBar.setCustomView(v);
		// getActionBar().setTitle("Event");
		// getActionBar().setIcon(R.drawable.ic_launcher);
		// getActionBar().setDisplayShowHomeEnabled(false);
		// getActionBar().setDisplayShowTitleEnabled(false);

		PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit()
				.putBoolean("UserProfile", false).commit();
		actionBar
				.setTitle(Html
						.fromHtml("<font color='#FFFFFF'><strong>rEvent</strong> </font>"));
		actionBar.setDisplayOptions(actionBar.getDisplayOptions()
				| ActionBar.DISPLAY_SHOW_CUSTOM);
		PreferenceManager.getDefaultSharedPreferences(this).edit()
				.putBoolean("Temp", false).commit();
		fragmentManger = new TapFragmentManager(getSupportFragmentManager());
		viewPager.setAdapter(fragmentManger);

		viewPager.setOffscreenPageLimit(2);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (String str : tabsName) {
			actionBar.addTab(actionBar.newTab().setText(str).setTag(str)
					.setTabListener(this));
		}

		Log.i("Token", "Token : "
				+ PreferenceManager.getDefaultSharedPreferences(this)
						.getString("RegistrationId", ""));

		// // For each of the sections in the app, add a tab to the action bar.
		// Log.i("SIZE", "SIZE : " + fragmentManger.getCount());
		// for (int i = 0; i < fragmentManger.getCount(); i++) {
		// // Create a tab with text corresponding to the page title defined by
		// // the adapter. Also specify this Activity object, which implements
		// // the TabListener interface, as the callback (listener) for when
		// // this tab is selected.
		//
		// LinearLayout view = (LinearLayout) getLayoutInflater().inflate(
		// R.layout.custom_tab, null);
		//
		// TextView title = (TextView) view.findViewById(R.id.title);
		// title.setText(tabsName[i]);
		//
		// actionBar.addTab(actionBar.newTab().setTag(tabsName[i])
		// // .setText(mSectionsPagerAdapter.getPageTitle(i))
		// .setCustomView(view).setTabListener(this));
		// }
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@SuppressWarnings({ "deprecation", "unused" })
			@Override
			public void onPageSelected(int tabPosition) {
				actionBar.setSelectedNavigationItem(tabPosition);
				Log.e("COUNT", "tab pos : " + tabPosition);
				if (tabPosition == 0) {
					AllEventsFragment.lstList.setVisibility(View.GONE);
					AllEventsFragment.mArraylst_GetAllEventsList.clear();
				} else if (tabPosition == 1) {
					// if (count != 0) {
					MyEventsFregment.lstList_AllMyEvents
							.setVisibility(View.GONE);
					MyEventsFregment.mArraylst_GetAllEventsList.clear();
					// }
					// count++;
				}

				PreferenceManager
						.getDefaultSharedPreferences(MainActivity.this).edit()
						.putString("Tab", tabName).commit();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				Log.e("COUNT", "onPageScrolled" + arg0);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				Log.e("COUNT", "onPageScrollStateChanged : " + state);
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("TAG", "OnResume : "
				+ PreferenceManager.getDefaultSharedPreferences(this)
						.getBoolean("Temp", true));
		PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit()
				.putBoolean("UserProfile", false).commit();
		// fragmentManger.notifyDataSetChanged();

		if (!PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
				.getBoolean("isLoggedIn", false)) {
			img_logout.setVisibility(View.GONE);
		} else {
			img_logout.setVisibility(View.VISIBLE);
		}

		// if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
		// "Temp", true)) {
		// // fragmentManger = null;
		// //
		// // fragmentManger = new
		// // TapFragmentManager(getSupportFragmentManager());
		// // viewPager.setAdapter(fragmentManger);
		// // viewPager.setCurrentItem(0);
		// // actionBar.setSelectedNavigationItem(0);
		// // ((MyEventsFregment) fragmentManger.getItem(1)).onRefresh();
		// // MyEventsFregment m = new MyEventsFregment();
		// // m.MyEvents();
		//
		// MyEventsFregment m = new MyEventsFregment();
		// m.setUserVisibleHint(true);
		//
		// // MyEventsFregment fragobj = new MyEventsFregment();
		// // getSupportFragmentManager().beginTransaction()
		// // .replace(android.R.id.content, fragobj).commit();
		//
		// }
		// fragmentManger = new TapFragmentManager(getSupportFragmentManager());
		// viewPager.setAdapter(fragmentManger);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		// editText = (EditText)
		// menu.findItem(R.id.action_filter).getActionView();
		// // editText.addTextChangedListener(textWatcher);
		// editText.addTextChangedListener(new TextWatcher() {
		//
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before,
		// int count) {
		// // TODO Auto-generated method stub
		// System.out
		// .println("Text [" + s + "] - Start [" + start
		// + "] - Before [" + before + "] - Count ["
		// + count + "]");
		// // if (count < before) {
		// // // We're deleting char so we need to reset the adapter data
		// // AllEventsFragment.mBaseAdapter.resetData();
		// // }
		//
		// // AllEventsFragment.mBaseAdapter.getFilter().filter(s.toString());
		// // AllEventsFragment.mBaseAdapter.filter(s.toString());
		// // AllEventsFragment.mBaseAdapter.getFilter().filter(s.toString());
		//
		// int textlength = editText.getText().length();
		//
		// if (PreferenceManager
		// .getDefaultSharedPreferences(MainActivity.this)
		// .getString("Tab", "All Events")
		// .equalsIgnoreCase("All Events")) {
		// AllEventsFragment.array_sort.clear();
		// for (int i = 0; i < AllEventsFragment.mArraylst_GetAllEventsList
		// .size(); i++) {
		// if (textlength <= AllEventsFragment.mArraylst_GetAllEventsList
		// .get(i).EVT_NAME.length()) {
		// if (editText
		// .getText()
		// .toString()
		// .equalsIgnoreCase(
		// (String) AllEventsFragment.mArraylst_GetAllEventsList
		// .get(i).EVT_NAME
		// .subSequence(0, textlength))) {
		// AllEventsFragment.array_sort
		// .add(AllEventsFragment.mArraylst_GetAllEventsList
		// .get(i));
		// }
		// }
		// }
		// AllEventsFragment.mBaseAdapter = new GetAllEventsAdapter(
		// AllEventsFragment.c, AllEventsFragment.array_sort);
		// AllEventsFragment.lstList
		// .setAdapter(AllEventsFragment.mBaseAdapter);
		// } else {
		// MyEventsFregment.array_sort.clear();
		// for (int i = 0; i < MyEventsFregment.mArraylst_GetAllEventsList
		// .size(); i++) {
		// if (textlength <= MyEventsFregment.mArraylst_GetAllEventsList
		// .get(i).EVT_NAME.length()) {
		// if (editText
		// .getText()
		// .toString()
		// .equalsIgnoreCase(
		// (String) MyEventsFregment.mArraylst_GetAllEventsList
		// .get(i).EVT_NAME
		// .subSequence(0, textlength))) {
		// MyEventsFregment.array_sort
		// .add(MyEventsFregment.mArraylst_GetAllEventsList
		// .get(i));
		// }
		// }
		// }
		// MyEventsFregment.mBaseAdapter = new GetMyEventsAdapter(
		// MyEventsFregment.c, MyEventsFregment.array_sort);
		// MyEventsFregment.lstList_AllMyEvents
		// .setAdapter(MyEventsFregment.mBaseAdapter);
		// }
		//
		// // AllEventsFragment.lstList.setAdapter(new
		// // ArrayAdapter<String>(
		// // ListViewSearchExample.this,
		// // android.R.layout.simple_list_item_1, array_sort));
		//
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void afterTextChanged(Editable s) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		//
		// MenuItem menuItem = menu.findItem(R.id.action_filter);
		// menuItem.setOnActionExpandListener(new OnActionExpandListener() {
		// @Override
		// public boolean onMenuItemActionCollapse(MenuItem item) {
		// // Do something when collapsed
		// menu.findItem(R.id.action_settings).setVisible(true);
		// menu.findItem(R.id.action_user_profile).setVisible(true);
		// return true; // Return true to collapse action view
		// }
		//
		// @Override
		// public boolean onMenuItemActionExpand(MenuItem item) {
		// // editText.clearFocus();
		// menu.findItem(R.id.action_settings).setVisible(false);
		// menu.findItem(R.id.action_user_profile).setVisible(false);
		// return true; // Return true to expand action view
		// }
		// });
		return true;
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.e("TAG", "onRestart");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("TAG", "onStart");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		// case R.id.action_blog:
		// Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
		// break;
		// case R.id.action_search:
		// Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
		// break;
		case R.id.action_rate:
			Intent viewIntent = new Intent("android.intent.action.VIEW",
					Uri.parse("https://play.google.com/store"));
			startActivity(viewIntent);

			break;
		case R.id.action_share:
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			String text = "Hi! I have just found an interesting event finding application named rEvent which helps you to identify and register within nearby events in my area... you can download the application from www.revent.co.in";
			intent.putExtra(Intent.EXTRA_TEXT, "rEvent");
			// if (intent != null) {
			intent.putExtra(Intent.EXTRA_TEXT, text);//
			startActivity(Intent.createChooser(intent, text));
			// } else {
			//
			// Toast.makeText(this, "App not found", Toast.LENGTH_SHORT)
			// .show();
			// }

			break;
		case R.id.action_help:
			Intent viewIntent1 = new Intent(MainActivity.this,
					ActivityContactUs.class);
			viewIntent1.putExtra("from", "Contact Us");
			startActivity(viewIntent1);
			// Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_About:
			Intent viewIntent2 = new Intent(MainActivity.this,
					ActivityContactUs.class);
			viewIntent2.putExtra("from", "About");
			startActivity(viewIntent2);

			// Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
			break;

		// case R.id.action_filter:
		// // Toast.makeText(this, "Filter", Toast.LENGTH_SHORT).show();
		//
		// performForgotPasswordOperation();
		//
		// break;
		// // case R.id.action_user_logout:
		// // Toast.makeText(this, "action_user_logout", Toast.LENGTH_SHORT)
		// // .show();
		// //
		// // break;
		//
		// case R.id.action_user_profile:
		//
		// PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
		// .edit().putBoolean("UserProfile", true).commit();
		//
		// Intent in;
		// if (PreferenceManager
		// .getDefaultSharedPreferences(MainActivity.this).getBoolean(
		// "isLoggedIn", false)) {
		// in = new Intent(MainActivity.this, UserProfileActivity.class);
		// in.putExtra("title", "Profile");
		// } else {
		// in = new Intent(MainActivity.this, ActivityLogin.class);
		// }
		// startActivity(in);
		// Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
		// break;
		default:
			break;
		}
		// int id = item.getItemId();
		//
		// if (id == R.id.action_settings) {
		//
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

		viewPager.setCurrentItem(tab.getPosition());
		tabName = tab.getTag().toString();

		// if (tabName.equalsIgnoreCase("My Events")) {
		// MyEventsFregment fragobj = new MyEventsFregment();
		// getSupportFragmentManager().beginTransaction()
		// .replace(android.R.id.content, fragobj).commit();
		// }
		// fragmentManger1 = new
		// TapFragmentManager1(getSupportFragmentManager(),
		// tab.getPosition());
		// viewPager.setAdapter(fragmentManger1);
		PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit()
				.putString("Tab", tabName).commit();
		Log.i("TABID", "tabId onTabSelected : " + tabName);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public class TapFragmentManager extends FragmentPagerAdapter {

		public TapFragmentManager(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int tab_position) {
			// TODO Auto-generated method stub
			Count = 0;
			PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
					.edit().putInt("Count", Count).commit();

			if (tab_position == 0) {
				return new AllEventsFragment();
			} else {
				return new MyEventsFregment();
			}

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tabsName.length;
		}

	}

	public class TapFragmentManager1 extends FragmentStatePagerAdapter {

		int tab_pos;

		public TapFragmentManager1(FragmentManager fm, int pos) {
			super(fm);
			// TODO Auto-generated constructor stub
			tab_pos = pos;
		}

		@Override
		public Fragment getItem(int tab_position) {
			// TODO Auto-generated method stub
			Count = 0;
			PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
					.edit().putInt("Count", Count).commit();

			if (tab_pos == 0) {
				return new AllEventsFragment();
			} else {
				return new MyEventsFregment();
			}

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tabsName.length;
		}

	}

	private TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

			// if (null != AllEventsFragment.mBaseAdapter) {
			// AllEventsFragment.mBaseAdapter.getFilter().filter(s);
			// }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	public void performForgotPasswordOperation() {
		if (Constants.checkNetworkStatus(getApplicationContext())) {
			LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView;
			textEntryView = factory.inflate(R.layout.dialog_forgot_password,
					null);
			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
			dialogbuilder
					.setView(textEntryView)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									txtEventName = (EditText) textEntryView
											.findViewById(R.id.txtEventName);
									String eventname = null;
									if (txtEventName.getText().toString()
											.trim() == null
											|| txtEventName.getText()
													.toString().trim()
													.equals("")
											|| txtEventName.getText()
													.toString().trim().length() <= 0) {
										Toast.makeText(getApplicationContext(),
												"Enter Event Name!",
												Toast.LENGTH_SHORT).show();
									} else {
										try {
											eventname = txtEventName.getText()
													.toString().trim();
											Bundle bundle = new Bundle();
											bundle.putString("FROM", eventname);
											if (tabName
													.equalsIgnoreCase("All Events")) {
												// AllEventsFragment fragobj =
												// new AllEventsFragment();
												// fragobj.setArguments(bundle);
												// getSupportFragmentManager()
												// .beginTransaction()
												// .replace(
												// android.R.id.content,
												// fragobj)
												// .commit();
											} else {
												MyEventsFregment fragobj = new MyEventsFregment();
												fragobj.setArguments(bundle);
												getSupportFragmentManager()
														.beginTransaction()
														.replace(
																android.R.id.content,
																fragobj)
														.commit();
											}

											// set Fragmentclass Arguments

											// Toast.makeText(
											// getApplicationContext(),
											// "" + eventname,
											// Toast.LENGTH_SHORT).show();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}

								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

								}
							});
			AlertDialog alertDialog = dialogbuilder.create();
			alertDialog.show();
		} else {
			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
			dialogbuilder.setCancelable(true);
			dialogbuilder.setTitle("rEvent");
			dialogbuilder.setMessage("No Internet Connection.");
			dialogbuilder.setNegativeButton("Close", closeButtonListener);
			dialogbuilder.setPositiveButton("Settings", settingsButtonListener);
			AlertDialog alertDialog = dialogbuilder.create();
			alertDialog.show();
		}
		settingsButtonListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent settingscreen = new Intent(
						android.provider.Settings.ACTION_SETTINGS);
				// settingscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(settingscreen);
				finish();
			}
		};
		closeButtonListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		};
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		Log.i("TAG",
				"Flag : "
						+ PreferenceManager.getDefaultSharedPreferences(this)
								.getBoolean("isLoggedIn", false));

		if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				"isLoggedIn", false)) {
			Log.i("TAG", "Flag : "
					+ PreferenceManager.getDefaultSharedPreferences(this)
							.getBoolean("isLoggedIn", false));

			PreferenceManager.getDefaultSharedPreferences(this).edit()
					.putBoolean("Temp1", true).commit();

			// Intent i = new Intent(Intent.ACTION_MAIN);
			// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// i.addCategory(Intent.CATEGORY_HOME);
			// // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// startActivity(i);
			// finish();
			// Toast.makeText(this, "Main Activity", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
	}
}
