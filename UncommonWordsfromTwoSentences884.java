/*
 * Problem: 884. Uncommon Words from Two Sentences
 *
 * Problem Statement:
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 * Given two sentences s1 and s2, return a list of all uncommon words.
 *
 * Intuition:
 * An uncommon word is essentially any word that appears exactly once across the combined pool of words 
 * from both sentences. If a word appears twice or more (either in the same sentence or across different 
 * sentences), it fails the criteria.
 *
 * Approach:
 * 1. Split both input strings into arrays of individual words using space as a delimiter.
 * 2. Maintain two HashSets: 'res' for words seen exactly once so far, and 'eliminated' for words 
 *    seen multiple times.
 * 3. Iterate through words of both sentences:
 *    - If a word is seen for the first time (not in 'res' or 'eliminated'), add it to 'res'.
 *    - If a word is already in 'res' or 'eliminated', it is a duplicate. Remove it from 'res' 
 *      and add it to 'eliminated' to ensure it's never picked again.
 * 4. Convert the final 'res' set into an array for the output.
 *
 * Time Complexity: O(M + N), where M and N are the lengths of strings s1 and s2. We traverse 
 * each string to split it and then iterate through the resulting word lists once.
 * Space Complexity: O(M + N) to store the words in the HashSets and the split arrays.
 *
 * Edge Cases:
 * - Sentences with only one word.
 * - All words are duplicates (result should be empty).
 * - One sentence is empty (though constraints usually prevent this).
 *
 * Dry Run:
 * s1 = "apple apple", s2 = "banana"
 * 1. Process "apple": not in res/elim -> res={apple}
 * 2. Process "apple": in res -> res={}, elim={apple}
 * 3. Process "banana": not in res/elim -> res={banana}
 * Result: ["banana"]
 *
 * Correctness Check:
 * The solution correctly identifies words with a global frequency of 1. Using two sets is a 
 * valid alternative to a Frequency Map (HashMap<String, Integer>).
 */

import java.util.*;

public class UncommonWordsfromTwoSentences884 {
    public static void main(String[] args) {
        String s1 = "this apple is sweet";
        String s2 = "this apple is sour";

        // Tokenize sentences into words based on spaces
        String[] s1sp = s1.split(" ");
        String[] s2sp = s2.split(" ");

        // 'eliminated' stores words that appeared more than once across both sentences
        HashSet<String> eliminated = new HashSet<>();
        // 'res' stores words that have appeared exactly once so far
        HashSet<String> res = new HashSet<>();

        // Process words from the first sentence
        for (String x : s1sp) {
            // If we haven't seen this word at all, it's a candidate for being uncommon
            if (!res.contains(x) && !eliminated.contains(x)) {
                res.add(x);
            } 
            // If it's already in 'res', it's the second time we see it -> move to 'eliminated'
            // If it's already in 'eliminated', it's the third+ time -> keep it in 'eliminated'
            else if (res.contains(x) || eliminated.contains(x)) {
                res.remove(x);
                eliminated.add(x);
            }
        }
        
        // Process words from the second sentence using the same logic
        for (String x : s2sp) {
            if (!res.contains(x) && !eliminated.contains(x)) {
                res.add(x);
            } else if (res.contains(x) || eliminated.contains(x)) {
                res.remove(x);
                eliminated.add(x);
            }
        }

        // Convert the set of unique words into the required array format
        String[] arr = new String[res.size()];
        res.toArray(arr);
        
        // Output the results
        for (String x : arr) {
            System.out.println(x);
        }
    }
}
