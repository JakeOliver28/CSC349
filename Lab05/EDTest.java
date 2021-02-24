/**
 * EDTest Class
 *
 * @author Jacob Oliver
 * @version Lab 5
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EDTest {

   public static void main(String[] args) throws FileNotFoundException {
      String[] words = new String[2];
      if (args.length == 1)
         words = oneDance(args);
      else if (args.length == 2)
         words = itTakesTwo(args);
      EditDistance thisED = new EditDistance();
      System.out.println("Edit distance between " + words[0] + " & " + words[1] + ":");
      System.out.println(thisED.findEditDistance(words[0], words[1]));
      System.out.println("Proper alignment:");
      thisED.getAlignment();
      
   }

   private static String[] oneDance(String[] args) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(args[0]));
      String[] words = new String[2];
      words[0] = sc.nextLine().trim();
      words[1] = sc.nextLine().trim();
      return words;
   }

   private static String[] itTakesTwo(String[] args) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(args[0]));
      Scanner sc2 = new Scanner(new File(args[1]));
      String[] words = new String[2];
      words[0] = sc.nextLine().trim();
      words[1] = sc2.nextLine().trim();
      return words;
   }

}
