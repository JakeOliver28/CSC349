import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.lang.Integer;

public class Bacon{

   public static void main(String[] args) throws FileNotFoundException{
      System.out.println("Finding Kevin Bacon words...");
      baconMoves(buildGraphs.getUndirected());
   }
   
   @SuppressWarnings("unchecked")
   public static void baconMoves(HashMap<String, HashMap<String, Integer>> undirectedGraph){
     
      // Sort undirectedGraph by degree - descending
      Set<Entry<String, HashMap<String, Integer>>> entries = undirectedGraph.entrySet();

      Comparator<Entry<String, HashMap<String, Integer>>> valueComparator = new Comparator<Entry<String, HashMap<String, Integer>>>(){
         
         @Override
         public int compare(Entry<String, HashMap<String, Integer>> e1, Entry<String, HashMap<String, Integer>> e2){
            
            int val1 = e1.getValue().size();
            int val2 = e2.getValue().size();

            return val2 - val1;

         }
      };

      List<Entry<String, HashMap<String, Integer>>> listOfEntries = new ArrayList<Entry<String, HashMap<String, Integer>>>(entries);

      Collections.sort(listOfEntries, valueComparator);
      LinkedHashMap<String, HashMap<String, Integer>> sortedByValue = new LinkedHashMap<String, HashMap<String, Integer>>(listOfEntries.size());

      for (Entry<String, HashMap<String, Integer>> entry : listOfEntries){
         sortedByValue.put(entry.getKey(), entry.getValue());
      }
      //System.out.println("HashMap after sorting entries by values ");
      Set<Entry<String, HashMap<String, Integer>>> entrySetSortedByValue = sortedByValue.entrySet();

     // for (Entry<String, HashMap<String, Integer>> mapping : entrySetSortedByValue){
     //    System.out.println(mapping.getKey() + " ==> " + mapping.getValue().size());
     // }

      int numReported = 0;

      // report the average path length and histogram of path lengths for the top 5 words

      for (Entry<String, HashMap<String, Integer>> mapping : entrySetSortedByValue){
         
         Queue<Object[]> objQueue = new LinkedList<Object[]>();
         //Queue<Entry<String, HashMap<String, Integer>>> vertQueue = new LinkedList<Entry<String, HashMap<String, Integer>>>();
         HashMap<String, Integer> distanceHM = new HashMap<String, Integer>();
         Object[] objList = new Object[2];

        // vertQueue.add(mapping);
            
         objList[0] = mapping.getKey();
         objList[1] = mapping.getValue();
         objQueue.add(objList);
         distanceHM.put((String)objList[0], (Integer)0);

         while (null != objQueue.peek()){
      
            objList = objQueue.remove();
            //Entry<String, HashMap<String, Integer>> currentVertex = vertQueue.remove();
         
            // iterate through adjacency list
            //for (Entry<String, Integer> adjacentVert : currentVertex.getValue().entrySet()){

            for (Entry<String, Integer> adjacentVert : ((HashMap<String, Integer>)objList[1]).entrySet()){
               if (!(distanceHM.containsKey(adjacentVert.getKey()))){
                  //vertQueue.add(undirectedGraph.get(adjacentVert.getKey()));      
                  Object[] objEntry = new Object[2];
                  objEntry[0] = adjacentVert.getKey();
                  objEntry[1] = undirectedGraph.get(objEntry[0]);
                  objQueue.add(objEntry);
                  distanceHM.put((String)objEntry[0], (Integer)(distanceHM.get(objList[0]) +  adjacentVert.getValue()));
               }
            }
         }

         int sum = 0;
         int numVertices = 0;
         HashMap<Integer, Integer> histogramHM = new HashMap<Integer, Integer>();

         for (Entry<String, Integer> distanceEntry : distanceHM.entrySet()){
            int value = distanceEntry.getValue();
            if (histogramHM.containsKey(value))
               histogramHM.put(value, histogramHM.get(value) + 1);
            else
               histogramHM.put(value, 1);
            sum += value;
            numVertices++;
         }
         System.out.println("\n");
         System.out.println("Kevin Bacon candidate: " + mapping.getKey() + "\n");
         System.out.format("Average path length to connected words: %.2f\n\n", (double)sum/(numVertices-1));

         System.out.println("Histogram: \n");
         Map<Integer, Integer> sortedMap = new TreeMap<>(histogramHM);
         for (Entry<Integer, Integer> histValue : sortedMap.entrySet()){
            System.out.println(histValue.getKey() + ":   " + histValue.getValue());
         }

         System.out.print("\n");

         if (++numReported >= 5)
            break;
      }

   }


}
