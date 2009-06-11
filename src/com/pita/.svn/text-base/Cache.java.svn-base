package com.pita;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

import android.util.Log;

/*! Cache: A cache of previously performed translations. 
 * 	
 * This stores and can retrieve previously calculated translations. When a
 * translation is needed, you can check in a cache first, potentially avoiding
 * a network access. Likewise, when a new translation is calculated that was
 * not previously in the cache, it should be inserted into the cache.
 * 
 * The cache is limited in the number of translations it will store. Entries in
 * cache are removed in a least-recently-inserted manner: when a cache entry
 * needs to be removed, the one that was least recently inserted is dropped.
 */
public class Cache {
	
	private static final String LOG_TAG = "PitaTranslate.Cache";
	
	/*! A single entry in the cache */
	private class Entry 
	{
		Entry(String fl,
			  String tl,
			  String from,
			  String to)
		{
			fromLang_ = fl;
			toLang_ = tl;
			from_ = from;
			to_ = to;
		}
		
		public String fromLang_;
		public String toLang_;
		public String from_;
		public String to_;
	}

	/*! The result of calling search. This contains both the matching Entry
	 * and the iterator from which the Entry was extracted. 
	 */
	private class SearchResult
	{
		SearchResult(Iterator<Entry> itr,
					 Entry e)
		{
			itr_ = itr;
			e_ = e;
		}
		
		public Iterator<Entry> itr_;
		public Entry e_;
	}
	
	/*! The list of Entry objects representing the cached values */
	private LinkedList<Entry> cache_;
	
	/*! The maximum size of the cache in number of Entry objects */
	private int maxSize_;
	
	/*! Constructs a new Cache with maxSize_ of \a maxSize */
	public Cache(int maxSize)
	{
		maxSize_ = maxSize;
	}
	
	/*! Searches the cache for an entry matching the parameters. Returns
	 * a SearchResult for the matching Entry if one is found, null otherwise.
	 */
	private SearchResult search(String fromLang,
								String toLang,
								String from)
	{
		Iterator<Entry> itr = cache_.iterator();
		while (itr.hasNext()) {
			Entry e = itr.next();
			if (e.from_.equals(from)         &&
				e.fromLang_.equals(fromLang) &&
				e.toLang_.equals(toLang))
				return new SearchResult(itr, e);
		}
		
		return null;
	}

	/*! Searches the cache for a translation of \a from from
	 * \a fromLang to \a toLang. Returns the translation on
	 * success, null otherwise.
	 */
	public String find(String fromLang,
					   String toLang,
					   String from)
	{
		SearchResult r =  search(fromLang, toLang, from);
		if (r == null) 
			return null;
		else 
			return r.e_.to_;
	}
	
	/*! Inserts a new translation into the cache, trimming the size of
	 * the cache if necessary. This checks to see if a matching translation
	 * already exists. If so, the match is removed from the cache before
	 * adding the new translation to the front (this is how the
	 * removal order is maintained.)
	 */
	public void insert(String fromLang,
					   String toLang,
					   String from,
					   String to) 
	{
		// First, see if the translation already exists in the cache,
		// removing it if so.
		SearchResult r = search(fromLang, toLang, from);
		if (r != null) r.itr_.remove();
		cache_.addFirst(new Entry(fromLang, toLang, from, to));
		
		trim();
	}
	
	/*! Removes elements from the cache until the cache is no larger than
	 * maxSize_.
	 */
	private void trim()
	{
		while (cache_.size() > maxSize_)
			cache_.removeLast();
	}
	
	/*! Writes the cache contents to \a os */
	public void save(OutputStream os)
	{
		try {
			ObjectOutputStream stream = new ObjectOutputStream(os);
			stream.writeObject(cache_);
		} catch (IOException e) {
			Log.w(LOG_TAG, "Unable to open write cache to output stream");
		}
	}
	
	/*! Reads the cache contents from \a is */
	public void load(InputStream is)
	{
		try {
			ObjectInputStream stream = new ObjectInputStream(is);
			cache_ = (LinkedList<Entry>)(stream.readObject());
		} catch (IOException e) {
			Log.i(LOG_TAG, "Unable to load cache from input stream");
		}
		catch (ClassNotFoundException e) {
			Log.w(LOG_TAG, "Wrong type of object read from cache file");
		}

		// In case deserialization fails, at least have a valid cache
		if (cache_ == null) cache_ = new LinkedList<Entry>();
		trim();
	}
}
