/*
 * Problem: 1408. String Matching in an Array
 *
 * Problem Statement:
 * Given an array of strings 'words', return all strings in 'words' that are a substring of another word in any order.
 *
 * Intuition:
 * A string 'A' is a substring of 'B' if 'B' contains 'A'. Since the constraints are small (up to 100 words),
 * we can compare every pair of words to check the substring relationship.
 *
 * Approach:
 * 1. Iterate through each word in the array.
 * 2. For each word, iterate through the array again to find a different word that contains the current word.
 * 3. If a match is found, add the current word to the result list and move to the next word.
 *
 * Time Complexity: O(N^2 * M^2) where N is the number of words and M is the maximum length of a word.
 * The nested loops take O(N^2) and the 'contains' method takes O(M^2) in the worst case.
 * Space Complexity: O(N * M) to store the result list.
 *
 * Edge Cases:
 * - Words that are identical: Handled by checking indices or using !equals.
 * - No substrings found: Returns an empty list.
 *
 * Dry Run:
 * words = ["mass","as","hero","superhero"]
 * i=0 ("mass"): check against others. "as" is in "mass", but "mass" is not in others.
 * i=1 ("as"): check against "mass". "mass".contains("as") is true. Add "as" to list.
 * i=2 ("hero"): check against "superhero". "superhero".contains("hero") is true. Add "hero" to list.
 * Result: ["as", "hero"]
 *
 * Correctness Check:
 * The solution is correct. The naive method uses !res.contains(y) which is O(N), making it slightly slower than the optimized method.
 */
import java.util.*;

public class StringMatchinginanArray1408 {

    // Naive Brute Force Method
    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();
        // Outer loop picks a potential 'super-string'
        for (String x : words) {
            // Inner loop picks a potential 'sub-string'
            for (String y : words) {
                // Check if y is a substring of x, they aren't the same word, and y isn't already in results
                if (x.contains(y) && !x.equals(y) && !res.contains(y)) {
                    res.add(y);
                }
            }
        }
        return res;
    }
    // Optimized Method

    // Helper function to determine if a specific word is a substring of any other word in the array
    public boolean check(String word, String[] words, int index) {
        for (int i = 0; i < words.length; i++) {
            // Ensure we don't compare the word with itself using the index
            // and check if the current word 'words[i]' contains the target 'word'
            if (index != i && words[i].contains(word)) {
                return true;
            }
        }
        return false;
    }

    public List<String> stringMatching1(String[] words) {
        List<String> list = new ArrayList<>();
        // Iterate through each word to see if it qualifies as a substring of any other word
        for (int i = 0; i < words.length; i++) {
            int index = i;
            // If the word at index i is found inside any other word, add it to the result
            if (check(words[i], words, index)) {
                list.add(words[i]);
            }
        }
        return list;
    }
}
