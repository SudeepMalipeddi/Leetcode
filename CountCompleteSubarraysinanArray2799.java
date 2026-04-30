/*
 * Problem: 2799. Count Complete Subarrays in an Array
 *
 * Problem Statement:
 * A subarray is called "complete" if the number of distinct elements in it is 
 * equal to the number of distinct elements in the entire array. Given an 
 * integer array nums, return the number of complete subarrays.
 *
 * Intuition:
 * The core requirement is to match the "distinct element count" of the whole array. 
 * By first identifying how many unique values exist globally, we can then 
 * evaluate every possible subarray to see if it meets this threshold.
 *
 * Approach:
 * 1. Calculate the total number of distinct elements in the entire array using a HashSet.
 * 2. Use a nested loop structure to examine every possible subarray [i, j].
 * 3. For each starting index 'i', maintain a new HashSet to track unique elements 
 *    as the end index 'j' expands.
 * 4. Whenever the size of the current HashSet equals the global distinct count, 
 *    increment the result counter.
 *
 * Time Complexity: O(n^2)
 * Each subarray is visited. For an array of size n, there are n*(n+1)/2 subarrays. 
 * HashSet operations (add, size) are O(1) on average.
 *
 * Space Complexity: O(n)
 * In the worst case (all elements distinct), the HashSets will store n elements.
 *
 * Edge Cases:
 * - Array with all identical elements: Every subarray is complete.
 * - Array with all distinct elements: Only subarrays containing all elements are complete.
 * - Smallest possible array (length 1): Always 1 complete subarray.
 *
 * Dry Run:
 * nums = [1, 3, 1, 2], totalDistinct = 3 ({1, 3, 2})
 * i=0: j=0 {1}, j=1 {1,3}, j=2 {1,3}, j=3 {1,3,2} -> count=1
 * i=1: j=1 {3}, j=2 {3,1}, j=3 {3,1,2} -> count=2
 * i=2: j=2 {1}, j=3 {1,2} -> count=2
 * i=3: j=3 {2} -> count=2
 * Result: 2 (Subarrays [1,3,1,2] and [3,1,2])
 *
 * Correctness Check:
 * The solution correctly identifies all complete subarrays using a brute-force 
 * approach. Note: For large input sizes (n > 2000), an O(n) sliding window 
 * approach using a frequency map would be more efficient.
 */
import java.util.*;

public class CountCompleteSubarraysinanArray2799 {
    public static int countCompleteSubarrays(int[] nums) {
        // Step 1: Determine the target number of unique elements in the entire array
        int totalDistinct = countDistinct(nums); 
        int count = 0;
        int n = nums.length;

        // Step 2: Iterate through every possible starting position of a subarray
        for (int i = 0; i < n; i++) {
            // For each new starting point, we track unique elements in the current window
            Set<Integer> seen = new HashSet<>(); 
            for (int j = i; j < n; j++) {
                // Step 3: Expand the subarray to the right and add the new element
                seen.add(nums[j]); 
                
                // Step 4: If the current window contains all global distinct elements, it's "complete"
                if (seen.size() == totalDistinct) {
                    count++; 
                }
            }
        }
        return count;
    }

    /**
     * Helper method to calculate the number of unique integers in an array.
     */
    private static int countDistinct(int[] nums) {
        Set<Integer> set = new HashSet<>(); 
        for (int num : nums) {
            set.add(num);
        }
        return set.size();
    }

    /**
     * Utility method to generate all possible subarrays.
     * Note: This is a helper for visualization and is not called by the main logic.
     */
    public static List<List<Integer>> findAllSubarrays(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = i; j < n; j++) {
                sub.add(arr[j]); 
                // We must create a new list copy because 'sub' is being mutated
                result.add(new ArrayList<>(sub)); 
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 1, 2, 2 };
        // The result for this input should be 4:
        // [1,3,1,2], [1,3,1,2,2], [3,1,2], [3,1,2,2]
        int result = countCompleteSubarrays(nums);
        System.out.println("Total Complete Subarrays: " + result);
    }
}
