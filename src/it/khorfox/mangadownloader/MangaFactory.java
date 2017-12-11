package it.khorfox.mangadownloader;

import java.util.Hashtable;

import it.khorfox.mangadownloader.base.MangaChapter;
import it.khorfox.mangadownloader.base.MangaList;
import it.khorfox.mangadownloader.interf.MangaChapterInterface;
import it.khorfox.mangadownloader.interf.MangaListInterface;
import it.khorfox.mangadownloader.mangaHere.MangaHereChapter;
import it.khorfox.mangadownloader.mangaHere.MangaHereList;

public class MangaFactory {

	private String mangaProvider;
	private Hashtable<String,String> urls;
	private Hashtable<String,MangaList> mangaLists;
	private Hashtable<String,MangaChapter> mangaChapterLists;

	public MangaFactory(String mangaProvider) {
		this.mangaProvider = mangaProvider;
		setUpUrlList();
		setUpMangaList();
		setUpMangaChapterList();
	}

	@SuppressWarnings("serial")
	private void setUpUrlList() {
		urls = new Hashtable<String,String>() {{
			put("MangaHere","http://www.mangahere.co/mangalist/");
		}};
	}

	@SuppressWarnings("serial")
	private void setUpMangaList() {
		mangaLists = new Hashtable<String,MangaList>() {{
			put("MangaHere", new MangaHereList());
		}};
	}

	@SuppressWarnings("serial")
	private void setUpMangaChapterList() {
		mangaChapterLists = new Hashtable<String,MangaChapter>() {{
			put("MangaHere", new MangaHereChapter());
		}};
	}


	public String getUrl(){
		return(urls.get(this.mangaProvider));
	}

	public MangaListInterface getMangaList() {
		return(mangaLists.get(this.mangaProvider));
	}
	public MangaChapterInterface getMangaChapter(String name, String url) {
		MangaChapter chapter = mangaChapterLists.get(this.mangaProvider);
		if((name.equals(null) || (name.equals("")))){
			name = mangaProvider + "default";
		}
		chapter.setName(name);
		chapter.setUrl(url);
		return chapter;
	}


}
