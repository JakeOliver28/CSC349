import java.util.*;
import java.util.Map.Entry;
import java.io.*;


public class WordChains{

   private static DiGraph graph;

   public static void main(String[] args) throws FileNotFoundException{

      Scanner file = new Scanner(new File(args[1])); 
      String lines;
      Scanner commLineScanner = new Scanner(System.in);
      int value = 0;

      System.out.println("Is your graph (0)undirected or (1)directed?");
      value = commLineScanner.nextInt();
      //graph = new DiGraph(buildGraphs.getUndirected());
      
      //graph.setStart("love");
      //System.out.println(graph.getPathTo("water"));
      //System.out.println(graph.getPathTo("games"));
      //System.out.println(graph.getPathTo("justin bieber"));

      while(file.hasNextLine()){
      
         lines = file.nextLine().trim();

         String[] line = lines.toString().split(",");
         line[1] = line[1].trim();

         if (use(line[0]) && use(line[1])){
            System.out.println("Finding path from '" + line[0] + "', to '" + line[1] + "'");
            if (value == 0)
               graph = new DiGraph(buildGraphs.getUndirected());
            else
               graph = new DiGraph(buildGraphs.getDirected());
            graph.setStart(line[0]);
            System.out.println(graph.getPathTo(line[1]));  
         }

      }
   }

   // notice the bad syntax with things not indented as much as proper
   private static class DiGraph {

   private TreeMap<String, Vertex> vertices = new TreeMap<String, Vertex>();

   private static class Edge {
      private Vertex dest;
      private double weight;

      public Edge(Vertex dest, double weight) {
         this.dest = dest;
         this.weight = weight;
      }
   }

   private static class Vertex implements Comparable<Vertex> {

      private String name;
      private TreeMap<String, Edge> edges = new TreeMap<String, Edge>();
      private boolean known;
      private double distance = Double.POSITIVE_INFINITY;
      private Vertex prior;

      public Vertex(String name) {
         this.name = name;
      }

      public void addEdge(Vertex adj, double weight) {
         // TreeMap put() returns null when no matching element in tree 
         if (edges.put(adj.name, new Edge(adj, weight)) != null) {
            throw new IllegalArgumentException("Duplicate edge");
         }
      }

      // Used by priority queue used in Dikstra's shortest path algorithm.
      public int compareTo(Vertex that) {
         return ((Double)this.distance).compareTo(that.distance);
      }
      
      public String toString() {
         return this.name;
      }

      // For debugging purposes if and as necessary...
      public String debugToString() {
         StringBuilder sb = new StringBuilder()
         .append("Vertex name: ").append(name)
         .append("\nKnown: ").append(known)
         .append("\nPrior: ").append(prior)
         .append("\nDistance: ").append(distance)
         .append("\nAdjacencies:");
         
         for (String name : edges.keySet()) {
            sb.append(' ').append(name);
         }
         
         return sb.toString();
      }   

   }

   public DiGraph(HashMap<String, HashMap<String, Integer>> newHash) 
      throws FileNotFoundException {

      // notice the bad syntax with no indent for the second for loop #laziness
      for (Entry<String, HashMap<String, Integer>> outEntry : newHash.entrySet()) {
      for (Entry<String, Integer> inEntry : outEntry.getValue().entrySet()){
         try{
            String fromStr = outEntry.getKey();
            String toStr = inEntry.getKey();
            Double doub = inEntry.getValue().doubleValue();
            if (doub < 0){
               throw new IllegalArgumentException();
            }
            if (vertices.containsKey(fromStr) && vertices.containsKey(toStr)){
               vertices.get(fromStr).edges.put(toStr, new Edge(vertices.get(toStr), doub));
            }
            else if (vertices.containsKey(fromStr)){
               vertices.put(toStr, new Vertex(toStr));
               vertices.get(fromStr).edges.put(toStr, new Edge(vertices.get(toStr), doub));
            }
            else if (vertices.containsKey(toStr)){
               vertices.put(fromStr, new Vertex(fromStr));
               vertices.get(fromStr).edges.put(toStr, new Edge(vertices.get(toStr), doub));
            }
            else{
               vertices.put(toStr, new Vertex(toStr));
               vertices.put(fromStr, new Vertex(fromStr));
               vertices.get(fromStr).edges.put(toStr, new Edge(vertices.get(toStr), doub));

            }
         }
         catch (NoSuchElementException e){
            throw new IllegalArgumentException();
         }
      }
      } // end second for loop
   }

   public void setStart(String from) {
      if (!vertices.containsKey(from)){
         throw new IllegalArgumentException();
      }
      else{
         vertices.get(from).distance = 0;
         vertices.get(from).known = true;
         vertices.get(from).prior = null;
         PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
         pq.add(vertices.get(from));
         while (pq.size() > 0){
            Vertex pollVert = pq.poll();
            vertices.get(pollVert.name).known = true;
            Collection<Edge> entries = pollVert.edges.values();
            for (Edge thisEdge : entries ){
               if (vertices.get(thisEdge.dest.name).distance > (pollVert.distance + thisEdge.weight)){
                  pq.remove(thisEdge.dest);
                  vertices.get(thisEdge.dest.name).distance = (pollVert.distance + thisEdge.weight);
                  vertices.get(thisEdge.dest.name).prior = vertices.get(pollVert.name);
                  pq.add(vertices.get(thisEdge.dest.name));
               }
            }
         }
      }

   }

   public String getPathTo(String to) {
      int[] numSteps = new int[1];
      numSteps[0] = 0;
      StringBuilder path = new StringBuilder();
      getPathTo(vertices.get(to), path, numSteps);
      path.append(", " + (numSteps[0] - 1) + " steps");
      
      return path.toString();
   }

   private void getPathTo(Vertex v, StringBuilder path, int[] numSteps) {
      numSteps[0]++;
      if (v.prior != null) {
         getPathTo(v.prior, path, numSteps);
         path.append(" -> ");
      }

      path.append(v.name);
   }

   public double getDistanceTo(String to) {
      return vertices.get(to).distance;
   }
   
   } // end DiGraph class

   public static boolean use(String s){
      int spaces = 0;

      for(char c : s.toCharArray()){
         if(c == ' ')
            spaces++;
      }

      return !(spaces > 1);

   }
   
}
