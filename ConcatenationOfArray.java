/*
 * Problem: LeetCode 1929 - Concatenation of Array
 * Problem Statement: Given an integer array nums, return an array ans where
 *   ans is formed by concatenating nums with itself.
 * Intuition: The output length is 2n, and both halves are identical to nums.
 * Approach:
 *   1) Allocate a new array of size 2 * n.
 *   2) Copy nums into the first half and again into the second half.
 * Time Complexity: O(n) for the copy.
 * Space Complexity: O(n) for the new array.
 * Edge Cases: Empty array, single element.
 * Dry Run: nums=[1,2,1] -> output=[1,2,1,1,2,1].
 * Correctness Check: Both halves are exact copies of nums by construction.
 */
public class ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        int[] output = new int[2 * nums.length];
        System.arraycopy(nums, 0, output, 0, nums.length); // first copy
        System.arraycopy(nums, 0, output, nums.length, nums.length); // second copy
        return output;
    }
}
