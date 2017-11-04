
package com.grarak.kerneladiutor.modified;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.grarak.kerneladiutor.R;

import java.util.List;
import java.util.Map;

public class ActivityMain extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
	


	private Button mLButtonRecord,mLButtonstopRecord;

    private ServiceReader mSR;

	
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		@SuppressLint("NewApi")
		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			mSR = ((ServiceReader.ServiceReaderDataBinder) service).getService();

		}
		
		@Override
		public void onServiceDisconnected(ComponentName className) {
			mSR = null;
		}
	};
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startService(new Intent(this, ServiceReader.class));
		setContentView(R.layout.activity_modified);




		mLButtonRecord = (Button) findViewById(R.id.LButtonRecord);
		mLButtonstopRecord = (Button) findViewById(R.id.LButtonStop);
		mLButtonRecord.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (mSR.isRecording()) {
					Toast.makeText(ActivityMain.this, "Stats is already running", Toast.LENGTH_SHORT).show();
				} else {
					mSR.startRecord();
					Toast.makeText(ActivityMain.this, "Cpu stats already started ", Toast.LENGTH_SHORT).show();
				}
			}
		});




	}



	public void stopr(View view){
		int id = R.string.menu_record_description;
		if (mSR.isRecording())
			id = R.string.menu_record_stop_description;
		Toast.makeText(ActivityMain.this, getString(id), Toast.LENGTH_SHORT).show();
		     mSR.stopRecord();
	   }


	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
	
	
	
	
	
	@Override
	public void onStart() {
		super.onStart();
		bindService(new Intent(this, ServiceReader.class), mServiceConnection, 0);

	}
	
	
	
	
	
	@Override
	public void onResume() {
		super.onResume();

	}
	
	
	
	
	
	@Override
	public void onPause() {
		super.onPause();

	}
	
	
	
	
	
	@Override
	public void onStop() {
		super.onStop();

	}
	
	
	
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		unbindService(mServiceConnection);
	}
	
	
	
	
	
	@Override
	public void onBackPressed() {

		super.onBackPressed();
	}
	

	
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == C.storagePermission && PackageManager.PERMISSION_DENIED == grantResults[0]) {
			Toast.makeText(ActivityMain.this, getString(R.string.w_main_storage_permission), Toast.LENGTH_LONG).show();
		}
	}
}
