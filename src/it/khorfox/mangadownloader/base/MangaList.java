/**
 * 
 */
package it.khorfox.mangadownloader.base;

import java.util.Hashtable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import it.khorfox.mangadownloader.interf.MangaListInterface;
import it.khorfox.mangadownloader.interf.MangaPageInterface;

/**
 * @author KHORFOX
 *
 */
public class MangaList implements MangaListInterface {

	private Hashtable<String,String> listManga = new Hashtable<String,String>();


	/* (non-Javadoc)
	 * @see it.khorfox.mangadownloader.base.MangaListInterface#read(java.lang.String)
	 */
	@Override
	public int read(String url) throws Exception {
		System.out.println("List at url:" + url);
		Document doc = Jsoup.connect(url).get();
        Elements content = doc.getElementsByClass(getClassId());
		if(content != null) {
			for (Element link : content) {
			  String linkHref = link.attr("href");
			  String linkText = link.text();
			  linkHref = (linkHref.startsWith("http")) ? linkHref : "http:" + linkHref;
			  listManga.put(linkText.toUpperCase(), linkHref);
			}
		}
		return listManga.size();
	}
	
	/* (non-Javadoc)
	 * @see it.khorfox.mangadownloader.base.MangaListInterface#getManga(java.lang.String)
	 */
	@Override
	public MangaPageInterface getManga(String mangaName) throws Exception {
		String url = this.listManga.get(mangaName.toUpperCase());
		if(url != null) {
			return new MangaPage(mangaName,url);
		}
		return null;
	}

	@Override
	public Hashtable<String, String> getListManga() {
		return listManga;
	}
	
	@Override
	public String getClassId() {
		return "";
	}

	
}
