/*
 * Problem: 290. Word Pattern
 *
 * Problem Statement:
 * Given a pattern and a string s, find if s follows the same pattern. 
 * Following the pattern means a full match, such that there is a bijection 
 * between a letter in pattern and a non-empty word in s.
 *
 * Intuition:
 * This is a mapping problem requiring a one-to-one (bijective) relationship. 
 * Each character in the pattern must map to exactly one unique word, and 
 * each unique word must map back to exactly one character.
 *
 * Approach:
 * 1. Split the input string 's' into an array of words using space as a delimiter.
 * 2. Perform a length check: if the number of characters in the pattern doesn't 
 *    match the number of words, a bijection is impossible.
 * 3. Use a HashMap to store the character-to-word mapping.
 * 4. Iterate through the pattern and words simultaneously:
 *    - If the character exists in the map, verify it maps to the current word.
 *    - If the character is new, ensure the current word hasn't already been 
 *      assigned to a different character (using containsValue).
 *    - If both checks pass, store the new mapping.
 *
 * Time Complexity: O(N + M * K) 
 * - N is the length of the pattern.
 * - M is the number of words.
 * - K is the average length of a word.
 * - Note: map.containsValue() is O(M), making the loop O(N * M) in the worst case.
 * Space Complexity: O(M) to store the split words and the mapping.
 *
 * Edge Cases:
 * - Pattern and string have different lengths (handled by initial check).
 * - Multiple characters mapping to the same word (handled by containsValue).
 * - Single character pattern and single word string.
 *
 * Dry Run:
 * pattern = "abba", s = "dog cat cat dog"
 * 1. i=0: 'a' -> "dog". Map: {a="dog"}
 * 2. i=1: 'b' -> "cat". Map: {a="dog", b="cat"}
 * 3. i=2: 'b' already in map. map.get('b') is "cat". Matches words[2].
 * 4. i=3: 'a' already in map. map.get('a') is "dog". Matches words[3].
 * Result: true.
 *
 * Correctness Check:
 * The solution is correct. However, using map.containsValue() results in O(N^2) 
 * behavior relative to the number of unique elements. A more optimized approach 
 * would use two HashMaps or one HashMap and one HashSet to achieve O(N) time.
 */

import java.util.HashMap;

public class WordPattern290 {
    public boolean wordPattern(String pattern, String s) {
        // Split the string into an array of words based on whitespace
        String[] words = s.split(" ");
        
        // A bijection requires the same number of keys (chars) and values (words)
        if (pattern.length() != words.length) {
            return false;
        }
        
        // Map to store the relationship: Character -> String
        HashMap<Character, String> map = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            
            // Check if we have encountered this pattern character before
            if (map.containsKey(c)) {
                // If seen, the current word must match the previously stored word
                if (!map.get(c).equals(words[i])) {
                    return false;
                }
            } else {
                // If the character is new, we must ensure the word isn't already 
                // mapped to a different character to maintain the bijection.
                if (map.containsValue(words[i])) {
                    return false;
                }
                // Establish the new mapping
                map.put(c, words[i]);
            }
        }
        return true;
    }
}
