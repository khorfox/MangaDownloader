package it.khorfox.mangadownloader.mangaHere;

import org.jsoup.nodes.Element;

import it.khorfox.mangadownloader.base.MangaPage;
import it.khorfox.mangadownloader.interf.MangaChapterInterface;
import it.khorfox.mangadownloader.interf.MangaPageInterface;

public class MangaHerePage extends MangaPage implements MangaPageInterface {

	@Override
	public boolean getRule(Element link) {
		if ((link != null) && (link.text() != null)) {
			return link.text().toUpperCase().startsWith(name.toUpperCase());
		}
		return false;
	}

	public MangaHerePage(String name, String url) {
		super(name, url);
	}

	public String getClassId() {
		return "color_0077";
	}
	
	public MangaChapterInterface getChapter(String name){
		String url = this.listChapter.get(name);
		if(url != null) {
			return new MangaHereChapter(name,url);
		}
		return null;
	}

	
	
}
