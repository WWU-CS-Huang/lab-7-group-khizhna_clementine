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

    public static HashMap<Character, Integer> countFrequency(String str) {
        HashMap<Character, Integer> charFMap = new HashMap<>();
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