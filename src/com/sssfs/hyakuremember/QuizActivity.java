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
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.Random;

public class QuizActivity extends Activity {
	// �I����
	ImageButton shimo1;
	ImageButton shimo2;
	ImageButton shimo3;
	ImageButton shimo4;

	// ��̋�
	TextView kaminoku;
	
	//�J�E���g
	TextView counter;

	// ChooseActivity����󂯎��
	int number;
	int number2;

	// �����ȊO��3�̉��̋�̗v�f
	int No[];

	// �����_��
	int[] random;

	// �S�̗̂v�f
	int[] never = new int[100];

	// ��萔
	int count = 0;
	int number3;

	// ���̋�̉摜�����
	ImageButton[] shimo = new ImageButton[4];

	// ����
	int right = 0;

	// ����Ȃ���������Ȃ�
	int[] res = new int[4];
	boolean quiz;
	int q = 1;

	// ���[�h�I��
	boolean repeat;
	boolean hardcore;

	// ��̋�
	final Integer[] string = { R.string.kami1, R.string.kami2, R.string.kami3,
			R.string.kami4, R.string.kami5, R.string.kami6, R.string.kami7,
			R.string.kami8, R.string.kami9, R.string.kami10, R.string.kami11,
			R.string.kami12, R.string.kami13, R.string.kami14, R.string.kami15,
			R.string.kami16, R.string.kami17, R.string.kami18, R.string.kami19,
			R.string.kami20, R.string.kami21, R.string.kami22, R.string.kami23,
			R.string.kami24, R.string.kami25, R.string.kami26, R.string.kami27,
			R.string.kami28, R.string.kami29, R.string.kami30, R.string.kami31,
			R.string.kami32, R.string.kami33, R.string.kami34, R.string.kami35,
			R.string.kami36, R.string.kami37, R.string.kami38, R.string.kami39,
			R.string.kami40, R.string.kami41, R.string.kami42, R.string.kami43,
			R.string.kami44, R.string.kami45, R.string.kami46, R.string.kami47,
			R.string.kami48, R.string.kami49, R.string.kami50, R.string.kami51,
			R.string.kami52, R.string.kami53, R.string.kami54, R.string.kami55,
			R.string.kami56, R.string.kami57, R.string.kami58, R.string.kami59,
			R.string.kami60, R.string.kami61, R.string.kami62, R.string.kami63,
			R.string.kami64, R.string.kami65, R.string.kami66, R.string.kami67,
			R.string.kami68, R.string.kami69, R.string.kami70, R.string.kami71,
			R.string.kami72, R.string.kami73, R.string.kami74, R.string.kami75,
			R.string.kami76, R.string.kami77, R.string.kami78, R.string.kami79,
			R.string.kami80, R.string.kami81, R.string.kami82, R.string.kami83,
			R.string.kami84, R.string.kami85, R.string.kami86, R.string.kami87,
			R.string.kami88, R.string.kami89, R.string.kami90, R.string.kami91,
			R.string.kami92, R.string.kami93, R.string.kami94, R.string.kami95,
			R.string.kami96, R.string.kami97, R.string.kami98, R.string.kami99,
			R.string.kami100, };

	// ���̋�
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

	// �����_������I�񂾂����Ԃ���őI�񂾂��ŏ������ς��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_main);
		count = 0;
		quiz = true;

		// ���[�h�ǂݍ���
		SharedPreferences mSp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		hardcore = mSp.getBoolean("hardcore", false);
		repeat = mSp.getBoolean("repeat", false);

		// ChooseActivity����l���󂯎��
		Intent mQuiz = getIntent();
		number = mQuiz.getIntExtra("number", 0);
		number2 = mQuiz.getIntExtra("number2", 0);

		// ���ڋN���������A�߂�B
		if (number == 0) {
			Toast.makeText(this, "�����ł���", Toast.LENGTH_SHORT).show();
			Intent main = new Intent(this, MainActivity.class);
			startActivity(main);
			finish();

		} else if (number2 == 100) {
			// 100�̃����_���̎�
			number3 = number2;
			random = new int[100];
			for (int i = 0; i < 100; i++) {
				random[i] = i;

			}
			shuffle(random);
		} else if (number2 == 50 || number2 == 25) {
			// 50��25�̃����_���̎�
			number3 = number2;
			Random mRandom = new Random();
			random = new int[50];
			for (int s = 0; s < number2; s++) {
				random[s] = mRandom.nextInt(99);
				int x = random[s];
				Log.v("a", String.valueOf(random[s]));
				for (s = 0; s < number2; s++)
					if (random[s] == x)
						break;
			}

		} else if (number == 1225) {
			// 1�񂩂�25��̎�
			count = 0;
			number = 24;
			number3 = 25;

		} else if (number == 26250) {
			// 26�񂩂�50��̎�
			count = 25;
			number = 49;
			number3 = 25;

		} else if (number == 51275) {
			// 51�񂩂�75��̎�
			count = 50;
			number = 74;
			number3 = 25;

		} else if (number == 762100) {
			// 76�񂩂�100��̎�
			count = 75;
			number = 99;
			number3 = 25;

		}
		// ����I��
		choices();
	}

	// �߂�Ȃ��悤�ɂ���
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
				Toast.makeText(this, "�߂�̂͋֎~�ł�", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	// �o�蕔��
	private void choices() {
		// TODO Auto-generated method stub
		// �����ȊO��3�̑I������I�ԕ���
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
					} else if (No[i] >= count) {
						No[i]++;
					}
					break;
				}
			}
		}

		// �I������z��res�ɒǉ��B
		res[0] = No[0];
		res[1] = No[1];
		res[2] = No[2];

		// �����_����������
		if (number2 == 100 || number2 == 50 || number2 == 25) {
			res[3] = random[count];

		} else {
			// �����_������Ȃ�������
			res[3] = count;

		}

		// ImageButton�����т�
		shimo1 = (ImageButton) findViewById(R.id.imageButton1);
		shimo2 = (ImageButton) findViewById(R.id.imageButton2);
		shimo3 = (ImageButton) findViewById(R.id.imageButton3);
		shimo4 = (ImageButton) findViewById(R.id.imageButton4);

		// ImageButton��z��shimo�ɒǉ��B������������ς�����
		shimo[0] = shimo1;
		shimo[1] = shimo2;
		shimo[2] = shimo3;
		shimo[3] = shimo4;

		// �z��shimo���V���b�t��
		shuffle(shimo);

		// �摜���Z�b�g
		shimo[0].setImageResource(image[res[0]]);
		shimo[1].setImageResource(image[res[1]]);
		shimo[2].setImageResource(image[res[2]]);
		shimo[3].setImageResource(image[res[3]]);
		
		
		counter = (TextView)findViewById(R.id.count);
		counter.setText(String.valueOf(q)+"���");

		// ��̋���Z�b�g
		kaminoku = (TextView) findViewById(R.id.kaminoku);

		if (number2 == 100 || number2 == 50 || number2 == 25) {
			// �����_����������
			kaminoku.setText(string[random[count]]);
		} else {
			// �����_������Ȃ�������
			kaminoku.setText(string[count]);
		}

	}

	// �V���b�t���̕���
	public static void shuffle(ImageButton[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			int t = (int) (Math.random() * i); // 0�`i-1�̒�����K���ɑI��

			// �I�΂ꂽ�l�ƌ�������
			ImageButton tmp = arr[i];
			arr[i] = arr[t];
			arr[t] = tmp;
		}
	}

	// �V���b�t���̕���
	public static void shuffle(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			int t = (int) (Math.random() * i); // 0�`i-1�̒�����K���ɑI��

			// �I�΂ꂽ�l�ƌ�������
			int tmp = arr[i];
			arr[i] = arr[t];
			arr[t] = tmp;
		}
	}

	// �ǂꂩ�{�^���������ꂽ��
	public void onClick(View v) {
		if (quiz == true) {
			// �����ꂽ�{�^�����擾
			ImageButton btn = (ImageButton) v;
			Log.v("this", btn.toString());
			q++;
			count++;

			// �������s�����̏���
			if (shimo[3] == btn) {
				// �����Ȃ�
				Toast.makeText(this, "�����ł�", Toast.LENGTH_SHORT).show();
				right++;

			} else {
				// �s�����Ȃ�
				Toast.makeText(this, "�s�����ł�", Toast.LENGTH_SHORT).show();
				if (hardcore) {
					Intent quiz = new Intent(this, QuizActivity.class);
					if (number2 == 25) {
						// 25�⃉���_��
						quiz.putExtra("number", 25);
						quiz.putExtra("number2", 25);
						startActivity(quiz);
						finish();

					} else if (number2 == 50) {
						// 50�⃉���_��
						quiz.putExtra("number", 50);
						quiz.putExtra("number2", 50);
						startActivity(quiz);
						finish();

					} else if (number2 == 100) {
						// 100�⃉���_��
						quiz.putExtra("number", 100);
						quiz.putExtra("number2", 100);
						startActivity(quiz);
						finish();

					} else if (number2 == 1225) {
						// 1�񂩂�25��
						quiz.putExtra("number", 1225);
						quiz.putExtra("number2", 1225);
						startActivity(quiz);
						finish();

					} else if (number2 == 26250) {
						// 26�񂩂�50��
						quiz.putExtra("number", 26250);
						quiz.putExtra("number2", 26250);
						startActivity(quiz);
						finish();

					} else if (number2 == 51275) {
						// 51�񂩂�75��
						quiz.putExtra("number", 51275);
						quiz.putExtra("number2", 51275);
						startActivity(quiz);
						finish();

					} else if (number2 == 762100) {
						// 76�񂩂�100��
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

			// ��肪�I��������ǂ���
			if (count == number) {
				// count�������猋�ʂ�\��
				setContentView(R.layout.result_main);
				TextView result1 = (TextView) findViewById(R.id.textView2);
				TextView result2 = (TextView) findViewById(R.id.textView3);
				result1.setText(String.valueOf(number3) + "�⒆");
				result2.setText(String.valueOf(right) + "�␳��");
				quiz = false;

			} else {
				// ������萔���������߂�
				setContentView(R.layout.quiz_main);
				choices();

			}
		} else if (quiz == false) {
			switch (v.getId()) {
			case R.id.title:
				// �^�C�g���ɖ߂�
				Intent title = new Intent(this, MainActivity.class);
				startActivity(title);
				finish();
				break;

			}
		}
	}
}
