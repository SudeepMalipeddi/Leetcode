/*
 * Problem Statement:
 * Merge sorted nums2 into nums1 (which has enough trailing space),
 * so nums1 becomes one sorted array in-place.
 *
 * Intuition:
 * Fill nums1 from the back so we never overwrite still-needed values in nums1.
 *
 * Approach:
 * Maintain three indices:
 * - i at end of valid part of nums1,
 * - j at end of nums2,
 * - k at end of full nums1 buffer.
 * Place larger of nums1[i] and nums2[j] into nums1[k], moving backward.
 *
 * Time Complexity:
 * O(m + n).
 *
 * Space Complexity:
 * O(1) extra.
 *
 * Edge Cases handled:
 * - n == 0 -> nums1 unchanged.
 * - m == 0 -> nums2 copied into nums1.
 * - Duplicate values handled naturally.
 *
 * Dry Run:
 * nums1=[1,2,3,0,0,0], m=3; nums2=[2,5,6], n=3
 * fill from back -> [1,2,2,3,5,6]
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 88 canonical reverse two-pointer solution.
 *
 * Correctness Check:
 * Backward placement preserves unread values and yields globally sorted final array.
 */

public class MergeSortedArray88{
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1; // Last initialized index in nums1.
        int j = n-1; // Last index in nums2.
        int k = m+n-1; // Write position from the very end of nums1 buffer.

        // Continue until all nums2 elements are placed.
        while(j>=0){
            // Choose larger tail value so merged suffix remains sorted.
            if(i>=0 && nums1[i]>nums2[j]){
                nums1[k--] = nums1[i--];
            }
            else{
                nums1[k--] = nums2[j--];
            }
        }
    }
}
