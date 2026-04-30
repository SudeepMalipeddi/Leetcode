/*
 * Problem: 203. Remove Linked List Elements
 *
 * Problem Statement:
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list 
 * that have Node.val == val, and return the new head.
 *
 * Intuition:
 * To delete a node in a singly linked list, we need to modify the 'next' pointer of the 
 * node immediately preceding the target node. A "dummy" node placed before the head 
 * acts as a sentinel, allowing us to handle head removals and middle/tail removals 
 * using the exact same logic without special-casing the head.
 *
 * Approach:
 * 1. Initialize a dummy node where dummy.next points to the head.
 * 2. Use a traversal pointer (curr) starting at the dummy node.
 * 3. While there is a next node, check its value.
 * 4. If the next node's value matches 'val', skip it by setting curr.next = curr.next.next.
 * 5. If it doesn't match, simply move the curr pointer forward.
 * 6. Return dummy.next, which points to the potentially new head of the list.
 *
 * Time Complexity: O(N), where N is the number of nodes in the linked list. We visit each node exactly once.
 * Space Complexity: O(1) for iterative solutions as we only use a few pointers. O(N) for recursive 
 * solutions due to the recursion stack depth.
 *
 * Edge Cases:
 * - The list is empty (head is null).
 * - All nodes in the list match the target value (returns null).
 * - The target value is only at the head or only at the tail.
 *
 * Dry Run:
 * List: 1 -> 2 -> 6 -> 3, val = 6
 * 1. dummy -> 1 -> 2 -> 6 -> 3. curr = dummy.
 * 2. curr.next (1) != 6. Move curr to 1.
 * 3. curr.next (2) != 6. Move curr to 2.
 * 4. curr.next (6) == 6. Set curr.next = curr.next.next (3). List: dummy -> 1 -> 2 -> 3.
 * 5. curr.next (3) != 6. Move curr to 3.
 * 6. curr.next is null. Loop ends. Return dummy.next (1).
 *
 * Correctness Check:
 * The iterative methods are correct. Note: removeElements2 is intended to be recursive but 
 * calls the iterative removeElements instead of itself; this will still work but isn't 
 * purely recursive.
 */
class RemoveLinkedListElements203 {
    
    public ListNode removeElements(ListNode head, int val) {
        
        if (head == null) {
            return null;
        }
        // Dummy node handles the case where the head itself needs to be removed.
        ListNode dummy = new ListNode();
        dummy.next = head;
        // curr starts at dummy so we can always look ahead to curr.next.
        ListNode curr = dummy;
        
        while (curr.next != null) {
            
            // Bypass node when it matches removal value.
            if (curr.next.val == val) {
                // We do NOT move curr here because the new curr.next needs to be checked.
                curr.next = curr.next.next;
            } else {
                // Only move forward if we didn't delete the next node.
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    
    public ListNode removeElements1(ListNode head, int val) {
        
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        // Two-pointer approach: prev tracks the last confirmed "good" node.
        ListNode prev = dummy;
        ListNode cur = head;

        
        while (cur != null) {
            
            if (cur.val == val) {
                // Link the previous node to the one after current, effectively deleting current.
                prev.next = cur.next;
            } else {
                // Current node is kept, so it becomes the new 'prev'.
                prev = cur;
            }
            // Always move the scanning pointer forward.
            cur = cur.next;
        }
        return dummy.next;
    }

    
    public ListNode removeElements2(ListNode head, int val) {
        
        if (head == null) {
            return null;
        }
        // Recursive logic: solve the problem for the rest of the list first.
        // Note: This calls the iterative removeElements above, not removeElements2.
        ListNode rightSideHead = removeElements(head.next, val);
        
        
        // Post-order processing: decide if the current head should be part of the result.
        if (head.val == val) {
            // If current head matches val, discard it and return the processed tail.
            return rightSideHead;
        }
        // If current head is kept, link it to the processed tail.
        head.next = rightSideHead;
        return head;
    }

}
