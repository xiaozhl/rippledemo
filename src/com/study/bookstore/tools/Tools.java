package com.study.bookstore.tools;

import java.util.UUID;

public class Tools {
	
	public static String getRandFileName()
	{
		String rnd = UUID.randomUUID().toString();
		
		String r  = rnd.replaceAll("-","");
		return r;
	}
}
