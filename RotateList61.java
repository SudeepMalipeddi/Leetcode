/*
 * Problem: 61. Rotate List
 *
 * Problem Statement:
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Intuition:
 * Rotating a list by k positions is equivalent to moving the last (k % length) nodes 
 * to the front of the list. By identifying the new tail (at position length - k) 
 * and the old tail, we can rearrange the pointers to "shift" the list.
 *
 * Approach:
 * 1. Traverse the list to find its length and the current tail node.
 * 2. Use k % length to handle cases where k is larger than the list size.
 * 3. If k is 0 (no rotation needed), return the head immediately.
 * 4. Find the new tail node at index (len - k - 1).
 * 5. Set the node after the new tail as the new head, break the link from the 
 *    new tail, and connect the old tail to the original head.
 *
 * Time Complexity: O(N) where N is the number of nodes in the list. We traverse 
 * the list once to find the length and once more (partially) to find the new tail.
 * Space Complexity: O(1) as we only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - head is null: Handled by the first if-statement.
 * - k = 0: Handled by the second if-statement.
 * - k is a multiple of length: Handled by k % len == 0 check.
 * - List has only one node: len will be 1, k % 1 will always be 0, returns head.
 *
 * Dry Run:
 * Input: head = [1, 2, 3, 4, 5], k = 2
 * 1. len = 5, ptr points to node 5.
 * 2. k = 2 % 5 = 2.
 * 3. Find new tail: Loop runs (5 - 2 - 1) = 2 times.
 *    - i=0: curr moves to 2.
 *    - i=1: curr moves to 3.
 * 4. newHead = curr.next (node 4).
 * 5. curr.next = null (3 -> null).
 * 6. ptr.next = head (5 -> 1).
 * Result: [4, 5, 1, 2, 3]
 *
 * Correctness Check:
 * The solution correctly handles the rotation logic and edge cases. Note that 
 * the variable 'ptr' is used to capture the original tail during the first pass.
 */
public class RotateList61 {
    public ListNode rotateRight(ListNode head, int k) {
        // Handle empty list edge case
        if (head == null)
            return null;
        
        // No rotation needed if k is 0
        if (k == 0)
            return head;
            
        int len = 0;
        ListNode curr = head, ptr = head;
        
        // First pass: Calculate length and identify the original tail node (ptr)
        while (curr != null) {
            len++;
            ptr = curr; // ptr will end up at the last non-null node
            curr = curr.next;
        }
        
        // Optimization: Rotation by length is a full cycle (no change)
        k = k % len;
        if (k == 0)
            return head;
            
        curr = head;
        // Second pass: Move to the node that will become the new tail.
        // The new tail is at index (len - k - 1).
        for (int i = 0; i < len - k - 1; i++) {
            curr = curr.next;
        }
        
        // The node after the new tail becomes the new head of the rotated list
        ListNode newHead = curr.next;
        
        // Break the list at the new tail to prevent cycles
        curr.next = null;
        
        // Connect the original tail to the original head
        ptr.next = head;
        
        return newHead;
    }
}
