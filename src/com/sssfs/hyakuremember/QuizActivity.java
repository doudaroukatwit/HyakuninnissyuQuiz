package com.sssfs.hyakuremember;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import java.util.Random;

public class QuizActivity extends Activity {
	// �I����
	ImageButton shimo1;
	ImageButton shimo2;
	ImageButton shimo3;
	ImageButton shimo4;
	ImageButton btn;

	// ��̋�
	TextView kaminoku;

	// �J�E���g
	TextView counter;

	// ����ڂ�
	TextView poem;
	boolean poemview;

	// ChooseActivity����󂯎��
	int number;
	int number2;

	// �����ȊO��3�̉��̋�̗v�f
	int No[];

	// �����_��
	int[] random;
	int[] array;

	// �S�̗̂v�f
	int[] never = new int[100];

	// ��萔
	int count = 0;
	int number3;

	// ���̋�̉摜�����
	ImageButton[] shimo = new ImageButton[4];

	// ����
	int right = 0;

	// ����s�����̋L�^�p
	String[] rString;

	// ����s�����̕\��
	TextView ans;
	
	//����
	TextView next;

	// SE
	MediaPlayer true1;
	MediaPlayer false1;

	// ����Ȃ���������Ȃ�
	int[] res = new int[4];
	boolean quiz;
	boolean push = false;
	int q = 1;
	int c = 0;

	// ���[�h�I��
	boolean repeat;
	boolean hardcore;

	// ��̋啶����
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

	// ���̋啶����
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
	// ���̋�摜
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
		true1 = MediaPlayer.create(getApplicationContext(), R.raw.true1);// �����̉����ǂݍ���
		false1 = MediaPlayer.create(getApplicationContext(), R.raw.false1);// �s�����̉����ǂݍ���

		// ���[�h�ǂݍ���
		SharedPreferences mSp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String mode_preference = mSp.getString("mode", "none");
		if (mode_preference.equals("hardcore"))
			hardcore = true;
		if (mode_preference.equals("repeat"))
			repeat = true;

		poemview = mSp.getBoolean("poemview", true);

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

		} else if (number2 == 100 || number2 == 50 || number2 == 25) {
			// 100�̃����_���̎�
			number3 = number2;
			random = new int[100];
			for (int i = 0; i < 100; i++) {
				random[i] = i;
			}
			shuffle(random);
		} else if (number == 1225) {
			// 1�񂩂�25��̎�
			c = 0;
			count = 0;
			number = 25;
			number3 = 25;

		} else if (number == 26250) {
			// 26�񂩂�50��̎�
			c = 25;
			count = 25;
			number = 50;
			number3 = 25;

		} else if (number == 51275) {
			// 51�񂩂�75��̎�
			c = 50;
			count = 50;
			number = 75;
			number3 = 25;

		} else if (number == 762100) {
			// 76�񂩂�100��̎�
			c = 75;
			count = 75;
			number = 100;
			number3 = 25;

		}
		rString = new String[10000];
		// ����I��
		choices();
	}

	// �߂�Ȃ��悤�ɂ���
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (quiz == true) {
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
					Toast.makeText(this, "�߂�̂͋֎~�ł�", Toast.LENGTH_SHORT).show();
					return false;
				}
			}
		} else {
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
					setContentView(R.layout.result_main);
					TextView result1 = (TextView) findViewById(R.id.textView2);
					TextView result2 = (TextView) findViewById(R.id.textView3);
					result1.setText(String.valueOf(number3) + "�⒆");
					result2.setText(String.valueOf(right) + "�␳��");
					quiz = false;
					return false;
				}
			}
		}
		return super.dispatchKeyEvent(event);

	}

	// �o�蕔��
	private void choices() {
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
					} else {
						if (No[i] >= count) {
							No[i]++;
						}
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
		// shuffle(shimo);
		array = new int[4];
		for (int s = 0; s < 4; s++) {
			array[s] = mRandom.nextInt(4);
			int x = array[s];
			for (s = 0; s < 4; s++)
				if (array[s] == x)
					break;
		}

		// �摜���Z�b�g
		shimo[array[0]].setImageResource(image[res[0]]);
		shimo[array[1]].setImageResource(image[res[1]]);
		shimo[array[2]].setImageResource(image[res[2]]);
		shimo[array[3]].setImageResource(image[res[3]]);

		ans = (TextView) findViewById(R.id.ans);
		next = (TextView) findViewById(R.id.next);

		counter = (TextView) findViewById(R.id.count);
		counter.setText(String.valueOf(q) + "���");
		// ��̋���Z�b�g
		kaminoku = (TextView) findViewById(R.id.kaminoku);
		poem = (TextView) findViewById(R.id.poem);
		if (number2 == 100 || number2 == 50 || number2 == 25) {
			// �����_����������
			kaminoku.setText(stringkami[random[count]]);
			if (poemview)
				poem.setText(String.valueOf(random[count] + 1) + "���");
		} else {
			// �����_������Ȃ�������
			kaminoku.setText(stringkami[count]);
			if (poemview)
				poem.setText(String.valueOf(count + 1) + "���");
		}
	}

	// �V���b�t���̕���
	public static void shuffle(int[] arr) {
		for (int i = 99; i > 0; i--) {
			int t = (int) (Math.random() * i); // 0�`i-1�̒�����K���ɑI��

			// �I�΂ꂽ�l�ƌ�������
			int tmp = arr[i];
			arr[i] = arr[t];
			arr[t] = tmp;
		}
	}

	// �ǂꂩ�{�^���������ꂽ��
	public void onClick(View v) {
		// �����ꂽ�{�^�����擾

		if (quiz) {
			if (!push) {
				btn = (ImageButton) v;
			}
			shimo[array[0]].setVisibility(View.INVISIBLE);
			shimo[array[1]].setVisibility(View.INVISIBLE);
			shimo[array[2]].setVisibility(View.INVISIBLE);
			shimo[array[3]].setEnabled(false);
			next.setText(R.string.nexts);
			// �������s�����̏���
			if (!push) {
				if (shimo[array[3]] == btn) {
					// �����Ȃ�
					true1.start();
					ans.setTextColor(Color.RED);
					ans.setText("��\n����");
					right++;
					rString[q - 1] = "��";
					push = true;
				} else {
					// �s�����Ȃ�
					false1.start();
					ans.setTextColor(Color.BLUE);
					ans.setText("�~\n�s����");
					if (!repeat) {
						rString[q - 1] = "�~";
					} else {
						rString[q - 1] = "��";
					}
					push = true;
				}
			}
		} else {
			switch (v.getId()) {
			case R.id.title:
				// �^�C�g���ɖ߂�
				Intent title = new Intent(this, MainActivity.class);
				startActivity(title);
				break;
			case R.id.answer:
				setContentView(R.layout.result_sub);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1);
				int i;
				// �v�f�̒ǉ��i1�j
				for (i = 0; i < number3; i++) {
					Log.v("a", String.valueOf(c));
					String iString = String.valueOf(i + 1);
					if (number2 == 100) {
						String kami = getString(stringkami[random[c]]);
						String shimo = getString(stringshimo[random[c]]);
						adapter.add(iString + "���" + "[" + rString[c] + "]\n"
								+ kami + "\n" + shimo);
					} else if (number2 == 50 || number2 == 25) {
						// �����_����������
						String kami = getString(stringkami[random[i]]);
						String shimo = getString(stringshimo[random[i]]);
						adapter.add(iString + "���" + "[" + rString[i] + "]\n"
								+ kami + "\n" + shimo);
					} else {
						// �����_������Ȃ�������
						String kami = getString(stringkami[c]);
						String shimo = getString(stringshimo[c]);
						adapter.add(iString + "���" + "[" + rString[i] + "]\n"
								+ kami + "\n" + shimo);

					}
					c++;

				}
				ListView list = (ListView) findViewById(R.id.listView);
				list.setAdapter(adapter);
				list.setFastScrollEnabled(true);
				list.setFastScrollAlwaysVisible(true);

				break;
			case R.id.again:
				again();
				break;
			}
		}
	}

	// ��ʂ̃^�b�`��F��
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (push && quiz) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				next.setText("");
				// ��萔��+1
				if (!(count == number3) || !(hardcore) && push) {
					count++;
					q++;
				}
				if (!(shimo[array[3]] == btn)) {
					if (hardcore) {
						again();
					}
					if (repeat)
						count--;
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
					// ������萔���������߂� �n�[�h�R�A�Ȃ炻�̏����͂��Ȃ�
					if ((shimo[array[3]] == btn && hardcore) || !(hardcore)) {
						shimo[array[0]].setVisibility(View.VISIBLE);
						shimo[array[1]].setVisibility(View.VISIBLE);
						shimo[array[2]].setVisibility(View.VISIBLE);
						shimo[array[3]].setEnabled(true);
						push = false;
						ans.setText("");
						choices();
					}
				}
			}
		}
		return true;
	}

	public void cancel(View v) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// �A���[�g�_�C�A���O�̃��b�Z�[�W��ݒ肵�܂�
		alertDialogBuilder.setMessage("�{���ɏI�����܂����H");
		// �A���[�g�_�C�A���O�̍m��{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
		alertDialogBuilder.setPositiveButton("�͂�",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						titleBack();
					}
				});
		// �A���[�g�_�C�A���O�̒����{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
		alertDialogBuilder.setNeutralButton("������",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		// �A���[�g�_�C�A���O�̃L�����Z�����\���ǂ�����ݒ肵�܂�
		alertDialogBuilder.setCancelable(true);
		AlertDialog alertDialog = alertDialogBuilder.create();
		// �A���[�g�_�C�A���O��\�����܂�
		alertDialog.show();
	}

	public void again() {
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

	public void titleBack() {
		Intent title = new Intent(this, MainActivity.class);
		startActivity(title);
		finish();
	}

}
