/**
 * CodeMap Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

import java.util.*;
import java.util.Map.Entry;

public class CodeMap {

   // instance variables
   private HashMap<Character, String> codeMap;
   private int size;

   public CodeMap() {
      codeMap = new HashMap<Character, String>();
      size = 0;
   }

   public void assignCode(char c, String code){
      int ascii = (int) c;
      char correctedCode;
      if (ascii >=65 && ascii <=90){
         correctedCode = (char)(c + (char)32);
         codeMap.put(correctedCode, code);
         size++;
      }
      else if (ascii >= 97 && ascii <= 122){
         codeMap.put(c, code);
         size++;
      }
      
   }

   public boolean isComplete(){
      return (size == 26);
   }

   public String convertChar(char c){
      if (codeMap.containsKey((Character)c))
         return codeMap.get((Character)c);

      return "";

   }

   public String convertText(String s){
   
      StringBuilder strBuild = new StringBuilder();

      for (int i = 0; i < s.length(); i++){
         Character c = (Character)s.charAt(i);
         if (codeMap.containsKey(c))
            strBuild.append(codeMap.get(c));
      }

      return strBuild.toString();
   }

   public void print(){
      System.out.println("Character   Code\n");

      for (Entry<Character, String> thisEntry : codeMap.entrySet()){
         System.out.println(thisEntry.getKey() + "           " + thisEntry.getValue());
      }

   }


//////////////////////////////////////////////////////////////////////////
// private Node interface

   private interface Node{
      public int compareTo(Object inNode);
      public byte getMin();
      public int getOccur();
   }

//////////////////////////////////////////////////////////////////////////
// prvate ascNode class

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

///////////////////////////////////////////////////////////////////////
// private treeNode interface

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
   
   }

   private static void generateCodes(Node node, String str){
      if (node instanceof treeNode){
         generateCodes(((treeNode)(node)).node1, new StringBuilder(str).append("0").toString());
         generateCodes(((treeNode)(node)).node2, new StringBuilder(str).append("1").toString());
      }
      else{
         ((ascNode)(node)).code = str;
      }
   }



}





