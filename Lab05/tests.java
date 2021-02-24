/**
 * tests class
 *
 * @author Jacob Oliver
 * @version Lab 5
 */

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class tests{

   @Test
   public void TextCheckerIsText1(){
      HashMap<String, Integer> dict = new HashMap<String, Integer>();
      dict.put("i", 1);
      dict.put("a", 1);
      dict.put("igloo", 1);
      dict.put("an", 1);

      TextChecker thisTC = new TextChecker();
      thisTC.setDictionary(dict);
      thisTC.setString("anigloo");
      assertTrue(thisTC.isText()); 
      thisTC.setString("aneigloo");
      assertFalse(thisTC.isText());

   }

   @Test
   public void EditDistanceTest1(){
      System.out.print("\n");
      EditDistance thisED = new EditDistance();
      assertEquals(1, thisED.findEditDistance("m", "q"));
      thisED.getAlignment();
      assertEquals(thisED.findEditDistance("jackie", "jake"), 2);
      thisED.getAlignment();
      assertEquals(thisED.findEditDistance("samantha", "sam"), 5);
      thisED.getAlignment();
      assertEquals(thisED.findEditDistance("jonathan", "jonathan"), 0);
      thisED.getAlignment();
      thisED.findEditDistance("samuel", "samson");
      thisED.getAlignment();
      thisED.findEditDistance("preface", "police");
      thisED.getAlignment();
      thisED.findEditDistance("dancer", "drinker");
      thisED.getAlignment();
      thisED.findEditDistance("animal", "danimals");
      thisED.getAlignment();
      thisED.findEditDistance("exponential", "polynomial");
      thisED.getAlignment();
   }
}
