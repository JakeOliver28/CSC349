public class lab02{
   
   public static int randomMed(int n, int[] list){
      
      int ranVal = list[n/2];
      int[] arrL = new int[n];
      int[] arrR = new int[n];
      int rightVal = 0;
      int leftVal = 0;

      for (int i = 0; i < n; i++){
         if (list[i] < ranVal)
            arrL[leftVal++] = list[i];
         else
            arrR[rightVal++] = list[i];
      }

      if (n < leftVal)
         return randomMed(n, arrL);
      else if (n > leftVal)
         return randomMed(n - leftVal - 1, arrR);
      else
         return ranVal;

   }

}
