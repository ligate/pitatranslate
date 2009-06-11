package com.pita;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class PitaTranslate extends Activity {
	
	// Preferences constants for the app.
	private static final String PREFS_NAME              = "Prefs";
	private static final String PREFS_TOP_LANG_INDEX    = "topLanguageIndex";
	private static final String PREFS_BOTTOM_LANG_INDEX = "bottomLanguageIndex";
	private static final String PREFS_NORMAL_ORDER      = "normalOrder";
	private static final String PREFS_TOP_TEXT          = "topText";
	private static final String PREFS_BOTTOM_TEXT       = "bottomText";
	
	private static final int HELP_ID  = Menu.FIRST;
    private static final int ABOUT_ID = Menu.FIRST + 1;
	
    private Translator translator_ = new Translator();
    
	private boolean normalOrder_;
	
	private TextView topLabel_;
	private TextView bottomLabel_;
	
	private EditText topEdit_;
	private EditText bottomEdit_;
	
	private Spinner topLangSpinner_;
	private Spinner bottomLangSpinner_;

	// We track selected position to avoid spurious translations caused by non-changes to
	// the language spinners
	private int topLangPos_;
	private int bottomLangPos_;
	
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        translator_.loadState(this);
        
        setContentView(R.layout.main);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int topLanguageIndex = settings.getInt(PREFS_TOP_LANG_INDEX, 0);
        int bottomLanguageIndex = settings.getInt(PREFS_BOTTOM_LANG_INDEX, 0);
        normalOrder_ = settings.getBoolean(PREFS_NORMAL_ORDER, true);
        String topText = settings.getString(PREFS_TOP_TEXT,  "");
        String bottomText = settings.getString(PREFS_BOTTOM_TEXT, "");
        
        // Initialize language selection spinners
        topLangSpinner_ = (Spinner) findViewById(R.id.top_lang);
        bottomLangSpinner_ = (Spinner) findViewById(R.id.bottom_lang);        
        populateLangSelectors();
        
        // update translation on spinner selection changes
		topLangSpinner_.setOnItemSelectedListener(
			new OnItemSelectedListener() {
				@Override
		       	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					if (position != topLangPos_) {
						topLangPos_ = position;
						updateTranslation();						
		       		}
				}
		        	@Override
				public void	onNothingSelected(AdapterView<?> parent) {}
			});
		
		bottomLangSpinner_.setOnItemSelectedListener(
			new OnItemSelectedListener() {
				@Override
		       	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					if (position != bottomLangPos_) {
						bottomLangPos_ = position;
						updateTranslation();						
		       		}
				}

				@Override
				public void	onNothingSelected(AdapterView<?> parent) {}
				});
			
        topLangSpinner_.setSelection(topLanguageIndex);
        bottomLangSpinner_.setSelection(bottomLanguageIndex);
    
        topLangPos_    = topLangSpinner_.getSelectedItemPosition();
        bottomLangPos_ = bottomLangSpinner_.getSelectedItemPosition();
        
        // Initialize to/from labels
        topLabel_ = (TextView) findViewById(R.id.top_label);
        bottomLabel_ = (TextView) findViewById(R.id.bottom_label);
        if (normalOrder_) {
        	topLabel_.setText(R.string.from);
        	bottomLabel_.setText(R.string.to);
        } else {
        	topLabel_.setText(R.string.to);
        	bottomLabel_.setText(R.string.from);
        }
        
        // Initialize edit boxes
        topEdit_ = (EditText) findViewById(R.id.top_edit);
        topEdit_.setOnKeyListener(
        		new OnKeyListener() {

					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						topLabel_.setText(R.string.from);
						bottomLabel_.setText(R.string.to);
						normalOrder_ = true;
						return false;
					}
        			
        		});
        topEdit_.setText(topText);

        bottomEdit_ = (EditText) findViewById(R.id.bottom_edit);
        bottomEdit_.setOnKeyListener(
        		new OnKeyListener() {
        			@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
        				topLabel_.setText(R.string.to);
        				bottomLabel_.setText(R.string.from);
        				normalOrder_ = false;
						return false;
					}
        		});
        bottomEdit_.setText(bottomText);
        
        // Initialize translate button
        Button xlateButton = (Button) findViewById(R.id.translate);
        xlateButton.setOnClickListener(
        		new OnClickListener() {
        		@Override
        		public void onClick(View v) {
        			updateTranslation();
        		}
        	});
        
        // Init clear button
        Button clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(
        		new OnClickListener() {
        			@Override
        			public void onClick(View v) {
        				topEdit_.setText("");
        				bottomEdit_.setText("");
        			}
        		});	
    }
    
    /*! Updates the language-selection boxes with the available lanaguage options.
     */
    private void populateLangSelectors() {
    	List<String> fullNames = new ArrayList<String>(LanguageMap.langMap.keySet());
    	java.util.Collections.sort(fullNames);
    	
    	ArrayAdapter<String> adapter = 
    		new ArrayAdapter<String>(this,
    				android.R.layout.simple_spinner_item,
    				fullNames);
    	adapter.setDropDownViewResource(
    			android.R.layout.simple_spinner_dropdown_item);   	
    	
    	topLangSpinner_.setAdapter(adapter);
    	bottomLangSpinner_.setAdapter(adapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, HELP_ID, 0, R.string.menu_help).setIcon(
        		android.R.drawable.ic_menu_help);
        menu.add(0, ABOUT_ID, 0,  R.string.menu_about).setIcon(
        		android.R.drawable.ic_menu_info_details);
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
        case HELP_ID:
            HelpDlg.show(this);
            return true;
        case ABOUT_ID:
        	AboutDlg.show(this);
            return true;
        }
       
        return super.onMenuItemSelected(featureId, item);
    }
    
    @Override
    protected void onPause(){
    	super.onPause();

    	translator_.saveState(this);
    	
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	SharedPreferences.Editor editor = settings.edit();

    	editor.putInt(
    			PREFS_TOP_LANG_INDEX,
    			topLangSpinner_.getSelectedItemPosition());
    	editor.putInt(
    			PREFS_BOTTOM_LANG_INDEX,
    			bottomLangSpinner_.getSelectedItemPosition());
    	editor.putBoolean(
    			PREFS_NORMAL_ORDER,
    			normalOrder_);
    	editor.putString(
    			PREFS_TOP_TEXT,
    			topEdit_.getText().toString());
    	editor.putString(
    			PREFS_BOTTOM_TEXT, 
    			bottomEdit_.getText().toString());
    	
    	editor.commit();
    }
    
    /*! This updates the displayed translation based on the currently selected
     * languages and text. Whichever TextView was edited most recently is 
     * considered the source text. The other TextView is updated with the
     * translation of the source text.
     */
    private void updateTranslation()
    {
    	// Determine the from- and to-EditViews based on
    	// normalOrder_'s value. 
    	TextView fromEdit = normalOrder_ ? topEdit_ : bottomEdit_;
    	TextView toEdit   = normalOrder_ ? bottomEdit_ : topEdit_;
    	
    	// Determine the language abbreviations for the selected languages.
    	String fromLang   = LanguageMap.abbrev(
    							(normalOrder_ 
    							 ? topLangSpinner_.getSelectedItem() 
    							 : bottomLangSpinner_.getSelectedItem()).toString());
    	String toLang     = LanguageMap.abbrev(
    							(normalOrder_ 
    							 ? bottomLangSpinner_.getSelectedItem() 
    							 : topLangSpinner_.getSelectedItem()).toString());
    	
    	// Perform the actual translation and update the to-EditView with
    	// the results.
    	try {
    		// TODO: Check for network and alert user or something?
    		
    		// TODO: Should we make multiple attempts? It seems that sometimes
    		// there are spurious, transient network dropouts.
    		
    		toEdit.setText(
    				translator_.translate(
    						fromEdit.getText().toString(),
    						fromLang,
    						toLang));
    	} catch (Exception e) {
    		// TODO: Think about what we might do better in the absence of a network. Maybe what we're doing
    		// now is fine, but perhaps we might just skip connecting to google if we can determine that
    		// there is no network available.
    		/*
    		ConnectivityManager cmgr = 
    			(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    		int msg = cmgr.getActiveNetworkInfo().isAvailable() 
    			? R.string.translation_error 
    			: R.string.translation_error_network_failure;
    		*/
    		
        	new AlertDialog.Builder(this)
    			.setMessage(R.string.translation_error)
    			.setPositiveButton(R.string.ok, null)
    			.show();    		
    	}
    }
}