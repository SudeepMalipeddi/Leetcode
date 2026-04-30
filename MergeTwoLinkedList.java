/*
 * Problem Statement: Merge two sorted linked lists and return one sorted list formed by re-linking existing nodes.
 * Intuition: At each step, the smaller current node must be the next node in the final sorted list.
 * Approach: Use recursion; pick the smaller head between l1 and l2, then recursively merge the remaining nodes.
 * Time Complexity: O(m + n), where m and n are lengths of the two lists.
 * Space Complexity: O(m + n) in recursion stack depth in the worst case.
 * Edge Cases handled: Either list is null; lists with different lengths; duplicate values across lists.
 * Dry Run: l1=1->3->5, l2=1->2->4 -> pick 1(l2), then 1(l1), then 2, 3, 4, 5 -> merged sorted list.
 * LeetCode matching/assumption: Matches LeetCode 21 (Merge Two Sorted Lists) style input/output.
 * Correctness Check: Base cases return the non-null remainder; recursive step preserves sorted order by always choosing the smaller head.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MergeTwoLinkedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // If l1 is exhausted, the rest of l2 is already sorted.
        if (l1 == null)
            return l2;
        // If l2 is exhausted, the rest of l1 is already sorted.
        if (l2 == null)
            return l1;
        // Choose the smaller head and recursively connect the remaining merged tail.
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
