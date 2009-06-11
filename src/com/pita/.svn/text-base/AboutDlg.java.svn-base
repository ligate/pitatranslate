package com.pita;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutDlg {
	static void show(Context ctxt) {
		LinearLayout layout = new LinearLayout(ctxt);
    	layout.setOrientation(LinearLayout.VERTICAL);
    	layout.setPadding(5,5,5,5);

    	TextView v = new TextView(ctxt);
    	v.setGravity(Gravity.CENTER_HORIZONTAL);
    	v.setText(R.string.app_name);
    	layout.addView(v);
    	
		v = new TextView(ctxt);
		v.setGravity(Gravity.CENTER_HORIZONTAL);
		v.setText("v" + ctxt.getString(R.string.app_version));
    	layout.addView(v);
    	
    	v = new TextView(ctxt);
    	v.setGravity(Gravity.CENTER_HORIZONTAL);
    	v.setText(ctxt.getString(R.string.project_url));
    	layout.addView(v);
    	
    	new AlertDialog.Builder(ctxt)
			.setView(layout)
			.setPositiveButton(R.string.ok, null)
			.show(); 
	}
}
