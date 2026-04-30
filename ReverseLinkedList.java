/*
 * Problem (from file name): Reverse Linked List (LeetCode 206).
 * Problem Statement:
 * Reverse a singly linked list and return the new head.
 *
 * Intuition:
 * Every node should point to its previous node instead of its next node.
 * While scanning left-to-right, we can rewire one pointer at a time and carry
 * the already-reversed prefix in `prev`.
 *
 * Approach:
 * 1. Keep three pointers: `prev` (reversed part), `curr` (current node), `nextTemp` (saved next).
 * 2. Reverse `curr.next` to point to `prev`.
 * 3. Advance `prev` and `curr`.
 * 4. When `curr` becomes null, `prev` is the new head.
 *
 * Time Complexity: O(n), because each node is visited once.
 * Space Complexity: O(1), only constant extra pointers are used.
 *
 * Edge Cases handled:
 * - Empty list (`head == null`) -> returns null.
 * - Single node list -> unchanged.
 *
 * Dry Run:
 * head: 1 -> 2 -> 3 -> null
 * Iteration 1: reverse 1 -> null, prev=1, curr=2
 * Iteration 2: reverse 2 -> 1,    prev=2, curr=3
 * Iteration 3: reverse 3 -> 2,    prev=3, curr=null
 * Return prev => 3 -> 2 -> 1 -> null
 *
 * Correctness Check:
 * This implementation is correct and matches the standard iterative solution.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        // Iterative solution
        // Initialize prev to null
        ListNode prev = null;
        // Initialize curr to head
        ListNode curr = head;
        // While loop to iterate through the linked list
        while (curr != null) {
            // Initialize nextTemp to curr.next
            ListNode nextTemp = curr.next;
            // Set curr.next to prev
            curr.next = prev;
            // Set prev to curr
            prev = curr;
            // Set curr to nextTemp
            curr = nextTemp;
        }
        // Return prev
        return prev;
    }
}
