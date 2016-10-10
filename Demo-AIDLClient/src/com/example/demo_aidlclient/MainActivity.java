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

		// ��Ϊ�п����ж��Ӧ��ͬʱ����RPC����������ͬ���÷���
		@Override
		public synchronized void onServiceConnected(ComponentName arg0,
				IBinder binder) {
			// ���IPerson�ӿ�
			person = IPerson.Stub.asInterface(binder);
			if (person != null) {
				try {
					// RPC��������
					String name = person.getName();
					Toast.makeText(MainActivity.this, "Զ�̽��̵��óɹ���ֵΪ �� " + name,
							Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "Զ�̽��̵���ʧ�ܣ� ",
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
                //��Ӧ���в���Ҫ��manifest������RemoteService   
                bindService(new Intent("forServiceAidl"), conn, Service.BIND_AUTO_CREATE);  
            }  
        });
	}


}
