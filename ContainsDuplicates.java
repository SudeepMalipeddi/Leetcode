/*
 * Problem: LeetCode 217 - Contains Duplicate
 * Problem Statement: Given an integer array, return true if any value appears
 *   at least twice, otherwise return false.
 * Intuition: A set detects duplicates by checking if a value already exists.
 * Approach:
 *   1) Iterate through nums.
 *   2) If the set already contains nums[i], return true.
 *   3) Otherwise add nums[i] to the set.
 * Time Complexity: O(n) average with hashing.
 * Space Complexity: O(n) for the set.
 * Edge Cases: Empty array, all unique elements, all identical elements.
 * Dry Run: nums=[1,2,3,1] -> add 1,2,3, then 1 already in set => true.
 * Correctness Check: The set stores all seen values, so any repeated value is
 *   detected at its second occurrence.
 */
// This is the Solution to the Question 217 in Leetcode
import java.util.HashSet;

class containsDuplicates{
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> numbers = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(numbers.contains(nums[i])) return true; // duplicate found
            else numbers.add(nums[i]); // remember value
        }
        return false;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)

// Another Solution: Sort the array and check if the adjacent elements are equal
// class containsDuplicate{
//     public boolean containsDuplicate(int[] nums){
//         Arrays.sort(nums);
//         for(int i=0;i<nums.length-1;i++){
//             if(nums[i]==nums[i+1]) return true;
//         }
//         return false;
//     }
// }

// Time Complexity: O(nlogn)
// Space Complexity: O(1)
