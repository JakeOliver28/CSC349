import java.io.*;
import java.util.*;

public class testDriver{

   public static void main (String[] args) throws FileNotFoundException{
   
      System.out.println("========= Lab2 TestDriver =============");

      // if there is an input file
      if(args.length != 0){
         inputFile(args);   
         return;
      }

   	long timeStamp = System.currentTimeMillis();
      timeStamp = timeStamp % 100000;

      PrintWriter pw = new PrintWriter(new File("test" + timeStamp + ".csv"));

      Random ran = new Random();
      int size = ran.nextInt(150);
      System.out.println("Generating random array of size " + size);
      int[] array = new int[size];
   
      for(int i = 0; i < size; i++)
         array[i] = ran.nextInt();

      Scanner scanner = new Scanner(System.in);


      
      while(true){
         
         printWelcomeMessage();
         int option = scanner.nextInt();
            
         switch(option){
               
            case 1: 
               System.out.println("Which method would you like to use?");
               System.out.println("(1) Sort based");
               System.out.println("(2) Randomized median");
               System.out.println("(3) Fast median k3");
               System.out.println("(4) Fast median k5");
               System.out.println("(5) Fast median k7");
         
               int algo = scanner.nextInt();
               
               runAlgorithm(algo, array, pw);
               pw.write("\n");
               break;
               

            case 2: 
               Scanner input = new Scanner(System.in);
               System.out.println("What array sizes would you like to use? (ex. 2 34 100)");
               String in = input.nextLine();

               Scanner line = new Scanner(in).useDelimiter(" ");
               
               System.out.println("How many times will you want to run each algorithm?");
               Scanner sc = new Scanner(System.in);

               int rep = sc.nextInt();
               int store = rep;
         
               while(line.hasNext()){
                    
                  int s = line.nextInt(); 
                     
                  System.out.println("\n\nArray Size: " + s + "\n");
                     
                  array = new int[s];

                  for(int i = 0; i < s; i++)
                     array[i] = ran.nextInt();

                  for(int i = 1; i < 6; i++){
                     double sum = 0;
                     double stDev = 0;
                     double[] values = new double[store];
                     while(rep-- > 0)
                        sum += (values[rep] = runAlgorithm(i, array, pw));
                     pw.write("\n");
                     stDev = calcStDev(store, values, sum);
                     System.out.println("Average Speed: " + sum/((double)store) + "\n");
                     System.out.format("Standard Deviation: %.3f\n\n", stDev);
                     rep = store;
                  }
               }
               break;
      
            case 3:
               size = ran.nextInt(150);
               System.out.println("Generating random array of size " + size);
               array = new int[size];
               for(int i = 0; i < size; i++)
                  array[i] = ran.nextInt();
               break;
   
            case 4:
               pw.close();
               return;
         }
      }
   }

   public static void printWelcomeMessage(){
      System.out.println("");
      System.out.println("What would you like to do with the array?");
      System.out.println("(1) Get the median of an array using a single algorithm");
      System.out.println("(2) Runtime experiment");
      System.out.println("(3) Generate another array");
      System.out.println("(4) Exit");
   }

   public static double calcStDev(int n, double[] values, double sum){
      double mean = sum/((double)n);
      double variance = 0;
      if (n == 1)
         return 0;
      for (int i = 0; i < n; i++)
         variance += Math.pow(mean - values[i], 2);
      return Math.pow(variance/(n-1), 0.5);
   }        
      
   public static double runAlgorithm(int algo, int[] array, PrintWriter pw){
      long start = 0;
      long stop = 0;

      int[] arrayCase1 = new int[array.length];
      int[] arrayCase2 = new int[array.length];   
      int[] arrayCase3 = new int[array.length];
      int[] arrayCase4 = new int[array.length];
      int[] arrayCase5 = new int[array.length];
      StringBuilder sb = new StringBuilder();


      switch(algo){

         case 1:
            start = System.nanoTime()/1000;
            lab2.sortBased(arrayCase1);
            stop = System.nanoTime()/1000;
            System.out.println("The sortBased algorithm took " + (stop - start)
            + " microseconds\n");
            sb.append(array.length + ", sortBased, " + (stop - start) + " microseconds\n");
            pw.write(sb.toString());
            break;

         case 2:
            start = System.nanoTime()/1000;
            lab2.randomMedian(arrayCase2);
            stop = System.nanoTime()/1000;
            System.out.println("The randomMed algorithm took " + (stop - start)
            + " microseconds\n");
            sb.append(array.length + ", randomMed, " + (stop - start) + " microseconds\n");
            pw.write(sb.toString());
            break;

         case 3:
            start = System.nanoTime()/1000;
            lab2.fastMedianK3(arrayCase3);
            stop = System.nanoTime()/1000;
            System.out.println("The fastMedianK3 algorithm took " + (stop - start)
            + " microseconds\n");
            sb.append(array.length + ", fastMedianK3, " + (stop - start) + " microseconds\n");
            pw.write(sb.toString());
            break;
         
         case 4:
            start = System.nanoTime()/1000;
            lab2.fastMedianK5(arrayCase4);
            stop = System.nanoTime()/1000;
            System.out.println("The fastMedianK5 algorithm took " + (stop - start)
            + " microseconds\n");
            sb.append(array.length + ", fastMedianK5, " + (stop - start) + " microseconds\n");
            pw.write(sb.toString());
            break;
             
         case 5: 
            start = System.nanoTime()/1000;
            lab2.fastMedianK7(arrayCase5);
            stop = System.nanoTime()/1000;
            System.out.println("The fastMedianK7 algorithm took " + (stop - start)
            + " microseconds\n");
            sb.append(array.length + ", fastMedianK7, " + (stop - start) + " microseconds\n");
            pw.write(sb.toString());
            break;
      }
      return stop - start;
   }

   public static void inputFile(String[] args) throws FileNotFoundException{

      Scanner file = new Scanner(new File(args[0]));

      String firstLine = file.nextLine();
      
      int r = Integer.parseInt(file.nextLine());

      int saved = r;

      Scanner ln = new Scanner(firstLine).useDelimiter(" ");

   	long timeStamp = System.currentTimeMillis();
      timeStamp = timeStamp % 100000;
      PrintWriter pw = new PrintWriter(new File("test" + timeStamp + ".csv"));

      Random ran = new Random();

      while(ln.hasNext()){

         int s = ln.nextInt(); 
                     
         System.out.println("\n\nArray Size: " + s + "\n");
            
         int[] array = new int[s];

         for(int i = 0; i < s; i++)
            array[i] = ran.nextInt();

         for(int i = 1; i < 6; i++){
            double sum = 0;
            double stDev = 0;
            double[] values = new double[saved];
            while(r-- > 0)
               sum += (values[r] = runAlgorithm(i, array, pw));
            pw.write("\n");
            stDev = calcStDev(saved, values, sum);
            System.out.println("Average Speed: " + sum/((double)saved) + "\n");
            System.out.format("Standard Deviation: %.3f\n\n", stDev);
            r = saved;
         }
      }
         
      pw.close();
      file.close();

   }
}
