/**
 * ClassicsStudy Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

import java.io.*;

public class ClassicsStudy {

   public static void main(String[] args) throws FileNotFoundException, IOException {

      String[] classics = {"alice.txt", "grimm.txt", "gulliver.txt", 
      "holmes.txt", "pride.txt", "tomsawyer.txt", "ulysses.txt"};

      for (String classic : classics)
         encodeClassic(classic);

   }

   public static void encodeClassic(String filename) throws FileNotFoundException, IOException {

      HuffmanCode thisHuffer = new HuffmanCode(filename);
      CodeMap thisCoder = thisHuffer.getHuffmanCodeMap();   
   
      int[] sizes = {2000, 4000, 8000, 16000, 32000, 50000, 70000, 90000, 
      110000, 130000, 150000, 170000, 190000, 210000, 230000, 250000, 
      270000};

      System.out.println("-----------------------------------------------\n\n\n");
      System.out.println("Results for: " + filename);
   
      for (int sizeOfFragment : sizes){
         if (encodeInitialFragments(filename, thisCoder, sizeOfFragment))
            return;  
         else
            System.out.println("---------------");
      }

   }

   public static boolean encodeInitialFragments(String filename, CodeMap thisCoder, 
   int sizeOfFragment) throws FileNotFoundException, IOException {

      FileInputStream FIS = new FileInputStream(filename);
      BufferedInputStream BIS = new BufferedInputStream(FIS);

      int sumOfEncodedBits = 0;
      int sumOfOriginalBytes = 0;

      byte value = (byte)BIS.read();
      String writtenCode;

      while ((value != -1) && (sumOfOriginalBytes < sizeOfFragment)){
         writtenCode = thisCoder.convertChar((char)value);
         sumOfEncodedBits += writtenCode.length();
         if (writtenCode.length() != 0)
            sumOfOriginalBytes++;
         value = (byte)BIS.read();
      }

      System.out.println("\nLength of fragment: " + 8 * sumOfOriginalBytes + 
      " bits; " + sumOfOriginalBytes + " bytes");
      System.out.println("Length of encoded file: " + sumOfEncodedBits + " bits; " + sumOfEncodedBits/8 + " bytes\n");
      System.out.format("Reduction ratio: %.4f  bits\n\n",
      ((double)sumOfEncodedBits)/(8*sumOfOriginalBytes));

      // if we have reached the end of file
      if (value == -1)
         return true;
      // if we have not reach the end of file
      else
         return false;

   }

}
