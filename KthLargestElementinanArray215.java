/*
 * Problem: 215. Kth Largest Element in an Array
 *
 * Problem Statement:
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Intuition:
 * To find the kth largest element, we need to track the 'k' largest values seen so far. 
 * A Min-Heap of size k is perfect for this: its root always stores the smallest value 
 * among the current k largest elements. This root is, by definition, the kth largest 
 * element of the set processed so far.
 *
 * Approach:
 * 1. Initialize a Min-Heap (PriorityQueue in Java).
 * 2. Add the first k elements of the array into the heap.
 * 3. For the remaining elements, compare each with the heap's root (the current kth largest).
 * 4. If an element is larger than the root, it belongs in the "top k" set. Remove the 
 *    old root and insert the new element.
 * 5. After iterating through the entire array, the root of the heap is the kth largest element.
 *
 * Time Complexity: O(n log k), where n is the number of elements in the array. 
 * Each of the n elements is processed, and heap operations (insertion/removal) take O(log k).
 * Space Complexity: O(k) to maintain a heap of size k.
 *
 * Edge Cases:
 * - k = 1: The heap will eventually contain the maximum element at the root.
 * - k = nums.length: The heap will contain all elements, and the root will be the minimum.
 * - Duplicates: The heap correctly handles duplicate values as separate entries.
 *
 * Dry Run:
 * nums = [3, 2, 1, 5, 6, 4], k = 2
 * 1. Initial heap (first 2): [2, 3] (2 is at the top)
 * 2. i=2, nums[2]=1: 1 is not > 2. Skip.
 * 3. i=3, nums[3]=5: 5 > 2. Poll 2, Offer 5. Heap: [3, 5]
 * 4. i=4, nums[4]=6: 6 > 3. Poll 3, Offer 6. Heap: [5, 6]
 * 5. i=5, nums[5]=4: 4 is not > 5. Skip.
 * Result: minHeap.peek() = 5.
 *
 * Correctness Check:
 * The solution is correct. It efficiently maintains the top-k elements using the 
 * Min-Heap property. No bugs or logical errors identified.
 */
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
        // Java's PriorityQueue implements a Min-Heap by default, where the smallest element is at the head.
        // Heap top stores the smallest among current top-k numbers.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Seed heap with first k elements.
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        // Evaluate remaining elements against current k-th largest (heap top).
        // We only care about elements that are larger than our current k-th largest candidate.
        for (int i = k; i < nums.length; i++) {
            // Only values larger than heap top can enter top-k set.
            if (nums[i] > minHeap.peek()) {
                // Remove current k-th largest candidate.
                minHeap.poll();
                // Insert better candidate.
                minHeap.offer(nums[i]);
            }
        }
        // After processing all elements, the heap contains the k largest elements of the array.
        // Top now equals k-th largest element overall.
        return minHeap.peek();
    }
}
