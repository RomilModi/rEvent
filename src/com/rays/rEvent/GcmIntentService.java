package com.rays.rEvent;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/*http://techlovejump.in/2013/11/android-push-notification-using-google-cloud-messaging-gcm-php-google-play-service-library/
 * techlovejump.in
 * tutorial link
 * 
 **/

public class GcmIntentService extends IntentService {
	Context context;
	Bundle extras;
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	public static final String TAG = "GCM Demo";

	// GcmIntentService
	public GcmIntentService() {
		super("GcmIntentService");
	}

	public GcmIntentService(Context mContext) {
		super("GcmIntentService");
		this.context = mContext;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		extras = intent.getExtras();
		String msg = intent.getStringExtra("message");
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		Log.e("TAG", "Response is : " + bundle2string(extras) + " & Message : "
				+ msg);

		if (!extras.isEmpty() && msg != null) {
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				sendNotification("Deleted messages on server: "
						+ extras.toString());
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {
				// This loop represents the service doing some work.
				for (int i = 0; i < 5; i++) {
					Log.i(TAG,
							"Working... " + (i + 1) + "/5 @ "
									+ SystemClock.elapsedRealtime());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
				Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
				// Post notification of received message.
				// sendNotification("Received: " + extras.toString());
				sendNotification(msg);
				Log.i(TAG, "Received: " + extras.toString());
			}
		}
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void sendNotification(String msg) {
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent myintent = new Intent(this, MainActivity.class);
		myintent.putExtra("message", msg);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				myintent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.revent_logo)
				.setContentTitle("rEvent")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg);
		mBuilder.setAutoCancel(true);
		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}

	public static void cancelNotification(Context context) {
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(getAppName(context),
				NOTIFICATION_ID);
	}

	private static String getAppName(Context context) {
		CharSequence appName = context.getPackageManager().getApplicationLabel(
				context.getApplicationInfo());
		return (String) appName;
	}

	// updateMyActivity(this);
	// private void updateMyActivity(Context context) {
	//
	// Intent intent = new Intent("unique_name");
	// // put whatever data you want to send, if any
	// // send broadcast
	// context.sendBroadcast(intent);
	// }

	public static String bundle2string(Bundle bundle) {
		String string = "{";
		for (String key : bundle.keySet()) {
			string += " " + key + " : " + bundle.get(key) + ",";
		}
		string += " }";
		return string;
	}

}
