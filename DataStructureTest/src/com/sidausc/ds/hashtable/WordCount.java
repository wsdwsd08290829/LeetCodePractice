package com.sidausc.ds.hashtable;




import java.io.IOException;
import java.util.Scanner;


/** A program that counts words in a document, printing the most frequent. */
public class WordCount {
  public static void main(String[] args) throws IOException { 
	 
    Scanner doc = new Scanner(System.in);
    
    doc.useDelimiter("[^a-zA-Z]");  // ignore non-letters
    
    HashTableMap<String,Integer> h = new HashTableMap<String,Integer>();
    String word;
    Integer count;
    while (doc.hasNext()) {
      word = doc.next(); 
      if (word.equals("")) continue; // ignore null strings between delimiters
      word = word.toLowerCase();     // ignore case 
      count = h.get(word); // get the previous count for this word
      if (count == null) 
	h.put(word, 1);       // autoboxing allows this
      else
	h.put(word, ++count); // autoboxing/unboxing allows this
    }
    int maxCount = 0;
    String maxWord = "no word";
    for (java.util.Map.Entry<String, Integer> ent : h.entrySet()) { // find max-count word
      if (ent.getValue() > maxCount) {
	maxWord = ent.getKey();
	maxCount = ent.getValue();
      }
    }
    System.out.print("The most frequent word is \"" + maxWord);
    System.out.println(",\" with word-count = " + maxCount + "."); 
  }
}