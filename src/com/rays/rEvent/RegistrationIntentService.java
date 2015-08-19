package com.rays.rEvent;

import java.io.IOException;

import android.app.IntentService;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.rays.rEvent.utils.Constants;

public class RegistrationIntentService extends IntentService {

	private static final String TAG = "RegIntentService";
	private static final String[] TOPICS = { "global" };

	public RegistrationIntentService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		try {
			// In the (unlikely) event that multiple refresh operations occur
			// simultaneously,
			// ensure that they are processed sequentially.
			synchronized (TAG) {
				// [START register_for_gcm]
				// Initially this call goes out to the network to retrieve the
				// token, subsequent calls
				// are local.
				// [START get_token]
				InstanceID instanceID = InstanceID.getInstance(this);
				String token = instanceID.getToken(/*
													 * getString(R.string.
													 * gcm_defaultSenderId)
													 */"16767411547",
						GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
				// [END get_token]
				Log.i(TAG, "GCM Registration Token: " + token);

				PreferenceManager.getDefaultSharedPreferences(this).edit()
						.putString("RegistrationId", token).commit();
				// TODO: Implement this method to send any registration to your
				// app's servers.
				// sendRegistrationToServer(token);

				// Subscribe to topic channels
				subscribeTopics(token);

				// You should store a boolean that indicates whether the
				// generated token has been
				// sent to your server. If the boolean is false, send the token
				// to your server,
				// otherwise your server should have already received the token.
				PreferenceManager.getDefaultSharedPreferences(this).edit()
						.putBoolean("RegistrationFlag", true).commit();
				// [END register_for_gcm]
			}
		} catch (Exception e) {
			Log.e(TAG, "Failed to complete token refresh", e);
			// If an exception happens while fetching the new token or updating
			// our registration data
			// on a third-party server, this ensures that we'll attempt the
			// update at a later time.
			PreferenceManager.getDefaultSharedPreferences(this).edit()
					.putBoolean("RegistrationFlag", false).commit();
		}
		// Notify UI that registration has completed, so the progress indicator
		// can be hidden.
		Intent registrationComplete = new Intent(
				Constants.REGISTRATION_COMPLETE);
		LocalBroadcastManager.getInstance(this).sendBroadcast(
				registrationComplete);
	}

	/**
	 * Persist registration to third-party servers.
	 * 
	 * Modify this method to associate the user's GCM registration token with
	 * any server-side account maintained by your application.
	 * 
	 * @param token
	 *            The new token.
	 */
	private void sendRegistrationToServer(String token) {
		// Add custom implementation, as needed.
	}

	/**
	 * Subscribe to any GCM topics of interest, as defined by the TOPICS
	 * constant.
	 * 
	 * @param token
	 *            GCM token
	 * @throws IOException
	 *             if unable to reach the GCM PubSub service
	 */
	// [START subscribe_topics]
	private void subscribeTopics(String token) throws IOException {
		for (String topic : TOPICS) {
			GcmPubSub pubSub = GcmPubSub.getInstance(this);
			pubSub.subscribe(token, "/topics/" + topic, null);
		}
	}
	// [END subscribe_topics]

}
