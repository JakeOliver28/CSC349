import java.util.*;
import java.lang.*;

public class lab2{

   public static void quickSort(int[] array, int left, int right){
      
      int pivot = array[ left + (right - left) / 2];
      int i = left; 
      int j = right;

      while(i <= j){
      
         // Moving index
         while(true){
            if(array[i] < pivot)
               i++;
            else
               break;
         }

         while(true){
            if(array[j] > pivot)
               j--; 
            else
               break;
         }

         if(i <= j){
            swap(array, i, j);
            i++; j--;
         }
      }
      
      if(left < j)
         quickSort(array, left, j);
      if(i < right)
         quickSort(array, i, right);

   }

   public static void quickSort(int[] array, int left, int right, int pivot){
      
      int i = left; 
      int j = right;

      while(i <= j){
      
         // Moving index
         while(true){
            if(array[i] < pivot)
               i++;
            else
               break;
         }

         while(true){
            if(array[j] > pivot)
               j--; 
            else
               break;
         }

         if(i <= j){
            swap(array, i, j);
            i++; j--;
         }
      }
      
      if(left < j)
         quickSort(array, left, j);
      if(i < right)
         quickSort(array, i, right);

   }

   public static void swap(int[] array, int i, int j){
      int temp = array[i];

      array[i] = array[j];
      array[j] = temp;

   }

   public static int sortBased(int[] array){
      quickSort(array, 0, (array.length-1));
      return array[array.length / 2];
   }

////////////////////////////////////////////////////////////TEST

   public static void randomSort(int[] array, int left, int right){

      Random ran = new Random();

      int pivot = array[ Math.abs(ran.nextInt(left+1) + right) ];
      
      int i = left; 
      int j = right;

      while(i <= j){
      
         // Moving index
         while(true){
            if(array[i] < pivot)
               i++;
            else
               break;
         }

         while(true){
            if(array[j] > pivot)
               j--; 
            else
               break;
         }

         if(i <= j){
            swap(array, i, j);
            i++; j--;
         }
      }
      
      if(left < j)
         quickSort(array, left, j);
      if(i < right)
         quickSort(array, i, right);

   }

   public static int randomMedian(int[] array){
      randomSort(array, 0, (array.length-1));
      return array[array.length / 2];
   }

///////////////////////////////////////////////////////////

   public static int fastMedianK3(int[] array){
      
      if (array.length < 3)
         return sortBased(array);

      int[] k1 = new int[array.length / 3 ];
      int[] k2 = new int[array.length / 3 ];
      int[] k3 = new int[(array.length / 3) + 2];
      int[] medians = new int[3];
      int pivot;
      int j = 0;

      for(int i = 0; i < array.length / 3; i++)
         k1[i] = array[i]; 

      for(int i = (array.length / 3); i < (2 * (array.length/3)); i++)
         k2[j++] = array[i];

      j = 0;
      
      for(int i = (2 * (array.length) / 3); i < array.length; i++)
         k3[j++] = array[i]; 

      medians[0] = sortBased(k1);

      medians[1] = sortBased(k2);

      medians[2] = sortBased(k3);

      pivot = sortBased(medians);
      
      quickSort(array, 0, (array.length-1), pivot);

      return array[array.length / 2];
   }

///////////////////////////////////////////////////////////////////////////////

   public static int fastMedianK5(int[] array){
      
      if (array.length < 5)
         return sortBased(array);

      int[] k1 = new int[array.length / 5];
      int[] k2 = new int[array.length / 5];
      int[] k3 = new int[array.length / 5];
      int[] k4 = new int[array.length / 5];
      int[] k5 = new int[(array.length / 5) + 4];

      int[] medians = new int[5];

      int pivot;
      int j = 0;

      for(int i = 0; i < array.length / 5; i++)
         k1[i] = array[i]; 

      for(int i = (array.length / 5); i < (2 * (array.length/5)); i++)
         k2[j++] = array[i];

      j = 0;
      
      for(int i = (2 * (array.length / 5)); i < (3 * (array.length/5)); i++)
         k3[j++] = array[i]; 

      j = 0;

      for(int i = (3 * (array.length / 5)); i < (4 * (array.length/5)); i++)
         k4[j++] = array[i]; 

      j = 0;

      for(int i = (4 * (array.length / 5)); i < array.length; i++)
         k5[j++] = array[i]; 
      

      medians[0] = sortBased(k1);

      medians[1] = sortBased(k2);

      medians[2] = sortBased(k3);

      medians[3] = sortBased(k4);

      medians[4] = sortBased(k5);

      pivot = sortBased(medians);
      
      quickSort(array, 0, (array.length-1), pivot);

      return array[array.length / 2];
   }

///////////////////////////////////////////////////////////

   public static int fastMedianK7(int[] array){
      
      if (array.length < 7)
         return sortBased(array);

      int[] k1 = new int[array.length / 7];
      int[] k2 = new int[array.length / 7];
      int[] k3 = new int[array.length / 7];
      int[] k4 = new int[array.length / 7];
      int[] k5 = new int[array.length / 7];
      int[] k6 = new int[array.length / 7];
      int[] k7 = new int[(array.length / 7) + 6];

      int[] medians = new int[7];

      int pivot;
      int j = 0;

      for(int i = 0; i < array.length / 7; i++)
         k1[i] = array[i]; 

      for(int i = (array.length / 7); i < (2 * (array.length/7)); i++)
         k2[j++] = array[i];

      j = 0;
      
      for(int i = ((2 * array.length)/7); i < (3 * (array.length/7)); i++)
         k3[j++] = array[i];

      j = 0;

      for(int i = (3 * (array.length/7)); i < (4 * (array.length/7)); i++)
         k4[j++] = array[i]; 

      j = 0;

      for(int i = (4 * (array.length/7)); i < (5 * (array.length/7)); i++)
         k5[j++] = array[i]; 

      j = 0;

      for(int i = (5 * (array.length/7)); i < (6 * (array.length/7)); i++)
         k6[j++] = array[i]; 

      j = 0;

      for(int i = (6 * (array.length/7)); i < array.length; i++)
         k7[j++] = array[i]; 

      medians[0] = sortBased(k1);

      medians[1] = sortBased(k2);

      medians[2] = sortBased(k3);

      medians[3] = sortBased(k4);

      medians[4] = sortBased(k5);
      
      medians[5] = sortBased(k6);
      
      medians[6] = sortBased(k7);

      pivot = sortBased(medians);
      
      quickSort(array, 0, (array.length-1), pivot);

      return array[array.length / 2];
   }

///////////////////////////////////////////////////////////

   public static void main(String args[]){
      
      int[] array = new int[100000];
      int[] array2 = new int[100000];
      int[] array3 = new int[100000];
      int[] array4 = new int[100000];
      ArrayList<Integer> arr = new ArrayList<Integer>();

      Random ran = new Random();

      System.out.println("Generating Array");

      for(int i = 0; i < array.length; i++){
         array[i] = array2[i] = array3[i] = array4[i] = (ran.nextInt());
         arr.add(array[i]);
         //System.out.println(array[i]);
      }

      Collections.sort(arr);
      
      System.out.println("k3: The median is " + fastMedianK3(array));
      System.out.println("k5: The median is " + fastMedianK5(array2));
      System.out.println("k7: The median is " + fastMedianK7(array3));
      System.out.println("ran:The median is " + randomMedian(array4));


      //System.out.println("The correct answer is: " + arr.get(array.length / 2));
   }
}


            
            



   
      
            

         

      
      
      
