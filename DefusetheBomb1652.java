/*
 * Problem Reference: LeetCode 1652 - Defuse the Bomb
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Use circular window sums for each index depending on sign of k.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n*|k|) or O(n) with rolling window
 *
 * Space Complexity:
 * O(n) output
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
public class DefusetheBomb1652 {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];
        if (k == 0) {
            return res;
        } else if (k > 0) {
            // Iterate through the active search space while maintaining the intended invariant.
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int m = 1; m <= k; m++) {
                    sum += code[(i + m) % n];
                }
                res[i] = sum;
            }
            return res;
        } else {
            k = -k;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int m = 1; m <= k; m++) {
                    sum += code[(i - m + n) % n];
                }
                res[i] = sum;
            }
            return res;
        }
    }
}