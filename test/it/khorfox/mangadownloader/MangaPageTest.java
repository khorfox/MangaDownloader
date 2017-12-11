package it.khorfox.mangadownloader;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.khorfox.mangadownloader.base.MangaPage;
import it.khorfox.mangadownloader.mangaHere.MangaHerePage;

public class MangaPageTest extends MangaTest {

	private MangaPage page;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		page = new MangaHerePage("Desire (KOTANI Kenichi)","http://www.mangahere.co/manga/desire_kotani_kenichi/");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetChapterList() {
		int chapters;
		System.out.println(page);
		try {
			chapters = page.getChapterList();
			assertTrue(chapters > 0);
			System.out.println(page.getListChapter());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
