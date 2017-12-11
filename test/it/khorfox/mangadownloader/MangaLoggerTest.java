package it.khorfox.mangadownloader;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MangaLoggerTest {

	private MangaLogger logger;

	@Before
	public void setUp() throws Exception {
		logger = new MangaLogger("MangaHere","Desire (KOTANI Kenichi)"); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLog() {
		try {
			logger.log("http://www.mangahere.co/manga/desire_kotani_kenichi/v02/c009/11.html");
			logger.pw.close();
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
