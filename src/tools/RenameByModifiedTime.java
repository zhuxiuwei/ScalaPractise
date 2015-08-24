package tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
/**
 * 批量修改文件名称为file modified time。
 * @author xiuzhu
 * 131228
 */
public class RenameByModifiedTime {
 
 private HashMap<String, Integer> existedNames = new HashMap<String, Integer>();	//保存已经生成过的文件名。防止重名。
 
 public void rename(File sourceFolder){
  if (sourceFolder.isDirectory()){
   String orginalNames[] = sourceFolder.list();
   for(String s : orginalNames){
    File f = new File(sourceFolder.getAbsolutePath() + "\\" + s);
    if (f.isFile()) { 
     String name = f.getName();
     String postfix = name.substring(name.lastIndexOf("."));
     try {
      Date modified = new Date(f.lastModified());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
      String newName = sdf.format(modified);
      
      //判断重复名字
      if(!existedNames.containsKey(newName))
    	  existedNames.put(newName, 1);
      else{
    	  int count = existedNames.get(newName);
    	  existedNames.put(newName, count + 1);
    	  newName = newName + "_" + count;
      }
      
      System.out.println(newName);
      f.renameTo(new File(sourceFolder.getAbsolutePath() + "\\" + newName + postfix));
     } catch (Exception e) {
      e.printStackTrace();
     }
     
    }
   }
  }
  
 }
 
 /**
  * @param args
  */
 public static void main(String[] args) {
  // path是待修改名字的照片所在的目录
  String path = "D:\\aaa";
  File sourceFolder = new File(path);
  new RenameByModifiedTime().rename(sourceFolder);
 }

}