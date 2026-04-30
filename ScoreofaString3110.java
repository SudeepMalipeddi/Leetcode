/*
 * Problem: 3110. Score of a String
 *
 * Problem Statement:
 * The score of a string is defined as the sum of the absolute differences between the ASCII values of adjacent characters.
 *
 * Intuition:
 * The problem asks for a cumulative sum of differences between neighbors. Since characters in Java are 
 * represented by their numeric ASCII/Unicode values, subtracting one char from another directly yields 
 * the difference in their positions in the character set.
 *
 * Approach:
 * 1. Initialize an accumulator variable 'score' to zero.
 * 2. Iterate through the string from index 0 up to the second-to-last character (length - 2).
 * 3. For each index i, calculate the absolute difference between the character at i and the character at i + 1.
 * 4. Add the result to the total score and return it after the loop completes.
 *
 * Time Complexity: O(n), where n is the length of the string. We perform a single linear pass through the string.
 * Space Complexity: O(1), as we only use a constant amount of extra space for the score variable regardless of input size.
 *
 * Edge Cases:
 * - Minimum length string (length 2): The loop runs exactly once.
 * - String with identical characters: The score will be 0 as all differences are 0.
 * - Large strings: The maximum difference between two ASCII characters is 127; with standard string length limits, the sum fits within a 32-bit integer.
 *
 * Dry Run:
 * Input: s = "zaz"
 * i = 0: |'z' - 'a'| = |122 - 97| = 25. score = 25.
 * i = 1: |'a' - 'z'| = |97 - 122| = 25. score = 25 + 25 = 50.
 * Result: 50.
 *
 * Correctness Check:
 * The solution is correct. The loop boundary (s.length() - 1) correctly prevents an IndexOutOfBoundsException 
 * when accessing the neighbor at i + 1.
 */
public class ScoreofaString3110 {
    public int scoreOfString(String s) {
        // Initialize the total score accumulator
        int score = 0;
        
        // Iterate until the second-to-last character so that i + 1 always points to a valid index
        for (int i = 0; i < s.length() - 1; i++) {
            // Subtracting chars in Java automatically uses their ASCII values.
            // Math.abs ensures we get the non-negative distance between the characters.
            int cur = Math.abs(s.charAt(i) - s.charAt(i + 1));
            
            // Add the current pair's difference to the running total
            score += cur;
        }
        
        return score;
    }
}
