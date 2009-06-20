package com.pita;

import java.util.HashMap;
import java.util.Map;

import com.google.api.translate.Language;

/*! Fundamentally, a mapping from human-readable language names
 * to the abbreviations used by the low-level translation systems.
 */
public class LanguageMap {
	
	public static final Map<String, Language> langMap = 
		new HashMap<String, Language>() {{
			put("Albanian",                 Language.ALBANIAN);
			put("Arabic",                	Language.ARABIC);
	        put("Bulgarian",             	Language.BULGARIAN);
	        put("Catalan",               	Language.CATALAN);
	        put("Chinese",               	Language.CHINESE);
	        put("Chinese (Simplified)",  	Language.CHINESE_SIMPLIFIED);
	        put("Chinese (Traditional)", 	Language.CHINESE_TRADITIONAL);
	        put("Croatian",              	Language.CROATIAN);
	        put("Czech",                 	Language.CZECH);
	        put("Danish",                	Language.DANISH);
	        put("Dutch",                 	Language.DUTCH);
	        put("English",              	Language.ENGLISH);
	        put("Estonian",                 Language.ESTONIAN);
	        put("Finnish",               	Language.FINNISH);
	        put("French",                	Language.FRENCH);
	        put("Galacian",                 Language.GALACIAN);
	        put("German",                	Language.GERMAN);
	        put("Greek",                 	Language.GREEK);
	        put("Hebrew",                	Language.HEBREW);
	        put("Hindi",                 	Language.HINDI);
	        put("Hungarian",                Language.HUNGARIAN);
	        put("Indonesian",            	Language.INDONESIAN);
	        put("Italian",               	Language.ITALIAN);
	        put("Japanese",  			 	Language.JAPANESE);
	        put("Korean", 					Language.KOREAN);
	        put("Latvian", 					Language.LATVIAN);
	        put("Lithuanian", 				Language.LITHUANIAN);
	        put("Maltese",                  Language.MALTESE);
	        put("Norwegian", 				Language.NORWEGIAN);
	        put("Polish", 					Language.POLISH);
	        put("Portugese", 				Language.PORTUGUESE);
	        put("Romanian", 				Language.ROMANIAN);
	        put("Russian", 					Language.RUSSIAN);
	        put("Serbian", 					Language.SERBIAN);
	        put("Slovak", 					Language.SLOVAK);
	        put("Slovenian", 				Language.SLOVENIAN);
	        put("Spanish", 					Language.SPANISH);
	        put("Swedish", 					Language.SWEDISH);
	        put("Tagalog",              	Language.FILIPINO);
	        put("Thai",                     Language.THAI);
	        put("Turkish",                  Language.TURKISH);
	        put("Ukranian", 				Language.UKRANIAN);
	        put("Vietnamese", 				Language.VIETNAMESE);
		}};
		
		/*! Returns the abbreviation for the languages \a full */
		public static Language langValue(String full) {
			return langMap.get(full);
		}
		
		/*! Returns the fullname for the language abbreviation \a abbrev */
		public static String fullName(String abbrev) {
			for (String key : langMap.keySet()) {
				if (langMap.get(key).toString() == abbrev)
					return key;
			}
			
			return null;
		}
	}
