/*
 * Problem: LeetCode 219 - Contains Duplicate II
 *
 * Problem Statement:
 * Given an integer array 'nums' and an integer 'k', return true if there are two distinct 
 * indices 'i' and 'j' in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Intuition:
 * To satisfy the condition, we need to find the same value appearing twice within a 
 * distance of k. By tracking the most recent index of every number we encounter, 
 * we can instantly check the distance whenever a duplicate is found. If the 
 * distance to the *most recent* occurrence is greater than k, then any earlier 
 * occurrence would also be greater than k, so we only ever need to store the latest index.
 *
 * Approach:
 * 1. Initialize a HashMap to store the mapping of value -> last seen index.
 * 2. Iterate through the array from left to right.
 * 3. For each element, check if it already exists in the map.
 * 4. If it exists, calculate the difference between the current index and the stored index.
 * 5. If the difference is <= k, return true.
 * 6. Update the map with the current index (this ensures we always compare against the closest previous instance).
 * 7. If the loop completes, return false.
 *
 * Time Complexity: O(n), where n is the length of the array. We perform a single pass, and HashMap operations are O(1) on average.
 * Space Complexity: O(n) in the worst case where all elements are unique and stored in the map.
 *
 * Edge Cases:
 * - k = 0: Returns false because i and j must be distinct (abs(i-j) > 0).
 * - nums.length < 2: Returns false as no pairs can exist.
 * - k is larger than the array length: Any duplicate will trigger a true result.
 *
 * Dry Run:
 * nums = [1, 2, 3, 1], k = 3
 * i = 0: map = {1:0}
 * i = 1: map = {1:0, 2:1}
 * i = 2: map = {1:0, 2:1, 3:2}
 * i = 3: nums[3] is 1. map contains 1 at index 0. 3 - 0 = 3. 3 <= 3 is true. Return true.
 *
 * Correctness Check:
 * The solution is correct. By updating the index in the map for every occurrence, 
 * we ensure that 'i - map.get(nums[i])' always represents the minimum possible 
 * distance for that specific value at that point in the iteration.
 */
import java.util.HashMap;

public class ContainsDuplicateII219 {
    /*
     * Problem: LeetCode 219 - Contains Duplicate II
     * Problem Statement: Return true if there are two equal elements whose
     *   indices differ by at most k.
     * Intuition: Track the most recent index of each value; if the distance to
     *   the current index is <= k, a valid pair exists.
     * Approach:
     *   1) Use a HashMap from value to last seen index.
     *   2) For each index i, if the value was seen and i - lastIndex <= k, return true.
     *   3) Update the last seen index.
     * Time Complexity: O(n) average with hashing.
     * Space Complexity: O(n) for the map.
     * Edge Cases: k=0, all unique elements, repeated elements far apart.
     * Dry Run: nums=[1,2,3,1], k=3 -> 1 seen at 0, i=3 diff=3 => true.
     * Correctness Check: The map always stores the closest previous index, so
     *   any qualifying pair is detected when it appears.
     */
    public boolean containsNearByDuplicate(int[] nums, int k) {
        // Map to store the value as the key and its most recent index as the value
        HashMap<Integer, Integer> map = new HashMap<>(); // value -> last index

        // Iterate through the array once to check for nearby duplicates
        for (int i = 0; i < nums.length; i++) {
            // If the current number has been seen before, check if the distance is within k
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            // Always update the map with the current index. This ensures that for future 
            // occurrences, we compare against the closest possible previous index.
            map.put(nums[i], i); // update most recent index
        }
        // No valid pair found after checking the entire array
        return false;
    }
}
