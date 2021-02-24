import java.util.*;
import java.io.*;

public class Connected{

   public static void main(String args[]) throws FileNotFoundException{

      try{

         ArrayList<String> highest = new ArrayList<String>();
         int connectedComponents = 0;
         int numDegree = 1;
         int highestDegree = 0;
         int x = 0;
         Iterator iterator;

         Scanner file = new Scanner(new File(args[0]));
         
         String line = file.nextLine();
         
         while(file.hasNextLine()){

            line = file.nextLine().toString();

            if(line.length() == 0) 
               continue;

            if(line.contains(":")){
               System.out.println("Number of connected components: " + connectedComponents);
               System.out.println("Highest Degree: " + highestDegree);
               System.out.println("Number of verticies with that degree: " + numDegree);
               Collections.sort(highest);
               System.out.println("The verticies with the highest degrees: ");
               for(int i = 0; i < highest.size(); i++){
                  if(highest.get(i) == null)
                     break;
                  System.out.println(highest.get(i));
               }
               System.out.println();
               connectedComponents =  highestDegree = 0;
               numDegree = 1;
               System.out.println(line);
               continue;
            }

            x = pullInt(line);

            if(x == highestDegree){
               highest.add(line);
               numDegree++;
            }
            
            if(x > highestDegree){

              iterator = highest.iterator(); 

               while(iterator.hasNext()){
                  if(iterator.next() != null)
                     iterator.remove();
               }

               highest.add(line);
               numDegree = 1;
               highestDegree = x;
            }


            connectedComponents++;

         }

         System.out.println("Number of connected componenets: " + connectedComponents);
         System.out.println("Highest Degree: " + highestDegree);
         System.out.println("Number of verticies with that degree: " + numDegree);
         Collections.sort(highest);
         System.out.println("The verticies with the highest degrees: ");
         for(int i = 0; i < highest.size(); i++){
            if(highest.get(i) == null)
               break;
            System.out.println(highest.get(i));
         }
         System.out.println();

      }

      catch(ArrayIndexOutOfBoundsException e){

         System.out.println("Usage: java Connected <InCSVFile>");
      }

   }

   public static int pullInt(String s){

      StringBuilder sb = new StringBuilder();
      int index = 0;

      for(int i = 0; i < s.length(); i++){
         if(s.toCharArray()[i] == ','){
            System.out.println("index value = " + i);
            index = i + 1;
         }
      }

      for(int i = index; i < s.length(); i++)  
         sb.append((s.toCharArray())[i]);

      return Integer.parseInt(sb.toString());

   }
}

      
         
         
   
