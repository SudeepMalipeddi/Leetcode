/*
 * Problem: LeetCode 15 - 3Sum
 *
 * Problem Statement:
 * Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]] 
 * such that i != j != k and nums[i] + nums[j] + nums[k] == 0.
 *
 * Intuition:
 * A brute-force O(n^3) approach is too slow. By sorting the array, we can fix one 
 * number and use the Two-Pointer technique to find the other two in O(n) time. 
 * Sorting also allows us to easily skip duplicate values to ensure the result 
 * contains only unique triplets.
 *
 * Approach:
 * 1. Sort the array to enable the two-pointer strategy and duplicate handling.
 * 2. Iterate through the array with index 'i' representing the first element.
 * 3. If nums[i] > 0, terminate early because no three numbers can sum to zero 
 *    if the smallest is positive.
 * 4. Use two pointers: 'j' starting at i+1 and 'k' starting at the end of the array.
 * 5. Calculate the sum. If sum < 0, move 'j' right; if sum > 0, move 'k' left; 
 *    if sum == 0, record the triplet and move both pointers while skipping duplicates.
 * 6. Skip duplicate values for 'i' at the end of each iteration.
 *
 * Time Complexity: O(n^2)
 * - Sorting takes O(n log n).
 * - The outer loop runs O(n) times, and the inner two-pointer scan runs O(n) 
 *   per outer iteration, resulting in O(n^2).
 *
 * Space Complexity: O(log n) to O(n)
 * - This depends on the space used by the sorting algorithm (Arrays.sort uses 
 *   Dual-Pivot Quicksort for primitives).
 *
 * Edge Cases:
 * - nums.length < 3: Handled by the initial check.
 * - All zeros: Handled by duplicate skipping (returns [[0,0,0]]).
 * - No solution: Returns an empty list.
 *
 * Dry Run:
 * nums = [-1, 0, 1, 2, -1, -4]
 * 1. Sort: [-4, -1, -1, 0, 1, 2]
 * 2. i=0 (nums[i]=-4): j=1, k=5. Sums are negative, j moves to k. No triplets.
 * 3. i=1 (nums[i]=-1): j=2, k=5. Sum = -1 + -1 + 2 = 0. Add [-1, -1, 2].
 *    Skip duplicates for j and k. j=3, k=4.
 *    Sum = -1 + 0 + 1 = 0. Add [-1, 0, 1].
 * 4. i=2: nums[2] is -1, same as nums[1]. Skip to avoid duplicate triplets.
 *
 * Correctness Check:
 * The solution correctly handles duplicates by skipping identical adjacent values 
 * for all three pointers (i, j, and k). The early exit `nums[i] > 0` is a valid 
 * optimization for sorted arrays.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15. 3Sum
 * Finds all unique triplets in the array which gives the sum of zero.
 */
class sum15 {
    /**
     * Time Complexity: O(n^2), where n is the length of the array.
     * Space Complexity: O(1) or O(n) depending on the sorting algorithm.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); // collect unique triplets
        // We will be using two pointers like the solution to the question of Valid
        // Palindrome question
        
        // A triplet requires at least 3 elements
        if (nums.length < 3) {
            return result;
        }

        // Sort the array to easily manage duplicates and use the two-pointer technique
        Arrays.sort(nums);
        int i = 0;

        // Loop through the array, stopping 2 elements before the end to leave room for j and k
        while (i < nums.length - 2) {
            // Optimization: If the current element is positive, the sum can never be zero 
            // since all subsequent elements in the sorted array are also positive.
            if (nums[i] > 0)
                break;

            // Initialize two pointers, one just after i and one at the end of the array
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                // If we found a triplet, add it to the result list
                if (sum == 0)
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                // If sum <= 0, we need a larger value to reach zero.
                // We move the left pointer 'j' to the right.
                // The while loop with '++j' increments j and skips identical values to avoid duplicate triplets.
                if (sum <= 0)
                    while (nums[j] == nums[++j] && j < k)
                        ;

                // If sum >= 0, we need a smaller value to reach zero.
                // We move the right pointer 'k' to the left.
                // The while loop with 'k--' checks the current value then decrements, skipping duplicates.
                if (sum >= 0)
                    while (nums[k--] == nums[k] && j < k)
                        ;
            }
            
            // After checking all pairs for the current nums[i], skip duplicate values 
            // for the first element of the triplet to ensure uniqueness in the next iteration.
            while (nums[i] == nums[++i] && i < nums.length - 2)
                ;
        }
        return result;
    }
}
