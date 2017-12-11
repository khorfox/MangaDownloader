package it.khorfox.mangadownloader.formatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MangaCbzFormatter implements MangaFormatter {

	protected Hashtable<String, String> folders;

	public MangaCbzFormatter(Hashtable<String, String> folders) {
		this.folders = folders;
	}

	public MangaCbzFormatter(String mainFolder) {
		folders = new Hashtable<String, String>();
		listSubFolders(mainFolder);
	}

	protected int listSubFolders(String mainFolder) {
		File dir = new File(mainFolder);
		String[] directories = dir.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
		for(String el:directories){
			folders.put(el, mainFolder + File.separator + el);
		}
		return directories.length;
	}

	@Override
	public void format(String outDirectory, String inDirectory) throws Exception {
		//System.out.println("Hash : " + folders);
		byte[] buffer = new byte[1024];
		ZipOutputStream zos = null;
		for(String chapter: folders.keySet()){
			String localFolder = folders.get(chapter);
			File dir = new File(localFolder);
			File[] pages = dir.listFiles();
			System.out.println("Pagine: " + chapter + " - " + pages.length);
			if (pages == null || pages.length == 0) {
				System.out.println("Folder vuoto: " + folders.get(chapter));
			} else {
				zos = new ZipOutputStream(new FileOutputStream(outDirectory+File.separator+chapter+".cbz"));
				for (File file : pages) {
					FileInputStream in = null;
					ZipEntry ze = new ZipEntry(file.getName());
					zos.putNextEntry(ze);
					try	{
						in = new FileInputStream(file);
						int len;
						while ((len = in.read(buffer)) > 0) {
							zos.write(buffer, 0, len);
						}
					} catch (Exception e){
						System.out.println("Errore nella carezione del zip " + e.getMessage());
					}
					finally {
						if(in!=null){
							in.close();
						}
					}
				}
				zos.closeEntry();
				zos.close();
			}
		}
	}

}
