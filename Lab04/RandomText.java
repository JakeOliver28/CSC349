/**
 * RandomText Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

import java.util.Random;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class RandomText {

   public static void main(String[] args) throws FileNotFoundException {
      PrintWriter outWriter = new PrintWriter("RandomText.txt");
      outWriter.write(generateFile(args));
      outWriter.close();
   }

   public static String generateFile(String[] args) throws FileNotFoundException {
      StringBuilder strBuild = new StringBuilder();
      int size = Integer.parseInt(args[0]);
      String latinAlphabet = "abcdefghijklmnopqrstuvwxyz";
      Random rand = new Random();
      for (int i=0; i<size; i++){
         int index = (int)((rand.nextDouble()) * (latinAlphabet.length()));
         strBuild.append(latinAlphabet.charAt(index));
      }
      return strBuild.toString();
   }

}
