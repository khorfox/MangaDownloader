package it.khorfox.mangadownloader.interf;

import java.util.Hashtable;

import org.jsoup.nodes.Element;

public interface MangaPageInterface {

	int getChapterList() throws Exception;

	String getClassId();

	MangaChapterInterface getChapter(String name);
	
	Hashtable<String,String> getListChapter();

	boolean getRule(Element el);
}