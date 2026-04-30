/*
 * Problem Statement:
 * In an even-length linked list, node i and node (n-1-i) are twins.
 * Return the maximum twin sum.
 *
 * Intuition:
 * Split list into two halves, reverse the second half, then walk both halves together
 * to compute pair sums directly.
 *
 * Approach:
 * 1) Use slow/fast pointers to reach the start of second half.
 * 2) Reverse second half in-place.
 * 3) Move one pointer from head and one from reversed half; track max pair sum.
 *
 * Time Complexity:
 * O(n), with a few linear passes.
 *
 * Space Complexity:
 * O(1) extra space.
 *
 * Edge Cases handled:
 * - Minimum even length list of 2 nodes.
 * - Larger even-length lists under problem constraints.
 *
 * Dry Run:
 * list: 1 -> 2 -> 3 -> 4
 * second half reversed: 4 -> 3
 * pair sums: 1+4=5, 2+3=5 => answer 5
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 2130 in-place reverse technique.
 * Assumes standard ListNode with fields val and next.
 *
 * Correctness Check:
 * Computes all twin pairs exactly once after alignment.
 * Note: reversing the second half is destructive and mutates the original list order.
 */

public class MaximumTwinSumofaLinkedList2130 {
    public static int PairSum(ListNode head) {
        // Direct answer for smallest valid even-length list.
        if (head.next.next == null) {
            return head.val + head.next.val;
        }
        int res = 0;
        ListNode ptr = head, cptr = head;

        // Move ptr by 1 and cptr by 2 to locate midpoint.
        while (cptr != null && cptr.next != null) {
            ptr = ptr.next;
            cptr = cptr.next.next;
        }

        cptr = reverse(ptr); // Reverse second half in-place (mutates list).
        ptr = head;

        // Compare aligned nodes from first half and reversed second half.
        while (cptr != null) {
            res = Math.max(res, ptr.val + cptr.val);
            ptr = ptr.next;
            cptr = cptr.next;
        }
        return res;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode ptr = head;

        // Standard iterative linked-list reversal.
        while (ptr != null) {
            ListNode temp = ptr.next; // Save next node before rewiring.
            ptr.next = prev; // Reverse pointer direction.
            prev = ptr;
            ptr = temp;
        }
        return prev;
    }
}
