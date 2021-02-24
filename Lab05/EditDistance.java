/**
 * EditDistance Class
 *
 * @author Jacob Oliver
 * @version Lab 5
 */

import java.util.TreeMap;
import java.util.PriorityQueue;
import java.util.Collection;

public class EditDistance{

   // instance variables
   private int[][] matrix;
   private int rows;
   private int cols;
   private String xString;
   private String yString;

   public EditDistance(){

   }

   public static void main(String[] args){

   }

   public int findEditDistance(String x, String y){
      rows = x.length() + 1;
      cols = y.length() + 1;
      xString = x;
      yString = y;
      matrix = new int[rows][cols];
      for (int i = 0; i < rows; i++)
         matrix[i][0] = i;
      for (int j = 0; j < cols; j++)
         matrix[0][j] = j;
      setDistances(x, y);
      return matrix[rows-1][cols-1];
   }

   private void setDistances(String x, String y){
      for (int i = 1; i < rows; i++){
         for (int j = 1; j < cols; j++){
            matrix[i][j] = Math.min(diff(x, y, i, j) + matrix[i-1][j-1], 
            Math.min(matrix[i-1][j] + 1, matrix[i][j-1] + 1));
         }
      }
   }

   private int diff(String x, String y, int i, int j){
      if (x.charAt(i-1) == y.charAt(j-1))
         return 0;
      else
         return 1;
   }

   public void getAlignment(){
      DiGraph thisDG = new DiGraph();
      thisDG.setStart("0 0");
      thisDG.printPathTo(((Integer)(rows-1)).toString() + " " + ((Integer)(cols-1)).toString());
   }

   private void getPath(String x, String y, int i, int j, 
   StringBuilder xBuild, StringBuilder yBuild){
      if ((i == (rows - 1)) || (j == (cols - 1))){
         endOfPath(x, y, i, j, xBuild, yBuild);
         return;
      }
      int down = matrix[i+1][j];
      int right = matrix[i][j+1];
      int diag = matrix[i+1][j+1];
      if ((diag <= right) && (diag <= down)){
         xBuild.append(x.charAt(i));
         yBuild.append(y.charAt(j));
         getPath(x, y, i+1, j+1, xBuild, yBuild);
      }
      else if (right <= down){
         xBuild.append("-");
         yBuild.append(y.charAt(j));
         getPath(x, y, i, j+1, xBuild, yBuild);
      }
      else{
         xBuild.append(x.charAt(i));
         yBuild.append("-");
         getPath(x, y, i+1, j, xBuild, yBuild);
      }
   }

   private void endOfPath(String x, String y, int i, int j, 
   StringBuilder xBuild, StringBuilder yBuild){
      if (i == (rows - 1)){
         while (j < (cols - 1)){
            xBuild.append("-");
            yBuild.append(y.charAt(j));
            j++;
         }
      }
      else if (j == (cols - 1)){
         while (i < (rows - 1)){
            xBuild.append(x.charAt(i));
            yBuild.append("-");
            i++;
         }
      }
      //System.out.println(xBuild.toString());
      //System.out.println(yBuild.toString());
   }

   private class DiGraph {

      private TreeMap<String, Vertex> vertices = new TreeMap<String, Vertex>();

      private class Edge {
         private Vertex dest;
         private double weight;

         public Edge(Vertex dest, double weight) {
            this.dest = dest;
            this.weight = weight;
         }
      } 

      private class Vertex implements Comparable<Vertex> {
         private String name;
         private TreeMap<String, Edge> edges = new TreeMap<String, Edge>();
         private boolean known;
         private double distance = Double.POSITIVE_INFINITY;
         private Vertex prior;

         public Vertex(String name) {
            this.name = name;
         }

         public void addEdge(Vertex adj, double weight) { 
            if (edges.put(adj.name, new Edge(adj, weight)) != null) {
               throw new IllegalArgumentException("Duplicate edge");
            }
         }
   
         public int compareTo(Vertex that) {
            return ((Double)this.distance).compareTo(that.distance);
         }

         public String toString() {
            return this.name;
         }

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

      } // end class Vertex

      public DiGraph() {
         for (Integer i = 0; i < rows; i++){
            for (Integer j = 0; j < cols; j++){
               createVertices(i, j);
            }
         }
      }

      private void createVertices(Integer i, Integer j){
         if ((i < (rows - 1)) && (j < (cols - 1)))
            adjacentVertices(i, j, i+1, j+1);
         if (i < (rows - 1))
            adjacentVertices(i, j, i+1, j);
         if (j < (cols - 1))
            adjacentVertices(i, j, i, j+1);
      }

      private void adjacentVertices(Integer i, Integer j, Integer adjI, Integer adjJ){
         String fromStr = i.toString() + " " + j.toString();
         String toStr = adjI.toString() + " " + adjJ.toString();
         Double doub = 1.0;
         if ((i == (adjI - 1)) && (j == (adjJ - 1))){
            if (xString.charAt(i) == yString.charAt(j))
               doub = 0.0;
         }
         //if (doub < 0){
           // System.out.println(xString);
           // System.out.println(yString);
           // System.out.println(doub);
           // System.out.println(matrix[i][j]);
           // System.out.println(matrix[adjI][adjJ]);
           // throw new IllegalArgumentException();
         //}
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

      public void printPathTo(String to) {
         StringBuilder xBuild = new StringBuilder();
         StringBuilder yBuild = new StringBuilder();
         getPathTo(vertices.get(to), xBuild, yBuild);
         System.out.println(xBuild);
         System.out.println(yBuild);
      }

      private void getPathTo(Vertex v, StringBuilder xBuild, StringBuilder yBuild) {
         if (v.prior != null) {
            getPathTo(v.prior, xBuild, yBuild);
            appendLetters(v, xBuild, yBuild);
         }
      }

      private void appendLetters(Vertex v, StringBuilder xBuild, StringBuilder yBuild) {
         String[] vParts = v.name.split(" ");
         String[] pParts = v.prior.name.split(" ");
         int vertexI = Integer.parseInt(vParts[0]);
         int vertexJ = Integer.parseInt(vParts[1]);
         int priorI = Integer.parseInt(pParts[0]);
         int priorJ = Integer.parseInt(pParts[1]);
         if ((vertexI == (priorI + 1)) && (vertexJ == (priorJ + 1))){
            xBuild.append(xString.charAt(priorI));
            yBuild.append(yString.charAt(priorJ));
         }
         else if (vertexI == (priorI + 1)){
            xBuild.append(xString.charAt(priorI));
            yBuild.append("-");
         }
         else{
            xBuild.append("-");
            yBuild.append(yString.charAt(priorJ));
         }
      }

      public double getDistanceTo(String to) {
         return vertices.get(to).distance;
      }

   } // end private class DiGraph

}
