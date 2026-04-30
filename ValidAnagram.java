/*
 * Problem: 242. Valid Anagram
 *
 * Problem Statement:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An anagram is a word or phrase formed by rearranging the letters of a different word 
 * or phrase, typically using all the original letters exactly once.
 *
 * Intuition:
 * Two strings are anagrams if they contain the exact same characters with the exact 
 * same frequencies. If we can normalize the strings (by sorting) or count the 
 * occurrences of each character, we can determine if they are identical in composition.
 *
 * Approach:
 * 1. Sorting: Convert strings to arrays, sort them, and compare. If they are anagrams, 
 *    the sorted arrays will be identical.
 * 2. HashMap: Use a frequency map to increment counts for string s and decrement for 
 *    string t. A net count of zero for all keys indicates an anagram.
 * 3. Frequency Array: Similar to HashMap but uses a fixed-size array (size 26) for 
 *    better performance when dealing with lowercase English letters.
 *
 * Time Complexity: 
 * - Sorting: O(N log N) where N is the length of the strings, due to the sorting algorithm.
 * - HashMap/Array: O(N) because we iterate through the strings a constant number of times.
 *
 * Space Complexity:
 * - Sorting: O(N) to store the character arrays.
 * - HashMap: O(K) where K is the number of unique characters (max 26 for English).
 * - Array: O(1) as the array size is fixed at 26 regardless of input size.
 *
 * Edge Cases:
 * - Strings of different lengths (should immediately return false for optimization).
 * - Empty strings (are technically anagrams of each other).
 * - Strings with Unicode characters (Array approach fails, HashMap approach works).
 *
 * Dry Run:
 * s = "anagram", t = "nagaram"
 * 1. Count 'a': s has 3, t has 3 -> net 0.
 * 2. Count 'n': s has 1, t has 1 -> net 0.
 * 3. ... all characters result in net 0. Return true.
 *
 * Correctness Check:
 * The code is logically correct. However, adding an initial check `if (s.length() != t.length()) return false;` 
 * would significantly improve performance for non-anagrams of different lengths.
 */

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class ValidAnagram {
    // Naive solution using Sorting
    public boolean isAnagram(String s, String t) {
        // Convert strings to character arrays to enable sorting
        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();

        // Sorting brings identical characters to the same relative positions
        Arrays.sort(schars);
        Arrays.sort(tchars);

        // If the sorted sequences are identical, the original strings are anagrams
        return Arrays.equals(schars, tchars);
    }
    
    // Using HashMap
    public boolean isAnagram1(String s, String t){
        // Map to store character frequencies
        Map<Character, Integer> count = new HashMap<>();

        // Increment frequency for every character found in string s
        for(char x: s.toCharArray()){
            count.put(x, count.getOrDefault(x, 0)+1);
        }

        // Decrement frequency for every character found in string t
        for(char x: t.toCharArray()){
            count.put(x, count.getOrDefault(x,0)-1);
        }

        // If strings are anagrams, every character's net frequency must be zero
        for(int val: count.values()){
            if(val!=0){
                return false;
            }
        }
        return true;
    }



    public boolean isAnagram2(String s, String t) {
        // Fixed-size array acts as a high-performance hash map for 'a'-'z'
        int[] count = new int[26];
        
        // Count the frequency of characters in string s
        for (char x : s.toCharArray()) {
            // Map character to index 0-25 by subtracting ASCII value of 'a'
            count[x - 'a']++;
        }
        
        // Decrement the frequency of characters in string t
        for (char x : t.toCharArray()) {
            count[x - 'a']--;
        }
        
        // Check if any character has non-zero frequency
        // If s and t were different lengths, at least one index would be non-zero
        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }
        
        return true;
    }
}
