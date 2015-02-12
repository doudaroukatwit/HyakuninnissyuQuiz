package com.sssfs.hyakuremember;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

public class SettingActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {
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

		// アップデートの項目
		Preference update = findPreference("update");
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

	@SuppressWarnings("deprecation")
	@Override
	public void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
		Preference p = findPreference("mode");
		SharedPreferences mSp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String mode_preference = mSp.getString("mode", "none");
		String mode = "モードを設定してください";
		if (mode_preference.equals("hardcore"))
			mode = "スパルタモード";
		if (mode_preference.equals("repeat"))
			mode = "反復モード";
		if (mode_preference.equals("none"))
			mode = "モードを設定";
		p.setSummary(mode);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		Log.i("onSharedPreferenceChanged", key);
		if (key.equals("mode")) {
			SharedPreferences mSp = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			String mode_preference = mSp.getString("mode", "none");
			@SuppressWarnings("deprecation")
			Preference p = (ListPreference) findPreference(key);
			String mode = "モードを設定してください";
			if (mode_preference.equals("hardcore"))
				mode = "スパルタモード";
			if (mode_preference.equals("repeat"))
				mode = "反復モード";
			if (mode_preference.equals("none"))
				mode = "モードを設定してください";
			p.setSummary(mode);
		}
	}

	public void way() {
		n++;
		if (n == 10 * w) {
			Toast.makeText(this,
					"ここを" + String.valueOf(n) + "回も連打するなんて物好きですねぇ…",
					Toast.LENGTH_SHORT).show();
			w++;
		}
	}
}