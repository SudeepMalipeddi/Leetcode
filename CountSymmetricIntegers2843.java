/*
 * Problem Reference: LeetCode 2843 - Count Symmetric Integers
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Iterate through range, split digits into halves, and compare half sums.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O((high-low+1)*d)
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
public class CountSymmetricIntegers2843 {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = low; i <= high; i++) {
            String curr = String.valueOf(i);
            int n = curr.length();
            if (n % 2 != 0) {
                continue;
            }
            int res1 = 0;
            int res2 = 0;
            for (int j = 0; j < n / 2; j++) {
                res1 += (curr.charAt(j) - '0');
                res2 += (curr.charAt(n / 2 + j) - '0');
            }
            if (res1 == res2) {
                count++;
            }
        }
        return count;
    }
}
