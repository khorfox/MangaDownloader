package it.khorfox.mangadownloader;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DecimalFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.khorfox.mangadownloader.interf.MangaChapterInterface;
import it.khorfox.mangadownloader.mangaHere.MangaHereChapter;

public class MangaChapterTest extends MangaTest{

	private MangaChapterInterface chapter;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		chapter = new MangaHereChapter("Desire (KOTANI Kenichi) 9","http://www.mangahere.co/manga/desire_kotani_kenichi/v02/c009/");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetImage2() {
		chapter = new MangaHereChapter("default","http://www.mangahere.co/manga/shin_mazinger_zero/c021/");
		try {
			String img = chapter.getImage();
			assertTrue(img !=null);
			assertTrue(!img.equals(""));
			System.out.println(img);
			//Open a URL Stream
			        URL url = new URL(img);
			        InputStream in = url.openStream();
			        OutputStream out = new BufferedOutputStream(new FileOutputStream( "prova.img"));
			        for (int b; (b = in.read()) != -1;) {
			            out.write(b);
			        }
			        out.close();
			        in.close();

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetNext() {
		try {
			MangaChapterInterface next = chapter.getNext();
			assertTrue(next !=null);
			assertTrue(!next.getUrl().equals(""));
			System.out.println(next);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFormatter() {
		int i = 0;
		DecimalFormat formatter = new DecimalFormat("0000");
		String formattata = formatter.format(++i);
		System.out.println(formattata);
		assertTrue(formattata.equals("0001"));
		assertTrue(formatter.format(++i).equals("0002"));
	}

	
	@Test
	public void testLoopNext() {
		try {
			int i = 0;
			String link = "";
			do {
				MangaHereChapter next = (MangaHereChapter) chapter.getNext();
				System.out.println(next);
				chapter.setUrl(next.url);
				link = next.url;
				i++;
				if(!link.contains(".html")){
					break;
				}
			} while((link != null ) && (link.length() > 4) && (i < 1000));
			assertTrue(i > 2);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetImage() {
		try {
			String img = chapter.getImage();
			assertTrue(img !=null);
			assertTrue(!img.equals(""));
			System.out.println(img);
			//Open a URL Stream
			        URL url = new URL(img);
			        InputStream in = url.openStream();
			        OutputStream out = new BufferedOutputStream(new FileOutputStream( "prova.img"));
			        for (int b; (b = in.read()) != -1;) {
			            out.write(b);
			        }
			        out.close();
			        in.close();
	
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
