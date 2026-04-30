/*
 * Problem: 451. Sort Characters By Frequency
 *
 * Problem Statement:
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 *
 * Intuition:
 * To sort by frequency, we first need to know the frequency of every character.
 * A HashMap is ideal for counting. Once we have counts, we can sort the unique
 * characters using a custom comparator that references these counts.
 *
 * Approach:
 * 1. Count frequencies of each character using a HashMap<Character, Integer>.
 * 2. Create a list of the unique characters (keys from the map).
 * 3. Sort the list of characters in descending order based on their values in the map.
 * 4. Iterate through the sorted list and append each character to a StringBuilder
 *    the number of times it appeared in the original string.
 *
 * Time Complexity: O(N + K log K) where N is the length of the string and K is the number 
 * of unique characters. Since K is limited by the alphabet size (e.g., 128 or 256), 
 * this is often considered O(N).
 * Space Complexity: O(N) to store the frequency map and the final result string.
 *
 * Edge Cases:
 * - Empty string: Map will be empty, loop won't run, returns empty string.
 * - Single character: Map size 1, returns same character.
 * - All characters same frequency: Order between them doesn't matter.
 *
 * Dry Run:
 * Input: "tree"
 * 1. Map: {t:1, r:1, e:2}
 * 2. Keys: [t, r, e]
 * 3. Sorted Keys: [e, t, r] (because map.get('e')=2 is > map.get('t')=1)
 * 4. Result: "ee" + "t" + "r" = "eetr"
 *
 * Correctness Check:
 * The solution is correct. Note that the class 'sortbynum' is defined but never used 
 * in the main method; the sorting is handled by a lambda expression instead.
 */
import java.util.*;

class sortbynum implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        return a - b;
    }
}

public class SortCharactersByFrequency451 {
    public static void main(String[] args) {
        String s = "tree";
        Map<Character, Integer> map = new HashMap<>();
        
        // Step 1: Build a frequency map of all characters in the string
        for (char x : s.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        
        System.out.println(map.entrySet());
        
        // Step 2: Extract the unique characters (keys) into a list for sorting
        List<Character> chars = new ArrayList<>(map.keySet());
        
        // Step 3: Sort the characters based on their frequency in the map
        // (b - a) results in a descending order (highest frequency first)
        chars.sort((a, b) -> map.get(b) - map.get(a));
        
        StringBuilder res = new StringBuilder();
        
        // Step 4: Reconstruct the string by appending each character 
        // according to its original frequency count
        for (char x : chars) {
            int n = map.get(x);
            for (int i = 0; i < n; i++) {
                res.append(x);
            }
        }
        
        System.out.println(res.toString());
    }
}
