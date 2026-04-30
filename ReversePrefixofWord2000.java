/*
 * Problem: 2000. Reverse Prefix of Word
 *
 * Problem Statement:
 * Given a 0-indexed string 'word' and a character 'ch', reverse the segment of 'word' 
 * that starts at index 0 and ends at the index of the first occurrence of 'ch' (inclusive).
 * If the character 'ch' does not exist in 'word', return the original string.
 *
 * Intuition:
 * The problem requires identifying a specific "pivot" point (the first occurrence of 'ch').
 * Once found, the string is split into two parts: the prefix (to be reversed) and 
 * the suffix (to be kept as is).
 *
 * Approach:
 * 1. Iterate through the string to find the first index 'i' where word.charAt(i) == ch.
 * 2. If found, store this index as 'right'. If not found, 'right' remains 0 by default.
 * 3. Use a StringBuilder to construct the new string by iterating backwards from 'right' to 0.
 * 4. Append the remaining part of the string (from 'right + 1' to the end) to the result.
 *
 * Time Complexity: O(N) where N is the length of the string. We perform one pass to find 
 * the character and another pass to build the reversed prefix and append the suffix.
 * Space Complexity: O(N) to store the characters in the StringBuilder for the result string.
 *
 * Edge Cases:
 * - 'ch' is not present in 'word': 'right' remains 0. The code reverses index 0 and 
 *   appends the rest, effectively returning the original string.
 * - 'ch' is the first character: 'right' is 0, prefix of length 1 is "reversed", returning original.
 * - 'ch' is the last character: The entire string is reversed.
 *
 * Dry Run:
 * word = "abcdefd", ch = 'd'
 * 1. Loop finds 'd' at index 3. right = 3.
 * 2. StringBuilder appends characters at indices 3, 2, 1, 0: 'd', 'c', 'b', 'a'.
 * 3. StringBuilder appends substring from index 4 (right + 1) to end: "efd".
 * 4. Result: "dcbaefd".
 *
 * Correctness Check:
 * The solution is correct. Note that if 'ch' is not found, the logic defaults to 
 * returning the original string because 'right' stays 0.
 */
public class ReversePrefixofWord2000 {
    public static String reversePrefix(String word, char ch) {
        int left = 0;
        int right = 0;
        
        // Find the first occurrence of the character 'ch'
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                right = i;
                // We only care about the first occurrence, so we break early
                break;
            }
        }
        
        // StringBuilder is used for efficient string construction as Strings are immutable in Java
        StringBuilder sb = new StringBuilder();
        
        // Reverse the prefix from index 'right' down to 'left' (0)
        for (int i = right; i >= left; i--) {
            sb.append(word.charAt(i));
        }
        
        // Append the remaining part of the string that was not part of the prefix
        sb.append(word.substring(right + 1));
        
        return sb.toString();
    }
}
