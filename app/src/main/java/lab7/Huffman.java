// Author: Angelica Khizhnyak and Clementine Penrose
// Date: 05.30.25
// Description: Lab 7

package lab7;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

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
    public static TrieNode buildTheTree(HashMap<Character, Integer> map){
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


    //returns a compressed binary representation of input string
    //Pre-con: Huffman tree is not malformed
    //Post-con: returned binary representation of input string
    public static String encode(TrieNode huffmanTree, String strToEncode){
        //create encoding table based off huffmanTree
        HashMap<Character, String> encodingTable = createEncodingTable(huffmanTree);

        //make the string to be returned at the end and a char array representation of input string
        String encodedStr = "";
        char[] chars = strToEncode.toCharArray();

        //for length of input string
        for(int i = 0; i < chars.length; i++){
            //get the code for the current character
            String code = encodingTable.get(chars[i]);
            //if its not null, add it to return string else, throw exception
            if(code != null){
                encodedStr = encodedStr + code;
            }else{
                throw new IllegalArgumentException();
            }
        }
        return encodedStr;
    }

    //creates and returns HashMap encoding table representation of input huffmantree
    //Pre-con: huffman tree is not malformed
    //Post-con: returned complete encoding table
    public static HashMap<Character, String> createEncodingTable(TrieNode huffmanTree){
        //initalize encoding Table as a HashMap of character and string
        HashMap<Character, String> encodingTable = new HashMap<>();
        //build the encoding table
        buildEncodingTree(huffmanTree, "", encodingTable);
        return encodingTable;
    }

    //builds encoding table based off of given node
    public static void buildEncodingTable(TrieNode node, String path, HashMap<Character, String> map){
        //if the node is null just return
        if(node == null){
            return;
        }
        //if the node is a leaf, put it in the map and return
        if(node.left == null && node.right == null){
            map.put(node.character, path);
            return;
        }

        //recurse through huffmanTree
        buildEncodingTable(node.left, path + "0", map);
        buildEncodingTable(node.right, path + "1", map);
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