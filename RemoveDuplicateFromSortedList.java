/*
 * Problem: LeetCode 83 - Remove Duplicates from Sorted List
 *
 * Problem Statement:
 * Given the head of a sorted linked list, delete all duplicates such that each
 * element appears only once. Return the linked list sorted as well.
 *
 * Intuition:
 * Since the list is sorted, all duplicate values appear consecutively. We can
 * traverse the list once with a single pointer, and whenever the current node's
 * value equals the next node's value, we skip the next node by rewiring the
 * `next` pointer.
 *
 * Approach:
 *   1. Initialize a pointer `ptr = head`.
 *   2. While `ptr != null`:
 *      - If `ptr.next == null`, break (reached end of list).
 *      - If `ptr.val == ptr.next.val`: a duplicate is found. Set
 *        `ptr.next = ptr.next.next` to bypass the duplicate node.
 *        Note: do NOT advance `ptr` in this case, because the new `ptr.next`
 *        could also be a duplicate.
 *      - Else: advance `ptr = ptr.next` to move to the next distinct value.
 *   3. Return `head` (original head is always preserved).
 *
 * Time Complexity: O(n) — each node is visited at most once (skipped nodes
 *                   are never visited again).
 * Space Complexity: O(1) — only the `ptr` pointer is used, no additional
 *                    data structures.
 *
 * Edge Cases:
 * - Null list (`head == null`): while loop condition fails immediately,
 *   returns null.
 * - Single node list: `ptr.next == null`, loop breaks on first iteration,
 *   returns head unchanged.
 * - All duplicates: e.g., 1->1->1->1 becomes 1.
 * - No duplicates: list returned unchanged.
 *
 * Dry Run:
 * Input: head = 1 -> 1 -> 2 -> 3 -> 3 -> null
 *
 * ptr = 1 (first node)
 *   ptr.val(1) == ptr.next.val(1)? Yes -> ptr.next = 2nd node's next = node(2)
 *   List: 1 -> 2 -> 3 -> 3 -> null, ptr stays at 1
 *   ptr.val(1) == ptr.next.val(2)? No -> ptr = node(2)
 *
 * ptr = 2
 *   ptr.val(2) == ptr.next.val(3)? No -> ptr = node(3)
 *
 * ptr = 3 (first 3)
 *   ptr.val(3) == ptr.next.val(3)? Yes -> ptr.next = node(null)
 *   List: 1 -> 2 -> 3 -> null, ptr stays at 3
 *   ptr.next == null? Yes -> break
 *
 * Return head: 1 -> 2 -> 3 -> null
 *
 * Correctness Check:
 * The algorithm is correct because in a sorted list, all duplicates of a value
 * form a contiguous block. When we encounter `ptr.val == ptr.next.val`, we keep
 * skipping next nodes until a different value or null is reached, then advance
 * ptr. This ensures each distinct value is kept exactly once.
 */
public class RemoveDuplicateFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ptr = head;
        
        while (ptr != null) {
            
            if (ptr.next == null)
                break;
            
            // Skip consecutive duplicate node in sorted order.
            if (ptr.val == ptr.next.val) {
                ptr.next = ptr.next.next;
            } else {
                ptr = ptr.next;
            }
        }
        return head;
    }
}
