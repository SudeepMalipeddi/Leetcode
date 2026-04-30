/*
 * Problem: LeetCode 1752 - Check if Array Is Sorted and Rotated
 *
 * Problem Statement:
 * Given an array nums, return true if the array was originally sorted in non-decreasing order, 
 * then rotated some number of positions (including zero).
 *
 * Intuition:
 * A non-decreasing sorted array that is rotated will have at most one "point of decrease" 
 * (a drop) when viewed circularly. If we find more than one such drop, the array 
 * cannot be a rotated version of a sorted array.
 *
 * Approach:
 * 1. Initialize a counter to track violations of the non-decreasing order.
 * 2. Check the "circular" boundary: if the first element is strictly less than the last element, 
 *    it suggests the array is either already sorted or invalidly rotated; we increment the 
 *    counter to account for this potential "break" in the rotated sequence.
 * 3. Iterate through the array and increment the counter for every internal drop 
 *    (where nums[i] > nums[i+1]).
 * 4. If the counter exceeds 1 at any point, the array cannot be a rotated sorted array.
 *
 * Time Complexity: O(n) because we perform a single linear scan of the array.
 * Space Complexity: O(1) as we only use a few integer variables regardless of input size.
 *
 * Edge Cases:
 * - Array with one element: Always true (0 drops).
 * - Array with all identical elements: Always true (0 drops).
 * - Already sorted array: True (1 drop counted at the boundary check).
 *
 * Dry Run:
 * nums = [3, 4, 5, 1, 2], n = 5
 * 1. nums[0] (3) < nums[4] (2) is false. count = 0.
 * 2. i=0: 3 > 4 is false.
 * 3. i=1: 4 > 5 is false.
 * 4. i=2: 5 > 1 is true. count = 1.
 * 5. i=3: 1 > 2 is false.
 * 6. Final count is 1. Returns true.
 *
 * Correctness Check:
 * The 'checking' method is correct. The 'check' method is logically flawed because it 
 * fails to update the 'min' variable during the search for the pivot index and 
 * uses an incorrect rotation logic.
 */
import java.util.Arrays;

public class CheckifArrayIsSortedandRotated1752 {

    // working code
    public boolean checking(int[] nums) {
        int count = 0, n = nums.length;
        
        // Circular check: In a rotated sorted array, the first element must be 
        // greater than or equal to the last element. If it's smaller, it counts 
        // as a "drop" in the circular sequence (unless the array is perfectly sorted).
        if (nums[0] < nums[n - 1]) {
            count++;
        }
        
        // Linear scan to find internal drops where the non-decreasing property is violated.
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
            }
            
            // Optimization: If we find more than one drop, it's impossible to be a rotated sorted array.
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    // doesn't work
    public static boolean check(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length); // keep a sorted reference
        int min = Integer.MAX_VALUE;
        int ind = 0;
        
        // BUG: This loop finds an index but 'min' is never updated (min = nums[i] is missing).
        // Consequently, 'ind' will simply be the index of the last element smaller than MAX_VALUE.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                ind = i;
            }
        }
        
        ind = ind - 1;
        
        // Attempting to "un-rotate" the array using the triple-reverse technique.
        helper(0, nums.length - 1, nums); // reverse whole array
        int resind = nums.length - ind;
        helper(0, resind - 1, nums); // reverse first segment
        helper(resind, nums.length - 1, nums); // reverse second segment
        
        // Compare the manually un-rotated array with a truly sorted version.
        Arrays.sort(sorted);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != sorted[i]) {
                System.out.println("Not a rotated array");
                return false;
            }
        }
        return true;
    }

    /**
     * Standard utility to reverse an array segment in-place using two pointers.
     */
    public static void helper(int start, int end, int[] arr) {
        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
