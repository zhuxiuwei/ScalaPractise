package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * Get rid of duplicated wechat videos. How to judge duplicate:
 * If videos of the same day get the same file size, then consider them to be duplicate.
 * @author xiuzhu
 * 2015-10-3
 */
public class HandleDuplicate {

	public String path = "";
	private String NOTVIDEOPATH = "";
	private String DUPLICATEDPATH = "";
	private String LOGPATH = "";
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	//NOTICE: Just need to match to date. No need to specify time(hour, minute, second)
	
	//Constructor
	public HandleDuplicate(String filePath){
		//Create folder
		this.path = filePath;
		this.NOTVIDEOPATH = path + File.separator + "notvideo";
		this.DUPLICATEDPATH =  path + File.separator + "duplicated";
		this.LOGPATH = path + File.separator + "log";
		File notVideoFolder = new File(NOTVIDEOPATH);
		File dupeFolder = new File(DUPLICATEDPATH);
		File logFolder = new File(LOGPATH);
		if(!notVideoFolder.exists())
			notVideoFolder.mkdirs();
		if(!dupeFolder.exists())
			dupeFolder.mkdirs();
		if(!logFolder.exists())
			logFolder.mkdirs();
	}
	
	public void handle(){
		System.out.println("*** Reminder: Execute RenameByModifiedTime.java before execute this. ***");
		File sourceFolder = new File(path);
		if (sourceFolder.isDirectory()) {
			File files[] = sourceFolder.listFiles();
			Map<String, String> dupMap = new HashMap<String, String>();
			for (File file : files) {
				if(file.isDirectory())
					continue;
				
				String name = file.getName();
				String postfix = name.substring(name.lastIndexOf(".")); 
				
				//if not mp4, move to "notvideo" folder
				if(!postfix.equals(".mp4"))
					moveFile(file, NOTVIDEOPATH);
				
				//handle duplicate videos
				String day = this.getDayFromFileName(file.getName());
				if(day.equals(""))	//Failed to extract date from file, skip this file.
					continue;
				String key = day + ":" + file.length();	//map key: day + file size, e.g. 20151002:202863
				if(!dupMap.containsKey(key))
					dupMap.put(key, name);
				else{	//duplicate file, move to duplicated folder
					String v = dupMap.get(key);
					v = v + " | " + name;
					dupMap.put(key, v);
					moveFile(file, DUPLICATEDPATH);
				}
			}
			
			//log updates
			logDuplications(dupMap);
			
		}else{
			System.out.println(path + "不是合法的目录！");
		}
	}
	
	//Extract date from filename.
	private String getDayFromFileName(String Filename){
		Date date = null;
		try {
			date = sdf.parse(Filename);
		} catch (ParseException e) {
			System.out.println("Exception when convert date format: " + Filename);
			return "";
		}
		String year = (1900 + date.getYear()) + "";
		String month = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1 + "";
		String day = date.getDate() < 10 ? "0" + date.getDate(): date.getDate() + "";
		return year + month + day;
	}
	
	//Move file to another folder
	private boolean moveFile(File file, String targetFolder){
		System.out.println("Moving '" + file + "' to folder '" + targetFolder + "'");
		File dest = new File(targetFolder + File.separator + file.getName());  
		return file.renameTo(dest);
	}
	
	//Log contents in dupMap
	public void logDuplications(Map<String, String> dupMap){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String logName = this.LOGPATH + File.separator + sdf.format(new Date()) + ".log";
			FileWriter fw = new FileWriter(new File(logName));
			Set<String> keySet = dupMap.keySet();
			fw.write( "Date" + "\t\t\t" + "Size" + "\t\t" + "FileNames\r\n");  
			for(String key: keySet){
				String v = dupMap.get(key);
				int size = v.split(" | ").length;
				if(size > 1)
					fw.write( key.split(":")[0] + "\t\t" + key.split(":")[1] + "\t\t" + dupMap.get(key) + "\r\n");  
			}
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			System.out.println("Exception when logging duplicated files:" + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java HandleDuplicate <path>");
			return;
		}
		HandleDuplicate r = new HandleDuplicate(args[0]);
		r.handle();
	}

}
