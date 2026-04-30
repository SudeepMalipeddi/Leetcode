/*
 * Problem: 2914. Minimum Number of Changes to Make Binary String Beautiful
 *
 * Problem Statement:
 * A binary string is "beautiful" if it can be partitioned into one or more substrings
 * of even length, where each substring contains only 1s or only 0s. Given an even-length
 * string s, return the minimum number of changes needed to make it beautiful.
 *
 * Intuition:
 * For a string to be partitionable into even-length uniform blocks, the most basic
 * requirement is that every disjoint pair of characters (s[0], s[1]), (s[2], s[3]), etc.,
 * must consist of identical characters. If every pair is uniform, the entire string
 * is guaranteed to be beautiful. Since we want the minimum changes, we only flip
 * one character in any pair that is currently mismatched (e.g., "01" or "10").
 *
 * Approach:
 * 1. Iterate through the string using a step of 2 to process it as a sequence of pairs.
 * 2. Compare the character at index i-1 with the character at index i.
 * 3. If they are different, increment the result counter.
 *
 * Time Complexity: O(n), where n is the length of the string, as we traverse the string once.
 * Space Complexity: O(1), as we only use a few integer variables regardless of input size.
 *
 * Edge Cases:
 * - String is already beautiful (e.g., "000011"): 0 changes.
 * - String requires maximum changes (e.g., "1010"): n/2 changes.
 * - Minimum length string (length 2): 0 or 1 change.
 *
 * Dry Run:
 * s = "1001", n = 4
 * i = 1: s[0] ('1') != s[1] ('0') -> res = 1
 * i = 3: s[2] ('0') != s[3] ('1') -> res = 2
 * Loop ends. Return 2.
 *
 * Correctness Check:
 * The solution is correct. By ensuring every block of size 2 is uniform, we satisfy
 * the condition for the string to be partitioned into even-length uniform substrings.
 */
/*
Problem Statement:
- Given an even-length binary string, compute the minimum flips so every 2-length block has equal characters.

Intuition:
- Each pair contributes independently: only mismatched pairs need one change.

Approach:
- Scan indices (0,1), (2,3), ... and count mismatches.

Time Complexity:
- O(n) because each character is visited once through paired iteration.

Space Complexity:
- O(1) extra space.

Edge Cases:
- Works for already-beautiful strings and all-0/all-1 inputs.

Dry Run:
- s="0101": pairs (0,1) and (0,1) mismatch twice, so answer is 2.
*/
public class MinimumNumberofChangestoMakeBinaryStringBeautiful2914 {
    public static int minChanges(String s) {
        int res = 0, n = s.length();
        
        // Each adjacent pair is independent, so count only mismatched pairs.
        // We iterate with a step of 2 to examine disjoint blocks of size 2.
        for (int i = 1; i < n; i += 2) {
            
            // If the two characters in the current pair are different, 
            // we must change one of them to make the pair uniform.
            if (s.charAt(i - 1) != s.charAt(i)) {
                res++;
            }
        }
        return res;
    }
}
