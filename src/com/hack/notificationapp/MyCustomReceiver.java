package com.hack.notificationapp;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			int duration = Toast.LENGTH_LONG;
			String action = intent.getAction();
			if (action.equals("com.example.UPDATE_STATUS")) {
				System.out.println("update view");
			} else if (action.equals("com.example.updateBooking")) {
				System.out.println("update booking");
			} else if (action.equals("com.example.updateMessage")) {
				System.out.println("update message");
			}
			String channel = intent.getExtras().getString("com.parse.Channel");
			JSONObject json = new JSONObject(intent.getExtras().getString(
					"com.parse.Data"));

			Iterator itr = json.keys();

			while (itr.hasNext()) {
				String key = (String) itr.next();
				System.out.println("..." + key + " => " + json.getString(key));
			}
			if(action.equals("com.example.UPDATE_STATUS")){
				final int NOTIFICATION_ID = 1;
				NotificationManager mNotificationManager;
				NotificationCompat.Builder builder;
				mNotificationManager = (NotificationManager) context
						.getSystemService(Context.NOTIFICATION_SERVICE);
				Bundle extras = new Bundle();
				Intent notificationIntent = new Intent(context, MainActivity.class);
				MainActivity.offerList.add("Table " + json.getString("tableId") + " has 200 Cash Coins offer");
				MainActivity.adapt.notifyDataSetChanged();
				TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
				// Adds the back stack
				//stackBuilder.addParentStack(OffersAcitvity.class);
				// Adds the Intent to the top of the stack
				stackBuilder.addNextIntent(notificationIntent);
				// Gets a PendingIntent containing the entire back stack
				PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
						PendingIntent.FLAG_UPDATE_CURRENT);

				long[] vibratePattern = { 0, 500, 1000 };
				Uri notificationSound = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
						context)
						.setSmallIcon(R.drawable.frugal_small)
						.setContentTitle("New Notification")
						.setAutoCancel(true)
						.setStyle(
								new NotificationCompat.BigTextStyle().bigText(json.getString("tableId")))
						.setContentText(
								"New notification from table number "
										+ json.getString("tableId"))
						.setVibrate(vibratePattern).setSound(notificationSound);

				mBuilder.setContentIntent(resultPendingIntent);
				mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());				
			}
		} catch (JSONException e) {
		}
	}
}