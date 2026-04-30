/*
 * Problem: 392. Is Subsequence
 *
 * Problem Statement:
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise. 
 * A subsequence is formed from the original string by deleting zero or more characters 
 * without disturbing the relative positions of the remaining characters.
 *
 * Intuition:
 * Since the relative order of characters in s must be preserved in t, we can use a 
 * greedy two-pointer approach. We scan t from left to right, matching characters 
 * of s as soon as they appear. If we successfully match every character of s 
 * before reaching the end of t, s is a subsequence.
 *
 * Approach:
 * 1. Initialize two pointers: s1 for string s and s2 for string t.
 * 2. Iterate through t using s2.
 * 3. Whenever s.charAt(s1) matches t.charAt(s2), increment s1 to look for the next character.
 * 4. Always increment s2 to continue scanning through the target string t.
 * 5. If s1 reaches the length of s, it means all characters were found in order.
 *
 * Time Complexity: O(N), where N is the length of string t. We traverse t at most once.
 * Space Complexity: O(1), as we only use a few integer variables for pointers and lengths.
 *
 * Edge Cases:
 * - s is empty: Returns true (an empty string is a subsequence of any string).
 * - t is empty: Returns false (unless s is also empty).
 * - s is longer than t: Returns false (s1 will never reach n1).
 *
 * Dry Run:
 * s = "abc", t = "ahbgdc"
 * - s1=0, s2=0: s[0]='a', t[0]='a' (Match!) -> s1=1, s2=1
 * - s1=1, s2=1: s[1]='b', t[1]='h' (No match) -> s2=2
 * - s1=1, s2=2: s[1]='b', t[2]='b' (Match!) -> s1=2, s2=3
 * - s1=2, s2=3: s[2]='c', t[3]='g' (No match) -> s2=4
 * - s1=2, s2=4: s[2]='c', t[4]='d' (No match) -> s2=5
 * - s1=2, s2=5: s[2]='c', t[5]='c' (Match!) -> s1=3, s2=6
 * - Loop ends: s1 (3) == n1 (3) -> returns true.
 *
 * Correctness Check:
 * The solution is correct. The greedy approach is optimal for this problem because 
 * matching a character as early as possible in t leaves the maximum number of 
 * remaining characters in t to match the rest of s.
 */

public class IsSubsequence392 {
    public boolean isSubsequence(String s, String t) {
        // s1 tracks our progress in the candidate subsequence s.
        // s2 tracks our current position in the target string t.
        int s1 = 0, s2 = 0, n1 = s.length(), n2 = t.length();
        
        // We continue as long as there are characters left to find in s 
        // and characters left to scan in t.
        while (s1 < n1 && s2 < n2) {
            // If the current character in s matches the current character in t,
            // we have found a match for the character at s1.
            if (s.charAt(s1) == t.charAt(s2)) {
                // Move to the next character in s that we need to find.
                s1++;
            }
            // Regardless of a match, we must move forward in t to find subsequent characters.
            s2++;
        }
        
        // If s1 has reached the length of s, it means every character in s 
        // was found in t in the correct relative order.
        return s1 == n1 ? true : false;
    }
}
