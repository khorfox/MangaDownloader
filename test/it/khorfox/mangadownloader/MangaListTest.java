package it.khorfox.mangadownloader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.khorfox.mangadownloader.base.MangaList;
import it.khorfox.mangadownloader.interf.MangaPageInterface;
import it.khorfox.mangadownloader.mangaHere.MangaHereList;

public class MangaListTest extends MangaTest {

	private MangaList reader;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		reader = new MangaHereList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		int pagineLette;
		try {
			pagineLette = reader.read("http://www.mangahere.co/mangalist/");
			assertTrue(pagineLette > 0);
			System.out.println(reader.getListManga());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetManga() {
		int pagineLette;
		try {
			pagineLette = reader.read("http://www.mangahere.co/mangalist/");
			assertTrue(pagineLette > 0);
			MangaPageInterface manga=reader.getManga("Japan Tengu Party Illustrated");
			assertNotNull(manga);
			System.out.println(manga);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetMangaNoPage() {
		int pagineLette;
		try {
			pagineLette = reader.read("http://www.mangahere.co/mangalist/");
			assertTrue(pagineLette > 0);
			MangaPageInterface manga=reader.getManga("DylanDog");
			assertNull(manga);
			System.out.println(manga);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
}
