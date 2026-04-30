/*
 * Problem: LeetCode 334 - Increasing Triplet Subsequence
 *
 * Problem Statement:
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) 
 * such that i < j < k and nums[i] < nums[j] < nums[k]. Otherwise, return false.
 *
 * Intuition:
 * The goal is to find three numbers in increasing order. We can achieve this by keeping track 
 * of the two smallest candidates for the first and second positions of the triplet. 
 * If we find any number larger than both, we have successfully completed a triplet.
 *
 * Approach:
 * 1. Initialize two variables, 'smol' and 'big', to infinity. These represent the 
 *    smallest and second-smallest elements found so far that could start a triplet.
 * 2. Iterate through the array:
 *    a. If the current number is less than or equal to 'smol', update 'smol'.
 *    b. Else if the current number is less than or equal to 'big', update 'big'.
 *    c. Else, the current number is greater than both 'smol' and 'big', meaning 
 *       we found our third element. Return true.
 * 3. If the loop finishes without returning true, no such triplet exists.
 *
 * Time Complexity: O(n) - We perform a single linear scan through the input array.
 * Space Complexity: O(1) - We only use two integer variables regardless of input size.
 *
 * Edge Cases:
 * - Array length < 3: The loop will finish and return false (correct).
 * - Strictly decreasing: 'smol' will keep updating, 'big' stays infinity, returns false.
 * - All elements same: 'num <= smol' will always be true, returns false.
 *
 * Dry Run:
 * Input: [2, 1, 5, 0, 6]
 * 1. num = 2: smol = 2, big = inf
 * 2. num = 1: smol = 1, big = inf
 * 3. num = 5: smol = 1, big = 5
 * 4. num = 0: smol = 0, big = 5 (Note: 5 still "remembers" it was preceded by 1)
 * 5. num = 6: 6 > big (5), return true.
 *
 * Correctness Check:
 * The solution is correct. Even if 'smol' is updated to a value that appears after 'big' 
 * (like in the dry run), 'big' only became a non-infinity value because there was 
 * *some* value before it that was smaller. Thus, any number > 'big' completes a valid triplet.
 */
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
        // smol: smallest value found so far.
        // big: smallest value found so far that is greater than some previous value.
        int smol = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        // Iterate through the active search space while maintaining the intended invariant.
        for (int num : nums) {
            // Important guard: this branch handles a boundary or constraint-critical condition.
            // If current is smaller than our smallest, update the floor.
            if (num <= smol) {
                smol = num;
            } 
            // If current is larger than smol but smaller than big, update the second element.
            // This tightens the window for the third element to be found.
            else if (num <= big) {
                big = num;
            } 
            // If we reach here, num > big AND big > some previous smol. Triplet found.
            else
                return true;
        }
        return false;
    }
}
