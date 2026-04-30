/*
 * Problem: 17. Letter Combinations of a Phone Number
 *
 * Problem Statement:
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations 
 * that the number could represent. A mapping of digits to letters (just like on the telephone 
 * buttons) is provided.
 *
 * Intuition:
 * This is a combinatorial search problem. Each digit represents a decision point with 3 or 4 
 * possible branches. To find all possible combinations, we need to explore every possible 
 * path from the first digit to the last, which is a classic application for backtracking (DFS).
 *
 * Approach:
 * 1. Create a static mapping (array or map) where the index corresponds to the digit.
 * 2. Handle the edge case where the input is empty by returning an empty list.
 * 3. Use a recursive helper function to build combinations character by character.
 * 4. In each recursive call, identify the letters associated with the current digit.
 * 5. Iterate through those letters, append one to the current path, and move to the next digit.
 * 6. When the path length equals the input length, a valid combination is formed.
 *
 * Time Complexity: O(4^N * N)
 * - N is the length of the input string.
 * - In the worst case (digits 7 or 9), there are 4 choices per digit.
 * - There are at most 4^N total combinations.
 * - String concatenation/creation in the base case takes O(N) time.
 *
 * Space Complexity: O(N)
 * - The recursion stack depth is equal to the number of digits (N).
 * - Note: The space for the output list is O(4^N * N), but usually, output space is not 
 *   counted towards auxiliary space complexity.
 *
 * Edge Cases:
 * - Empty string: The problem specifies returning an empty list, not a list containing an empty string.
 *
 * Dry Run:
 * digits = "23"
 * helper(index 0, "") -> digit '2' maps to "abc"
 *   - pick 'a', helper(index 1, "a") -> digit '3' maps to "def"
 *     - pick 'd', helper(index 2, "ad") -> index == length, add "ad"
 *     - pick 'e', helper(index 2, "ae") -> index == length, add "ae"
 *     - pick 'f', helper(index 2, "af") -> index == length, add "af"
 *   - pick 'b', helper(index 1, "b") ... adds "bd", "be", "bf"
 *   - pick 'c', helper(index 1, "c") ... adds "cd", "ce", "cf"
 *
 * Correctness Check:
 * The solution correctly uses recursion to simulate nested loops of variable depth. 
 * Using string concatenation `current + letters.charAt(i)` creates a new string for 
 * each call, which implicitly handles backtracking (the original `current` remains 
 * unchanged for the next iteration of the loop).
 */

import java.util.*;

public class LetterCombinationsofaPhoneNumber17 {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        // Requirement: If input is empty, return an empty list.
        if (digits.length() == 0)
            return res;
            
        // Mapping index to letters: 0 and 1 are placeholders as digits start from 2.
        String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        
        // Start the recursive process from the first digit (index 0).
        helper(res, digits, "", 0, mapping);
        return res;
    }

    private void helper(List<String> res, String digits, String current, int index, String[] mapping) {
        // Base Case: If the current combination length matches the input length, we are done with this path.
        if (index == digits.length()) {
            res.add(current);
            return;
        }
        
        // Convert the character digit (e.g., '2') to its integer value (2) to index the mapping array.
        String letters = mapping[digits.charAt(index) - '0'];
        
        // Standard Backtracking/DFS: Try every possible letter for the current digit.
        for (int i = 0; i < letters.length(); i++) {
            // Recurse to the next digit (index + 1).
            // Note: String concatenation creates a new String object, so we don't need 
            // to manually "undo" the choice (backtrack) like we would with a StringBuilder.
            helper(res, digits, current + letters.charAt(i), index + 1, mapping);
        }
    }
}
