/*
 * Problem: 49. Group Anagrams
 *
 * Problem Statement:
 * Given an array of strings, group the anagrams together. An anagram is a word or phrase 
 * formed by rearranging the letters of a different word or phrase, typically using all 
 * the original letters exactly once.
 *
 * Intuition:
 * The core insight is that all anagrams, when their characters are sorted alphabetically, 
 * will produce the exact same string (a "canonical form"). By using this sorted string 
 * as a key in a HashMap, we can collect all original strings that share that same signature.
 *
 * Approach:
 * 1. Initialize a HashMap where the key is the sorted version of a word and the value is a list of its anagrams.
 * 2. Iterate through each string in the input array.
 * 3. Convert the string to a character array and sort it to create the canonical key.
 * 4. Check if the key exists in the map; if not, initialize a new ArrayList.
 * 5. Add the original string to the list corresponding to its sorted key.
 * 6. Return a new list containing all the grouped lists from the map's values.
 *
 * Time Complexity: O(N * K log K)
 * Where N is the number of strings in the input array and K is the maximum length of a string. 
 * We iterate through N strings, and for each, we perform a sort taking O(K log K) time.
 *
 * Space Complexity: O(N * K)
 * We store all N strings of average length K in the HashMap.
 *
 * Edge Cases:
 * - Empty input array: The loop won't execute, returning an empty list.
 * - Array with one string: Returns a list containing one list with that single string.
 * - Empty strings (""): Correctly groups empty strings together as they sort to an empty string.
 *
 * Dry Run:
 * Input: ["eat", "tea", "tan"]
 * 1. "eat" -> char array ['e','a','t'] -> sorted ['a','e','t'] -> key "aet". Map: {"aet": ["eat"]}
 * 2. "tea" -> char array ['t','e','a'] -> sorted ['a','e','t'] -> key "aet". Map: {"aet": ["eat", "tea"]}
 * 3. "tan" -> char array ['t','a','n'] -> sorted ['a','n','t'] -> key "ant". Map: {"aet": ["eat", "tea"], "ant": ["tan"]}
 * Result: [["eat", "tea"], ["tan"]]
 *
 * Correctness Check:
 * The solution is correct. It uses the standard sorting approach to identify anagrams. 
 * The use of HashMap ensures efficient grouping.
 */
/*
 * Problem Reference: LeetCode 49 - Group Anagrams
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Hash by canonical signature (sorted word or char counts).
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total chars * log k) or O(total chars)
 *
 * Space Complexity:
 * O(n)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Map to store the sorted "signature" as the key and the list of original anagrams as the value.
        HashMap<String, List<String>> map = new HashMap<>();
        // Sorted-character signature ensures all anagrams map to the same key.
        for(int i = 0; i < strs.length; i++){
            String s1 = strs[i];
            // Convert string to char array because Strings are immutable and cannot be sorted in-place.
            char[] arr = s1.toCharArray();
            // Sorting is the transformation that makes all anagrams identical.
            Arrays.sort(arr);
            // Create a new string from the sorted array to use as a hashable key.
            String str = new String(arr);

            // If this specific character combination hasn't been seen, create a new group.
            if(!map.containsKey(str)){
                map.put(str, new ArrayList<>());
            }
            // Add the original, unsorted string to its corresponding anagram group.
            map.get(str).add(s1);
        }
        // Return all the grouped lists stored in the map values.
        return new ArrayList<>(map.values());
    }
}
