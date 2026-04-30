/*
 * Problem Reference: LeetCode 2185 - Counting Words With a Given Prefix
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Scan words and count matches with startsWith.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total characters)
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
public class CountingWordsWithaGivenPrefix2185 {
    public int countWordsWithGivenPrefix(String[] words, String prefix) {
        int count = 0;
        // Iterate through the active search space while maintaining the intended invariant.
        for (String word : words) {
            if (word.startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    public int countWordsWithGivenPrefix1(String[] words, String prefix) {
        int count = 0;
        for (String word : words) {
            if (word.indexOf(prefix) == 0) {
                count++;
            }
        }
        return count;
    }
}
