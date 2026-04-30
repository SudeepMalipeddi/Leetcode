/*
 * Problem: LeetCode 2095 - Delete the Middle Node of a Linked List
 *
 * Problem Statement:
 * Given the head of a linked list, delete the middle node and return the head of the modified linked list.
 * For a list of size n, the middle node is the ⌊n / 2⌋-th node from the start (0-indexed).
 *
 * Intuition:
 * To delete a node in a singly linked list, we need access to the node immediately preceding it. 
 * We can use the "Tortoise and Hare" (slow and fast pointers) technique to find the middle. 
 * By moving a fast pointer twice as fast as a slow pointer, the slow pointer will reach 
 * the middle exactly when the fast pointer reaches the end.
 *
 * Approach:
 * 1. Handle the edge case: If the list has only one node, deleting the middle results in an empty list (return null).
 * 2. Initialize 'slow' and 'fast' pointers at the head, and a 'prev' pointer to null.
 * 3. Traverse the list: 'fast' moves two steps, 'slow' moves one step. 'prev' tracks the node before 'slow'.
 * 4. Once 'fast' reaches the end, 'slow' points to the middle node.
 * 5. Update 'prev.next' to point to 'slow.next', effectively bypassing and removing the middle node.
 *
 * Time Complexity: O(n) where n is the number of nodes. We traverse the list at most once.
 * Space Complexity: O(1) as we only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - List with 1 node: Handled by the initial guard clause (returns null).
 * - List with 2 nodes: 'slow' will point to the 2nd node, 'prev' to the 1st. 1st node's next becomes null.
 *
 * Dry Run:
 * Input: 1 -> 2 -> 3 -> 4 -> 5
 * 1. Initial: slow=1, fast=1, prev=null
 * 2. Step 1: prev=1, slow=2, fast=3
 * 3. Step 2: prev=2, slow=3, fast=5
 * 4. Loop ends (fast.next is null).
 * 5. prev.next = slow.next (2.next = 4).
 * Result: 1 -> 2 -> 4 -> 5
 *
 * Correctness Check:
 * The solution correctly identifies the ⌊n / 2⌋-th node. For n=4 (1->2->3->4), the middle is index 2 (value 3).
 * slow starts at 1, fast at 1.
 * Step 1: prev=1, slow=2, fast=3.
 * Step 2: prev=2, slow=3, fast=null.
 * Loop ends. prev(2).next = slow(3).next(4). Result: 1->2->4. Correct.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode ptr) {
        this.val = val;
        this.next = ptr;
    }
}

public class DeletetheMiddleNodeofaLinkedList2095 {
    public ListNode deleteMiddle(ListNode head) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        // If the list is empty or has only one node, there is no "middle" to keep, or 
        // deleting the only node results in an empty list.
        if (head == null || head.next == null) {
            return null;
        }

        // slow moves 1 step, fast moves 2 steps. 
        // prev is used to keep track of the node right before the middle (slow).
        ListNode slow = head, fast = head, prev = null;

        // Iterate through the active search space while maintaining the intended invariant.
        // The condition fast.next != null ensures we can move fast two steps ahead.
        while (fast != null && fast.next != null) {
            prev = slow;           // Track the predecessor of the middle node
            slow = slow.next;      // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps
        }

        // At this point, slow is the middle node. 
        // We delete it by linking its predecessor (prev) to its successor (slow.next).
        prev.next = slow.next;

        // Return the original head of the modified list.
        return head;
    }

    public static void main(String[] args) {
        // Test Case: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        DeletetheMiddleNodeofaLinkedList2095 obj = new DeletetheMiddleNodeofaLinkedList2095();
        obj.deleteMiddle(head);

        // Print the modified list to verify the middle node (3) was removed.
        ListNode ptr = head;
        while (ptr != null) {
            System.out.print(ptr.val + " ");
            ptr = ptr.next;
        }
    }
}
