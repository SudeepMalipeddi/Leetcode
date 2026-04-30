/*
 * Problem: 19. Remove Nth Node From End of List
 *
 * Problem Statement:
 * Given the head of a singly linked list, remove the nth node from the end of the list and return its head.
 * The challenge is to perform this operation in a single pass through the list.
 *
 * Intuition:
 * To find the nth node from the end without knowing the total length, we can use two pointers (fast and slow).
 * By maintaining a fixed gap of 'n' nodes between the two pointers, when the leading (fast) pointer 
 * reaches the end of the list, the trailing (slow) pointer will be positioned exactly before the node 
 * that needs to be removed.
 *
 * Approach:
 * 1. Initialize two pointers, 'fast' and 'slow', both pointing to the head of the list.
 * 2. Advance the 'fast' pointer 'n' steps forward. This creates the required gap.
 * 3. Check if 'fast' is null. If it is, the node to remove is the head itself (since n equals the list length).
 * 4. Move both 'fast' and 'slow' pointers forward one step at a time until 'fast' reaches the last node.
 * 5. At this point, 'slow' is at the (n+1)-th node from the end.
 * 6. Update 'slow.next' to skip the target node (slow.next.next).
 *
 * Time Complexity: O(L), where L is the length of the linked list. We traverse the list at most once.
 * Space Complexity: O(1), as we only use two pointers regardless of the list size.
 *
 * Edge Cases:
 * - The list has only one node and n=1: The head is removed, returning null.
 * - Removing the head node: Handled by checking if 'fast' becomes null after the initial 'n' steps.
 * - Removing the tail node: Handled naturally by the logic.
 *
 * Dry Run:
 * Input: head = [1, 2, 3, 4, 5], n = 2
 * 1. fast = 1, slow = 1.
 * 2. Move fast 2 steps: fast is now at 3.
 * 3. fast is not null, so we enter the while loop.
 * 4. Loop 1: slow moves to 2, fast moves to 4.
 * 5. Loop 2: slow moves to 3, fast moves to 5.
 * 6. fast.next is now null. Exit loop.
 * 7. slow.next = slow.next.next (3.next = 5). Node 4 is removed.
 * 8. Return head: [1, 2, 3, 5].
 *
 * Correctness Check:
 * The solution correctly handles the "remove head" case and the "one pass" requirement. 
 * Note: The problem assumes n is always valid (1 <= n <= length).
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;

    }
}

public class RemoveNthNodeFromEndofList19 {
    public ListNode removeNode(ListNode head, int n) {
        ListNode fast = head, slow = head;
        
        // Create a gap of n nodes between fast and slow pointers.
        // After this loop, fast is at the (n+1)-th node from the start.
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        // If fast is null, it means n is equal to the length of the list.
        // In this specific case, the node to be removed is the head.
        if (fast == null) {
            return head.next;
        }
        
        // Move both pointers until fast reaches the last node.
        // Because of the initial gap, slow will stop exactly one node before the target.
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // Skip the nth node from the end by re-linking the 'next' pointer.
        slow.next = slow.next.next;

        return head;
    }
}
