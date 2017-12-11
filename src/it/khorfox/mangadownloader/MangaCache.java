package it.khorfox.mangadownloader;

import java.util.ArrayList;

public class MangaCache {
	
	public ArrayList<String> cache;

	public MangaCache() {
		super();
		cache = new ArrayList<String>();
	}

	public void put(String linea) {
		cache.add(linea);
	}

	public boolean find(String key) {
		for(String el: cache) {
			if (el.equalsIgnoreCase(key)) {
				return true;
			}
		}
		return false;
	}

	public void whois() {
		if(cache.size() == 0) {
			System.out.println("Empty cache");
		} else {
			System.out.println("Non Empty cache, size: " + cache.size());
		}
		
	}

	@Override
	public String toString() {
		return cache.toString();
	}
}
