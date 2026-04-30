/*
 * Problem Statement: Find the k-th largest element in an unsorted integer array (not the k-th distinct element).
 * Intuition: Keep only the k largest seen so far in a min-heap; heap top is current k-th largest.
 * Approach: Push first k numbers into min-heap, then for each remaining value replace heap top if value is larger.
 * Time Complexity: O(n log k): first k inserts + (n-k) comparisons with at most one poll/offer (log k) each.
 * Space Complexity: O(k) because heap stores exactly k elements.
 * Edge Cases handled: k==1 yields max element; k==nums.length yields minimum after heap maintenance.
 * Dry Run: nums=[3,2,1,5,6,4],k=2 -> heap [2,3]; see 5 replace 2 => [3,5], see 6 replace 3 => [5,6], result 5.
 * LeetCode matching: Matches LeetCode 215 (Kth Largest Element in an Array).
 * Correctness Check: Min-heap invariant is maintained correctly; no correctness issue observed.
 */

import java.util.PriorityQueue;

public class KthLargestElementinanArray215 {
    public int findKthLargest(int[] nums, int k) {
        // Heap top stores the smallest among current top-k numbers.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Seed heap with first k elements.
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        // Evaluate remaining elements against current k-th largest (heap top).
        for (int i = k; i < nums.length; i++) {
            // Only values larger than heap top can enter top-k set.
            if (nums[i] > minHeap.peek()) {
                // Remove current k-th largest candidate.
                minHeap.poll();
                // Insert better candidate.
                minHeap.offer(nums[i]);
            }
        }
        // Top now equals k-th largest element overall.
        return minHeap.peek();
    }
}
