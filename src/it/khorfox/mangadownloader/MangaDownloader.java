package it.khorfox.mangadownloader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Hashtable;

import it.khorfox.mangadownloader.formatter.MangaCbzFormatter;
import it.khorfox.mangadownloader.formatter.MangaFormatter;
import it.khorfox.mangadownloader.interf.MangaChapterInterface;
import it.khorfox.mangadownloader.interf.MangaListInterface;
import it.khorfox.mangadownloader.interf.MangaPageInterface;
import it.khorfox.mangadownloader.mangaHere.MangaHereChapter;

public class MangaDownloader {

	public String mangaProvider;
	public String manga;
	public String outDirectory;
	public Hashtable<String,String> chapters;
	private MangaLogger logger;
	private String mangaDirectory;
	private MangaCache cache;
	private MangaFactory factory;
	private MangaFormatter outputFormatter;
	private MangaListInterface list;
	
	public MangaDownloader(String mangaProvider, String manga, String outDirectory) {
		super();
		this.mangaProvider = mangaProvider;
		this.manga = manga;
		this.outDirectory = outDirectory;
		chapters = new Hashtable<String,String>();
	}

	public void download() throws Exception {
		init();
		String url = factory.getUrl();
		list = factory.getMangaList();
		int elenco = list.read(url);
		System.out.println(list.getListManga());
		if(elenco == 0) {
			throw new Exception("Nothing to List");
		}
		loadPages();
		System.out.println("Inizio Zip");
		outputFormatter.format(mangaDirectory,mangaDirectory);
		System.out.println("Fine Zip");
		removeTempDirectories();
	}

	private void download(String chapterUrl, String chapterName) throws Exception {
		init();
		MangaChapterInterface chapter = factory.getMangaChapter(chapterName,chapterUrl );
		this.getChapter(new DecimalFormat("0000"), chapter);
		System.out.println("Inizio Zip");
		outputFormatter.format(mangaDirectory,mangaDirectory);
		System.out.println("Fine Zip");
		removeTempDirectories();
	}

	private void init() throws Exception {
		factory = new MangaFactory(mangaProvider);
		outputFormatter = new MangaCbzFormatter(chapters); 
		mangaDirectory = outDirectory + File.separator + manga;
		createOutDirectory(mangaDirectory);
		logger = new MangaLogger(this.mangaProvider,this.manga);
		cache = logger.load();
		cache.whois();
	}
	
	private void loadPages() throws Exception {
		try {
			System.out.println("Inizio Scarico");
			MangaPageInterface page=list.getManga(manga);
			System.out.println(page);
			int nPage = page.getChapterList();
			System.out.println("Lette: " + nPage);
			System.out.println("Capitoli: " + page.getChapterList());
			for(String el: page.getListChapter().keySet()) {
				DecimalFormat formatter = new DecimalFormat("0000");
				MangaChapterInterface chapter = page.getChapter(el);
				System.out.println("Capitolo: " + chapter);
				getChapter(formatter, chapter);
			}
			
		}
		finally {
			logger.closeLogger();
			System.out.println("Fine Scarico");
		}
	}

	private void getChapter(DecimalFormat formatter,
			MangaChapterInterface chapter) throws Exception {
		String chapterDirectory = mangaDirectory + File.separator + chapter.getName();
		createOutDirectory(chapterDirectory);
		chapters.put(chapter.getName(),chapterDirectory);
		String link = "";
		int i = 0;
		do {
			if (!cache.find(chapter.getName()+chapter.getUrl())) {
				write(chapter.getImage(), 
						chapterDirectory
						+ File.separator
						+ formatter.format(++i)
						+ ".jpg");
				logger.log(chapter.getName()+chapter.getUrl());
			}
			MangaHereChapter next = (MangaHereChapter) chapter.getNext();
			chapter.setUrl(next.url);
			link = next.url;
			if(!link.contains(".html")){
				break;
			}
		} while((link != null ) && (link.length() > 4) && (i < 1000));
	}

	private void removeTempDirectories() {
		System.out.println("Inizio Cancellazione Directory");
		for(String dirName: chapters.keySet()){
			File dirToRemove = new File(dirName);
			System.out.println("Cancellata " + dirName + " con esito " + dirToRemove.delete());
		}
		System.out.println("Fine Cancellazione Directory");
	}

	private void createOutDirectory(String name) {
	      File file = new File(name);
	        if (!file.exists()) {
	            if (file.mkdir()) {
	                //System.out.println("Directory " + name + " is created!");
	            } else {
	                System.out.println("Failed to create directory!" + name);
	            }
	        }	
	}

	private void write(String urlImage, String name) {
		//System.out.println("Immagine: " + urlImage);
		//System.out.println("File: " + name);
		try {
			if ((urlImage != null) && (urlImage != "")) {
				URL url  = new URL(urlImage);
		        InputStream in = url.openStream();
		        OutputStream out = new BufferedOutputStream(new FileOutputStream(name));
		        for (int b; (b = in.read()) != -1;) {
		            out.write(b);
		        }
		        out.close();
		        in.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		if ((args.length == 0 ) ||  ((args.length < 3 ) && (args.length != 5 )) )	{
			System.out.println("Incorrect number of arguments: the syntax is provider manga directory [user password] [options]");
		}
		MangaDownloader downloader = new MangaDownloader(args[0],args[1],args[2]);
		if (args.length >= 5) {
			MangaDownloaderAuthenticator authenticator = new MangaDownloaderAuthenticator(args[3], args[4]);
			authenticator.setProxy();
		}
		// check options
		String chapter = "";
		String name = "";
		for(int i = 2; i < args.length; i++ ) {
			String op = args[i];
			if(op.equals("-c") && i < args.length - 1) {
				chapter = args[i+1];
				System.out.println("option single chapter url: " + chapter);
			}
			if(op.equals("-n") && i < args.length - 1) {
				name = args[i+1];
				System.out.println("option single chapter name: " + name);
			}
		}
		if(!chapter.equals("")){
			System.out.println("download single chapter");
			downloader.download(chapter,name);
		} else {
			downloader.download();
		}
	}
	
}
