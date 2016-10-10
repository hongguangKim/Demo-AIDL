package com.example.demo_aidlserver;

import android.app.Service;
import android.com.personservice.IPerson.Stub;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private Stub iPerson = new PersonImpl();

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("service", "onBind...");
		return iPerson;
	}

}
