package com.pita;

import java.io.FileNotFoundException;

import android.content.Context;
import android.util.Log;

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
	
    private Cache cache_;

    public Translator()
    {
    	// TODO: This should be a user-configurable setting
    	cache_ = new Cache(100);
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
