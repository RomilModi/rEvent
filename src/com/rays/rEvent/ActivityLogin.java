package com.rays.rEvent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rays.rEvent.RequestTask.AsyncCallListener;
import com.rays.rEvent.RequestTask.LoginRequestTask;
import com.rays.rEvent.bean.ForgotPasswordbean;
import com.rays.rEvent.bean.ForgotPasswordbean.D;
import com.rays.rEvent.bean.Loginbean;
import com.rays.rEvent.bean.Loginbean.GetLogin;
import com.rays.rEvent.utils.Constants;

public class ActivityLogin extends Activity implements OnClickListener {

	private EditText userInput;
	private EditText passwordInput;
	private CheckBox chkRemPwd;
	private String userName, password, email;
	private ImageView loginBtn;
	private Loginbean Loginbean;
	private ArrayList<GetLogin> mArraylstLogin;
	private TextView forgotPassword;
	private ForgotPasswordbean ForgotPasswordbean;
	private ArrayList<D> mArraylstForgotPassword;
	DialogInterface.OnClickListener closeButtonListener;
	DialogInterface.OnClickListener settingsButtonListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		mArraylstForgotPassword = new ArrayList<ForgotPasswordbean.D>();
		forgotPassword = (TextView) findViewById(R.id.forgotPassword);
		forgotPassword.setOnClickListener(this);
		userInput = (EditText) findViewById(R.id.userInput);
		passwordInput = (EditText) findViewById(R.id.passwordInput);
		chkRemPwd = (CheckBox) findViewById(R.id.chkRemPwd);
		loginBtn = (ImageView) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(this);

		mArraylstLogin = new ArrayList<Loginbean.GetLogin>();
		if (PreferenceManager.getDefaultSharedPreferences(ActivityLogin.this)
				.getBoolean("isRemembered", false)) {
			userInput.setText(PreferenceManager.getDefaultSharedPreferences(
					ActivityLogin.this).getString("Username", ""));
			passwordInput.setText(PreferenceManager
					.getDefaultSharedPreferences(ActivityLogin.this).getString(
							"Password", ""));
			chkRemPwd.setChecked(true);
		}
		chkRemPwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					userName = userInput.getText().toString().trim();
					password = passwordInput.getText().toString().trim();
					PreferenceManager
							.getDefaultSharedPreferences(ActivityLogin.this)
							.edit().putString("Username", userName).commit();
					PreferenceManager
							.getDefaultSharedPreferences(ActivityLogin.this)
							.edit().putString("Password", password).commit();

					PreferenceManager
							.getDefaultSharedPreferences(ActivityLogin.this)
							.edit().putBoolean("isRemembered", true).commit();

				} else {
					PreferenceManager
							.getDefaultSharedPreferences(ActivityLogin.this)
							.edit().putBoolean("isRemembered", false).commit();
				}

			}
		});
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

	private void getLogin(String data) {
		// TODO Auto-generated method stub
		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new Loginbean(), Loginbean.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {
				Loginbean = (com.rays.rEvent.bean.Loginbean) object;
				mArraylstLogin.addAll(Loginbean.GetLoginDetails);
				String flag = null, COM_ID = null, MEMBER_ID = null;
				for (int i = 0; i < mArraylstLogin.size(); i++) {
					flag = mArraylstLogin.get(i).FLAGE;
					COM_ID = mArraylstLogin.get(i).COM_ID;
					MEMBER_ID = mArraylstLogin.get(i).MEMBER_ID;
				}
				if (flag.equalsIgnoreCase("true")) {
					if (PreferenceManager.getDefaultSharedPreferences(
							ActivityLogin.this)
							.getBoolean("UserProfile", false)) {
						Intent in = new Intent(ActivityLogin.this,
								UserProfileActivity.class);
						startActivity(in);
						finish();
						PreferenceManager
								.getDefaultSharedPreferences(ActivityLogin.this)
								.edit().putBoolean("isLoggedIn", true).commit();
					} else {
						PreferenceManager
								.getDefaultSharedPreferences(ActivityLogin.this)
								.edit()
								.putString("Username",
										userInput.getText().toString().trim())
								.commit();
						PreferenceManager
								.getDefaultSharedPreferences(ActivityLogin.this)
								.edit().putBoolean("isLoggedIn", true).commit();
						PreferenceManager
								.getDefaultSharedPreferences(ActivityLogin.this)
								.edit().putBoolean("Temp", true).commit();
						PreferenceManager
								.getDefaultSharedPreferences(ActivityLogin.this)
								.edit().putString("CompanyId", COM_ID).commit();
						PreferenceManager
								.getDefaultSharedPreferences(ActivityLogin.this)
								.edit().putString("MemberId", MEMBER_ID)
								.commit();

						finish();
					}
				} else {

					Constants.showMessageDialog(ActivityLogin.this, "rEvent",
							"Please enter correct Username or Password.");
				}
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
			LoginRequestTask.execute(Constants.WS_URL_GETCHECKCREDENTIALS,
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
		switch (v.getId()) {
		case R.id.loginBtn:
			JSONObject data = new JSONObject();
			userName = userInput.getText().toString().trim();
			password = passwordInput.getText().toString().trim();
			try {
				if (fieldValidation()) {
					data.put("EmailId", userInput.getText().toString().trim());
					data.put("Password", passwordInput.getText().toString()
							.trim());
					data.put("DeviceID", PreferenceManager
							.getDefaultSharedPreferences(ActivityLogin.this)
							.getString("RegistrationId", ""));
					Log.i("Data", "Data is : " + data.toString());
					getLogin(data.toString());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.forgotPassword:
			performForgotPasswordOperation();
			break;
		default:
			break;
		}
	}

	public void performForgotPasswordOperation() {
		if (Constants.checkNetworkStatus(getApplicationContext())) {
			LayoutInflater factory = LayoutInflater.from(ActivityLogin.this);
			final View textEntryView;
			textEntryView = factory.inflate(R.layout.dialog_forgot_password,
					null);
			final EditText forgotemailId = (EditText) textEntryView
					.findViewById(R.id.txtEventName);
			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(
					ActivityLogin.this);
			dialogbuilder
					.setView(textEntryView)
					.setPositiveButton("Send New Password",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									email = forgotemailId.getText().toString()
											.trim();
									if (fieldValidation1()) {
										JSONObject data1 = new JSONObject();
										try {
											data1.put("Email", email);
											getForgotPassword(data1.toString());
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											loginBtn.setEnabled(true);
										}
									}

								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
								}
							});
			AlertDialog alertDialog = dialogbuilder.create();
			alertDialog.show();
		} else {
			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(
					ActivityLogin.this);
			dialogbuilder.setCancelable(true);
			dialogbuilder.setTitle(Constants.CONNECTIVITY_ERROR_TITLE);
			dialogbuilder.setMessage(Constants.CONNECTIVITY_ERROR_MESSAGE);
			dialogbuilder.setNegativeButton("Close", closeButtonListener);
			dialogbuilder.setPositiveButton("Settings", settingsButtonListener);
			AlertDialog alertDialog = dialogbuilder.create();
			alertDialog.show();
		}
	}

	private void getForgotPassword(String data) {
		// TODO Auto-generated method stub
		LoginRequestTask LoginRequestTask = new LoginRequestTask(this,
				new ForgotPasswordbean(), ForgotPasswordbean.class);
		LoginRequestTask.setAsyncCallListener(new AsyncCallListener() {
			@Override
			public void onResponseReceived(Object object) {
				ForgotPasswordbean = (ForgotPasswordbean) object;
				mArraylstForgotPassword.addAll(ForgotPasswordbean.d);
				for (int i = 0; i < mArraylstForgotPassword.size(); i++) {
					if (mArraylstForgotPassword.get(i).getFlag()
							.equalsIgnoreCase("true")) {
						Constants.showMessageDialog(ActivityLogin.this,
								"rEvent", mArraylstForgotPassword.get(i)
										.getMesg());
					}
				}
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
			LoginRequestTask.execute(Constants.WS_URL_GETFORGOTPASSWORD,
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

	public boolean fieldValidation() {
		boolean flag = true;
		if (!Constants.validateString(userName)) {
			flag = false;
			Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
		} else if (!Constants.validateString(password)) {
			flag = false;
			Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
		}
		return flag;

	}

	public boolean fieldValidation1() {
		boolean flag = true;

		if (!Constants.validateString(email)) {
			flag = false;
			Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
		} else if (!Constants.isEmailValid(email)) {
			flag = false;
			Toast.makeText(this, "Enter Correct Email", Toast.LENGTH_SHORT)
					.show();
		}
		return flag;
	}

	@Override
	public void onBackPressed() {

		Intent in = new Intent(this, MainActivity.class);
		startActivity(in);
		finish();

		// Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show();

	}
}
