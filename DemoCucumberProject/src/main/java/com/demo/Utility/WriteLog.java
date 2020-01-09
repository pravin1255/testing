package com.demo.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
public class WriteLog {
	
	public static int step_counter=0;
	public static Date dateobj1;
	static String FileName="";
	static String SubFolder="";
	
	public static String createReportingFolder()
	{		
		FileName=new Date().toString().substring(0,10);
		FileName=FileName+StringUtils.right(new Date().toString(),4);
		
		SubFolder=new Date().toString().substring(0,19);
		SubFolder=SubFolder.replace(":", "_");
		
		System.out.println("SUB_FOLDER "+SubFolder);
		
		File file=new File("target//OUTPUT_HTML//"+FileName);
		
		Global.FOLDER_PATH="target//OUTPUT_HTML//"+FileName+"//"+SubFolder;
		
		if(!file.exists())
		{
			if(file.mkdirs())
			{
				System.out.println("Directory created successfully");
			}
			else
			{
				System.out.println("Error creating directory");
			}
		}
		else
			System.out.println("File is already present");
		
		Global.SCR_FOLDER_PATH="..//"+SubFolder+"//";
		
		File scrFile=new File("target//OUTPUT_HTML//"+FileName+"//"+SubFolder);
		
		if(!scrFile.exists())
		{
			if(scrFile.mkdir())
			{
				System.out.println("Sccreenshot folder created successfully");
				
				File sourceLocation=new File(System.getProperty("user.dir")+"//target//cucumber-reports");
				System.out.println("Source location "+sourceLocation.toString());
				File targetLocation = new File("target//OUTPUT_HTML//"+FileName+"//"+SubFolder);
				
				System.out.println("Target location "+targetLocation.toString());
				try {
					FileUtils.copyDirectory(sourceLocation, targetLocation);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("error in creating screenshot folder");
			}
		}
		else
			System.out.println("Screenshot folder already created");
		
		return FileName+"//"+SubFolder;
	}
	
	public static void copyScreenshot() {

		File sourceLocation = new File(System.getProperty("user.dir") + "//target//cucumber-reports");
		File targetLocation = new File("target//OUTPUT_HTML//" + FileName + "//" + SubFolder);
		try {
			FileUtils.copyDirectory(sourceLocation, targetLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}