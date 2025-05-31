// Author: Angelica Khizhnyak and Clementine Penrose
// Date: 05.30.25
// Description: Lab 7

package lab7;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

//added these two for building 
import java.util.PriorityQueue;
import java.util.Map;

public class Huffman {
    
    
    //Here is the trienode class necessary for the building
    static class TrieNode implements Comparable<TrieNode>{
        
        //makes a hashMap of children, and initalizes all attributes
        HashMap<Character, TrieNode> children = new HashMap<>();
        int frequency;
        char character;
        TrieNode left, right;

        //for when you are making a new node from the frequency hashMap
        public TrieNode(char character, int frequency){
            this.character = character;
            this.frequency = frequency;
        }

        //for when you are making a connector node when building the tree
        public TrieNode(TrieNode left, TrieNode right){
            this.left = left;
            this.right = right;
            this.frequency = left.frequency + right.frequency;
        }

        //needed for priority queue to sort by occurance
        @Override
        public int compareTo(TrieNode other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

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

    // returns a Huffman tree 
    //Pre: map is not empty
    //Post: Huffman tree returned
    public static void buildTheTree(HashMap<Character, Integer> map){
        //forest for all leaf nodes to be put in
        PriorityQueue<TrieNode> forest = new PriorityQueue<>();
        //for the amount of entries in the map
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            //for every entry make a new leaf node with the key and value and add it to forest
            forest.add(new TrieNode(entry.getKey(), entry.getValue()));
        }

        //while the forest is not a single item
        while(forest.size() > 1){
            //combine them with a parent
            TrieNode left = forest.poll();
            TrieNode right = forest.poll();
            TrieNode parent = new TrieNode(left, right);
            //add parent back into forest
            forest.add(parent);
        }
        //returns huffman tree
        return forest.poll();
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