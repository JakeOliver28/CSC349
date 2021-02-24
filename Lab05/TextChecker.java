/**
 * TextChecker Class
 *
 * @author Jacob Oliver
 * @version Lab 5
 */

import java.io.*;
import java.util.*;

public class TextChecker{

   // instance variables
   private static String text;
   private static int textLen;
   private static HashMap<String, Integer> dict;
   private static boolean[] isTextArr;

   public TextChecker(){
      text = "";
      textLen = 0;
      dict = new HashMap<String, Integer>();
      isTextArr = new boolean[textLen];
   }

   public static void main(String[] args) throws FileNotFoundException{
      if (args.length != 2){
         System.out.println("java TextChecker <Dictionary File> <Text File>");
         return;
      }
      System.out.println("");
      Scanner dictSc = new Scanner(new File(args[0]));
      Scanner textSc = new Scanner(new File(args[1]));
      TextChecker thisTC = new TextChecker();
      setDict(dictSc);
      analyzeText(textSc);
      dictSc.close();
      textSc.close();
   }

   private static void setDict(Scanner dictSc){
      while (dictSc.hasNextLine()){
         dict.put(dictSc.nextLine().trim(), 1);
      }
   }
   
   public static void analyzeText(Scanner textSc){
      while (textSc.hasNextLine()){
         setString(textSc.nextLine().trim());
         if (isText())
            split();
         else
            System.out.println(text + "\nNot a valid sequence of words\n");
      }
   }

   public boolean dict(int i, int j) throws 
   IndexOutOfBoundsException, IllegalArgumentException {
      if ((i < 0) || (j >= textLen))
         throw new IndexOutOfBoundsException();
      else if (i < j)
         throw new IllegalArgumentException();
      return dict.containsKey(text.substring(i, j+1));
   }

   public void setDictionary(HashMap<String, Integer> newDict){
      dict = newDict;
   }

   public static void setString(String s){
      text = s;
      textLen = s.length();
      isTextArr = new boolean[textLen];
   }

   public static boolean isText(){
      //boolean[] isTextArr = new boolean[textLen];
      boolean thisValue;
      for (int i = 0; i < textLen; i++)
         isTextArr[i] = checkSubstring(i);
      return isTextArr[textLen-1];
   }

   private static boolean checkSubstring(int i){
      if (dict.containsKey(text.substring(0, i+1)))
         return true;
      for (int j = i-1; j >= 0; j--){
         if (isTextArr[j] && dict.containsKey(text.substring(j+1, i+1)))
            return true;
      }
      return false;
   }

   public static void split(){
      System.out.println(text);
      findSplit(textLen - 1);
      System.out.println("\n");
   }
      
   private static void findSplit(int j){
      if (dict.containsKey(text.substring(0, j+1)))
         System.out.print(text.substring(0, j+1));
      else{
         int k;
         for (k = j-1; k >= 0; k--){
            if (isTextArr[k] && dict.containsKey(text.substring(k+1, j+1))){
               findSplit(k);
               break;
            }
         }
         System.out.print(" " + text.substring(k+1, j+1));
      }
      //strBuild.append(" " + text.substring(j+1, i+1);
   }

}
