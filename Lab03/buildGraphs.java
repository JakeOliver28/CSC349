import java.util.*;
import java.io.*;

public class buildGraphs{

   public static void main(String[] args) throws FileNotFoundException{
         openFile(args);
   }


   @SuppressWarnings("unchecked")
   public static HashMap<String, HashMap<String, Integer>> getDirected(){

      String[] args = new String[1];
      args[0] = "wordgame_20170721.csv";

      HashMap<String, HashMap<String, Integer>> undirectedGraph =
      new HashMap<String, HashMap<String, Integer>>();

      HashMap<String, HashMap<String, Integer>> directedGraph =
      new HashMap<String, HashMap<String, Integer>>();

      HashMap<Integer, Integer> inDegrees = new HashMap<Integer, Integer>();

      try{

         Scanner file = new Scanner(new File(args[0]));
         String lines;
         int value = 0;

         lines = file.nextLine();

         while(file.hasNextLine()){

            lines = file.nextLine();

            String[] line = lines.toString().split(",");

            if(use(line[1]) && use(line[2])){

               if(undirectedGraph.containsKey(line[1])){
                  if(undirectedGraph.get(line[1]).containsKey(line[2])){
                     value = undirectedGraph.get(line[1]).get(line[2]);
                     undirectedGraph.get(line[1]).put(line[1], ++value);
                  }

                  else{
                     undirectedGraph.get(line[1]).put(line[2], 1);
                  }
               }

               else{
                  HashMap<String, Integer> temp = new HashMap<String, Integer>();
                  temp.put(line[2], 1);
                  undirectedGraph.put(line[1], temp);
               }

               if(!(line[1].equals(line[2]))){

                  if(undirectedGraph.containsKey(line[2])){
                     if(undirectedGraph.get(line[2]).containsKey(line[1])){
                        value = undirectedGraph.get(line[2]).get(line[1]);
                        undirectedGraph.get(line[2]).put(line[1], ++value);
                     }

                     else{
                        undirectedGraph.get(line[2]).put(line[1], 2);
                     }
                  }

                  else{
                     HashMap<String, Integer> temp = new HashMap<String, Integer>();
                     temp.put(line[1], 2);
                     undirectedGraph.put(line[2], temp);
                  }
               }

               if(directedGraph.containsKey(line[1])){
                  if(directedGraph.get(line[1]).containsKey(line[2])){
                     value = directedGraph.get(line[1]).get(line[2]);
                     directedGraph.get(line[1]).put(line[1], ++value);
                  }

                  else{
                     directedGraph.get(line[1]).put(line[2], 1);
                  }
               }

               else{
                  HashMap<String, Integer> temp = new HashMap<String, Integer>();
                  temp.put(line[2], 1);
                  directedGraph.put(line[1], temp);
               }
            }
         }

         String sep = System.getProperty("line.separator");

         try(Writer writer = new FileWriter("undirectedGraph.csv")){
            for(Map.Entry<String, HashMap<String, Integer>> entry : undirectedGraph.entrySet()){

               writer.append(entry.getKey());
               writer.append(':');
               writer.append(sep);

               for(Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()){
                  writer.append("\t" + entry2.getKey());
                  writer.append(',');
                  writer.append(entry2.getValue().toString());
                  writer.append(sep);
               }
               writer.append(sep);

            }
         }

         catch(Exception ex){
            ex.printStackTrace(System.err);
         }

         int v = 0;

         try(Writer writer = new FileWriter("directedGraph.csv")){
            for(Map.Entry<String, HashMap<String, Integer>> entry : directedGraph.entrySet()){

               writer.append(entry.getKey());
               writer.append(':');
               writer.append(sep);

               for(Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()){

                  if(inDegrees.containsKey((entry2.getValue()))){
                     v = inDegrees.get(entry2.getValue());
                     inDegrees.put(entry2.getValue(), ++v);
                  }
                  else{
                     inDegrees.put(entry2.getValue(), 1);
                  }

                  writer.append("\t" + entry2.getKey());
                  writer.append(',');
                  writer.append(entry2.getValue().toString());
                  writer.append(sep);
               }
               writer.append(sep);

            }

            List keys = new ArrayList(inDegrees.keySet());

            Collections.sort(keys);

        //    System.out.println("Histogram of the in-degress of the directed graph");
        //    for(Object i : keys)
       //        System.out.println(i.toString() + ":" + inDegrees.get(i));

         }

         catch(Exception ex){
            ex.printStackTrace(System.err);
         }

      }

   catch(Exception e){
      System.out.println("Usage: java buildGraph <InCSVFile>");
   }
      return directedGraph;

   }

   @SuppressWarnings("unchecked")
   public static HashMap<String, HashMap<String, Integer>> getUndirected(){
      
      String[] args = new String[1];
      args[0] = "wordgame_20170721.csv";

      HashMap<String, HashMap<String, Integer>> undirectedGraph =
      new HashMap<String, HashMap<String, Integer>>();

      HashMap<String, HashMap<String, Integer>> directedGraph =
      new HashMap<String, HashMap<String, Integer>>();

      HashMap<Integer, Integer> inDegrees = new HashMap<Integer, Integer>();

      try{

         Scanner file = new Scanner(new File(args[0]));
         String lines;
         int value = 0;

         lines = file.nextLine();

         while(file.hasNextLine()){

            lines = file.nextLine();

            String[] line = lines.toString().split(",");

            if(use(line[1]) && use(line[2])){
      
               if(undirectedGraph.containsKey(line[1])){
                  if(undirectedGraph.get(line[1]).containsKey(line[2])){
                     value = undirectedGraph.get(line[1]).get(line[2]);
                     undirectedGraph.get(line[1]).put(line[1], ++value);
                  }

                  else{
                     undirectedGraph.get(line[1]).put(line[2], 1);
                  }
               }

               else{
                  HashMap<String, Integer> temp = new HashMap<String, Integer>();
                  temp.put(line[2], 1);
                  undirectedGraph.put(line[1], temp);
               }

               if(!(line[1].equals(line[2]))){

                  if(undirectedGraph.containsKey(line[2])){
                     if(undirectedGraph.get(line[2]).containsKey(line[1])){
                        value = undirectedGraph.get(line[2]).get(line[1]);
                        undirectedGraph.get(line[2]).put(line[1], ++value);
                     }

                     else{
                        undirectedGraph.get(line[2]).put(line[1], 2);
                     }
                  }

                  else{
                     HashMap<String, Integer> temp = new HashMap<String, Integer>();
                     temp.put(line[1], 2);
                     undirectedGraph.put(line[2], temp);
                  }
               }

               if(directedGraph.containsKey(line[1])){
                  if(directedGraph.get(line[1]).containsKey(line[2])){
                     value = directedGraph.get(line[1]).get(line[2]);
                     directedGraph.get(line[1]).put(line[1], ++value);
                  }

                  else{
                     directedGraph.get(line[1]).put(line[2], 1);
                  }
               }

               else{
                  HashMap<String, Integer> temp = new HashMap<String, Integer>();
                  temp.put(line[2], 1);
                  directedGraph.put(line[1], temp);
               }
            }
         }

         String sep = System.getProperty("line.separator");

         try(Writer writer = new FileWriter("undirectedGraph.csv")){
            for(Map.Entry<String, HashMap<String, Integer>> entry : undirectedGraph.entrySet()){

               writer.append(entry.getKey());
               writer.append(':');
               writer.append(sep);

               for(Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()){
                  writer.append("\t" + entry2.getKey());
                  writer.append(',');
                  writer.append(entry2.getValue().toString());
                  writer.append(sep);
               }
               writer.append(sep);

            }
         }

         catch(Exception ex){
            ex.printStackTrace(System.err);
         }

         int v = 0;

         try(Writer writer = new FileWriter("directedGraph.csv")){
            for(Map.Entry<String, HashMap<String, Integer>> entry : directedGraph.entrySet()){

               writer.append(entry.getKey());
               writer.append(':');
               writer.append(sep);

               for(Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()){

                  if(inDegrees.containsKey((entry2.getValue()))){
                     v = inDegrees.get(entry2.getValue());
                     inDegrees.put(entry2.getValue(), ++v);
                  }
                  else{
                     inDegrees.put(entry2.getValue(), 1);
                  }

                  writer.append("\t" + entry2.getKey());
                  writer.append(',');
                  writer.append(entry2.getValue().toString());
                  writer.append(sep);
               }
               writer.append(sep);

            }

            List keys = new ArrayList(inDegrees.keySet());

            Collections.sort(keys);

         //   System.out.println("Histogram of the in-degress of the directed graph");
         //   for(Object i : keys)
         //      System.out.println(i.toString() + ":" + inDegrees.get(i));

         }

         catch(Exception ex){
            ex.printStackTrace(System.err);
         }

      }

   catch(Exception e){
      System.out.println("Usage: java buildGraph <InCSVFile>");
   }
      return undirectedGraph;

   }

   @SuppressWarnings("unchecked")
   public static void openFile(String[] args) throws FileNotFoundException{
   
      HashMap<String, HashMap<String, Integer>> undirectedGraph = 
      new HashMap<String, HashMap<String, Integer>>();

      HashMap<String, HashMap<String, Integer>> directedGraph = 
      new HashMap<String, HashMap<String, Integer>>();

      //For Part 3
      HashMap<Integer, Integer> inDegrees = new HashMap<Integer, Integer>();

      try{

         Scanner file = new Scanner(new File(args[0]));
         String lines;
         int value = 0;

         //Skipping the first line
         lines = file.nextLine();

         while(file.hasNextLine()){

            lines = file.nextLine();

            String[] line = lines.toString().split(",");

            if(use(line[1]) && use(line[2])){


               //For undirected Graphs
               if(undirectedGraph.containsKey(line[1])){
                  if(undirectedGraph.get(line[1]).containsKey(line[2])){
                     value = undirectedGraph.get(line[1]).get(line[2]);
                     undirectedGraph.get(line[1]).put(line[1], ++value);
                  }

                  else{
                     undirectedGraph.get(line[1]).put(line[2], 1);
                  }
               }

               else{
                  HashMap<String, Integer> temp = new HashMap<String, Integer>();
                  temp.put(line[2], 1);
                  undirectedGraph.put(line[1], temp);
               }

               if(!(line[1].equals(line[2]))){
                  
                  if(undirectedGraph.containsKey(line[2])){
                     if(undirectedGraph.get(line[2]).containsKey(line[1])){
                        value = undirectedGraph.get(line[2]).get(line[1]);
                        undirectedGraph.get(line[2]).put(line[1], ++value);
                     }

                     else{
                        undirectedGraph.get(line[2]).put(line[1], 2);
                     }
                  }

                  else{
                     HashMap<String, Integer> temp = new HashMap<String, Integer>();
                     temp.put(line[1], 2);
                     undirectedGraph.put(line[2], temp);
                  }
               }

               //For directed Graphs
               if(directedGraph.containsKey(line[1])){
                  if(directedGraph.get(line[1]).containsKey(line[2])){
                     value = directedGraph.get(line[1]).get(line[2]);
                     directedGraph.get(line[1]).put(line[1], ++value);
                  }

                  else{
                     directedGraph.get(line[1]).put(line[2], 1);
                  }
               }

               else{
                  HashMap<String, Integer> temp = new HashMap<String, Integer>();
                  temp.put(line[2], 1);
                  directedGraph.put(line[1], temp);
               }
            }
         }

         String sep = System.getProperty("line.separator");

         try(Writer writer = new FileWriter("undirectedGraph.csv")){
            for(Map.Entry<String, HashMap<String, Integer>> entry : undirectedGraph.entrySet()){

               writer.append(entry.getKey());
               writer.append(':');
               writer.append(sep);

               for(Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()){
                  writer.append("\t" + entry2.getKey());
                  writer.append(',');
                  writer.append(entry2.getValue().toString());
                  writer.append(sep);
               }
               writer.append(sep);
            
            }
         }

         catch(Exception ex){
            ex.printStackTrace(System.err);  
         }

         int v = 0;

         try(Writer writer = new FileWriter("directedGraph.csv")){
            for(Map.Entry<String, HashMap<String, Integer>> entry : directedGraph.entrySet()){

               writer.append(entry.getKey());
               writer.append(':');
               writer.append(sep);

               for(Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()){

                  //In degrees histogram
                  if(inDegrees.containsKey((entry2.getValue()))){
                     v = inDegrees.get(entry2.getValue());
                     inDegrees.put(entry2.getValue(), ++v);
                  }
                  else{
                     inDegrees.put(entry2.getValue(), 1);
                  }

                  writer.append("\t" + entry2.getKey());
                  writer.append(',');
                  writer.append(entry2.getValue().toString());
                  writer.append(sep);
               }
               writer.append(sep);
            
            }

            List keys = new ArrayList(inDegrees.keySet());
         
            Collections.sort(keys);

            System.out.println("Histogram of the in-degress of the directed graph");
            for(Object i : keys)
               System.out.println(i.toString() + ":" + inDegrees.get(i));
            
         }

         catch(Exception ex){
            ex.printStackTrace(System.err);  
         }
                  
      }

   catch(Exception e){
      System.out.println("Usage: java buildGraph <InCSVFile>");
   }

   //Directed Graph stuff

   HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

   int value = 0;
   int x = 0;
   int out = 0;
   
   Scanner file = new Scanner(new File("directedGraph.csv"));
   
   String line = file.nextLine();
   
   while(file.hasNextLine()){

      line = file.nextLine().toString();

      if(line.length() == 0) 
         continue;

      if(line.contains(":")){

         if(map.containsKey(out)){
            value = map.get(out);
            map.put(out, ++value);
         }

         else{
            map.put(out, 1);
         }

         out = 0;

         continue;
      }

      x = pullInt(line);
      out = out + x;

   }
   
   List keys = new ArrayList(map.keySet());
   Collections.sort(keys);

   System.out.println();
   System.out.println("Histogram of out-degrees of the directed graph:");
   for(Object i : keys)
      System.out.println(i.toString() + ":" + map.get(i));

   //undirected graph

   map = new HashMap<Integer, Integer>();

   value = 0;
   x = 0;
   
   file = new Scanner(new File("undirectedGraph.csv"));
   
   line = file.nextLine();
   
   while(file.hasNextLine()){

      line = file.nextLine().toString();

      if(line.length() == 0) 
         continue;

      if(line.contains(":"))
         continue;

      x = pullInt(line);

      if(map.containsKey(x)){
         value = map.get(x);
         map.put(x, ++value);
      }

      else{
         map.put(x, 1);
      }

   }
   
   keys = new ArrayList(map.keySet());
   Collections.sort(keys);

   System.out.println();
   System.out.println("Histogram of the undirected graph:");
   for(Object i : keys)
      System.out.println(i.toString() + ":" + map.get(i));

   }

   // If there is more than one space, I am discarding the information
   public static boolean use(String s){
      int spaces = 0;
      
      for(char c : s.toCharArray()){
         if(c == ' ')
            spaces++;
      }

      return !(spaces > 1);

   }

   public static int pullInt(String s){

      StringBuilder sb = new StringBuilder();
      int index = 0;

      for(int i = 0; i < s.length(); i++){
         if(s.toCharArray()[i] == ','){
            index = i + 1;
         }
      }

      for(int i = index; i < s.length(); i++)  
         sb.append((s.toCharArray())[i]);

      return Integer.parseInt(sb.toString());
   }
}
