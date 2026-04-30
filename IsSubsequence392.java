/*
 * Problem Statement: Determine whether string s is a subsequence of string t (all chars of s appear in order in t).
 * Intuition: Scan t once and greedily match the next needed character from s whenever possible.
 * Approach: Two pointers: s1 tracks matched prefix in s, s2 scans t; increment s1 only on character match.
 * Time Complexity: O(|t|) in worst case because s2 moves forward at most once per character of t.
 * Space Complexity: O(1) because only indices and lengths are stored.
 * Edge Cases handled: Empty s returns true (s1==n1 initially); empty t with non-empty s returns false.
 * Dry Run: s="abc", t="ahbgdc": match a at t[0], b at t[2], c at t[5] => s1 reaches 3, return true.
 * LeetCode matching: Matches LeetCode 392 (Is Subsequence).
 * Correctness Check: Two-pointer invariant is sound; no correctness issue observed.
 */

public class IsSubsequence392 {
    public boolean isSubsequence(String s, String t) {
        // s1 -> pointer in s, s2 -> pointer in t.
        int s1 = 0, s2 = 0, n1 = s.length(), n2 = t.length();
        // Stop when either s is fully matched or t is exhausted.
        while (s1 < n1 && s2 < n2) {
            // Consume one char from s only when current chars align.
            if (s.charAt(s1) == t.charAt(s2)) {
                s1++;
            }
            // Always advance through t.
            s2++;
        }
        // All characters of s must be matched in sequence.
        return s1 == n1 ? true : false;
    }
}
