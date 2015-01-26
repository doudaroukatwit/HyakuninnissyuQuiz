package com.sssfs.hyakuremember;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseActivity extends Activity {
	Button one;
	Button two;
	Button three;
	Button four;
	int ch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent choose = getIntent();
		ch = 0;
		ch = choose.getIntExtra("CHOOSED", 0); // MainActivityからintを受け取る
		if (ch == 1) {
			// ランダムの選択を開く
			setContentView(R.layout.random_choose);
			one = (Button) findViewById(R.id.twfive);
			two = (Button) findViewById(R.id.fifty);
			three = (Button) findViewById(R.id.hundred);

		} else if (ch == 2) {
			// 順番の選択を開く
			setContentView(R.layout.order_choose);
			one = (Button) findViewById(R.id.one2twf);
			two = (Button) findViewById(R.id.twsix2fifty);
			three = (Button) findViewById(R.id.fifo2sevfive);
			four = (Button) findViewById(R.id.sevsix2hund);
		} else if (ch == 0) {
			// 直接起動した時、戻る。
			Toast.makeText(this, "無理でした", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
			finish();
		}

	}

	public void onClick(View v) {
		Intent quiz = new Intent(this, QuizActivity.class);
		switch (v.getId()) {
		// 25問ランダム
		case R.id.twfive:
			quiz.putExtra("number", 25);
			quiz.putExtra("number2", 25);
			startActivity(quiz);
			finish();
			break;
		// 50問ランダム
		case R.id.fifty:
			quiz.putExtra("number", 50);
			quiz.putExtra("number2", 50);
			startActivity(quiz);
			finish();
			break;
		// 100問ランダム
		case R.id.hundred:
			quiz.putExtra("number", 100);
			quiz.putExtra("number2", 100);
			startActivity(quiz);
			finish();
			break;
		// 1首から25首
		case R.id.one2twf:
			quiz.putExtra("number", 1225);
			quiz.putExtra("number2", 1225);
			startActivity(quiz);
			finish();
			break;
		// 26首から50首
		case R.id.twsix2fifty:
			quiz.putExtra("number", 26250);
			quiz.putExtra("number2", 26250);
			startActivity(quiz);
			finish();
			break;
		// 51首から75首
		case R.id.fifo2sevfive:
			quiz.putExtra("number", 51275);
			quiz.putExtra("number2", 51275);
			startActivity(quiz);
			finish();
			break;
		// 76首から100首
		case R.id.sevsix2hund:
			quiz.putExtra("number", 762100);
			quiz.putExtra("number2", 762100);
			startActivity(quiz);
			finish();
			break;
		}
	}
}
