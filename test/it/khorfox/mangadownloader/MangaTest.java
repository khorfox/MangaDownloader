package it.khorfox.mangadownloader;

import org.junit.After;
import org.junit.Before;

public class MangaTest {

	@Before
	public void setUp() throws Exception {
		MangaDownloaderAuthenticator authenticator = new MangaDownloaderAuthenticator("TL001023", "0rt0p3d!a");
		authenticator.setProxy();
	}

	@After
	public void tearDown() throws Exception {
	}

}
