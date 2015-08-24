package tools;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 批量修改图片名称，名称格式为yyyy-MM-dd-HH.mm.ss，增加或减少一定时间。
 * @author xiuzhu
 * 131228
 */
public class Renamer {
 
 private String getNewName(String oldname, int amount) throws ParseException{
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss"); 
  Date startDate = formatter.parse(oldname);
  Calendar c1 = Calendar.getInstance();
  c1.setTime(startDate);
  c1.add(Calendar.SECOND, amount);
  return formatter.format(c1.getTime()).toString();
 }
 
 public void rename(File sourceFolder, int amuount){
  if (sourceFolder.isDirectory()){
   String orginalNames[] = sourceFolder.list();
   for(String s : orginalNames){
    File f = new File(sourceFolder.getAbsolutePath() + "\\" + s);
    if (f.isFile()) { 
     String name = f.getName();
     String temp[] = name.split(".jpg");
     String nameNoExtend = temp[0];
     try {
      String newName = getNewName(nameNoExtend, amuount);
      f.renameTo(new File(sourceFolder.getAbsolutePath() + "\\" + newName + ".jpg"));
     } catch (ParseException e) {
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
  String path = "F:\\相册.吉大北邮年3-7月 大4下学期毕业旅行";
  //要调整的时间。单位为秒。
  int amount = -43200;
  File sourceFolder = new File(path);
  new Renamer().rename(sourceFolder, amount);
 }

}