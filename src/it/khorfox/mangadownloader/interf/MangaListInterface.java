package it.khorfox.mangadownloader.interf;

import java.util.Hashtable;

public interface MangaListInterface {

	int read(String url) throws Exception;

	MangaPageInterface getManga(String mangaName) throws Exception;
	Hashtable<String,String> getListManga();

	String getClassId();
}