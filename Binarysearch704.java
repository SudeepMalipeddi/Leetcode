/*
 * Problem: LeetCode 704. Binary Search
 *
 * Problem Statement:
 * Given a sorted array of integers nums and a target value, return the index if
 * the target is found. Otherwise return -1.
 *
 * Intuition:
 * Because the array is sorted, we can repeatedly halve the search space by comparing
 * the middle value with the target.
 *
 * Approach:
 * 1. Initialize left and right pointers to the array bounds.
 * 2. While left <= right, compute mid and compare nums[mid] to target.
 * 3. Narrow the search to the half that can still contain the target.
 *
 * Time Complexity: O(log n) because the search space halves each step.
 * Space Complexity: O(1).
 *
 * Edge Cases handled:
 * - Target not present.
 * - Single-element array.
 * - Target at ends of the array.
 *
 * Dry Run:
 * nums = [-1,0,3,5,9,12], target = 9
 * mid=2 (3) -> target greater, left=3
 * mid=4 (9) -> found, return 4
 *
 * Correctness Check:
 * Classic binary search; invariants maintain that target can only be in [left, right].
 *
 * LeetCode Match:
 * LeetCode 704 - "Binary Search".
 */
public class Binarysearch704 {
    public int search(int nums[], int target){
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            // Midpoint avoids overflow by using left + (right-left)/2
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                // Target is in the right half
                left = mid + 1;
            }
            else if(nums[mid] > target){
                // Target is in the left half
                right = mid - 1;
            }
        }
        // Target not found
        return -1;
    }
}
