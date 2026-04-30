/*
 * Problem Statement:
 * Given a linked list containing only values 0, 1, and 2, sort it in non-decreasing order.
 *
 * Intuition:
 * With only three distinct values, counting frequencies is straightforward.
 * Another common technique is splitting nodes into 0/1/2 chains and reconnecting.
 *
 * Approach:
 * sortList (counting rewrite):
 * - First pass counts number of 0s, 1s, 2s.
 * - Second pass overwrites node data in sorted order using counts.
 *
 * sortList2 (three-list partition):
 * - Attach each node to zero/one/two dummy-headed list.
 * - Connect zero -> one (or two), then one -> two, terminate two.
 *
 * Time Complexity (with concrete justification):
 * sortList: O(n) (two linear passes).
 * sortList2 intent: O(n) (single linear partition + O(1) reconnect).
 *
 * Space Complexity (with concrete justification):
 * O(1) auxiliary for both (fixed counters/pointers; no extra per-node allocations besides 3 dummy nodes).
 *
 * Edge Cases handled:
 * - Empty list / single node in sortList2 guard.
 * - Lists containing only one value type (all 0s/all 1s/all 2s).
 *
 * Dry Run (concrete example with state):
 * head: 2->1->0->2
 * sortList counts: c0=1,c1=1,c2=2
 * rewrite pass: node1=0,node2=1,node3=2,node4=2 -> 0->1->2->2
 *
 * LeetCode matching/assumption:
 * Matches common "Sort a linked list of 0s, 1s and 2s" interview/GFG variant (not a direct LeetCode-numbered problem).
 * Assumes node data is restricted to {0,1,2}.
 *
 * Correctness Check:
 * sortList is consistent with counting-sort logic.
 * Critical risk in sortList2: loop never advances `current` (missing `current = current.next`),
 * so it can loop forever on non-empty input. This comment highlights the existing issue without changing code.
 */

class ListNode {
    int data;
    ListNode next;

    ListNode() {
    }

    ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListSort0s1s2s {
    public ListNode sortList(ListNode head) {
        ListNode ptr = head;
        int count0 = 0, count1 = 0, count2 = 0;
        // First pass: count frequencies of each allowed value.
        while (ptr != null) {
            if (ptr.data == 0) {
                count0++;
            } else if (ptr.data == 1) {
                count1++;
            } else {
                count2++;
            }
            ptr = ptr.next;
        }
        ptr = head;
        // Second pass: rewrite node values in sorted bucket order.
        while (ptr != null) {
            if (count0 > 0) {
                ptr.data = 0;
                count0--;
            } else if (count1 > 0) {
                ptr.data = 1;
                count1--;
            } else {
                ptr.data = 2;
                count2--;
            }
            ptr = ptr.next;
        }
        return head;
    }

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode zeroHead = new ListNode(0), zero = zeroHead;
        ListNode oneHead = new ListNode(0), one = oneHead;
        ListNode twoHead = new ListNode(0), two = twoHead;
        ListNode current = head;
        // Partition nodes by value into three chains using tail pointers.
        while (current != null) {
            if (current.data == 0) {
                zero.next = current;
                zero = zero.next;
            } else if (current.data == 1) {
                one.next = current;
                one = one.next;
            } else {
                two.next = current;
                two = two.next;
            }
        }
        // Stitch 0-list to 1-list if present, else directly to 2-list.
        zero.next = oneHead.next != null ? oneHead.next : twoHead.next;
        one.next = twoHead.next;
        two.next = null;
        return zeroHead.next;
    }

}
