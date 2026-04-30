/*
 * Problem Reference: LeetCode 334 - Increasing Triplet Subsequence
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Track smallest and second-smallest seen so far.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
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
public class IncreasingTripletSubsequence334 {
    public boolean increasingTriplet(int[] nums) {
        int smol = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        // Iterate through the active search space while maintaining the intended invariant.
        for (int num : nums) {
            // Important guard: this branch handles a boundary or constraint-critical condition.
            if (num <= smol) {
                smol = num;
            } else if (num <= big) {
                big = num;
            } else
                return true;
        }
        return false;
    }
}
