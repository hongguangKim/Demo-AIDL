package com.example.demo_aidlclient;

import android.app.Activity;
import android.app.Service;
import android.com.personservice.IPerson;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private IPerson person;
	private Button btn;

	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
		}

		// 因为有可能有多个应用同时进行RPC操作，所以同步该方法
		@Override
		public synchronized void onServiceConnected(ComponentName arg0,
				IBinder binder) {
			// 获得IPerson接口
			person = IPerson.Stub.asInterface(binder);
			if (person != null) {
				try {
					// RPC方法调用
					String name = person.getName();
					Toast.makeText(MainActivity.this, "远程进程调用成功！值为 ： " + name,
							Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "远程进程调用失败！ ",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button1);  
        btn.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View arg0) {  
                //该应用中不需要在manifest中配置RemoteService   
                bindService(new Intent("forServiceAidl"), conn, Service.BIND_AUTO_CREATE);  
            }  
        });
	}


}
