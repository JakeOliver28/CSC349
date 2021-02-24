/**
 * A partial implementation of a directed graph class to explore
 * Dijkstra's shortest-path algorithm.
 *
 * @author Hatalsky/Jones - Provided partial solution
 * @author Jacob Oliver
 * @version CPE 103 Lab 13
 */
import java.io.*;
import java.util.*;

public class DiGraph {
   /* Provided/Required */
   private TreeMap<String, Vertex> vertices = new TreeMap<String, Vertex>();

   /* Provided/Required - should not need to modify
    * The edge class is used such that we may store the weights easily. */
   private static class Edge {
      private Vertex dest;
      private double weight;

      public Edge(Vertex dest, double weight) {
         this.dest = dest;
         this.weight = weight;
      }
   }

   /* Provided/Required - should not need to modify */
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
   } // End class Vertex

   /**
    * Constructs a directed graph from a file containing the weighted
    * edges.  A weighted edge is specified as three values, separated
    * by whitespace, in the following order: the from-vertex as a
    * String, the to-vertex as a String, and the weight as a double.
    *
    * @param fileName the name of a file containing the weighted
    * edges (see the method's detailed description for the file's
    * format).
    *
    * @throws FileNotFoundException if the file is not found
    * @throws IllegalArgumentException if:
    * <ul>
    *   <li>the file is empty</li>
    *   <li>the input is mal-formed</li>
    *   <li>the graph contains a negative edge</li>
    * </ul>
    */
   public DiGraph(String fileName) throws FileNotFoundException {
      File file = new File(fileName);
      Scanner sc = new Scanner(file);
      if (!sc.hasNext()){
         throw new IllegalArgumentException();
      }
      while (sc.hasNext()){
         try{
            String fromStr = sc.next();
            String toStr = sc.next();
            Double doub = sc.nextDouble();
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
   }

   /**
    * Applies Dijkstra's shortest path algorithm to the graph using
    * the specified vertex as the starting vertex. 
    *
    * @param from The name of the starting vertex.
    *
    * @throws IllegalArgumentException if the specified vertex is not
    * in the graph
    */
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
               // if the current distance value for the destination vertex is 
               // greater than the current distance plus the edge weight
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

   /**
    * Provided: Returns a shortest path to the specified vertex from
    * the current start-vertex. 
    *
    * @param to the to-vertex whose path you want
    * @return the path from the current start-vertex to the specified
    * to-vertex
    */
   public String getPathTo(String to) {
      StringBuilder path = new StringBuilder();
      getPathTo(vertices.get(to), path);

      return path.toString();
   }
   
   /*
    * Provided: Recursive helper method for public getPathTo().
    */
   private void getPathTo(Vertex v, StringBuilder path) {
      if (v.prior != null) {
         getPathTo(v.prior, path);
         path.append(' ');
      }

      path.append(v.name);
   }

   /**
    * Provided: Returns the distance of the shortest path to the
    * specified vertex to the current start-vertex. 
    *
    * @param to the to-vertex whose distance you want
    * @return the distance from the current start-vertex to the
    * specified to-vertex
    */
   public double getDistanceTo(String to) {
      return vertices.get(to).distance;
   }
}
