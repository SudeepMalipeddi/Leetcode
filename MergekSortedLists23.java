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
    public ListNode mergeKLists(ListNode[] lists) {
        // No lists to merge.
        if (lists.length == 0) {
            return null;
        }
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
        Collections.sort(arr);
        ListNode head = new ListNode(0);
        ListNode ptr = head;

        // Recreate nodes from sorted values.
        for (int x : arr) {
            ptr.next = new ListNode(x);
            ptr = ptr.next;
        }
        return head.next;

    }

    public ListNode mergeLists(ListNode[] lists) {
        // Guard for null or empty input.
        if (lists == null || lists.length == 0) {
            return null;
        }
        // Merge lists in ranges: [0..k-1].
        return Helper(lists, 0, lists.length - 1);
    }

    public ListNode Helper(ListNode[] lists, int start, int end) {
        // Single list range: already merged.
        if (start == end) {
            return lists[start];
        }
        // Two-list range: direct merge.
        if (start + 1 == end) {
            return merge(lists[start], lists[end]);
        }
        int mid = start + (end - start) / 2;
        // Recursively merge left half and right half, then combine them.
        ListNode left = Helper(lists, start, mid);
        ListNode right = Helper(lists, mid + 1, end);
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
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
        curr.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }
}
