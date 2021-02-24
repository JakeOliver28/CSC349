import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.io.*;

public class test{

   @Test
   public void testBacon() throws FileNotFoundException{
      Bacon.baconMoves(buildGraphs.getUndirected());
   }

}
