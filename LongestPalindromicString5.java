/*
 * Problem Statement: Return the longest palindromic substring of the given string.
 * Intuition: Every palindrome is centered either at one character (odd length) or between two characters (even length).
 * Approach: For each index, expand around both center types and store the best [start, length] seen so far.
 * Time Complexity: O(n^2) in the worst case (for example, strings like "aaaaa...").
 * Space Complexity: O(1) extra space.
 * Edge Cases handled: Empty/single-character strings, fully palindromic strings, and ties where earliest best is retained by update condition.
 * Dry Run: For "babad", centers at index 1 and 2 expand to "bab"/"aba", maxLen becomes 3 and substring is returned.
 * LeetCode matching/assumption: Matches LeetCode 5 center-expansion strategy.
 * Correctness Check: Expansion stops exactly when bounds fail or characters mismatch, so recorded window always remains a valid palindrome.
 */

public class LongestPalindromicString5 {
	private int lo, maxLen;

	public String longestPalindrome(String s) {
		int len = s.length();
		// Strings of length 0 or 1 are already palindromes.
		if (len < 2)
			return s;

		// Try each index as odd and even palindrome center.
		for (int i = 0; i < len - 1; i++) {
			extendPalindrome(s, i, i); // Odd-length center.
			extendPalindrome(s, i, i + 1); // Even-length center.
		}
		return s.substring(lo, lo + maxLen);
	}

	private void extendPalindrome(String s, int j, int k) {
		// Expand outward while characters mirror each other.
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		// Update best window using final expanded bounds.
		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}
}
