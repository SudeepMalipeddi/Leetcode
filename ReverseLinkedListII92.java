/*
 * Problem: LeetCode 92 - Reverse Linked List II
 *
 * Problem Statement:
 * Given the head of a singly linked list and two integers `left` and `right`
 * (1-indexed, left <= right), reverse the nodes from position `left` to position
 * `right` and return the modified list. The rest of the list remains unchanged.
 *
 * Intuition:
 * The key insight is to use a "head insertion" technique within the target
 * segment. We keep the node before the segment (`prev`) fixed, and repeatedly
 * take the node immediately after the current front of the segment (`curr`)
 * and move it to the position right after `prev`. After `right - left` such
 * moves, the segment is reversed.
 *
 * Approach:
 *   1. Handle early exit: if `head.next == null` or `head == null` or
 *      `left == right`, return `head` unchanged.
 *      (Note: the null check ordering `head.next == null || head == null`
 *       can throw NullPointerException if head is null; ideally `head == null`
 *       should be checked first, but the code is preserved as-is.)
 *   2. Create a `dummy` node with `dummy.next = head` to simplify edge cases
 *      where reversal starts at head.
 *   3. Initialize `prev = dummy` and advance it `left - 1` steps so it points
 *      to the node just before the reversal segment.
 *   4. Set `curr = prev.next` (first node in the segment; stays fixed as the
 *      anchor — its relative position moves to the end of the segment).
 *   5. Repeat `right - left` times (loop control variable `i`):
 *      - `ListNode temp = curr.next` (node to be moved to front of segment).
 *      - Remove `temp` from its position: `curr.next = temp.next`.
 *      - Insert `temp` after `prev`: `temp.next = prev.next`, then
 *        `prev.next = temp`.
 *   6. Return `dummy.next` (the actual head, which may have changed).
 *
 * Time Complexity: O(n) — we traverse up to `left` to position `prev`, then
 *                   perform `right - left` O(1) rewirings.
 * Space Complexity: O(1) — only a few pointers (`dummy`, `prev`, `curr`,
 *                    `temp`) are used.
 *
 * Edge Cases:
 * - `left == right`: no reversal needed, returns head immediately.
 * - Reversal includes head (left = 1): dummy node ensures `prev` exists.
 * - Single node list: caught by `head.next == null` check (but ordering issue
 *   noted above means this only works reliably when head is not null).
 *
 * Dry Run:
 * Input: head = 1 -> 2 -> 3 -> 4 -> 5, left = 2, right = 4
 *
 * Early checks: head.next != null, head != null, left(2) != right(4) -> proceed.
 * dummy -> 1 -> 2 -> 3 -> 4 -> 5
 * prev starts at dummy, advances left-1 = 1 step: prev = 1
 * curr = prev.next = 2
 *
 * Loop (right - left = 2 iterations):
 *
 * i=0:
 *   temp = curr.next = 3
 *   curr.next = temp.next = 4             (2 -> 4)
 *   temp.next = prev.next = 2             (3 -> 2)
 *   prev.next = temp = 3                  (1 -> 3)
 *   List: 1 -> 3 -> 2 -> 4 -> 5
 *
 * i=1:
 *   temp = curr.next = 4
 *   curr.next = temp.next = 5             (2 -> 5)
 *   temp.next = prev.next = 3             (4 -> 3)
 *   prev.next = temp = 4                  (1 -> 4)
 *   List: 1 -> 4 -> 3 -> 2 -> 5
 *
 * Return dummy.next: 1 -> 4 -> 3 -> 2 -> 5
 *
 * Correctness Check:
 * The head-insertion technique is a standard approach for reversing a sublist.
 * Each iteration moves one node from after `curr` to after `prev`, effectively
 * reversing the segment one node at a time from the front. After `right - left`
 * iterations, all nodes originally between `left+1` and `right` have been moved
 * before `curr`, resulting in a reversed segment. The dummy node correctly
 * handles the case where `left = 1` (reversal starts at head).
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
