/*
 * Problem: 4. Median of Two Sorted Arrays
 *
 * Problem Statement:
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The median is the middle value in a sorted list; if the list has an even number of elements, it is the average of the two middle values.
 *
 * Intuition:
 * To find the median, we don't need to fully merge and store the combined arrays. We only need to traverse 
 * the virtual merged array until we reach the middle position. By keeping track of the current and 
 * previous elements during a two-pointer traversal, we can calculate the median with constant extra space.
 *
 * Approach:
 * 1. Initialize two pointers, index1 and index2, to the start of each array.
 * 2. Calculate the middle point of the combined length: (m + n) / 2.
 * 3. Iterate from 0 up to this middle point. In each step:
 *    - Store the current value (med2) into med1 (previous value).
 *    - Compare elements at index1 and index2, picking the smaller one to be the new med2.
 *    - If one array is exhausted, continue picking from the remaining array.
 * 4. After the loop, if the total length is even, return the average of med1 and med2.
 * 5. If the total length is odd, return med2.
 *
 * Time Complexity: O(m + n)
 * We iterate through approximately half of the total elements (m + n) / 2 times.
 *
 * Space Complexity: O(1)
 * We only use a fixed number of integer variables (index1, index2, med1, med2) regardless of input size.
 *
 * Edge Cases:
 * - One array is empty: The logic correctly handles this via the exhaustion checks.
 * - Arrays have different lengths: The two-pointer approach naturally handles size disparity.
 * - Total length is 1: The loop runs once, med2 becomes the single element, and it is returned.
 *
 * Dry Run:
 * nums1 = [1, 3], nums2 = [2]
 * Total length = 3 (Odd). Target index = 3 / 2 = 1.
 * i = 0: med1 = 0, nums1[0] < nums2[0] (1 < 2), med2 = 1, index1 = 1.
 * i = 1: med1 = 1, nums1[1] > nums2[0] (3 > 2), med2 = 2, index2 = 1.
 * Loop ends. Total length is odd, return med2 = 2.0.
 *
 * Correctness Check:
 * The solution is correct for O(m+n) requirements. Note that the optimal LeetCode solution 
 * usually requires O(log(min(m,n))) using binary search on the partition, but this 
 * linear approach is more intuitive and passes most basic constraints.
 */

public class MedianOfSortedArrays4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int index1 = 0;
        int index2 = 0;
        
        // med2 tracks the current element in the virtual merged sequence.
        // med1 tracks the element immediately preceding med2 (needed for even-length medians).
        int med1 = 0;
        int med2 = 0;

        // We only need to iterate until we reach the middle of the combined arrays.
        // For a total length N, the median is at index N/2 (if odd) or average of N/2-1 and N/2 (if even).
        for (int i = 0; i <= (nums1.length + nums2.length) / 2; i++) {
            // Before updating med2, save its current value as the "previous" element.
            med1 = med2; 

            // Case 1: nums1 is exhausted. Pick from nums2.
            if (index1 == nums1.length) {
                med2 = nums2[index2];
                index2++;
            } 
            // Case 2: nums2 is exhausted. Pick from nums1.
            else if (index2 == nums2.length) {
                med2 = nums1[index1];
                index1++;
            } 
            // Case 3: Both arrays have elements. Pick the smaller one to maintain sorted order.
            else if (nums1[index1] < nums2[index2]) {
                med2 = nums1[index1];
                index1++;
            } 
            // Case 4: nums2 element is smaller or equal.
            else {
                med2 = nums2[index2];
                index2++;
            }
        }

        // If total length is even, the median is the average of the two middle elements.
        if ((nums1.length + nums2.length) % 2 == 0) {
            // Cast to float/double to ensure decimal division.
            return (float) (med1 + med2) / 2;
        }
        
        // If total length is odd, med2 is exactly the middle element.
        return med2; 
    }
}
