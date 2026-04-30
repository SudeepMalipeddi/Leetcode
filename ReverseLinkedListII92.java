/*
 * Problem (from file name): Reverse Linked List II (LeetCode 92).
 * Problem Statement:
 * Reverse a linked-list segment from position `left` to `right` (1-indexed)
 * and return the head of the modified list.
 *
 * Intuition:
 * Keep the prefix before `left` fixed, then repeatedly take the node after the
 * current front of the segment and insert it at the segment front (head insertion).
 *
 * Approach:
 * 1. Use a dummy node to simplify cases where reversal starts at head.
 * 2. Move `prev` to the node before `left`.
 * 3. Set `curr = prev.next` (first node in segment).
 * 4. Repeat `right-left` times:
 *    - Extract `temp = curr.next`
 *    - Remove `temp` from its place
 *    - Insert `temp` immediately after `prev`
 * 5. Return `dummy.next`.
 *
 * Time Complexity: O(n), single traversal plus bounded local rewiring.
 * Space Complexity: O(1), no extra data structures.
 *
 * Edge Cases handled:
 * - `left == right` (no reversal).
 * - Reversal including head (dummy handles it).
 *
 * Dry Run:
 * 1->2->3->4->5, left=2, right=4
 * prev at 1, curr at 2
 * Step1: move 3 in front => 1->3->2->4->5
 * Step2: move 4 in front => 1->4->3->2->5
 * Return head => 1->4->3->2->5
 *
 * Correctness Check:
 * Core reversal logic is correct.
 * Bug note: `if (head.next == null || head == null || left == right)` can throw
 * NullPointerException when `head == null` because `head.next` is evaluated first.
 * Safer order would check `head == null` before `head.next` (comment only; code unchanged).
 */
public class ReverseLinkedListII92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null || head == null || left == right)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;

        for (int i = 0; i < right - left; i++) {
            // Remove the node right after `curr` and insert it after `prev`.
            ListNode temp = curr.next;
            curr.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }
        return dummy.next;
    }
}
