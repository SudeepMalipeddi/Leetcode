/*
 * Problem: LeetCode 153 - Find Minimum in Rotated Sorted Array
 *
 * Problem Statement:
 * Given an array of unique elements that was originally sorted in ascending order and then 
 * rotated between 1 and n times, find the minimum element in the array.
 *
 * Intuition:
 * A sorted array that is rotated will have two sorted sub-segments. There is exactly one 
 * "inflection point" where the value drops (e.g., [4, 5, 6, 1, 2]). This inflection point 
 * is the minimum element. We can use Binary Search to find this point in O(log n) time 
 * by determining which half of the array contains the "drop."
 *
 * Approach:
 * 1. Handle base cases for empty or single-element arrays.
 * 2. Use two pointers, low and high, to define the search space.
 * 3. Calculate the middle index. If the middle element is smaller than the element 
 *    before it, we've found the inflection point (the minimum).
 * 4. If the left part is sorted (nums[low] <= nums[mid]) but the middle element is 
 *    greater than the rightmost element, the rotation point must be in the right half.
 * 5. Otherwise, the rotation point is in the left half.
 *
 * Time Complexity: O(log n) - The search space is halved in each iteration of the binary search.
 * Space Complexity: O(1) - Only a constant amount of extra space is used for pointers.
 *
 * Edge Cases:
 * - Array is not rotated (already sorted): The loop will narrow down to the first element.
 * - Array is rotated n times (back to original): Same as not rotated.
 * - Array has only one or two elements: Handled by base cases and loop conditions.
 *
 * Dry Run:
 * nums = [4, 5, 6, 7, 0, 1, 2]
 * 1. low=0, high=6, mid=3 (val=7). 7 > 6 is false. 4 <= 7 && 7 > 2 is true -> low = 4.
 * 2. low=4, high=6, mid=5 (val=1). 1 < 0 is false. 0 <= 1 && 1 > 2 is false -> high = 4.
 * 3. Loop ends (low is not < high). Return nums[4] which is 0.
 *
 * Correctness Check:
 * The solution is correct. The logic effectively identifies the unsorted half of the 
 * array where the minimum must reside. The check `nums[mid] < nums[mid-1]` is a 
 * shortcut to catch the minimum as soon as `mid` lands on the inflection point.
 */

/*Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
[a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements,
return the minimum element of this array.

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.


Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 


You must write an algorithm that runs in O(log n) time. */

public class FindMinimuminRotatedSortedArray153 {
    public static int findmin(int[] nums) {
        int n = nums.length;
        // Basic safety check for empty arrays.
        if (n == 0) {
            return 0;
        }
        // If there is only one element, it is by definition the minimum.
        if (n == 1) {
            return nums[0];
        }
        int low = 0, high = n - 1;
        // Iterate through the active search space while maintaining the intended invariant.
        while (low < high) {
            int mid = (low + high) / 2;
            // Important guard: If the current element is smaller than the one before it,
            // we have found the point where the sorted order breaks (the minimum).
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            // Check if the left half is sorted and the right half contains the rotation.
            // If nums[mid] > nums[high], the "drop" to the minimum must be to the right of mid.
            if (nums[low] <= nums[mid] && nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                // Otherwise, the minimum is either at mid or to the left of mid.
                high = mid - 1;
            }
        }
        // If the loop finishes, low and high have converged on the minimum element.
        return nums[low];
    }

    public static void main(String[] args) {
        int[] arr = { 11, 13, 15, 17 };
        System.out.println(findmin(arr));
    }
}
