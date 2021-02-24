/**
 * AdHocValidate Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

import java.io.*;

public class AdHocValidate {
   
   public static void main(String[] args) throws FileNotFoundException, IOException {
      validateDatHoc(args[0]);
   }

   public static void validateDatHoc(String filename) 
   throws FileNotFoundException, IOException {
      AdHocCode AHCM = new AdHocCode();
         
      CodeMap thisCoder = AHCM.getAdHocCodeMap();

      System.out.println("AdHocCodeMap for " + filename + "\n");
      thisCoder.print();

      BufferedWriter writer = new BufferedWriter(new FileWriter("AdHocOutput.txt"));

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
