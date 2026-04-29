/*
 * Problem: LeetCode 15 - 3Sum
 * Problem Statement: Given an integer array nums, return all unique triplets
 *   [nums[i], nums[j], nums[k]] such that i != j != k and the sum is zero.
 * Intuition: Sorting enables a two-pointer scan to find pairs that complement a
 *   fixed first number while skipping duplicates.
 * Approach:
 *   1) Sort the array to make duplicate handling and pointer moves deterministic.
 *   2) Fix an index i, then use two pointers j (i+1) and k (end) to search for
 *      pairs that sum to -nums[i].
 *   3) Move pointers based on the sum, skipping duplicate values to avoid
 *      repeated triplets.
 *   4) Early stop when nums[i] > 0 because all later numbers are non-negative.
 * Time Complexity: O(n^2) for the two-pointer scan per i, plus O(n log n) sort.
 * Space Complexity: O(1) extra (ignoring output), or O(n) depending on sort.
 * Edge Cases: nums length < 3, all positive/negative values, many duplicates.
 * Dry Run: nums=[-1,0,1,2,-1,-4] -> sort [-4,-1,-1,0,1,2];
 *   i=-4 => move j until sums approach 0 (no triplet),
 *   i=-1 => j=2,k=5 gives sum=0 => [-1,-1,2], then j=3,k=4 sum=0 => [-1,0,1].
 * Correctness Check: The sorted order guarantees that moving j right increases
 *   the sum and moving k left decreases it, so all valid pairs are explored
 *   exactly once for each i while duplicate skips keep results unique.
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
        if (nums.length < 3) {
            return result;
        }

        // Sort the array to easily manage duplicates and use the two-pointer technique
        Arrays.sort(nums);
        int i = 0;

        // Loop through the array, stopping 2 elements before the end
        while (i < nums.length - 2) {
            // If the current element is positive, the sum can never be zero since array is
            // sorted
            if (nums[i] > 0)
                break;

            // Initialize two pointers, one just after i and one at the end
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                // If we found a triplet, add it to the result
                if (sum == 0)
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                // If sum <= 0, we need a larger value, so we move the left pointer to the right
                // Skipping duplicates for the second element
                if (sum <= 0)
                    while (nums[j] == nums[++j] && j < k)
                        ;

                // If sum >= 0, we need a smaller value, so we move the right pointer to the
                // left
                // Skipping duplicates for the third element
                if (sum >= 0)
                    while (nums[k--] == nums[k] && j < k)
                        ;
            }
            // Skip duplicates for the first element
            while (nums[i] == nums[++i] && i < nums.length - 2)
                ;
        }
        return result;
    }
}
