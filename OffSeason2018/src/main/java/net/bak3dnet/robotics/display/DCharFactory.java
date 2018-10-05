package net.bak3dnet.robotics.display;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jake Armstrong
 * @since 0.1.0
 * @version 0.1.0
 */
public class DCharFactory {

    private static final Map<Character,byte[]> characterRegistry;

    //Initialize Static Variables
    static {

        Map<Character,byte[]> aMap = new HashMap<Character,byte[]>();

        /**
         * TODO: Fill the hash map with variables we know about.
         */

        //This is the only way I currently understand how to set the matrix
        byte[] char0 = {(byte) 0b00000000,(byte)0b00000000};
        aMap.put('0', char0);

        //char1. . .

        //char2. . .

        //. . .
        
        //charz . . .
        
        characterRegistry = Collections.unmodifiableMap(aMap);

    }

    /**
     * 
     * @param beforeString The string before it is converted into a DChar Array
     * @return Returns a processed array of DChars.
     * 
     */
    public static DChar[] createDChars(String beforeString) {
        //Regex stuff
        Pattern periods = Pattern.compile("[^1-z]");
        Matcher matcher = periods.matcher(beforeString);

        int periodCount = 0;

        //Counts all the periods in the string
        while(matcher.find()) {

            periodCount++;

        }

        //Makes an array of all the DChars without the inlcuded periods.
        DChar[] convertedString = new DChar[beforeString.length()-periodCount];
        
        for(int i = 0; i < beforeString.length(); i++) {

            //Because I am lazy
            char preChar = beforeString.charAt(i);

            /**
             * If it has a decimal for the next character, we want that to bring the period to the end of this character
             * so that the period can be on the character's display.
             * It also skips over the period character the next iteration.
             */
            if(beforeString.charAt(i+1) == '.') {

                convertedString[i] = new DChar(preChar, false, getBinaryMatrix(preChar));
                i++;

            } else {

                convertedString[i] = new DChar(preChar, false, getBinaryMatrix(preChar));

            }  
        
        }

        //Returns the converted String
        return convertedString;

    }

    /**
     * @return Returns the matrix data for the passed char
     * 
     * @param characterToMatrix The character to become the matrix
     */
    private static byte[] getBinaryMatrix(char charToMatrix) {

        byte[] defaultValue = {(byte)0b11111111,(byte)0b11111011};

        return characterRegistry.getOrDefault(charToMatrix,defaultValue);

    } 

}