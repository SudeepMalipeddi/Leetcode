/*
 * Problem: 1913. Maximum Product Difference Between Two Pairs
 *
 * Problem Statement:
 * Given an integer array nums, find four distinct indices (a, b, c, d) such that 
 * the product difference (nums[a] * nums[b]) - (nums[c] * nums[d]) is maximized.
 *
 * Intuition:
 * To maximize the expression (A * B) - (C * D), we need to maximize the minuend (A * B) 
 * and minimize the subtrahend (C * D). Since all numbers in the input are positive 
 * (per LeetCode constraints), this is achieved by multiplying the two largest 
 * numbers and subtracting the product of the two smallest numbers.
 *
 * Approach:
 * 1. Sorting Approach: Sort the array. The two smallest values will be at indices 0 and 1, 
 *    and the two largest will be at the last two indices.
 * 2. Linear Scan: Iterate through the array once while maintaining four variables to 
 *    track the two largest and two smallest values found so far.
 *
 * Time Complexity:
 * - maxProductDifference: O(n log n) due to the sorting algorithm.
 * - maxProductDifference2: O(n) as it requires a single pass through the array.
 *
 * Space Complexity:
 * - O(1) or O(log n) depending on the sorting implementation's stack space. 
 *   The linear scan approach is strictly O(1).
 *
 * Edge Cases:
 * - Minimum array size (n=4): The algorithm must pick all four elements.
 * - Duplicate values: If the two largest values are the same (e.g., [9, 9, 2, 1]), 
 *   the logic must correctly identify both.
 *
 * Dry Run:
 * nums = [5, 6, 2, 7, 4]
 * 1. Maxima: 7, 6 (Product = 42)
 * 2. Minima: 2, 4 (Product = 8)
 * 3. Result: 42 - 8 = 34
 *
 * Correctness Check:
 * The logic is correct. For non-negative integers, the maximum product is always 
 * formed by the two largest elements, and the minimum product by the two smallest.
 */

import java.util.Arrays;

public class MaximumProductDifferenceBetweenTwoPairs1913 {
    public int maxProductDifference(int[] nums) {
        // Sorting places smallest values at the start and largest at the end.
        Arrays.sort(nums);
        int n = nums.length;
        // The difference between the product of the two largest and two smallest.
        return (nums[n - 1] * nums[n - 2]) - (nums[0] * nums[1]);
    }

    public int maxProductDifference2(int[] nums) {
        // Initialize tracking variables to extreme values.
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        // Single pass to find the top two and bottom two values.
        for (int i = 0; i < nums.length; i++) {
            // Update logic for the two largest values.
            if (nums[i] > max1) {
                max2 = max1; // The previous largest becomes the second largest.
                max1 = nums[i];
            } else if (nums[i] > max2) {
                // If current is not larger than max1 but larger than max2.
                max2 = nums[i];
            }

            // Update logic for the two smallest values.
            if (nums[i] < min1) {
                min2 = min1; // The previous smallest becomes the second smallest.
                min1 = nums[i];
            } else if (nums[i] < min2) {
                // If current is not smaller than min1 but smaller than min2.
                min2 = nums[i];
            }
        }
        return (max1 * max2) - (min1 * min2);
    }

    /**
     * Helper method to extract the current max and min from the array.
     * Note: This method is destructive as it modifies the input array.
     */
    private int[] getMaxMin(int[] nums) {
        int len = nums.length;
        int maxi = 0, mini = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            // Find the maximum value and its index.
            if (nums[i] > max) {
                max = nums[i];
                maxi = i;
            }
            // Find the minimum value, ignoring elements previously marked as "used".
            if (nums[i] < min && nums[i] != Integer.MIN_VALUE) {
                min = nums[i];
                mini = i;
            }
        }

        // Mark picked values as Integer.MIN_VALUE so they are ignored in subsequent calls.
        nums[maxi] = Integer.MIN_VALUE;
        nums[mini] = Integer.MIN_VALUE;

        return new int[] { max, min };
    }
}
