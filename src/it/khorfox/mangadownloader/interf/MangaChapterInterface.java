package it.khorfox.mangadownloader.interf;

public interface MangaChapterInterface {

	public String getImage() throws Exception;

	public MangaChapterInterface getNext() throws Exception;

	public String getName();

	public String getUrl();
	
	public void setName(String name);

	public void setUrl(String url);

	public String getImageClassId();

	public String getNextClassId();

}