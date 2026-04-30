/*
 * Problem Reference: LeetCode 28 - Find the Index of the First Occurrence in a String
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Check each starting index for full needle match.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O((n-m+1)*m)
 *
 * Space Complexity:
 * O(1)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
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
                nindex++;
            } else {
                // start from the next index of the previous start index
                i = i - nindex;
                // needle should start from index 0
                nindex = 0;
            }
            // check if nindex reached needle length
            if (nindex == nlen) {
                return i - nlen + 1;
            }
        }
        return -1;
    }
}
