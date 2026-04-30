/*
 * Problem: LeetCode 28 - Find the Index of the First Occurrence in a String
 *
 * Problem Statement:
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, 
 * or -1 if needle is not part of haystack.
 *
 * Intuition:
 * The algorithm performs a linear scan of the haystack. When characters match the needle, it advances 
 * a secondary pointer. If a mismatch occurs, it "backtracks" the haystack pointer to the position 
 * immediately following the start of the previous (failed) match attempt to try again.
 *
 * Approach:
 * 1. Maintain a pointer `i` for the haystack and `nindex` for the needle.
 * 2. If characters at both pointers match, increment `nindex` to check the next character of the needle.
 * 3. If they don't match, reset `i` to the index where the current match attempt started (`i - nindex`), 
 *    allowing the loop's increment to move it to the next starting position. Reset `nindex` to 0.
 * 4. If `nindex` reaches the length of the needle, a full match is found.
 *
 * Time Complexity: O(N * M) where N is the length of haystack and M is the length of needle. 
 * In the worst case, we might backtrack and re-examine characters multiple times.
 * Space Complexity: O(1) as we only use a constant amount of extra space for integer variables.
 *
 * Edge Cases:
 * - Needle is longer than haystack: Returns -1.
 * - Needle is empty: The behavior depends on the loop, but typically returns 0 in standard implementations.
 * - No match found: Returns -1.
 *
 * Dry Run:
 * haystack = "mississippi", needle = "issip"
 * 1. i=0 ('m'), nindex=0 ('i'): No match. i = 0-0=0. nindex=0. Loop i++ -> i=1.
 * 2. i=1 ('i'), nindex=0 ('i'): Match. nindex=1.
 * 3. i=2 ('s'), nindex=1 ('s'): Match. nindex=2.
 * 4. i=3 ('s'), nindex=2 ('s'): Match. nindex=3.
 * 5. i=4 ('i'), nindex=3 ('i'): Match. nindex=4.
 * 6. i=5 ('s'), nindex=4 ('p'): Mismatch! i = 5-4=1. nindex=0. Loop i++ -> i=2.
 * 7. Search restarts from haystack index 2.
 *
 * Correctness Check:
 * The solution is correct for a brute-force approach. Note that resetting `i = i - nindex` 
 * is the key to the "sliding window" behavior where the window only moves forward by 1 
 * after a failed match.
 */
public class Index_of_string {
    public int strStr(String haystack, String needle) {
        int hlen = haystack.length();
        int nlen = needle.length();
        int nindex = 0;
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < hlen; i++) {
            // as long as the characters are equal increment nindex
            if (haystack.charAt(i) == needle.charAt(nindex)) {
                // Potential match in progress; move to the next character in the needle
                nindex++;
            } else {
                // start from the next index of the previous start index
                // This backtracks the haystack pointer 'i' to the start of the current 
                // matching attempt. The 'i++' in the for-loop will then move it to 
                // the very next character to start a fresh comparison.
                i = i - nindex;
                // needle should start from index 0
                nindex = 0;
            }
            // check if nindex reached needle length
            if (nindex == nlen) {
                // Success: return the starting index of the match.
                // Since 'i' is currently at the end of the match, we subtract 
                // (needle length - 1) to get the start.
                return i - nlen + 1;
            }
        }
        // If the loop finishes without returning, the needle was not found.
        return -1;
    }
}
