/*
 * Problem: LeetCode 217 - Contains Duplicate
 *
 * Problem Statement:
 * Given an integer array nums, return true if any value appears at least twice in the array, 
 * and return false if every element is distinct.
 *
 * Intuition:
 * To detect a duplicate, we need a way to remember the elements we have already encountered. 
 * A HashSet is the ideal data structure for this because it allows us to check for the 
 * existence of an element and insert a new element in O(1) average time.
 *
 * Approach:
 * 1. Initialize a HashSet to store the unique integers seen so far.
 * 2. Iterate through the input array 'nums'.
 * 3. For each element, check if it is already present in the HashSet.
 * 4. If it is present, we have found a duplicate; return true immediately.
 * 5. If it is not present, add the element to the HashSet and continue.
 * 6. If the loop finishes without finding any duplicates, return false.
 *
 * Time Complexity: O(n)
 * We traverse the array of size n exactly once. Each HashSet operation (contains and add) 
 * takes O(1) time on average.
 *
 * Space Complexity: O(n)
 * In the worst case (where all elements are unique), we will store all n elements in the HashSet.
 *
 * Edge Cases:
 * - Empty array: The loop will not execute, and the method will return false.
 * - Single element array: The loop runs once, no duplicate is found, returns false.
 * - Array with all identical elements: Returns true upon reaching the second element.
 *
 * Dry Run:
 * nums = [1, 2, 3, 1]
 * 1. i = 0, nums[0] = 1: 1 is not in 'numbers'. Add 1. Set: {1}
 * 2. i = 1, nums[1] = 2: 2 is not in 'numbers'. Add 2. Set: {1, 2}
 * 3. i = 2, nums[2] = 3: 3 is not in 'numbers'. Add 3. Set: {1, 2, 3}
 * 4. i = 3, nums[3] = 1: 1 is already in 'numbers'! Return true.
 *
 * Correctness Check:
 * The solution is correct. It efficiently identifies duplicates using a hash-based approach. 
 * Note: The commented-out sorting solution would require 'import java.util.Arrays;' to compile.
 */
// This is the Solution to the Question 217 in Leetcode
import java.util.HashSet;

class containsDuplicates{
    public boolean containsDuplicate(int[] nums) {
        // We use a HashSet because it provides O(1) average time complexity for lookups.
        HashSet<Integer> numbers = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            // Check if the current number has been encountered before.
            if(numbers.contains(nums[i])) return true; // duplicate found
            // If not seen, add it to the set to track it for future iterations.
            else numbers.add(nums[i]); // remember value
        }
        // If we reach the end of the loop, no duplicates were found.
        return false;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)

// Another Solution: Sort the array and check if the adjacent elements are equal
// class containsDuplicate{
//     public boolean containsDuplicate(int[] nums){
//         // Sorting takes O(n log n) time but allows us to find duplicates in O(1) extra space.
//         Arrays.sort(nums);
//         // After sorting, any duplicate values will be positioned next to each other.
//         for(int i=0;i<nums.length-1;i++){
//             // Compare the current element with the next one.
//             if(nums[i]==nums[i+1]) return true;
//         }
//         return false;
//     }
// }

// Time Complexity: O(nlogn)
// Space Complexity: O(1) (depending on the sorting algorithm's stack space)
