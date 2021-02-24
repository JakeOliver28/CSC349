import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class CSV {
   public static void main(String[]args) throws FileNotFoundException{
      PrintWriter pw = new PrintWriter(new File("test.csv"));
      StringBuilder sb = new StringBuilder();

      int[] storedList = new int[10];
      for (int i = 0; i < 10; i++)
         storedList[i] = i;

      for (int i = 0; i < 10; i++){
         sb.append(storedList[i]);
         sb.append(',');
      } 
      sb.append('\n');

      pw.write(sb.toString());
      pw.close();
      System.out.println("done!");
   }
}
