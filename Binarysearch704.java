/*
 * Problem: LeetCode 704 - Binary Search
 * Problem Statement: Given a sorted array, return the index of target or -1
 *   if it is not present.
 * Intuition: Binary search halves the search space by comparing with the middle.
 * Approach:
 *   1) Maintain left and right bounds.
 *   2) Compute mid, compare nums[mid] with target.
 *   3) Shrink the half that cannot contain the target.
 * Time Complexity: O(log n).
 * Space Complexity: O(1).
 * Edge Cases: Empty array, target outside range, duplicates (returns any index).
 * Dry Run: nums=[-1,0,3,5,9,12], target=9 -> mid=2 (3), left=3 -> mid=4 (9).
 * Correctness Check: Invariant that target, if present, lies within [left,right].
 */
public class Binarysearch704 {
    public int search(int nums[], int target){
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            int mid = left + (right - left)/2; // avoid overflow
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        return -1;
    }
}
