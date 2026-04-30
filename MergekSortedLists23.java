/*
 * Problem: 23. Merge k Sorted Lists
 *
 * Problem Statement:
 * Given an array of k linked-lists, each sorted in ascending order, 
 * merge all the linked-lists into one sorted linked-list and return it.
 *
 * Intuition:
 * While we can merge lists one by one, that results in redundant comparisons. 
 * A Divide and Conquer approach (similar to Merge Sort) allows us to merge 
 * pairs of lists in parallel levels, reducing the number of times each node 
 * is processed from O(k) to O(log k).
 *
 * Approach:
 * 1. Brute Force (mergeKLists): Flatten all nodes into an array, sort the array, 
 *    and reconstruct a new linked list.
 * 2. Divide and Conquer (mergeLists): Recursively split the array of lists 
 *    into two halves until base cases (0, 1, or 2 lists) are reached, then 
 *    merge them using a standard two-pointer sorted merge.
 *
 * Time Complexity: 
 * - mergeKLists: O(N log N) where N is the total number of nodes, due to sorting.
 * - mergeLists: O(N log k) where N is total nodes and k is the number of lists.
 *
 * Space Complexity: 
 * - mergeKLists: O(N) to store all node values in an auxiliary list.
 * - mergeLists: O(log k) for the recursive call stack.
 *
 * Edge Cases:
 * - lists array is empty: return null.
 * - lists array contains null elements: handled by merge logic.
 * - Lists have different lengths: handled by the two-pointer merge.
 *
 * Dry Run:
 * lists = [[1,4], [1,3], [2]]
 * 1. Helper(0, 2) splits into Helper(0, 1) and Helper(2, 2).
 * 2. Helper(0, 1) merges [1,4] and [1,3] -> [1,1,3,4].
 * 3. Helper(2, 2) returns [2].
 * 4. Final merge of [1,1,3,4] and [2] -> [1,1,2,3,4].
 *
 * Correctness Check:
 * The solution is correct. Note that mergeKLists creates entirely new ListNode 
 * objects, whereas mergeLists/merge reuses the existing nodes by adjusting pointers.
 */

/*
 * Problem Statement: Merge k sorted linked lists into one sorted linked list.
 * Intuition: Merging sorted lists is easiest by repeatedly combining smaller sorted results.
 * Approach: This file has two strategies: (1) flatten all values then sort and rebuild, (2) divide-and-conquer pairwise merge.
 * Time Complexity: flatten+sort method is O(N log N); divide-and-conquer method is O(N log k), where N is total nodes.
 * Space Complexity: flatten+sort uses O(N) extra list storage; divide-and-conquer uses O(log k) recursion stack (excluding output links).
 * Edge Cases handled: Empty lists array; null list heads; uneven list lengths; duplicate values.
 * Dry Run: For [1->4->5, 1->3->4, 2->6], divide-and-conquer merges pairs then final pair to produce sorted 1->1->2->3->4->4->5->6.
 * LeetCode matching/assumption: Matches LeetCode 23 (Merge k Sorted Lists).
 * Correctness Check: Tradeoff is explicit: first method is simpler but O(N log N) with value flattening/rebuild; second method is asymptotically better O(N log k) by merging sorted lists directly.
 */

import java.util.*;

public class MergekSortedLists23 {
    /**
     * Approach 1: Brute Force via Sorting.
     * Collects all values, sorts them, and builds a fresh list.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // No lists to merge.
        if (lists.length == 0) {
            return null;
        }
        // Use an ArrayList to store all values across all k lists.
        ArrayList<Integer> arr = new ArrayList<>();
        // Collect every node value from all lists.
        for (ListNode ptr : lists) {
            // Walk one list completely before moving to the next list.
            while (ptr != null) {
                arr.add(ptr.val);
                ptr = ptr.next;
            }
        }
        // Sort globally, then rebuild a new list in non-decreasing order.
        // This takes O(N log N) time.
        Collections.sort(arr);
        
        // Use a dummy head to simplify the logic of appending nodes to a new list.
        ListNode head = new ListNode(0);
        ListNode ptr = head;

        // Recreate nodes from sorted values. Note: this creates N new objects.
        for (int x : arr) {
            ptr.next = new ListNode(x);
            ptr = ptr.next;
        }
        return head.next;

    }

    /**
     * Approach 2: Divide and Conquer.
     * Efficiently merges lists by pairing them up, reducing the merge depth to log(k).
     */
    public ListNode mergeLists(ListNode[] lists) {
        // Guard for null or empty input.
        if (lists == null || lists.length == 0) {
            return null;
        }
        // Merge lists in ranges: [0..k-1].
        return Helper(lists, 0, lists.length - 1);
    }

    /**
     * Recursive helper that partitions the array of lists.
     */
    public ListNode Helper(ListNode[] lists, int start, int end) {
        // Single list range: already merged.
        if (start == end) {
            return lists[start];
        }
        // Two-list range: direct merge. This is a small optimization for the base case.
        if (start + 1 == end) {
            return merge(lists[start], lists[end]);
        }
        
        // Calculate midpoint to split the current range of lists into two halves.
        int mid = start + (end - start) / 2;
        
        // Recursively merge left half and right half, then combine them.
        ListNode left = Helper(lists, start, mid);
        ListNode right = Helper(lists, mid + 1, end);
        
        // Combine the two sorted results into one.
        return merge(left, right);
    }

    /**
     * Standard iterative merge of two sorted linked lists.
     * Uses O(1) extra space (excluding the nodes themselves).
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        // Dummy node acts as a placeholder for the start of the merged list.
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // Standard two-pointer merge for two sorted linked lists.
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        // Attach whichever list still has remaining nodes. 
        // Since lists are sorted, we just link the remainder.
        curr.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }
}
