/*
 * Problem: 1. Two Sum
 *
 * Problem Statement:
 * Given an array of integers 'nums' and an integer 'target', return the indices of the two numbers 
 * such that they add up to the target. You may assume that each input would have exactly one 
 * solution, and you may not use the same element twice.
 *
 * Intuition:
 * The core challenge is finding a pair of numbers (a, b) such that a + b = target. 
 * This can be rewritten as finding a 'complement' where b = target - a. 
 * We can either search for this complement by scanning the rest of the array (Brute Force) 
 * or by using a Hash Map to look it up in constant time.
 *
 * Approach:
 * 1. Brute Force: Use a nested loop to compare every possible pair of numbers.
 * 2. Two-Pass HashMap: 
 *    - First pass: Iterate through the array to map each value to its index.
 *    - Second pass: For each number, calculate its required complement and check if it 
 *      exists in the map at a different index.
 *
 * Time Complexity: 
 * - Brute Force: O(n^2) because for every element, we iterate through the rest of the array.
 * - HashMap: O(n) because we traverse the list of n elements exactly twice, and hash table 
 *   lookups take O(1) on average.
 *
 * Space Complexity: 
 * - Brute Force: O(1) as we do not use any additional data structures that scale with input size.
 * - HashMap: O(n) as we store up to n elements in the HashMap.
 *
 * Edge Cases:
 * - The target is the sum of the same number appearing twice (e.g., [3, 3], target 6).
 * - The array contains negative numbers.
 * - The complement exists but is the current element itself (handled by index check).
 *
 * Dry Run:
 * nums = [2, 7, 11], target = 9
 * 1. Map is built: {2:0, 7:1, 11:2}
 * 2. i=0: nums[i]=2. complement = 9 - 2 = 7.
 * 3. Map contains 7 at index 1. 1 != 0.
 * 4. Return [0, 1].
 *
 * Correctness Check:
 * The solution is correct. The twoSum2 method correctly handles the "don't use the same 
 * element twice" constraint by checking `map.get(complement) != i`.
 */

import java.util.HashMap;

public class TwoSums {
    // This is the Solution to the Question 1 in Leetcode using brute force method
    public int[] twoSum(int[] nums, int target){
        int n = nums.length;
        // Outer loop picks the first element of the potential pair
        for(int i = 0; i<n; i++){
            // Inner loop starts from i+1 to ensure we don't pick the same element twice
            // and to avoid redundant checks of pairs already seen.
            for(int j = i+1; j < n; j++){
                if(nums[i] + nums[j] == target) return new int[] {i,j};
            }
        }
        return new int[] {};
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)

    // This is the Solution to the Question 1 in Leetcode using HashMap
    public int[] twoSum2(int[] nums, int target){
        // We use a HashMap to store <Value, Index> pairs for O(1) average time lookup
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        // making the hashmap: This is the first pass
        for(int i = 0; i<n; i++){
            map.put(nums[i],i);
        }
        // checking if the complement exists in the hashmap: This is the second pass
        for(int i=0; i<n; i++){
            int complement = target - nums[i];
            // We must ensure the complement exists AND it is not the current element we are looking at
            if(map.containsKey(complement) && map.get(complement)!=i){
                return new int[]{i,map.get(complement)};
            }
        }
        return new int[]{};
    }
}
// Time Complexity: O(n)
// Space Complexity: O(n)
