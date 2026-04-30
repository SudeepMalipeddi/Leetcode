/*
 * Problem Statement:
 * Given an integer array nums, maximize
 * (nums[a] * nums[b]) - (nums[c] * nums[d])
 * using four distinct indices, and return that maximum product difference.
 *
 * Intuition:
 * To maximize the difference, use the two largest numbers for the positive product
 * and the two smallest numbers for the negative product.
 *
 * Approach:
 * - Method 1: Sort, then pick last two and first two values.
 * - Method 2: Single pass tracking top two maxima and bottom two minima.
 *
 * Time Complexity:
 * - maxProductDifference: O(n log n) due to sorting.
 * - maxProductDifference2: O(n) single pass.
 *
 * Space Complexity:
 * O(1) extra (ignoring sort implementation internals).
 *
 * Edge Cases handled:
 * - Works with duplicate values.
 * - Handles minimal valid length (n >= 4 by problem constraints).
 * - Handles mixed positive/negative values via min/max tracking.
 *
 * Dry Run:
 * nums = [5,6,2,7,4]
 * largest two: 7,6 -> 42
 * smallest two: 2,4 -> 8
 * answer = 42 - 8 = 34
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 1913 expected strategies (sort or linear min/max tracking).
 *
 * Correctness Check:
 * Selecting extreme pairs (largest two, smallest two) is optimal for this objective.
 */

import java.util.Arrays;

public class MaximumProductDifferenceBetweenTwoPairs1913 {
    public int maxProductDifference(int[] nums) {
        // Sorting places smallest values at front and largest at end.
        Arrays.sort(nums);
        int n = nums.length;
        return (nums[n - 1] * nums[n - 2]) - (nums[0] * nums[1]);
    }

    public int maxProductDifference2(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        // Keep top-2 maxima and bottom-2 minima in one traversal.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max1) {
                max2 = max1; // Old best becomes second best.
                max1 = nums[i];
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }

            if (nums[i] < min1) {
                min2 = min1; // Old smallest becomes second smallest.
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }
        return (max1 * max2) - (min1 * min2);
    }

    private int[] getMaxMin(int[] nums) {
        int len = nums.length;
        int maxi = 0, mini = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        // Find current max and min indices.
        for (int i = 0; i < len; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxi = i;
            }
            if (nums[i] < min && nums[i] != Integer.MIN_VALUE) {
                min = nums[i];
                mini = i;
            }
        }

        // Mark picked values so caller can ignore them in repeated extraction patterns.
        nums[maxi] = Integer.MIN_VALUE;
        nums[mini] = Integer.MIN_VALUE;

        return new int[] { max, min };
    }
}
