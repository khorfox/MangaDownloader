package it.khorfox.mangadownloader.formatter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MangaCbzFormatterTest {

	private MangaCbzFormatter formatter;

	@Before
	public void setUp() throws Exception {
		Hashtable<String, String> folders = new Hashtable<String, String>();
		folders.put("Desire (KOTANI Kenichi) 9",
				"C:\\Temp\\Manga\\Desire (KOTANI Kenichi)\\Desire (KOTANI Kenichi) 9");
		folders.put("Desire (KOTANI Kenichi) 8",
				"C:\\Temp\\Manga\\Desire (KOTANI Kenichi)\\Desire (KOTANI Kenichi) 8");
		folders.put("Desire (KOTANI Kenichi) 41",
				"C:\\Temp\\Manga\\Desire (KOTANI Kenichi)\\Desire (KOTANI Kenichi) 41");
		formatter = new MangaCbzFormatter(folders);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			formatter.format("C:\\Temp\\Manga\\Desire (KOTANI Kenichi)", 
					"C:\\Temp\\Manga\\Desire (KOTANI Kenichi)");
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testListSubFolders() {
		MangaCbzFormatter formatter = new MangaCbzFormatter("C:\\Temp\\Manga\\Desire (KOTANI Kenichi)");
		int totSubFolders = formatter.listSubFolders("C:\\Temp\\Manga\\Desire (KOTANI Kenichi)");
		System.out.println(formatter.folders);
		assertTrue(totSubFolders>0);
	}

	@Test
	public void testAnotherFormat() {
		MangaCbzFormatter formatter = new MangaCbzFormatter("C:\\Temp\\Manga\\Desire (KOTANI Kenichi)");
		int totSubFolders = formatter.listSubFolders("C:\\Temp\\Manga\\Desire (KOTANI Kenichi)");
		System.out.println(formatter.folders);
		try {
			formatter.format("C:\\Temp\\Manga\\Desire (KOTANI Kenichi)", 
					"C:\\Temp\\Manga\\Desire (KOTANI Kenichi)");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		assertTrue(totSubFolders>0);
	}
	
}
