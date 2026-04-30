/*
 * Problem: 328. Odd Even Linked List
 *
 * Problem Statement:
 * Given the head of a singly linked list, group all nodes with odd indices together 
 * followed by nodes with even indices, and return the reordered list. 
 * The first node is considered index 1 (odd), the second index 2 (even), and so on.
 *
 * Intuition:
 * Instead of creating new nodes, we can rearrange the existing nodes by maintaining 
 * two separate chains: one for odd-indexed nodes and one for even-indexed nodes. 
 * By traversing the list once and jumping over nodes (odd.next = odd.next.next), 
 * we effectively split the list into two interleaved sequences.
 *
 * Approach:
 * 1. Handle base cases: if the list has 0 or 1 node, no rearrangement is needed.
 * 2. Initialize 'odd' pointer at the head and 'even' pointer at head.next.
 * 3. Store 'evenHead' to link the end of the odd chain to the start of the even chain later.
 * 4. Iterate through the list:
 *    - Connect the current odd node to the next odd node (skipping the even node).
 *    - Connect the current even node to the next even node (skipping the odd node).
 *    - Advance both pointers.
 * 5. Finally, attach the tail of the odd list to the head of the even list.
 *
 * Time Complexity: O(n) where n is the number of nodes, as we traverse the list exactly once.
 * Space Complexity: O(1) as we only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - Empty list (head == null): returns null.
 * - Single node list: returns the head.
 * - Two nodes: returns the head (already in odd-even order).
 *
 * Dry Run:
 * Input: 1 -> 2 -> 3 -> 4 -> 5
 * Initial: odd=1, even=2, evenHead=2
 * Iteration 1: 
 *   1.next = 3; odd = 3
 *   2.next = 4; even = 4
 * Iteration 2:
 *   3.next = 5; odd = 5
 *   4.next = null; even = null
 * Loop ends (even is null).
 * Final step: odd.next (5.next) = evenHead (2).
 * Result: 1 -> 3 -> 5 -> 2 -> 4
 *
 * Correctness Check:
 * The solution correctly handles both even and odd length lists. The loop condition 
 * (even != null && even.next != null) ensures we don't encounter NullPointerExceptions 
 * when accessing even.next.next.
 */
public class OddEvenLinkedList328 {
    public ListNode oddevenList(ListNode head) {
        
        // If the list is empty or has only one node, it's already "sorted" by odd/even indices.
        if (head == null || head.next == null) {
            return head;
        }
        // 'odd' starts at index 1, 'even' starts at index 2.
        // 'evenhead' is crucial to keep track of where the even chain starts.
        ListNode odd = head, even = head.next, evenhead = head.next;

        
        // Relink odd and even chains in-place while preserving relative order.
        // We use 'even' to drive the loop because it is always ahead of 'odd'.
        while (even != null && even.next != null) {
            // Point the current odd node to the next odd node (skipping the even node).
            odd.next = odd.next.next;
            // Point the current even node to the next even node (skipping the odd node).
            even.next = even.next.next;
            // Move pointers forward to their newly assigned next nodes.
            odd = odd.next;
            even = even.next;
        }
        // After the loop, the odd chain is complete. Connect its tail to the head of the even chain.
        odd.next = evenhead;

        return head;
    }
}
