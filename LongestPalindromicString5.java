/*
 * Problem: 5. Longest Palindromic Substring
 *
 * Problem Statement:
 * Given a string s, return the longest palindromic substring in s.
 * A palindrome is a string that reads the same forward and backward.
 *
 * Intuition:
 * A palindrome mirrors around its center. A string of length N has 2N-1 possible centers:
 * N centers at each character (for odd-length palindromes like "aba") and N-1 centers 
 * between characters (for even-length palindromes like "abba"). By expanding outwards 
 * from each possible center, we can find the maximum length palindrome.
 *
 * Approach:
 * 1. Iterate through the string, treating each index 'i' as a potential center.
 * 2. For each 'i', perform two expansions: one for odd lengths (center is i) and one for even lengths (center is i and i+1).
 * 3. In the expansion helper, move two pointers (left and right) outwards as long as the characters match.
 * 4. Update the global maximum length and starting position whenever a longer palindrome is found.
 * 5. Return the substring using the stored start index and length.
 *
 * Time Complexity: O(N^2)
 * Each expansion can take O(N) time, and we perform this for each of the N characters.
 *
 * Space Complexity: O(1)
 * We only store a few integer variables (lo, maxLen) regardless of input size.
 *
 * Edge Cases:
 * - String length < 2: The string itself is the longest palindrome.
 * - String with all identical characters: Expansion will go to the full length.
 * - No palindromes longer than 1: The first character will be returned.
 *
 * Dry Run:
 * Input: "babad"
 * i=0: extend(0,0) -> "b" (len 1); extend(0,1) -> "" (len 0). maxLen=1, lo=0.
 * i=1: extend(1,1) -> "bab" (len 3); extend(1,2) -> "" (len 0). maxLen=3, lo=0.
 * i=2: extend(2,2) -> "aba" (len 3); extend(2,3) -> "" (len 0). maxLen=3 (no update).
 * Result: s.substring(0, 0+3) -> "bab"
 *
 * Correctness Check:
 * The solution is correct. Note that using instance variables (lo, maxLen) makes the class 
 * stateful; in a production multi-threaded environment, these should be passed as an 
 * array or object to ensure thread safety.
 */

public class LongestPalindromicString5 {
	// lo tracks the starting index of the longest palindrome found
	// maxLen tracks the length of the longest palindrome found
	private int lo, maxLen;

	public String longestPalindrome(String s) {
		int len = s.length();
		// Strings of length 0 or 1 are already palindromes.
		if (len < 2)
			return s;

		// Iterate through the string to treat every index as a potential center.
		// We stop at len - 1 because the even-length check (i, i+1) handles the last boundary.
		for (int i = 0; i < len - 1; i++) {
			// Case 1: Odd length palindrome (e.g., "aba", center is 'b')
			extendPalindrome(s, i, i); 
			// Case 2: Even length palindrome (e.g., "abba", center is between 'b' and 'b')
			extendPalindrome(s, i, i + 1); 
		}
		// Extract the result using the global pointers updated during the loops.
		return s.substring(lo, lo + maxLen);
	}

	private void extendPalindrome(String s, int j, int k) {
		// Expand outward from the center (j, k) as long as:
		// 1. Pointers are within string boundaries.
		// 2. Characters at pointers match (maintaining palindrome property).
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		
		// After the loop, j and k have moved one step past the valid palindrome boundaries.
		// The valid palindrome is from index (j + 1) to (k - 1).
		// Length = (k - 1) - (j + 1) + 1 = k - j - 1.
		if (maxLen < k - j - 1) {
			lo = j + 1; // Start of the valid palindrome
			maxLen = k - j - 1; // Length of the valid palindrome
		}
	}
}
