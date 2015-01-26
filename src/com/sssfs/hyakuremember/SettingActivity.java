package com.sssfs.hyakuremember;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class SettingActivity extends PreferenceActivity {
	SharedPreferences mPref;
	SharedPreferences.Editor mEditor;
	boolean survival;
	OnPreferenceClickListener onPreferenceClickListener;
	int n;
	int w = 1;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference_main);
		Preference hide = (Preference) findPreference("version");
		hide.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				way();
				return true;
			}
		});

		// �A�b�v�f�[�g�̍���
		Preference update = (Preference) findPreference("update");
		update.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Uri uri = Uri
						.parse("https://www.dropbox.com/sh/bkxpsouoxiw3h20/AACgfQPcjDFXryBhOxv1EuQja?dl=0");
				Intent i = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(i);
				return true;
			}
		});
	}

	public void way() {
		n++;
		if (n == 10 * w) {
			Toast.makeText(this,
					"������" + String.valueOf(n) + "����A�ł���Ȃ�ĕ��D���ł��˂��c",
					Toast.LENGTH_SHORT).show();
			w++;
		}
	}
}
