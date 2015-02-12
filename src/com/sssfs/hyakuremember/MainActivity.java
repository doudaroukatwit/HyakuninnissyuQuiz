package com.sssfs.hyakuremember;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button random;
	Button order;
	boolean back;

	@SuppressLint("InlinedApi") @Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().getDecorView().setBackgroundResource(
				R.drawable.backgroundimage);
		setTheme(android.R.style.Theme_Holo_Light_NoActionBar_TranslucentDecor);
		random = (Button) findViewById(R.id.rdm);
		order = (Button) findViewById(R.id.ord);
		back = false;
	}

	public void onClick(View v) {
		Intent choose = new Intent(getApplicationContext(),
				ChooseActivity.class);
		switch (v.getId()) {
		case R.id.rdm:
			// ランダムを開く
			choose.putExtra("CHOOSED", 1);
			startActivity(choose);
			break;
		case R.id.ord:
			// 順番を開く
			choose.putExtra("CHOOSED", 2);
			startActivity(choose);
			break;
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);
				// アラートダイアログのメッセージを設定します
				alertDialogBuilder.setMessage("本当に終了しますか？");
				// アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
				alertDialogBuilder.setPositiveButton("はい",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								close();
							}
						});
				alertDialogBuilder.setNeutralButton("いいえ", null);
				alertDialogBuilder.setCancelable(true);
				AlertDialog alertDialog = alertDialogBuilder.create();
				// アラートダイアログを表示します
				alertDialog.show();
			}
		}
		return super.dispatchKeyEvent(event);
	}

	public void close() {
		this.moveTaskToBack(true);
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
