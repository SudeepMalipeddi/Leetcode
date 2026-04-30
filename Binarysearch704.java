/*
 * Problem: LeetCode 704 - Binary Search
 *
 * Problem Statement:
 * Given an array of integers nums which is sorted in ascending order, and an integer target, 
 * write a function to search target in nums. If target exists, then return its index. 
 * Otherwise, return -1.
 *
 * Intuition:
 * Since the input array is already sorted, we can avoid a linear scan. By comparing the 
 * target to the middle element, we can determine if the target lies in the left half 
 * or the right half, effectively doubling our efficiency at every step.
 *
 * Approach:
 * 1. Initialize two pointers, left (start of array) and right (end of array).
 * 2. While left is less than or equal to right, calculate the middle index.
 * 3. If the middle element is the target, return the index.
 * 4. If the target is larger, discard the left half by moving the left pointer.
 * 5. If the target is smaller, discard the right half by moving the right pointer.
 *
 * Time Complexity: O(log n) because the search space is halved in each iteration.
 * Space Complexity: O(1) as we only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - Array with a single element.
 * - Target is the first or last element.
 * - Target is not present in the array.
 *
 * Dry Run:
 * nums = [-1, 0, 3, 5, 9, 12], target = 9
 * 1. left=0, right=5, mid=2 (nums[2]=3). 3 < 9, so left = mid + 1 = 3.
 * 2. left=3, right=5, mid=4 (nums[4]=9). 9 == 9, return index 4.
 *
 * Correctness Check:
 * The solution correctly implements the standard binary search algorithm. The use of 
 * left + (right - left) / 2 prevents potential integer overflow.
 */
public class Binarysearch704 {
    public int search(int nums[], int target){
        int left = 0;
        int right = nums.length-1;

        // Use <= to ensure we check the case where left == right (the last remaining element)
        while(left <= right){
            // Standard mid calculation can overflow if left + right > Integer.MAX_VALUE.
            // This alternative formula is mathematically identical but safer.
            int mid = left + (right - left)/2; 
            
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                // Target is in the right half, so move the lower bound up
                left = mid + 1;
            }
            else if(nums[mid] > target){
                // Target is in the left half, so move the upper bound down
                right = mid - 1;
            }
        }
        
        // If the loop terminates, the target was not found in the array
        return -1;
    }
}
