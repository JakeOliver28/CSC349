/**
 * AdHocCode Class
 *
 * @author Jacob Oliver
 * @version Lab 4
 */

public class AdHocCode {

   //instance variables
   private CodeMap adHocCodeMap;

   public AdHocCode(){
      adHocCodeMap = new CodeMap();
      adHocCodeMap.assignCode('a', "00001");
      adHocCodeMap.assignCode('b', "00010");
      adHocCodeMap.assignCode('c', "00011");
      adHocCodeMap.assignCode('d', "00100");
      adHocCodeMap.assignCode('e', "00101");
      adHocCodeMap.assignCode('f', "00110");
      adHocCodeMap.assignCode('g', "00111");
      adHocCodeMap.assignCode('h', "01000");
      adHocCodeMap.assignCode('i', "01001");
      adHocCodeMap.assignCode('j', "01010");
      adHocCodeMap.assignCode('k', "01011");
      adHocCodeMap.assignCode('l', "01100");
      adHocCodeMap.assignCode('m', "01101");
      adHocCodeMap.assignCode('n', "01110");
      adHocCodeMap.assignCode('o', "01111");
      adHocCodeMap.assignCode('p', "10000");
      adHocCodeMap.assignCode('q', "10001");
      adHocCodeMap.assignCode('r', "10010");
      adHocCodeMap.assignCode('s', "10011");
      adHocCodeMap.assignCode('t', "10100");
      adHocCodeMap.assignCode('u', "10101");
      adHocCodeMap.assignCode('v', "10110");
      adHocCodeMap.assignCode('w', "10111");
      adHocCodeMap.assignCode('x', "11000");
      adHocCodeMap.assignCode('y', "11001");
      adHocCodeMap.assignCode('z', "11010");
   }

   public CodeMap getAdHocCodeMap(){
      return adHocCodeMap;
   }

}
