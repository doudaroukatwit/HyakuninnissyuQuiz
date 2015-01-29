package com.sssfs.hyakuremember;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.Random;

public class QuizActivity extends Activity {
	// 選択肢
	ImageButton shimo1;
	ImageButton shimo2;
	ImageButton shimo3;
	ImageButton shimo4;

	// 上の句
	TextView kaminoku;

	// カウント
	TextView counter;

	// ChooseActivityから受け取る
	int number;
	int number2;

	// 答え以外の3つの下の句の要素
	int No[];

	// ランダム
	int[] random;
	int[] array;

	// 全体の要素
	int[] never = new int[100];

	// 問題数
	int count = 0;
	int number3;

	// 下の句の画像入れる
	ImageButton[] shimo = new ImageButton[4];

	// 正解数
	int right = 0;

	// いらないかもしれない
	int[] res = new int[4];
	boolean quiz;
	int q = 1;

	// モード選択
	boolean repeat;
	boolean hardcore;

	// 上の句文字列
	final Integer[] stringkami = { R.string.kami1, R.string.kami2,
			R.string.kami3, R.string.kami4, R.string.kami5, R.string.kami6,
			R.string.kami7, R.string.kami8, R.string.kami9, R.string.kami10,
			R.string.kami11, R.string.kami12, R.string.kami13, R.string.kami14,
			R.string.kami15, R.string.kami16, R.string.kami17, R.string.kami18,
			R.string.kami19, R.string.kami20, R.string.kami21, R.string.kami22,
			R.string.kami23, R.string.kami24, R.string.kami25, R.string.kami26,
			R.string.kami27, R.string.kami28, R.string.kami29, R.string.kami30,
			R.string.kami31, R.string.kami32, R.string.kami33, R.string.kami34,
			R.string.kami35, R.string.kami36, R.string.kami37, R.string.kami38,
			R.string.kami39, R.string.kami40, R.string.kami41, R.string.kami42,
			R.string.kami43, R.string.kami44, R.string.kami45, R.string.kami46,
			R.string.kami47, R.string.kami48, R.string.kami49, R.string.kami50,
			R.string.kami51, R.string.kami52, R.string.kami53, R.string.kami54,
			R.string.kami55, R.string.kami56, R.string.kami57, R.string.kami58,
			R.string.kami59, R.string.kami60, R.string.kami61, R.string.kami62,
			R.string.kami63, R.string.kami64, R.string.kami65, R.string.kami66,
			R.string.kami67, R.string.kami68, R.string.kami69, R.string.kami70,
			R.string.kami71, R.string.kami72, R.string.kami73, R.string.kami74,
			R.string.kami75, R.string.kami76, R.string.kami77, R.string.kami78,
			R.string.kami79, R.string.kami80, R.string.kami81, R.string.kami82,
			R.string.kami83, R.string.kami84, R.string.kami85, R.string.kami86,
			R.string.kami87, R.string.kami88, R.string.kami89, R.string.kami90,
			R.string.kami91, R.string.kami92, R.string.kami93, R.string.kami94,
			R.string.kami95, R.string.kami96, R.string.kami97, R.string.kami98,
			R.string.kami99, R.string.kami100, };

	// 下の句文字列
	final Integer[] stringshimo = { R.string.shimo1, R.string.shimo2,
			R.string.shimo3, R.string.shimo4, R.string.shimo5, R.string.shimo6,
			R.string.shimo7, R.string.shimo8, R.string.shimo9,
			R.string.shimo10, R.string.shimo11, R.string.shimo12,
			R.string.shimo13, R.string.shimo14, R.string.shimo15,
			R.string.shimo16, R.string.shimo17, R.string.shimo18,
			R.string.shimo19, R.string.shimo20, R.string.shimo21,
			R.string.shimo22, R.string.shimo23, R.string.shimo24,
			R.string.shimo25, R.string.shimo26, R.string.shimo27,
			R.string.shimo28, R.string.shimo29, R.string.shimo30,
			R.string.shimo31, R.string.shimo32, R.string.shimo33,
			R.string.shimo34, R.string.shimo35, R.string.shimo36,
			R.string.shimo37, R.string.shimo38, R.string.shimo39,
			R.string.shimo40, R.string.shimo41, R.string.shimo42,
			R.string.shimo43, R.string.shimo44, R.string.shimo45,
			R.string.shimo46, R.string.shimo47, R.string.shimo48,
			R.string.shimo49, R.string.shimo50, R.string.shimo51,
			R.string.shimo52, R.string.shimo53, R.string.shimo54,
			R.string.shimo55, R.string.shimo56, R.string.shimo57,
			R.string.shimo58, R.string.shimo59, R.string.shimo60,
			R.string.shimo61, R.string.shimo62, R.string.shimo63,
			R.string.shimo64, R.string.shimo65, R.string.shimo66,
			R.string.shimo67, R.string.shimo68, R.string.shimo69,
			R.string.shimo70, R.string.shimo71, R.string.shimo72,
			R.string.shimo73, R.string.shimo74, R.string.shimo75,
			R.string.shimo76, R.string.shimo77, R.string.shimo78,
			R.string.shimo79, R.string.shimo80, R.string.shimo81,
			R.string.shimo82, R.string.shimo83, R.string.shimo84,
			R.string.shimo85, R.string.shimo86, R.string.shimo87,
			R.string.shimo88, R.string.shimo89, R.string.shimo90,
			R.string.shimo91, R.string.shimo92, R.string.shimo93,
			R.string.shimo94, R.string.shimo95, R.string.shimo96,
			R.string.shimo97, R.string.shimo98, R.string.shimo99,
			R.string.shimo100, };
	// 下の句画像
	final Integer[] image = { R.drawable.shimo1, R.drawable.shimo2,
			R.drawable.shimo3, R.drawable.shimo4, R.drawable.shimo5,
			R.drawable.shimo6, R.drawable.shimo7, R.drawable.shimo8,
			R.drawable.shimo9, R.drawable.shimo10, R.drawable.shimo11,
			R.drawable.shimo12, R.drawable.shimo13, R.drawable.shimo14,
			R.drawable.shimo15, R.drawable.shimo16, R.drawable.shimo17,
			R.drawable.shimo18, R.drawable.shimo19, R.drawable.shimo20,
			R.drawable.shimo21, R.drawable.shimo22, R.drawable.shimo23,
			R.drawable.shimo24, R.drawable.shimo25, R.drawable.shimo26,
			R.drawable.shimo27, R.drawable.shimo28, R.drawable.shimo29,
			R.drawable.shimo30, R.drawable.shimo31, R.drawable.shimo32,
			R.drawable.shimo33, R.drawable.shimo34, R.drawable.shimo35,
			R.drawable.shimo36, R.drawable.shimo37, R.drawable.shimo38,
			R.drawable.shimo39, R.drawable.shimo40, R.drawable.shimo41,
			R.drawable.shimo42, R.drawable.shimo43, R.drawable.shimo44,
			R.drawable.shimo45, R.drawable.shimo46, R.drawable.shimo47,
			R.drawable.shimo48, R.drawable.shimo49, R.drawable.shimo50,
			R.drawable.shimo51, R.drawable.shimo52, R.drawable.shimo53,
			R.drawable.shimo54, R.drawable.shimo55, R.drawable.shimo56,
			R.drawable.shimo57, R.drawable.shimo58, R.drawable.shimo59,
			R.drawable.shimo60, R.drawable.shimo61, R.drawable.shimo62,
			R.drawable.shimo63, R.drawable.shimo64, R.drawable.shimo65,
			R.drawable.shimo66, R.drawable.shimo67, R.drawable.shimo68,
			R.drawable.shimo69, R.drawable.shimo70, R.drawable.shimo71,
			R.drawable.shimo72, R.drawable.shimo73, R.drawable.shimo74,
			R.drawable.shimo75, R.drawable.shimo76, R.drawable.shimo77,
			R.drawable.shimo78, R.drawable.shimo79, R.drawable.shimo80,
			R.drawable.shimo81, R.drawable.shimo82, R.drawable.shimo83,
			R.drawable.shimo84, R.drawable.shimo85, R.drawable.shimo86,
			R.drawable.shimo87, R.drawable.shimo88, R.drawable.shimo89,
			R.drawable.shimo90, R.drawable.shimo91, R.drawable.shimo92,
			R.drawable.shimo93, R.drawable.shimo94, R.drawable.shimo95,
			R.drawable.shimo96, R.drawable.shimo97, R.drawable.shimo98,
			R.drawable.shimo99, R.drawable.shimo100, };

	// ランダムから選んだか順番からで選んだかで処理が変わる
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_main);
		count = 0;
		quiz = true;

		// モード読み込み
		SharedPreferences mSp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		hardcore = mSp.getBoolean("hardcore", false);
		repeat = mSp.getBoolean("repeat", false);

		// ChooseActivityから値を受け取る
		Intent mQuiz = getIntent();
		number = mQuiz.getIntExtra("number", 0);
		number2 = mQuiz.getIntExtra("number2", 0);

		// 直接起動した時、戻る。
		if (number == 0) {
			Toast.makeText(this, "無理でした", Toast.LENGTH_SHORT).show();
			Intent main = new Intent(this, MainActivity.class);
			startActivity(main);
			finish();

		} else if (number2 == 100) {
			// 100のランダムの時
			number3 = number2;
			random = new int[100];
			for (int i = 0; i < 100; i++) {
				random[i] = i;

			}
			shuffle(random);
		} else if (number2 == 50 || number2 == 25) {
			// 50と25のランダムの時
			number3 = number2;
			Random mRandom = new Random();
			random = new int[50];
			for (int s = 0; s < number2; s++) {
				random[s] = mRandom.nextInt(99);
				int x = random[s];
				for (s = 0; s < number2; s++)
					if (random[s] == x)
						break;
			}

		} else if (number == 1225) {
			// 1首から25首の時
			count = 0;
			number = 25;
			number3 = 25;

		} else if (number == 26250) {
			// 26首から50首の時
			count = 25;
			number = 50;
			number3 = 25;

		} else if (number == 51275) {
			// 51首から75首の時
			count = 50;
			number = 75;
			number3 = 25;

		} else if (number == 762100) {
			// 76首から100首の時
			count = 75;
			number = 100;
			number3 = 25;

		}
		// 問題を選ぶ
		choices();
	}

	// 戻れないようにする
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
				Toast.makeText(this, "戻るのは禁止です", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	// 出題部分
	private void choices() {
		// TODO Auto-generated method stub
		// 答え以外の3つの選択肢を選ぶ部分
		Random mRandom = new Random();
		int[] No = new int[3];
		for (int i = 0; i < 3; i++) {
			No[i] = mRandom.nextInt(99);
			int x = No[i];
			for (i = 0; i < 3; i++) {
				if (No[i] == x) {
					if (number2 == 100 || number2 == 50 || number2 == 25) {
						if (No[i] >= random[count]) {
							No[i]++;
						}
					} else {
						if (No[i] >= count) {
							No[i]++;
						}
					}
					break;
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			Log.v(String.valueOf(i), String.valueOf(No[i]));
		}

		// 選択肢を配列resに追加。
		res[0] = No[0];
		res[1] = No[1];
		res[2] = No[2];

		// ランダムだったら
		if (number2 == 100 || number2 == 50 || number2 == 25) {
			res[3] = random[count];

		} else {
			// ランダムじゃなかったら
			res[3] = count;

		}

		// ImageButtonを結びつけ
		shimo1 = (ImageButton) findViewById(R.id.imageButton1);
		shimo2 = (ImageButton) findViewById(R.id.imageButton2);
		shimo3 = (ImageButton) findViewById(R.id.imageButton3);
		shimo4 = (ImageButton) findViewById(R.id.imageButton4);

		// ImageButtonを配列shimoに追加。ここ汚いから変えたい
		shimo[0] = shimo1;
		shimo[1] = shimo2;
		shimo[2] = shimo3;
		shimo[3] = shimo4;

		// 配列shimoをシャッフル
		//shuffle(shimo);
		array = new int[4];
		for (int s = 0; s < 4; s++) {
			array[s] = mRandom.nextInt(4);
			int x = array[s];
			for (s = 0; s < 4; s++)
				if (array[s] == x)
					break;
		}
		

		// 画像をセット
		shimo[array[0]].setImageResource(image[res[0]]);
		shimo[array[1]].setImageResource(image[res[1]]);
		shimo[array[2]].setImageResource(image[res[2]]);
		shimo[array[3]].setImageResource(image[res[3]]);

		counter = (TextView) findViewById(R.id.count);
		counter.setText(String.valueOf(q) + "問目");

		// 上の句をセット
		kaminoku = (TextView) findViewById(R.id.kaminoku);

		if (number2 == 100 || number2 == 50 || number2 == 25) {
			// ランダムだったら
			kaminoku.setText(stringkami[random[count]]);
		} else {
			// ランダムじゃなかったら
			kaminoku.setText(stringkami[count]);
		}

	}

	// シャッフルの部分
	public static void shuffle(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			int t = (int) (Math.random() * i); // 0～i-1の中から適当に選ぶ

			// 選ばれた値と交換する
			int tmp = arr[i];
			arr[i] = arr[t];
			arr[t] = tmp;
		}
	}

	// どれかボタンが押されたら
	public void onClick(View v) {
		if (quiz == true) {
			// 押されたボタンを取得
			ImageButton btn = (ImageButton) v;
			q++;
			count++;

			// 正解か不正解の処理
			if (shimo[array[3]] == btn) {
				// 正解なら
				Toast.makeText(this, "正解です", Toast.LENGTH_SHORT).show();
				right++;

			} else {
				// 不正解なら
				Toast.makeText(this, "不正解です", Toast.LENGTH_SHORT).show();
				if (hardcore) {
					Intent quiz = new Intent(this, QuizActivity.class);
					if (number2 == 25) {
						// 25問ランダム
						quiz.putExtra("number", 25);
						quiz.putExtra("number2", 25);
						startActivity(quiz);
						finish();

					} else if (number2 == 50) {
						// 50問ランダム
						quiz.putExtra("number", 50);
						quiz.putExtra("number2", 50);
						startActivity(quiz);
						finish();

					} else if (number2 == 100) {
						// 100問ランダム
						quiz.putExtra("number", 100);
						quiz.putExtra("number2", 100);
						startActivity(quiz);
						finish();

					} else if (number2 == 1225) {
						// 1首から25首
						quiz.putExtra("number", 1225);
						quiz.putExtra("number2", 1225);
						startActivity(quiz);
						finish();

					} else if (number2 == 26250) {
						// 26首から50首
						quiz.putExtra("number", 26250);
						quiz.putExtra("number2", 26250);
						startActivity(quiz);
						finish();

					} else if (number2 == 51275) {
						// 51首から75首
						quiz.putExtra("number", 51275);
						quiz.putExtra("number2", 51275);
						startActivity(quiz);
						finish();

					} else if (number2 == 762100) {
						// 76首から100首
						quiz.putExtra("number", 762100);
						quiz.putExtra("number2", 762100);
						startActivity(quiz);
						finish();

					}
				}
				if (repeat) {
					count--;
					q--;
				}
			}

			// 問題が終わったかどうか
			if (count == number) {
				// count問やったら結果を表示
				setContentView(R.layout.result_main);
				TextView result1 = (TextView) findViewById(R.id.textView2);
				TextView result2 = (TextView) findViewById(R.id.textView3);
				result1.setText(String.valueOf(number3) + "問中");
				result2.setText(String.valueOf(right) + "問正解");
				quiz = false;

			} else {
				// もし問題数が違ったら戻す
				setContentView(R.layout.quiz_main);
				choices();

			}
		} else if (quiz == false) {
			switch (v.getId()) {
			case R.id.title:
				// タイトルに戻る
				Intent title = new Intent(this, MainActivity.class);
				startActivity(title);
				finish();
				break;

			}
		}
	}

	public void cancel(View v) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// アラートダイアログのメッセージを設定します
		alertDialogBuilder.setMessage("本当に終了しますか？");
		// アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		alertDialogBuilder.setPositiveButton("はい",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						titleBack();
					}
				});
		// アラートダイアログの中立ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		alertDialogBuilder.setNeutralButton("いいえ",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		// アラートダイアログのキャンセルが可能かどうかを設定します
		alertDialogBuilder.setCancelable(true);
		AlertDialog alertDialog = alertDialogBuilder.create();
		// アラートダイアログを表示します
		alertDialog.show();
	}

	public void titleBack() {
		Intent title = new Intent(this, MainActivity.class);
		startActivity(title);
		finish();
	}

}
