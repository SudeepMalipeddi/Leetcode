/*
 * Problem: 409. Longest Palindrome
 *
 * Problem Statement:
 * Given a string s which consists of lowercase or uppercase letters, return the length of the 
 * longest palindrome that can be built with those letters. Letters are case sensitive.
 *
 * Intuition:
 * A palindrome is symmetric. To maximize its length, we need to use characters in pairs 
 * (one for the left side, one for the right). If a character appears an odd number of times, 
 * we can use all but one of its occurrences to form pairs. Finally, a single character 
 * (any character with at least one remaining instance) can be placed in the center.
 *
 * Approach:
 * 1. Count the frequency of each character using an array.
 * 2. Iterate through the frequencies to calculate the usable "even" parts.
 * 3. If a character has an even frequency, add the whole count to the total.
 * 4. If a character has an odd frequency, add (count - 1) to the total and set a flag 
 *    indicating an odd character is available for the center.
 * 5. Return the total count plus 1 if the "odd" flag is set.
 *
 * Time Complexity: O(n) where n is the length of the string. We iterate through the string 
 * once to count frequencies and then iterate through a fixed-size array.
 * Space Complexity: O(1) because the frequency array size is constant (50), regardless of input size.
 *
 * Edge Cases:
 * - String with length 1: Returns 1.
 * - String with all unique characters: Returns 1.
 * - String with all even counts: Returns the length of the string.
 *
 * Dry Run:
 * Input: "abccccdd"
 * Counts: a:1, b:1, c:4, d:2
 * - 'a': count=0, odd=1 (frequency 1)
 * - 'b': count=0, odd=1 (frequency 1)
 * - 'c': count=4, odd=1 (frequency 4 is even)
 * - 'd': count=4+2=6, odd=1 (frequency 2 is even)
 * Result: 6 + 1 = 7
 *
 * Correctness Check:
 * CRITICAL BUG: The array size is 50. The range from 'A' (65) to 'z' (122) is 58 characters. 
 * Accessing 'c - 'A'' for lowercase letters like 'p' through 'z' will cause an 
 * ArrayIndexOutOfBoundsException. The logic for the palindrome calculation is correct, 
 * but the memory allocation for the frequency map is insufficient for standard English letters.
 */

public class LongestPalindrome409 {
    public int longestPalindrome(String s) {
        // Array to store character frequencies. Note: size 50 is insufficient for all A-z characters.
        int[] arr = new int[50];
        int count = 0, odd = 0;
        
        // Build frequency counts for each character bucket used by this implementation.
        for (char c : s.toCharArray()) {
            // Map character to index. This will crash for characters with ASCII > 114.
            arr[c - 'A']++;
        }
        
        // Convert frequencies into maximum palindrome length contribution.
        for (int c : arr) {
            // Even frequency can be used completely because every character has a matching pair.
            if (c % 2 == 0 && c != 0) {
                count += c;
            // For odd frequency > 1, use the largest even portion (c-1) and reserve a possible center.
            } else if (c % 2 == 1 && c != 1) {
                count += c - 1;
                odd = 1;
            // A single leftover odd character can still be the center of the palindrome.
            } else if (c == 1) {
                odd = 1;
            }
        }
        
        // The final length is the sum of all pairs plus at most one character in the middle.
        return count + odd;
    }
}
