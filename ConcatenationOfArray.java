/*
 * Problem: 1929. Concatenation of Array
 *
 * Problem Statement:
 * Given an integer array nums of length n, you want to create an array ans of length 2n
 * where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n.
 *
 * Intuition:
 * The goal is to duplicate the input array and append it to itself. Since the output
 * size is fixed at 2 * n, we can pre-allocate the memory and perform two bulk copy operations.
 *
 * Approach:
 * 1. Calculate the length of the input array 'n'.
 * 2. Initialize a new integer array 'output' with size 2 * n.
 * 3. Copy all elements from 'nums' into 'output' starting at index 0.
 * 4. Copy all elements from 'nums' into 'output' again, but starting at index 'n'.
 *
 * Time Complexity: O(n) - We visit each element of the input array to copy it into the new array.
 * Space Complexity: O(n) - We allocate a new array of size 2n to store the result.
 *
 * Edge Cases:
 * - nums.length == 0: The result should be an empty array.
 * - nums.length == 1: The result should be [nums[0], nums[0]].
 *
 * Dry Run:
 * Input: nums = [1, 2, 1]
 * 1. n = 3. output = new int[6] -> [0, 0, 0, 0, 0, 0]
 * 2. First copy: output[0..2] = nums[0..2] -> [1, 2, 1, 0, 0, 0]
 * 3. Second copy: output[3..5] = nums[0..2] -> [1, 2, 1, 1, 2, 1]
 * Return [1, 2, 1, 1, 2, 1]
 *
 * Correctness Check:
 * The solution correctly implements the concatenation logic using built-in efficient array copying.
 */
public class ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        // Create a result array that is exactly double the size of the input.
        int[] output = new int[2 * nums.length];
        
        // System.arraycopy is a highly optimized native method for copying arrays.
        // Parameters: (source, sourceStart, destination, destinationStart, length)
        
        // Copy the original array into the first half of the output array.
        System.arraycopy(nums, 0, output, 0, nums.length); // first copy
        
        // Copy the original array into the second half of the output array.
        // Note the destination offset is nums.length, which starts the copy right after the first half.
        System.arraycopy(nums, 0, output, nums.length, nums.length); // second copy
        
        return output;
    }
}
