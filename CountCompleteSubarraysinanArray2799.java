/*
 * Problem: LeetCode 2799 - Count Complete Subarrays in an Array
 * Problem Statement: Count subarrays that contain all distinct elements present
 *   in the entire array.
 * Intuition: If we know the total number of distinct values, each subarray can
 *   be checked by tracking how many distinct values it includes.
 * Approach (current implementation):
 *   1) Compute totalDistinct for the entire array.
 *   2) For every starting index i, expand j and track distinct values in a set.
 *   3) If the set size equals totalDistinct, increment count.
 * Time Complexity: O(n^2) due to nested loops with set operations.
 * Space Complexity: O(n) for the set.
 * Edge Cases: All elements identical (every subarray is complete), all distinct.
 * Dry Run: nums=[1,3,1,2,2], totalDistinct=3; starting at i=0, j reaches 3
 *   to include {1,3,2} => count++ for that and subsequent j.
 * Correctness Check: The brute-force expansion checks every subarray, so all
 *   complete subarrays are counted. Improvement note: a sliding window with
 *   frequency map can achieve O(n) time.
 */
import java.util.*;

public class CountCompleteSubarraysinanArray2799 {
    public static int countCompleteSubarrays(int[] nums) {
        int totalDistinct = countDistinct(nums); // distinct count for full array
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>(); // distinct elements in current subarray
            for (int j = i; j < n; j++) {
                seen.add(nums[j]); // expand subarray [i..j]
                if (seen.size() == totalDistinct) {
                    count++; // current subarray is complete
                }
            }
        }
        return count;
    }

    private static int countDistinct(int[] nums) {
        Set<Integer> set = new HashSet<>(); // used only to count unique values
        for (int num : nums) {
            set.add(num);
        }
        return set.size();
    }

    public static List<List<Integer>> findAllSubarrays(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = i; j < n; j++) {
                sub.add(arr[j]); // extend current subarray
                result.add(new ArrayList<>(sub)); // store a copy
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 1, 2, 2 };
        countCompleteSubarrays(nums); // sample invocation
    }
}
