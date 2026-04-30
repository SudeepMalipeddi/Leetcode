/*
 * Problem: LeetCode 27 - Remove Element
 *
 * Problem Statement:
 * Given an integer array 'nums' and an integer 'val', remove all occurrences of 'val' in 'nums' in-place.
 * The order of the elements may be changed. Return the number of elements in 'nums' which are not equal to 'val'.
 *
 * Intuition:
 * Since we need to modify the array in-place and the problem allows the remaining elements to be in any order,
 * we can use a two-pointer approach. One pointer iterates through the array to find valid elements, 
 * while the other pointer tracks the position where the next valid element should be stored.
 *
 * Approach:
 * 1. Initialize a pointer 'index' to 0. This will track the size of the modified array.
 * 2. Iterate through the array using a loop variable 'i' from 0 to nums.length - 1.
 * 3. For each element, check if nums[i] is not equal to the target 'val'.
 * 4. If it is not equal, copy nums[i] to the position 'index' and increment 'index'.
 * 5. If it is equal, skip the element.
 * 6. After the loop, 'index' represents the count of elements that are not equal to 'val'.
 *
 * Time Complexity: O(n), where n is the number of elements in the array. We visit each element once.
 * Space Complexity: O(1), as we are modifying the input array in-place without using extra data structures.
 *
 * Edge Cases:
 * - Empty array: The loop won't run, and it returns 0.
 * - Array where all elements are 'val': 'index' never increments, returns 0.
 * - Array where no elements are 'val': 'index' increments for every element, returns nums.length.
 *
 * Dry Run:
 * nums = [3, 2, 2, 3], val = 3
 * i = 0: nums[0] is 3 (matches val), skip. index = 0.
 * i = 1: nums[1] is 2 (no match), nums[0] = 2, index = 1.
 * i = 2: nums[2] is 2 (no match), nums[1] = 2, index = 2.
 * i = 3: nums[3] is 3 (matches val), skip. index = 2.
 * Final return: 2. Modified array prefix: [2, 2].
 *
 * Correctness Check:
 * The solution correctly overwrites values to be removed by shifting valid values to the front.
 * Note: The method name 'removeelement' is lowercase in the provided snippet; this is preserved.
 */
public class RemoveElement {
    public int removeelement(int[] nums, int val) {
        // 'index' keeps track of the position where the next non-val element should be placed.
        int index = 0;
        
        // Iterate through the entire array to inspect each element.
        for (int i = 0; i < nums.length; i++) {
            
            // Compact non-target values to the front segment.
            // If the current element is not the value we want to remove, we "keep" it.
            if (nums[i] != val) {
                // Place the kept element at the current 'index' position.
                nums[index] = nums[i];
                // Move the 'index' forward to prepare for the next valid element.
                index++;
            }
        }
        // The value of 'index' now represents the count of elements that were not equal to 'val'.
        return index;
    }
}
