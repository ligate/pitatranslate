package com.pita;

import java.io.FileNotFoundException;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.api.translate.Translate;

/*! Class for performing translation between languages.
 * 
 * Translator is responsible for actually doing translations between the various
 * languages it supports. Translator uses a cache of recent translations to 
 * speed up common, repeated requests. If the cache doesn't contain the 
 * requested translation then the google translation service is used.
 */
public class Translator {
	
	private static final String LOG_TAG = "PitaTranslate.Translator";
	private static final String PREFS_MAX_CACHE_SIZE = "maxCacheSize";
	
    private Cache cache_;

    public Translator(Context ctxt)
    {
    	SharedPreferences prefs = ctxt.getSharedPreferences(Common.PREFS_NAME, 0);
    	
    	cache_ = new Cache(prefs.getInt(PREFS_MAX_CACHE_SIZE, 100));	
    	
    	prefs.registerOnSharedPreferenceChangeListener(
    			new SharedPreferences.OnSharedPreferenceChangeListener() {
    				@Override
    				public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
    					if (key == PREFS_MAX_CACHE_SIZE)
    					{
    						cache_.setMaxSize(sp.getInt(PREFS_MAX_CACHE_SIZE, 100));
    					}
    				}
    			});
    }
    
    public void loadState(Context ctxt) {
    	try {
    		cache_.load(
    				ctxt.openFileInput(
    						ctxt.getResources().getString(
    								R.string.cache_filename)));
    	} catch (FileNotFoundException e) {
    		Log.i(LOG_TAG, "Unable to open cache file for loading");
    	}
    }

    public void saveState(Context ctxt) {
    	try {
    		// TODO: For some reason, we're not de/serializing properly...figure this out.
    		cache_.save(
    				ctxt.openFileOutput(
    						ctxt.getResources().getString(
    								R.string.cache_filename),
    						Context.MODE_PRIVATE));
    	} catch (FileNotFoundException e) {
    		Log.w(LOG_TAG, "Unable to open cache file for saving");
    	}
    }

    /*! Performs a translation of \a text from language \a from
     * to language \a to. Both \a from and \a to must be appropriate
     * language abbreviations (see LanguageMap)
     */
    public String translate(String text,
			    			String from,
			    			String to) throws Exception {
    	if (from.length() == 0)
    		return "";
    	
    	// First check the cache
    	String result = cache_.find(from, to, text);
    		
    	// If the cache didn't have the answer, ask google
    	if (result == null)
    		result = Translate.translate(text, from, to);

    	// No matter where the translation came from, always put it in the
    	// cache
    	cache_.insert(from, 
    				  to, 
    				  text, 
    				  result);
    	
    	return result;
    }

};
