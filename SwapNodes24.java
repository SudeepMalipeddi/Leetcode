/*
 * Problem: 24. Swap Nodes in Pairs
 *
 * Problem Statement:
 * Given a linked list, swap every two adjacent nodes and return its head. 
 * You must solve the problem without modifying the values in the list's nodes 
 * (i.e., only nodes themselves may be changed).
 *
 * Intuition:
 * To swap two nodes (A -> B), we need to make B point to A. However, we must also 
 * ensure that A points to the rest of the list (the next pair), and that the 
 * node preceding A (from the previous swap) points to B.
 *
 * Approach:
 * 1. Handle base cases: if the list is empty or has only one node, return it.
 * 2. Identify the second node as the new head of the entire list.
 * 3. Iterate through the list in pairs using a 'cur' pointer.
 * 4. For each pair, perform the swap by reassigning 'next' pointers.
 * 5. Use a "look-ahead" check to link the current pair's tail to the next pair's head.
 *
 * Time Complexity: O(N), where N is the number of nodes in the linked list. 
 * We traverse the list exactly once.
 * Space Complexity: O(1), as we only use a few pointer variables regardless of list size.
 *
 * Edge Cases:
 * - Empty list (head is null).
 * - Single node list (head.next is null).
 * - List with an odd number of nodes (the last node remains in its position).
 *
 * Dry Run:
 * Input: 1 -> 2 -> 3 -> 4
 * 1. Initial: head=1, newHead=2, cur=1.
 * 2. Loop 1: temp=1, cur=2. 
 *    - 1.next points to 3.
 *    - 2.next points to 1. (List: 2 -> 1 -> 3 -> 4)
 *    - cur moves to 3.
 *    - Look-ahead: 3 and 4 exist, so 1.next points to 4. (List: 2 -> 1 -> 4 -> 3)
 * 3. Loop 2: temp=3, cur=4.
 *    - 3.next points to null.
 *    - 4.next points to 3. (List: 2 -> 1 -> 4 -> 3 -> null)
 *    - cur moves to null. Loop ends.
 * Result: 2 -> 1 -> 4 -> 3.
 *
 * Correctness Check:
 * The solution is correct. It properly handles the pointer re-assignments 
 * required to swap nodes and maintains the connectivity of the list.
 */
public class SwapNodes24 {
    public ListNode swapPairs(ListNode head) {
        // Base case: If the list is empty or has only one node, no swapping is needed.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        ListNode cur = head;
        // The second node will become the new head of the list after the first swap.
        ListNode newHead = head.next;

        // Iterate as long as there is a pair of nodes to swap.
        while (cur != null && cur.next != null) {
            // 'temp' tracks the first node of the current pair.
            ListNode temp = cur;
            // 'cur' moves to the second node of the current pair.
            cur = cur.next;

            // Step 1: Link the first node to the node following the current pair.
            temp.next = cur.next;
            // Step 2: Link the second node back to the first node (the actual swap).
            cur.next = temp;

            // Move 'cur' to the start of the next pair.
            cur = temp.next;

            /* 
             * If another pair exists, the current pair's tail (temp) must point 
             * to the second node of that next pair, because that second node 
             * will become the new head of that pair after it is swapped.
             */
            if (cur != null && cur.next != null) {
                temp.next = cur.next;
            }
        }
        return newHead;
    }
}
