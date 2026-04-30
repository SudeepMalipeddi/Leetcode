/*
 * Problem: 35. Search Insert Position
 *
 * Problem Statement:
 * Given a sorted array of distinct integers and a target value, return the index if the 
 * target is found. If not, return the index where it would be if it were inserted in order.
 *
 * Intuition:
 * Since the input array is sorted and we need an O(log n) solution, Binary Search is the 
 * optimal approach. The key insight is that when the binary search loop finishes without 
 * finding the target, the 'start' pointer will naturally point to the index where the 
 * target should be inserted to maintain the sorted property.
 *
 * Approach:
 * 1. Initialize 'start' at 0 and 'end' at the last index of the array.
 * 2. While 'start' <= 'end', calculate the 'mid' index.
 * 3. Compare nums[mid] with the target:
 *    - If equal, we found the target; return 'mid'.
 *    - If nums[mid] < target, the target must be in the right half; set start = mid + 1.
 *    - If nums[mid] > target, the target must be in the left half; set end = mid - 1.
 * 4. If the loop ends, the target was not found, and 'start' is the correct insertion index.
 *
 * Time Complexity: O(log n) - The search space is halved in every iteration.
 * Space Complexity: O(1) - Only a few integer variables are used regardless of input size.
 *
 * Edge Cases:
 * - Target is smaller than all elements (returns 0).
 * - Target is larger than all elements (returns nums.length).
 * - Target is already present in the array (returns its existing index).
 * - Array contains only one element.
 *
 * Dry Run:
 * nums = [1, 3, 5, 6], target = 2
 * 1. start = 0, end = 3. mid = (0 + 3) / 2 = 1. nums[1] = 3.
 * 2. 3 > 2, so end = mid - 1 = 0.
 * 3. start = 0, end = 0. mid = (0 + 0) / 2 = 0. nums[0] = 1.
 * 4. 1 < 2, so start = mid + 1 = 1.
 * 5. start (1) > end (0), loop terminates. Return start = 1.
 *
 * Correctness Check:
 * The logic is correct for finding the insertion point. Note: (start + end) / 2 can 
 * potentially cause an integer overflow if the sum exceeds 2^31 - 1. A safer 
 * implementation is start + (end - start) / 2.
 */

/*Given a sorted array of distinct integers and a target value, 
return the index if the target is found. If not,
return the index where it would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity. */

public class SearchInsertPosition35 {
    public int searchInsertPosition(int[] nums, int target) {
        // Using binary search to find the element
        // Initialize pointers for the current search range
        int start = 0;
        int end = nums.length - 1;

        // Standard binary search loop: continues as long as the search space is not empty
        while (start <= end) {
            // Calculate the midpoint of the current range
            int mid = (start + end) / 2;

            if (nums[mid] < target) {
                // If target is greater than mid, ignore the left half
                start = mid + 1;
            } else if (nums[mid] > target) {
                // If target is smaller than mid, ignore the right half
                end = mid - 1;
            } else {
                // Target found, return the index immediately
                return mid;
            }
        }
        // returning start as the element to insert
        // because that is the place where the element should be if
        // it isn't present in the array
        // After the loop, 'start' is the index of the first element greater than target
        return start;
    }
}
