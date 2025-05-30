// Author: Angelica Khizhnyak and Clementine Penrose
// Date: 05.30.25
// Description: Lab 7

package lab7;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

public class Huffman {

    // returns a HashMap with each character and their frequency
    // Pre: str is not empty
    // Post: HashMap is returned
    public static HashMap<Character, Integer> countFrequency(String str) {
        HashMap<Character, Integer> charFMap = new HashMap<>();
        char[] charArray = str.toCharArray();

        // for each character in array, put the character in with the value being the frequency
        // the frequency gets added up
        for (char c : charArray) {
            charFMap.put(c, charFMap.getOrDefault(c, 0) + 1);
        }
        return charFMap;
    }

    public static void buildTheTree(HashMap<Character, Integer> map){
        
    }
    public static void main(String[] args) {
        // Setting up scanner and file
        String fileName = args[0];
        File file = new File(fileName);
        Scanner sc;

        try {
            sc = new Scanner(file);

        } catch (FileNotFoundException e) {
            return;
        }
        
        
    }
}