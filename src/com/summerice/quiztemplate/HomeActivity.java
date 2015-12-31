package com.summerice.quiztemplate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		((Button) findViewById(R.id.testBtn)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, QuizActivity.class);
				startActivity(i);
			}
		});
		((Button) findViewById(R.id.rateBtn)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent marketIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://play.google.com/store/apps/details?id=" + getString(R.string.pkgName)));
				startActivity(marketIntent);
				finish();
			}
		});
		((Button) findViewById(R.id.shareBtn)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Created a string variable to make it look less ugly
				String temp = "Check out " + getString(R.string.app_name)
						+ "!!! https://play.google.com/store/apps/details?id=" + getString(R.string.pkgName);
				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, temp);
				startActivity(Intent.createChooser(shareIntent, "Share via"));
				finish();
			}
		});
	}
}
