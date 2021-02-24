/**
 * HuffmanCode Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

import java.io.*;
import java.util.PriorityQueue;

public class HuffmanCode {

   // instance variables
   private ascNode[] nodeArr = new ascNode[256];

   public HuffmanCode(String filename) throws FileNotFoundException, IOException {
      FileInputStream FIS = new FileInputStream(filename);
      BufferedInputStream BIS = new BufferedInputStream(FIS);
      int value = BIS.read();
      while (value != -1){
         if ((value >= 65) && (value <= 90))
            value = value + 32;
         // if we find a non-Latin alphabet character, skip it
         if (!((value >= 97) && (value <= 122))){
            value = BIS.read();
            continue;        
         }
         if (nodeArr[value] != null)
            nodeArr[value].occur++;
         else
            nodeArr[value] = new ascNode((byte)value);
         value = BIS.read();
      }
   }

   public CodeMap getHuffmanCodeMap() {
      // CodeMap to return at end of method
      CodeMap codes = new CodeMap();

      // add all Latin character nodes (lower case) into priority queue
      PriorityQueue<Node> pq = new PriorityQueue<Node>();
      for (int i=97; i < 123; i++){
         if (nodeArr[i] != null){
            pq.add(nodeArr[i]);
         }
      }

      // combine all nodes in priority queue into one node
      int numberOfNodes = pq.size();
      while (pq.size() > 1){
         pq.add(new treeNode(pq.poll(), pq.poll()));
      }
      Node huff = pq.poll();
      
      // use the tree to generate codes for each ASCII char that occurred in file
      StringBuilder strBuild = new StringBuilder(0);

      if (numberOfNodes != 0){
         generateCodes(huff, new String(), codes);
      }

      return codes;

   }



//////////////////////////////////////////////////////////////////////////
// private Node interface

   private interface Node{
      public int compareTo(Object inNode);
      public byte getMin();
      public int getOccur();
   }

//////////////////////////////////////////////////////////////////////////
// private ascNode class

   private static class ascNode implements Node, Comparable<Object>{
      private byte ascii;
      private int occur;
      private String code;

      public ascNode(byte val){
         ascii = val;
         occur = 1;
      }

      public int compareTo(Object inNode){
         if (((Node)inNode).getOccur() < this.occur){
            return 1;
         }
         else if (((Node)inNode).getOccur() > this.occur){
            return -1;
         }
         else{
            if (((Node)inNode).getMin() < this.ascii){
               return 1;
            }
            else if (((Node)inNode).getMin() > this.ascii){
               return -1;
            }
            else{
               return 0;
            }
         }
      }

      public byte getMin(){
         return this.ascii;
      }

      public int getOccur(){
         return this.occur;
      }

   }

//////////////////////////////////////////////////////////////////////////
// private treeNode class

   private static class treeNode implements Node, Comparable<Object>{

      private Node node1;
      private Node node2;
      private int occur;
      private String code;

      public treeNode(Node node1, Node node2){
         if (node1.compareTo(node2) < 0){
            this.node1 = node1;
            this.node2 = node2;
         }
         else{
            this.node1 = node2;
            this.node2 = node1;
         }
         this.occur = node1.getOccur() + node2.getOccur();
      }

      public int compareTo(Object inNode){
         if (this.occur > ((Node)(inNode)).getOccur()){
            return 1;
         }
         else if (this.occur < ((Node)(inNode)).getOccur()){
            return -1;
         }
         else{
            if (this.getMin() > ((Node)(inNode)).getMin()){
               return 1;
            }
            else if (this.getMin() < ((Node)(inNode)).getMin()){
               return -1;
            }
            else{
               return 0;
            }
         }
      }

      public byte getMin(){
         return (byte)Math.min(node1.getMin(), node2.getMin());
      }

      public int getOccur(){
         return this.occur;
      }

   } // end treeNode class

   private static void generateCodes(Node node, String str, CodeMap codes){
      if (node instanceof treeNode){
         generateCodes(((treeNode)(node)).node1, new StringBuilder(str).append("0").toString(), codes);
         generateCodes(((treeNode)(node)).node2, new StringBuilder(str).append("1").toString(), codes);
      }
      else{
         ((ascNode)(node)).code = str;
         codes.assignCode(((char)((ascNode)(node)).ascii), str);
      }
   }

} // end class 


