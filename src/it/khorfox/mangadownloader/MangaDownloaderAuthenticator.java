package it.khorfox.mangadownloader;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class MangaDownloaderAuthenticator {
	public String authUser;
	public String authPassword;

	public MangaDownloaderAuthenticator(String authUser, String authPassword) {
		this.authUser = authUser;
		this.authPassword = authPassword;
	}

	protected void setProxy(){
		
		Authenticator.setDefault(
	               new Authenticator() {
	                  public PasswordAuthentication getPasswordAuthentication() {
	                     return new PasswordAuthentication(
	                           authUser, authPassword.toCharArray());
	                  }
	               }
	            );
		
		System.setProperty("http.proxyHost", "10.14.224.112");
		System.setProperty("http.proxyPort", "8080");
	}
	


}