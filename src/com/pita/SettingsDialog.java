package com.pita;

import android.app.Dialog;
import android.content.Context;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

public class SettingsDialog 
	extends Dialog 
{

	EditText cache_size_edit_;
	
	public SettingsDialog(Context context,
						  int maxCacheSize) {
		super(context);
		
		setTitle(R.string.settings_title);
		setContentView(R.layout.settings_dlg);
		
		cache_size_edit_ = (EditText) findViewById(R.id.cache_size_edit);
		cache_size_edit_.setFilters(new InputFilter[]{ DigitsKeyListener.getInstance() });
		cache_size_edit_.setText(String.valueOf(maxCacheSize));
	}

	public int getMaxCacheSize()
	{
		return Integer.parseInt(cache_size_edit_.getText().toString());
	}
	
}
