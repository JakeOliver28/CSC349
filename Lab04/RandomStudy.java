/**
 * RandomStudy Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

import java.io.*;

public class RandomStudy {

   public static void main(String[] args) throws FileNotFoundException, IOException {
      String[] docSizeArr = {"128", "256", "512", "1024", 
      "2048", "3000", "4096", "5000", "7000", "8192"};

      for (String size : docSizeArr)
         runRandomStudy(size);      
   }

   public static void runRandomStudy(String size) throws FileNotFoundException, IOException {
      String[] commLine = {size};
      String randFile;
      int totalBitsOfFiles = 0;
      int totalBitsOfCompressedFiles = 0;
      
      for (int i = 0; i < 32; i++){
         RandomText.main(commLine);
         randFile = RandomText.generateFile(commLine);
         HuffmanCode thisHuffer = new HuffmanCode("RandomText.txt");

         CodeMap thisCoder = thisHuffer.getHuffmanCodeMap();

         FileInputStream FIS = new FileInputStream("RandomText.txt");
         BufferedInputStream BIS = new BufferedInputStream(FIS);

         int sumOfEncodedBits = 0;
         int sumOfOriginalBytes = 0;

         byte value = (byte)BIS.read();
         String writtenCode;

         while (value != -1){
            writtenCode = thisCoder.convertChar((char)value);
            sumOfEncodedBits += writtenCode.length();
            if (writtenCode.length() != 0)
               sumOfOriginalBytes++;
            value = (byte)BIS.read();
         }
   
         totalBitsOfFiles += (8 * sumOfOriginalBytes);
         totalBitsOfCompressedFiles += sumOfEncodedBits;

      }

      double averageBitsOfFiles = ((double)totalBitsOfFiles)/32;
      double averageBitsOfCompressedFiles = ((double)totalBitsOfCompressedFiles)/32;
      int adHocLength = 5 * Integer.parseInt(size);

      System.out.println("\nLength of original files: " + averageBitsOfFiles/8 + " characters; " + averageBitsOfFiles + " bits");
      System.out.println("Average length of Huffman code encoding: " + averageBitsOfCompressedFiles/8 + " bytes");
      System.out.format("Average Huffman savings ratio: %.4f  bits\n",
      (averageBitsOfCompressedFiles)/(averageBitsOfFiles));
      System.out.println("Length of ad hoc code encoding: " + adHocLength/8 + " bytes");
      System.out.format("Ad hoc savings ratio: %.4f  bits\n",
      (adHocLength)/(averageBitsOfFiles));

   }

}
