/*
 * Problem: LeetCode 88 - Merge Sorted Array
 *
 * Problem Statement:
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * nums1 has size m + n where the first m elements are the valid sorted portion and the remaining
 * n positions are zero-padded to accommodate nums2.
 *
 * Intuition:
 * Merging from the front would require shifting elements in nums1. Instead, fill nums1 from the
 * back (largest elements first). Since the trailing n positions in nums1 are empty, we never
 * overwrite unprocessed values. This backward two-pointer approach achieves in-place O(1) extra space.
 *
 * Approach:
 *   1. Initialize three pointers: i = m - 1 (last valid element in nums1), j = n - 1 (last element
 *      in nums2), k = m + n - 1 (last position in the full nums1 buffer).
 *   2. While j >= 0 (there are still nums2 elements to place):
 *      - If i >= 0 AND nums1[i] > nums2[j], place nums1[i] at nums1[k], then decrement i and k.
 *      - Otherwise, place nums2[j] at nums1[k], then decrement j and k.
 *   3. When j < 0, all nums2 elements have been merged. Any remaining nums1 elements are already
 *      in their correct positions (no action needed).
 *
 * Time Complexity: O(m + n) — each element from nums1 and nums2 is processed at most once.
 * Space Complexity: O(1) — only three integer pointers, no additional array allocation.
 *
 * Edge Cases:
 * - n == 0: while loop body never executes, nums1 remains unchanged. Correct.
 * - m == 0: i = -1, the else-branch always fires, copying all of nums2 into nums1. Correct.
 * - Duplicate values: the condition nums1[i] > nums2[j] places nums2[j] when equal, which is stable
 *   and produces correct sorted order.
 * - All nums2 larger than nums1: i decrements fully, then j elements fill the front. Correct.
 * - All nums1 larger than nums2: all nums1 shift to the back first, then nums2 fills the front.
 *
 * Dry Run:
 * nums1 = [1, 2, 3, 0, 0, 0], m = 3
 * nums2 = [2, 5, 6], n = 3
 * Initial: i=2 (nums1[2]=3), j=2 (nums2[2]=6), k=5
 * Step 1: i>=0 and nums1[2]=3 > nums2[2]=6? No → nums1[5]=6, j=1, k=4
 * Step 2: i>=0 and nums1[2]=3 > nums2[1]=5? No → nums1[4]=5, j=0, k=3
 * Step 3: i>=0 and nums1[2]=3 > nums2[0]=2? Yes → nums1[3]=3, i=1, k=2
 * Step 4: i>=0 and nums1[1]=2 > nums2[0]=2? No → nums1[2]=2, j=-1, k=1
 * j = -1, loop ends.
 * Final nums1 = [1, 2, 2, 3, 5, 6]. Correct sorted merge.
 *
 * Correctness Check:
 * The while loop invariant: at the start of each iteration, nums1[k+1..m+n-1] contains the largest
 * (m+n-1-k) elements from both arrays in sorted order, and no element in nums1[0..i] or nums2[0..j]
 * exceeds nums1[k+1]. The loop terminates with all nums2 elements placed, guaranteeing a fully
 * sorted nums1.
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
