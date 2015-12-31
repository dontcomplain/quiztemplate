package com.summerice.quiztemplate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

// Parent activity for the other classes to inherit the menu customization from
public class MainActivity extends AppCompatActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// Automatically handle clicks on the Home/Up button, so long
		// As you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_credits) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
			// Set title
			alertDialogBuilder.setTitle("Credits");
			// Set dialog message
			alertDialogBuilder.setMessage(R.string.detail_credits).setCancelable(false).setPositiveButton("Done",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// If this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}
					});

			// Create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();
			// Show it
			alertDialog.show();
			return true;
		} else if (id == android.R.id.home) {
			// Close activity when the back button
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
