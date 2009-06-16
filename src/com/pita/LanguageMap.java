package com.pita;

import java.util.HashMap;
import java.util.Map;

/*! Fundamentally, a mapping from human-readable language names
 * to the abbreviations used by the low-level translation systems.
 */
public class LanguageMap {
	
	public static final Map<String, String> langMap = 
		new HashMap<String, String>() {{
			put("Arabic",                	"ar");
	        put("Bulgarian",             	"bg");
	        put("Catalan",               	"ca");
	        put("Chinese",               	"zh");
	        put("Chinese (Simplified)",  	"zh-CN");
	        put("Chinese (Traditional)", 	"zh-TW");
	        put("Croatian",              	"hr");
	        put("Czech",                 	"cs");
	        put("Danish",                	"da");
	        put("Dutch",                 	"nl");
	        put("English",              	"en");
	        put("Finnish",               	"fi");
	        put("French",                	"fr");
	        put("German",                	"de");
	        put("Greek",                 	"el");
	        put("Hebrew",                	"iw");
	        put("Hindi",                 	"hi");
	        put("Indonesian",            	"id");
	        put("Italian",               	"it");
	        put("Japanese",  			 	"ja");
	        put("Korean", 					"ko");
	        put("Latvian", 					"lv");
	        put("Lithuanian", 				"lt");
	        put("Norwegian", 				"no");
	        put("Polish", 					"pl");
	        put("Portugese", 				"pt");
	        put("Romanian", 				"ro");
	        put("Russian", 					"ru");
	        put("Serbian", 					"sr");
	        put("Slovak", 					"sk");
	        put("Slovenian", 				"sl");
	        put("Spanish", 					"es");
	        put("Swedish", 					"sv");
	        put("Tagalog",              	"tl");
	        put("Ukranian", 				"uk");
	        put("Vietnamese", 				"vi");
		}};
		
		/*! Returns the abbreviation for the languages \a full */
		public static String abbrev(String full) {
			return langMap.get(full);
		}
		
		/*! Returns the fullname for the language abbreviation \a abbrev */
		public static String fullName(String abbrev) {
			for (String key : langMap.keySet()) {
				if (langMap.get(key) == abbrev)
					return key;
			}
			
			return null;
		}
	}
