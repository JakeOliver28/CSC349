/**
 * HuffmanValidate Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

import java.io.*;

public class HuffmanValidate {

   public static void main(String[] args) 
   throws FileNotFoundException, IOException{

      huffDatBoi(args[0]);

   }

   public static void huffDatBoi(String filename) 
   throws FileNotFoundException, IOException{

      HuffmanCode thisHuffer = new HuffmanCode(filename);
      CodeMap thisCoder = thisHuffer.getHuffmanCodeMap();

      System.out.println("CodeMap for " + filename + "\n");
      thisCoder.print();

      BufferedWriter writer = new BufferedWriter(new 
      FileWriter("HuffmanOutput.txt"));
     
      FileInputStream FIS = new FileInputStream(filename);
      BufferedInputStream BIS = new BufferedInputStream(FIS);

      int sumOfEncodedBits = 0;
      int sumOfOriginalBytes = 0;

      byte value = (byte)BIS.read();
      String writtenCode;

      while (value != -1){
         writtenCode = thisCoder.convertChar((char)value);         
         writer.write(writtenCode);
         sumOfEncodedBits += writtenCode.length();
         if (writtenCode.length() != 0)
            sumOfOriginalBytes++;
         value = (byte)BIS.read();
      }

      System.out.println("\nLength of original file: " + 8 * sumOfOriginalBytes + " bits");
      System.out.println("Length of encoded file: " + sumOfEncodedBits + " bits\n");
      System.out.format("Reduction ratio: %.4f  bits\n\n", 
      ((double)sumOfEncodedBits)/(8*sumOfOriginalBytes));
      

      writer.close();
   }
}
