/*
 * Problem: LeetCode 15. 3Sum
 *
 * Problem Statement:
 * Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]]
 * such that i, j, and k are distinct and nums[i] + nums[j] + nums[k] == 0.
 *
 * Intuition:
 * Sorting lets us fix one number and use a two-pointer sweep to find complementary
 * pairs that sum to the negative of the fixed number while skipping duplicates.
 *
 * Approach:
 * 1. Sort the array to enable two-pointer search and duplicate skipping.
 * 2. For each index i as the first element, if nums[i] > 0, stop (no triple can sum to 0).
 * 3. Use two pointers (j = i + 1, k = end) to find pairs that complete the sum.
 * 4. Move pointers inward based on the sum, skipping duplicates for i, j, and k.
 *
 * Time Complexity: O(n^2) due to a linear two-pointer scan for each i (sorting is O(n log n)).
 * Space Complexity: O(1) extra (ignoring output list), plus sort implementation overhead.
 *
 * Edge Cases handled:
 * - Arrays with fewer than 3 elements.
 * - All positive or all negative arrays.
 * - Arrays with many duplicates or multiple zeros.
 *
 * Dry Run:
 * nums = [-1, 0, 1, 2, -1, -4]
 * sorted = [-4, -1, -1, 0, 1, 2]
 * i = -4 -> j,k sums are too small, move j until j >= k (no triple)
 * i = -1 -> j = -1, k = 2 -> sum = 0 => add [-1, -1, 2], skip duplicates
 *            j = 0, k = 1 -> sum = 0 => add [-1, 0, 1]
 *
 * Correctness Check:
 * The overall strategy is correct for 3Sum. However, the duplicate-skipping loops use
 * pre-increment/pre-decrement before bounds checks:
 *   while (nums[j] == nums[++j] && j < k) ...
 * This can read out of bounds if j reaches k or end. Suggested fix (not applied):
 * check bounds before incrementing (e.g., while (j + 1 < k && nums[j] == nums[j + 1]) j++).
 *
 * LeetCode Match:
 * LeetCode 15 - "3Sum".
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
        List<List<Integer>> result = new ArrayList<>();
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

            // Scan the remaining range to find pairs that sum with nums[i] to 0
            while (j < k) {
                // Current candidate triplet sum
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
