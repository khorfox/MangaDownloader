package it.khorfox.mangadownloader.mangaHere;

import it.khorfox.mangadownloader.base.MangaList;
import it.khorfox.mangadownloader.interf.MangaListInterface;
import it.khorfox.mangadownloader.interf.MangaPageInterface;

public class MangaHereList extends MangaList implements MangaListInterface {

	@Override
	public MangaPageInterface getManga(String mangaName) throws Exception {
		String url = getListManga().get(mangaName.toUpperCase());
		if(url != null) {
			return new MangaHerePage(mangaName,url);
		} else {
			throw new Exception("manga non trovato: " + mangaName);
		}
	}

	@Override
	public String getClassId() {
		return "manga_info";
	}

	
}
