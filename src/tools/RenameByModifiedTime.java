package tools;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author xiuzhu 131228
 */
public class RenameByModifiedTime {
	private String renameType = "1"; // 1 photo or Wechat video, 2 big video
	private String path = "";	//file source folder path
	private HashMap<String, Integer> existedNames = new HashMap<String, Integer>();

	//constructor specify path and file type
	public RenameByModifiedTime(String filePath, String fileType){
		this.path = filePath;
		this.renameType = fileType;
	}
	
	//rename method
	public void rename() {
		File sourceFolder = new File(path);
		if (sourceFolder.isDirectory()) {
			String orginalNames[] = sourceFolder.list();
			for (String s : orginalNames) {
				File f = new File(sourceFolder.getAbsolutePath() + "\\" + s);
				if (f.isFile()) {
					String name = f.getName();
					String postfix = name.substring(name.lastIndexOf("."));
					try {
						Date modified = new Date(f.lastModified());
						SimpleDateFormat sdf = null;
						if (renameType.equals("2"))
							sdf = new SimpleDateFormat("yyyyMMdd_HHmm_"); // videos
						if (renameType.equals("1"))
							sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss"); // pictures
						String newName = sdf.format(modified);
						if (!existedNames.containsKey(newName))
							existedNames.put(newName, 1);
						else {
							int count = existedNames.get(newName);
							existedNames.put(newName, count + 1);
							newName = newName + "_" + count;
						}
						System.out.println(name + " -> " + newName + postfix);
						f.renameTo(new File(sourceFolder.getAbsolutePath() + "\\" + newName + postfix));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			System.out.println(sourceFolder + " is not a legal directory!");
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Usage: java RenameByModifiedTime <path> <FileType>。 FileType is 1 or 2, 1: picture or Wechat video, 2: video");
			return;
		}
		RenameByModifiedTime r = new RenameByModifiedTime(args[0], args[1]);
		r.rename();
	}
}
