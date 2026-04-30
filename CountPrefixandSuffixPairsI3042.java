/*
 * Problem Reference: LeetCode 3042 - Count Prefix and Suffix Pairs I
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Brute-force all i < j pairs and test startsWith/endsWith.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n^2 * m)
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
class CountPrefixandSuffixPairsI3042 {
    public static int countPrefixSuffixPairs(String[] words) {
        int count = 0;

        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String word2 = words[j];
                // Important guard: this branch handles a boundary or constraint-critical condition.
                if (word2.startsWith(word1) && word2.endsWith(word1)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] words = { "a", "aba", "ababa", "aa" };
        System.out.println(countPrefixSuffixPairs(words));
    }
}