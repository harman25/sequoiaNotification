package com.hack.notificationapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class MainActivity extends Activity {

	public static List<String> offerList = new ArrayList<String>();
	Context context;
	public static ListView lv;
	public static DealListAdapter adapt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_main);
		if(!(offerList.size() > 0)){
			offerList.add("Table 1 has a Surprise Gift offer");
			offerList.add("Table 2 has a 200 Cash Coins offer");
			offerList.add("Table 3 has a Instant Reward offer");
			offerList.add("Table 4 has a Surprise Gift offer");
			offerList.add("Table 5 has a Instant Reward offer");
		}

		adapt = new DealListAdapter(context, offerList);
		lv = (ListView) findViewById(R.id.deal_listview);
		lv.setAdapter(adapt);

		Parse.initialize(this, "vMFTELLhOo9RDRql9HpV9lKRot5xQTCCD63wkYdQ",
				"mdz7n8XUjy3u0MSQRnuwmogqXZrw3qJnRwmRxx0g");
		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();

		CharSequence text = ParseInstallation.getCurrentInstallation()
				.getObjectId();
		int duration = Toast.LENGTH_LONG;

		String customerID = ParseInstallation.getCurrentInstallation()
				.getObjectId();

		String URL = "http://sequoiahack.herokuapp.com/register?rId=R1&custId="
				+ customerID;
		try {
			String x = new AsyncNetwork().execute(URL).get();
			System.out.println("response is " + x);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("customer id is " + customerID);

	}
}
