/*
 * Problem: LeetCode 206 - Reverse Linked List
 *
 * Problem Statement:
 * Given the head of a singly linked list, reverse the list and return the new
 * head (the original tail). The reversal must be done in-place.
 *
 * Intuition:
 * Every node's `next` pointer should point to its predecessor instead of its
 * successor. By maintaining a `prev` pointer representing the already-reversed
 * prefix, we iterate through the list, reversing one link at a time. After
 * processing all nodes, `prev` points to the original tail, which becomes the
 * new head.
 *
 * Approach:
 *   1. Initialize `prev = null` (the reversed prefix starts empty).
 *   2. Initialize `curr = head` (the node currently being processed).
 *   3. While `curr != null`:
 *      - Save the next node: `ListNode nextTemp = curr.next`.
 *      - Reverse the link: `curr.next = prev`.
 *      - Advance `prev` to current node: `prev = curr`.
 *      - Advance `curr` to the saved next node: `curr = nextTemp`.
 *   4. Return `prev` (the new head of the reversed list).
 *
 * Time Complexity: O(n) — each node is visited exactly once.
 * Space Complexity: O(1) — only three pointers (`prev`, `curr`, `nextTemp`)
 *                    are used regardless of list size.
 *
 * Edge Cases:
 * - Empty list (`head == null`): while loop skips, returns null.
 * - Single node list: one iteration reverses the link then exits, returns the
 *   same node as new head.
 *
 * Dry Run:
 * Input: head = 1 -> 2 -> 3 -> 4 -> null
 *
 * Initial: prev=null, curr=1
 *
 * Iteration 1:
 *   nextTemp = 2
 *   curr.next = null       (1 -> null)
 *   prev = 1, curr = 2
 *   State: null <- 1    2 -> 3 -> 4 -> null
 *
 * Iteration 2:
 *   nextTemp = 3
 *   curr.next = 1         (2 -> 1)
 *   prev = 2, curr = 3
 *   State: null <- 1 <- 2    3 -> 4 -> null
 *
 * Iteration 3:
 *   nextTemp = 4
 *   curr.next = 2         (3 -> 2)
 *   prev = 3, curr = 4
 *   State: null <- 1 <- 2 <- 3    4 -> null
 *
 * Iteration 4:
 *   nextTemp = null
 *   curr.next = 3         (4 -> 3)
 *   prev = 4, curr = null
 *   State: null <- 1 <- 2 <- 3 <- 4
 *
 * curr == null, exit loop. Return prev = 4.
 * Output: 4 -> 3 -> 2 -> 1 -> null
 *
 * Correctness Check:
 * The invariant is that `prev` always points to the head of the reversed prefix
 * (nodes already processed in reverse order), and `curr` points to the next
 * unprocessed node. Each iteration prepends `curr` to the front of the reversed
 * prefix. When all nodes are processed, `prev` is the fully reversed list.
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
