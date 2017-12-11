package it.khorfox.mangadownloader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MangaLogger {

	public String fileName;
	private File file;
	protected PrintWriter pw;

	public MangaLogger(String mangaProvider, String mangaName) {
		super();
		fileName = mangaProvider + mangaName + ".log";
		file = new File(fileName);
	}

	public MangaCache load() throws Exception {
		MangaCache cache = new MangaCache();
		boolean logExists = file.exists();
		if(!logExists) {
			file.createNewFile();
			System.out.println("Creo log " + file.getAbsolutePath());
		} else {
			System.out.println("Recupero log " + file.getAbsolutePath());
			String linea = "";
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ( (linea = reader.readLine()) != null) {
				cache.put(linea);
			}
			reader.close();
		}
		return cache;
	}

	public void closeLogger(){
		System.out.println("Close logger");
		if (pw!= null) {
			try{
				pw.flush();
				pw.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void log(String log) {
		if(pw==null){
			try {
				pw = new PrintWriter (new BufferedWriter( new FileWriter(file, true) ));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("loggo: " + log);
		pw.println(log);
	}

}
