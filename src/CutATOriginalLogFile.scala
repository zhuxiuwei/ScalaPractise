import java.util.Scanner
import java.io.File
import java.io.FileWriter

object CutATOriginalLogFile extends App{
  val sc = new Scanner(new File("""C:\Users\xiuzhu\Desktop\AuditTrial Doc\audittraildata\audittrail.txt"""))
  val lineCount = 10000
  val fw = new FileWriter(new File(s"C:\\Users\\xiuzhu\\Desktop\\AuditTrial Doc\\audittraildata\\audittrail${lineCount}.txt"))
  
  for( i <- 0 to lineCount){
    val s = sc.nextLine()
    fw.write(s)
    fw.write("\r\n")
  }
  fw.flush()
  fw.close()
}