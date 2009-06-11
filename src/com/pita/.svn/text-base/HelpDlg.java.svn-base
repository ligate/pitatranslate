package com.pita;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelpDlg {
	static void show(Context ctxt) {
		LinearLayout layout = new LinearLayout(ctxt);
    	layout.setOrientation(LinearLayout.VERTICAL);
    	layout.setPadding(5,5,5,5);
    	
    	TextView v = new TextView(ctxt);
    	v.setText(R.string.help_msg);
    	layout.addView(v);
    	
    	new AlertDialog.Builder(ctxt)
			.setView(layout)
			.setPositiveButton(R.string.ok, null)
			.show(); 
	}
}
