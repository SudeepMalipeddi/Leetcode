/*
 * Problem: 234. Palindrome Linked List
 *
 * Problem Statement:
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * A palindrome is a sequence that reads the same forward and backward.
 *
 * Intuition:
 * To check for a palindrome in O(1) space, we need to compare the first half of the list 
 * with the second half. Since linked lists are unidirectional, we cannot traverse 
 * backwards from the end. The solution is to find the middle, reverse the second half, 
 * and then perform a linear comparison.
 *
 * Approach:
 * 1. Use the "Tortoise and Hare" (slow and fast pointers) to find the midpoint of the list.
 * 2. Reverse the second half of the linked list in-place.
 * 3. Compare the values of the first half and the reversed second half node by node.
 * 4. If any values mismatch, it's not a palindrome. Otherwise, it is.
 *
 * Time Complexity: O(n), where n is the number of nodes. We traverse the list to find the 
 * middle, reverse the second half, and compare the halves.
 * Space Complexity: O(1), as we modify the list in-place and only use a few pointer variables.
 *
 * Edge Cases:
 * - Single node: Handled by the initial check (returns true).
 * - Two nodes: Correctly compares the two values.
 * - Odd length: The middle node is included in the reversed second half; comparison 
 *   terminates correctly when the second half is exhausted.
 *
 * Dry Run:
 * Input: 1 -> 2 -> 2 -> 1
 * 1. Fast/Slow: Slow (cptr) reaches the first '2' (middle).
 * 2. Reverse: The second half (2 -> 1) is reversed to (1 -> 2).
 * 3. Compare: First half (1 -> 2) vs Reversed second half (1 -> 2). All match.
 *
 * Correctness Check:
 * The logic is correct for O(n) time and O(1) space. Note: The input list is modified 
 * by the reversal. In some interview contexts, you may be asked to reverse it back 
 * to its original state before returning.
 */
public class PalindromeLinkedList234 {

    public boolean isPallindrome(ListNode head) {
        
        // A list with only one node is always a palindrome.
        if (head.next == null) {
            return true;
        }
        ListNode ptr = head, cptr = head;
        
        // Fast/slow pointers locate middle of the list.
        // 'ptr' (fast) moves two steps while 'cptr' (slow) moves one step.
        while (ptr != null && ptr.next != null) {
            ptr = ptr.next.next;
            cptr = cptr.next;
        }

        ListNode prev = null;
        
        // Reverse second half in-place for direct value comparison.
        // We use a standard iterative reversal starting from the midpoint (cptr).
        while (cptr != null) {
            ListNode temp = cptr.next; // Store next node
            cptr.next = prev;          // Reverse current node's pointer
            prev = cptr;               // Move prev forward
            cptr = temp;               // Move current forward
        }

        // Reset 'ptr' to the start of the list and 'cptr' to the head of the reversed second half.
        ptr = head;
        cptr = prev;
        
        // Compare the two halves node by node.
        while (cptr != null) {
            
            // If values differ, the list is not a palindrome.
            if (ptr.val != cptr.val) {
                return false;
            }
            ptr = ptr.next;
            cptr = cptr.next;
        }
        return true;
    }
}
