/*
 * Problem Statement: Given a string, return the maximum possible length of a palindrome that can be built using its characters.
 * Intuition: Use all even-frequency characters fully, and for odd-frequency characters use the largest even part plus at most one odd center.
 * Approach: Count character frequencies, add full even counts, add (count - 1) for odd counts, and track whether one odd center can be placed.
 * Time Complexity: O(n + A), where n is string length and A is the frequency-array size used by this implementation.
 * Space Complexity: O(A) for the frequency array.
 * Edge Cases handled: Single-character strings, all-even counts, all-odd counts, and mixed frequencies.
 * Dry Run: For "abccccdd" -> counts: a1 b1 c4 d2, take 4 + 2 + one odd center = 7.
 * LeetCode matching/assumption: Matches LeetCode 409 logic; current indexing uses c - 'A' with array size 50.
 * Correctness Check: Important note: this frequency array is unsafe for full ASCII/Unicode and can index out of bounds for many characters; LeetCode-safe alternatives usually use size 58/128/256 or a map.
 */

public class LongestPalindrome409 {
    public int longestPalindrome(String s) {
        int[] arr = new int[50];
        int count = 0, odd = 0;
        // Build frequency counts for each character bucket used by this implementation.
        for (char c : s.toCharArray()) {
            arr[c - 'A']++;
        }
        // Convert frequencies into maximum palindrome length contribution.
        for (int c : arr) {
            // Even frequency can be used completely.
            if (c % 2 == 0 && c != 0) {
                count += c;
            // For odd frequency > 1, use the even portion and reserve a possible center.
            } else if (c % 2 == 1 && c != 1) {
                count += c - 1;
                odd = 1;
            // A single leftover odd character can still be the center.
            } else if (c == 1) {
                odd = 1;
            }
        }
        return count + odd;
    }
}
