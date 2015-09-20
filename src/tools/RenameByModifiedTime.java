
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
/**
 * @author xiuzhu
 * 131228
 */
public class RenameByModifiedTime {
 public String renameType = "1";	//1 photo, 2 video
 private HashMap<String, Integer> existedNames = new HashMap<String, Integer>();	
 
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
      SimpleDateFormat sdf = null;
	  if(renameType.equals("2"))
		sdf = new SimpleDateFormat("yyyyMMdd_HHmm_");	//videos
	  if(renameType.equals("1"))
		sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");	//pictures
      String newName = sdf.format(modified);
      
      if(!existedNames.containsKey(newName))
    	  existedNames.put(newName, 1);
      else{
    	  int count = existedNames.get(newName);
    	  existedNames.put(newName, count + 1);
    	  newName = newName + "_" + count;
      }
      
      System.out.println(name + " -> " + newName);
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
  if(args.length == 0){
	  System.out.println("Input 1 or 2. 1: picture or Wechat video, 2: video");
	  return;
  }
  String path = "E:\\rename";
  File sourceFolder = new File(path);
  RenameByModifiedTime r = new RenameByModifiedTime();
  r.renameType = args[0];
  r.rename(sourceFolder);
 }

}
