/**
 * EDStudy Class
 *
 * @author Jacob Oliver
 * @version Lab 5
 */

import java.util.Random;


public class EDStudy {

   public static void main(String[] args){
      runPart(1);
      runPart(2);
   }

   public static void runPart(int part){
      long timeSum = 0;
      long editDistanceSum = 0;
      int numReps = 1000;
      if (part == 1)
         System.out.println("Results for Part 1: Finding edit distance between two identical strings\n");
      else if (part == 2){
         System.out.println("Results for Part 2: Finding edit distance between two different strings of the same size\n");
         numReps = 5000;
      }
      for (int i = 1; i <= 512; i = 2*i){
         for (int j = 0; j < numReps; j++){
            EditDistance ed = new EditDistance();
            String testString = generateString(i);
            String testString2;
            if (part == 1)
               testString2 = testString;
            else
               testString2 = generateString(i);
            long startTime = System.nanoTime();
            editDistanceSum += ed.findEditDistance(testString, testString2);
            long endTime = System.nanoTime();
            timeSum += (endTime - startTime);
         }
         double averageTime = timeSum/((double)numReps);
         double averageEditDistance = editDistanceSum/((double)numReps);
         System.out.println("For strings of size " + i + ":");
         System.out.format("Average runtime is %.3f microseconds\n", averageTime/1000.0);
         if (part == 2)
            System.out.format("Average edit distance is %.3f\n", averageEditDistance);
         System.out.print("\n");
         timeSum = 0;
         editDistanceSum = 0;
      }

      System.out.print("\n\n");

   }

   public static String generateString(int size){
      String alphabet = "ATCG";
      StringBuilder build = new StringBuilder();
      Random rand = new Random();
      
      while (build.length() < size){
         int index = (int)(rand.nextFloat() * alphabet.length());
         build.append(alphabet.charAt(index));
      }

      return build.toString();
   }

}
