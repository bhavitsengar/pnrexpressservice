/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.util;

public class Helper {
	
	public static String apikey = "ldchv8253";

	public static void setProxy(){
		
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost","148.87.19.20");
		System.setProperty("http.proxyPort","80");
		System.setProperty("https.proxySet", "true");
		System.setProperty("https.proxyHost","148.87.19.20");
		System.setProperty("https.proxyPort","80");
	}
	
}
