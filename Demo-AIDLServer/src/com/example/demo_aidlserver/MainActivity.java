package com.example.demo_aidlserver;

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
	private IPerson iPerson;
	private Button bindBtn, unbindBtn;

	private ServiceConnection conn = new ServiceConnection() {

		// 断开连接时调用
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
		}

		// 连接时调用
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder binder) {
			iPerson = IPerson.Stub.asInterface(binder);
			if (iPerson != null) {
				try {
					iPerson.setName("My name is 'Server AIDL'");
					Toast.makeText(MainActivity.this, "赋值成功!",
							Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "赋值失败!",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bindBtn = (Button)findViewById(R.id.button1);  
        unbindBtn = (Button)findViewById(R.id.button2);  
        bindBtn.setOnClickListener(listener);  
        unbindBtn.setOnClickListener(listener);  
	}

	private OnClickListener listener = new OnClickListener() {  
        @Override  
        public void onClick(View view) {  
            switch (view.getId()) {  
            case R.id.button1:  
                //本应用中需要在manifest中配置RemoteService
            	Toast.makeText(MainActivity.this, "bind service!",
						Toast.LENGTH_LONG).show();
                bindService(new Intent("forServiceAidl"), conn, Service.BIND_AUTO_CREATE);  
                break;  
            case R.id.button2:  
                unbindService(conn);  
                Toast.makeText(MainActivity.this, "unbind!",
						Toast.LENGTH_LONG).show();
                break;  
            default:  
                break;  
            }  
        }  
    };  

}
