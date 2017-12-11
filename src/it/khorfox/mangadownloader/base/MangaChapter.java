package it.khorfox.mangadownloader.base;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import it.khorfox.mangadownloader.interf.MangaChapterInterface;

public class MangaChapter implements MangaChapterInterface {

	public MangaChapter() {
		super();
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}


	public String name;
	public String url;
	public MangaChapter(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	@Override
	public String toString() {
		return "MangaChapter [name=" + name + ", url=" + url + "]";
	}

	/* (non-Javadoc)
	 * @see it.khorfox.mangadownloader.MangaChapterInterface#getImage()
	 */
	@Override
	public String getImage() throws Exception{
		String linkText = "";
		Document doc = Jsoup.connect(url).get();
		Elements content = doc.getElementsByClass(getImageClassId());
		if(content != null) {
			for (Element link : content) {
				for(Node el: link.childNodes()){
					if(el.nodeName().equals("a")){
						for(Node node: el.childNodes()){
							if(node.nodeName().equals("img") && node.attr("id").equals("image")){
								linkText=node.absUrl("src");
							}
						}
					}
				}
			}
		}
		return linkText;
	}
	

	/* (non-Javadoc)
	 * @see it.khorfox.mangadownloader.MangaChapterInterface#getNext()
	 */
	@Override
	public MangaChapterInterface getNext() throws Exception{
		String linkText = "";
		Document doc = Jsoup.connect(url).get();
		Elements content = doc.getElementsByClass(getNextClassId());
		if(content != null) {
			for (Element link : content) {
				linkText= link.attr("href");
				linkText = linkText.startsWith("http:") ? linkText : "http:" + linkText;
			}
		}
		return new MangaChapter(name, linkText);
	}

	@Override
	public String getImageClassId() {
		return "";
	}

	@Override
	public String getNextClassId() {
		return "";
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	
}
