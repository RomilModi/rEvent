package com.rays.rEvent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.rays.rEvent.utils.Constants;

import java.io.IOException;

public class ActivitySplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 4000;
    static final String TAG = "GCMDemo";
    GoogleCloudMessaging gcm;
    String regid;
    String SENDER_ID = "16767411547";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ActivitySplashScreen.this,
                        MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);

        // Intent intent = new Intent(this, RegistrationIntentService.class);
        // startService(intent);

        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = PreferenceManager.getDefaultSharedPreferences(
                    ActivitySplashScreen.this).getString("RegistrationId", "");
            Log.e("RegisrtationId", "Registration Id before is : " + regid);
            if (regid.isEmpty()) {
                new RegisterBackground().execute();
            }
        }

    }

    class RegisterBackground extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg0) {
            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging
                            .getInstance(ActivitySplashScreen.this);
                }
                regid = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regid;
                // System.out.println("nikhil........."+msg);
                storeRegistrationId(ActivitySplashScreen.this, regid);
            } catch (IOException ex) {
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging
                                .getInstance(ActivitySplashScreen.this);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;
                    // System.out.println("nikhil........."+msg);
                    // Persist the regID - no need to register again.
                    storeRegistrationId(ActivitySplashScreen.this, regid);
                } catch (Exception e) {
                    try {
                        if (gcm == null) {
                            gcm = GoogleCloudMessaging
                                    .getInstance(ActivitySplashScreen.this);
                        }
                        regid = gcm.register(SENDER_ID);
                        msg = "Device registered, registration ID=" + regid;
                        // System.out.println("nikhil........."+msg);
                        // Persist the regID - no need to register again.
                        storeRegistrationId(ActivitySplashScreen.this, regid);
                    } catch (Exception e1) {

                    }
                }
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            Log.e("RegisrtationId", "Registration Id After is : " + regid);
        }

        private void storeRegistrationId(Context context, String regId) {
            int appver = getAppVersion(context);
            PreferenceManager
                    .getDefaultSharedPreferences(ActivitySplashScreen.this)
                    .edit().putString("RegistrationId", regId).commit();
            PreferenceManager
                    .getDefaultSharedPreferences(ActivitySplashScreen.this)
                    .edit().putInt("ApplicationVersion", appver).commit();
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
}
