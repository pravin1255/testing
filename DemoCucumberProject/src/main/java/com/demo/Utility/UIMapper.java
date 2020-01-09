package com.demo.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UIMapper {
	
	static FileInputStream stream;
	String RepositoryFile;
	static Properties prop=new Properties();
	
	public UIMapper(String fileName) throws IOException {
		this.RepositoryFile=fileName;
		stream=new FileInputStream(RepositoryFile);
		prop.load(stream);		
	}
	
	public static String getValue(String key) throws IOException
	{
		//prop.load(stream);
		System.out.println("Key is "+key);
		String value=prop.getProperty(key);
		System.out.println("Key ="+key+" value = "+value);
		return value;
	}
}