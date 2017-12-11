package it.khorfox.mangadownloader.mangaHere;

import it.khorfox.mangadownloader.base.MangaChapter;
import it.khorfox.mangadownloader.interf.MangaChapterInterface;

public class MangaHereChapter extends MangaChapter {

	public MangaHereChapter(String name, String url) {
		super(name, url);
	}

	public MangaHereChapter(MangaChapterInterface chapter) {
		this.name = chapter.getName();
		this.url = chapter.getUrl();
	}
	
	public MangaHereChapter() {
	}

	@Override
	public String getImageClassId() {
		return "read_img";
	}

	@Override
	public String getNextClassId() {
		return "next_page";
	}

	@Override
	public MangaChapterInterface getNext() throws Exception {
		return new  MangaHereChapter(super.getNext());
	}

	
	
}
