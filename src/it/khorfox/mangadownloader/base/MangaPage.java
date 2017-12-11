package it.khorfox.mangadownloader.base;

import java.util.Hashtable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import it.khorfox.mangadownloader.interf.MangaChapterInterface;
import it.khorfox.mangadownloader.interf.MangaPageInterface;

public class MangaPage implements MangaPageInterface {

	@Override
	public String toString() {
		return "MangaPage [name=" + name + ", url=" + url + "]";
	}

	public String name;
	public String url;
	protected Hashtable<String,String> listChapter = new Hashtable<String,String>();
	
	public MangaPage(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
	
	/* (non-Javadoc)
	 * @see it.khorfox.mangadownloader.base.MangaPageInterface#getChapterList()
	 */
	@Override
	public int getChapterList() throws Exception{
		Document doc = Jsoup.connect(url).get();
        Elements content = doc.getElementsByClass(getClassId());
		if(content != null) {
			for (Element link : content) {
			  String linkHref = link.attr("href");
			  String linkText = link.text();
			  if (getRule(link)) {
				  linkHref = linkHref.startsWith("http:") ? linkHref : "http:" + linkHref;
				  listChapter.put(linkText, linkHref);
			  }
			}
		}
		return listChapter.size();
	}
	
	/* (non-Javadoc)
	 * @see it.khorfox.mangadownloader.base.MangaPageInterface#getClassId()
	 */
	@Override
	public String getClassId() {
		return "";
	}

	/* (non-Javadoc)
	 * @see it.khorfox.mangadownloader.base.MangaPageInterface#getChapter(java.lang.String)
	 */
	@Override
	public MangaChapterInterface getChapter(String name){
		String url = this.listChapter.get(name);
		if(url != null) {
			return new MangaChapter(name,url);
		}
		return null;
	}

	@Override
	public Hashtable<String, String> getListChapter() {
		return listChapter;
	}

	@Override
	public boolean getRule(Element el) {
		return true;
	}
	
	
}
