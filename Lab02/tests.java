import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class tests{

   @Test
   public void testMed1(){
      int[] thisBoi = new int[5];

      for (int i = 0; i < 5; i++)
         thisBoi[i] = i;
       
      assertEquals(3, lab02.randomMed(5, thisBoi));
   }

   @Test
   public void testMed2(){
      int[] thisBoi = new int[5];

      thisBoi[0] = 5;
      thisBoi[1] = 90;
      thisBoi[2] = 3;
      thisBoi[3] = 0;
      thisBoi[4] = 65;

      assertEquals(5, lab02.randomMed(5, thisBoi));
   }

}
