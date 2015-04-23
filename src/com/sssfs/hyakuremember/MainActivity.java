package com.sssfs.hyakuremember;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button random;
	Button order;
	boolean back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().getDecorView().setBackgroundResource(
				R.drawable.backgroundimage);
		random = (Button) findViewById(R.id.rdm);
		order = (Button) findViewById(R.id.ord);
		back = false;
	}

	public void onClick(View v) {
		Intent choose = new Intent(getApplicationContext(),
				ChooseActivity.class);
		switch (v.getId()) {
		case R.id.rdm:
			// ÉâÉìÉ_ÉÄÇäJÇ≠
			choose.putExtra("CHOOSED", 1);
			startActivity(choose);
			break;
		case R.id.ord:
			// èáî‘ÇäJÇ≠
			choose.putExtra("CHOOSED", 2);
			startActivity(choose);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
