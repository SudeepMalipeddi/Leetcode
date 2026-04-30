/*
 * Problem Statement:
 * Given two strings word1 and word2, build a new string by alternating characters
 * from each word, then append leftover characters from the longer word.
 *
 * Intuition:
 * Walk with one index i and append from each word when that index is valid.
 *
 * Approach:
 * Use StringBuilder for efficient appends.
 * While either word still has characters at i:
 * - append word1[i] if available,
 * - append word2[i] if available,
 * then increment i.
 *
 * Time Complexity:
 * O(m + n), every character appended once.
 *
 * Space Complexity:
 * O(m + n) for output builder (required for result string).
 *
 * Edge Cases handled:
 * - One word empty.
 * - Different lengths (remaining suffix appended automatically via checks).
 *
 * Dry Run:
 * word1="abc", word2="pqrstu"
 * append sequence: a,p,b,q,c,r then s,t,u -> "apbqcrstu"
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 1768 alternating-append solution.
 *
 * Correctness Check:
 * Each index contributes at most one char from each word in order, preserving alternation.
 */

public class MergeStringsAlternatively1768 {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder res = new StringBuilder();
        int i = 0;

        // Continue while at least one word still has a character at position i.
        while (i < word1.length() || i < word2.length()) {
            if (i < word1.length()) {
                res.append(word1.charAt(i)); // Append from first word when available.
            }
            if (i < word2.length()) {
                res.append(word2.charAt(i)); // Append from second word when available.
            }
            i++;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String word1 = "abc";
        String word2 = "pqrstuz";
        MergeStringsAlternatively1768 test = new MergeStringsAlternatively1768();
        System.out.println(test.mergeAlternately(word1, word2));
    }
}
