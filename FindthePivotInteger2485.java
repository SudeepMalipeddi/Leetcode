/*
 * Problem Reference: LeetCode 2485 - Find the Pivot Integer
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Compare prefix sum and suffix sum around each candidate pivot.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n) or O(1) formula depending on implementation
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
public class FindthePivotInteger2485 {
    public int pivotInteger(int n) {
        if (n == 1) {
            return 1;
        }
        int res = -1;
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = n / 2; i < n; i++) {
            int sumb = ((i) * (i + 1)) / 2;
            int num = n - i + 1;
            int sumf = (num) * (i) + (num - 1) * (num) / 2;
            if (sumb == sumf) {
                res = i;
            }
        }
        return res;
    }
}
