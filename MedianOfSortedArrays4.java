/*
 * Problem Statement:
 * Given two sorted arrays nums1 and nums2, return the median of the combined sorted data.
 *
 * Intuition:
 * We do not need a full merge; we only need values up to the middle index.
 * Keep the last two extracted values to handle even total length.
 *
 * Approach:
 * Perform a merge-until-middle scan using two pointers:
 * - pick smaller front element from nums1/nums2 each step,
 * - track med1 (previous extracted) and med2 (current extracted),
 * - after reaching middle, compute median.
 *
 * Time Complexity:
 * O(m + n) in the worst case for this implementation.
 *
 * Space Complexity:
 * O(1) extra space.
 *
 * Edge Cases handled:
 * - One array empty.
 * - Different array sizes.
 * - Even vs odd combined length.
 *
 * Dry Run:
 * nums1=[1,3], nums2=[2]
 * extracted order up to middle: 1,2
 * odd length => median = 2
 *
 * LeetCode matching/assumption:
 * Matches a valid LeetCode 4 merge-based solution and assumes both inputs are sorted.
 *
 * Correctness Check:
 * Correct for sorted arrays using merge-until-middle.
 * Note: this is O(m+n), not the classic expected O(log(min(m,n))) optimal approach for LeetCode 4.
 */

public class MedianOfSortedArrays4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int index1 = 0;
        int index2 = 0;
        int med1 = 0;
        int med2 = 0;

        // Pull exactly middle+1 elements from virtual merged order.
        for (int i = 0; i <= (nums1.length + nums2.length) / 2; i++) {
            med1 = med2; // Previous extracted value.

            // Handle exhaustion of either array before normal comparison.
            if (index1 == nums1.length) {
                med2 = nums2[index2];
                index2++;
            } else if (index2 == nums2.length) {
                med2 = nums1[index1];
                index1++;
            } else if (nums1[index1] < nums2[index2]) {
                med2 = nums1[index1];
                index1++;
            } else {
                med2 = nums2[index2];
                index2++;
            }
        }

        // Even total length uses average of two middle values.
        if ((nums1.length + nums2.length) % 2 == 0) {
            return (float) (med1 + med2) / 2;
        }
        return med2; // Odd total length: single middle value.
    }
}
