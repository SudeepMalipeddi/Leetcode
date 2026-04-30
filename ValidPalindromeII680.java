/*
 * Problem: 680. Valid Palindrome II
 *
 * Problem Statement:
 * Given a string s, return true if the string can be a palindrome after deleting at most one character from it.
 *
 * Intuition:
 * A palindrome reads the same forwards and backwards. We use a two-pointer approach to compare characters 
 * from both ends. If we encounter a mismatch, we have exactly one "life" (one deletion) to use. We can 
 * either skip the character at the left pointer or the character at the right pointer and check if 
 * the remaining substring is a strict palindrome.
 *
 * Approach:
 * 1. Initialize two pointers: 'left' at the start (0) and 'right' at the end (length - 1).
 * 2. Move pointers toward the center as long as characters match.
 * 3. Upon the first mismatch, branch into two checks:
 *    a. Check if the substring from left + 1 to right is a palindrome.
 *    b. Check if the substring from left to right - 1 is a palindrome.
 * 4. If either branch is true, the original string is valid under the "at most one deletion" rule.
 *
 * Time Complexity: O(N), where N is the length of the string. In the worst case, we traverse the 
 * string once in the main loop and once more in the helper function.
 * Space Complexity: O(1), as we only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - String is already a palindrome: The loop completes, and we return true.
 * - Mismatch at the very beginning or very end.
 * - String with only two characters: Always returns true (deleting one leaves a single char).
 *
 * Dry Run:
 * s = "abca"
 * 1. left=0 ('a'), right=3 ('a'). Match. left=1, right=2.
 * 2. left=1 ('b'), right=2 ('c'). Mismatch!
 * 3. Call isPalindrome(s, 2, 2) [substring "c"] OR isPalindrome(s, 1, 1) [substring "b"].
 * 4. Both helper calls return true. Result: true.
 *
 * Correctness Check:
 * The solution is correct. It efficiently handles the single-deletion constraint by using a 
 * greedy approach until a mismatch occurs, then checking both possible recovery paths.
 */

import java.util.LinkedList;

class ValidPalindromeII680 {
    public boolean validPalindrome(String s) {
        // Initialize pointers at the boundaries of the string
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // If characters at current pointers don't match, we must attempt a deletion
            if (s.charAt(left) != s.charAt(right)) {
                // We try skipping the left character OR skipping the right character.
                // If either resulting substring is a palindrome, the whole string is valid.
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        // If the loop finishes without a mismatch, it's already a palindrome
        return true;
    }

    /**
     * Helper method to check if a substring is a strict palindrome.
     * This does not allow for any further deletions.
     */
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
    
}
