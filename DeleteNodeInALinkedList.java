/*
 * Problem: LeetCode 237 - Delete Node in a Linked List
 *
 * Problem Statement:
 * You are given a node in a singly linked list that is to be deleted. You will not be given access to the head of the list.
 * Instead, you are given access to the node to be deleted directly. It is guaranteed that the node to be deleted is not a tail node.
 *
 * Intuition:
 * In a singly linked list, deleting a node usually requires access to the previous node to update its 'next' pointer. 
 * Since we only have the current node, we can't modify the previous node's pointer. 
 * The trick is to "disguise" the current node as its successor by copying the successor's data, and then removing the successor.
 *
 * Approach:
 * 1. Copy the value of the next node into the current node.
 * 2. Update the current node's next pointer to point to the node after the next node (effectively skipping the next node).
 *
 * Time Complexity: O(1) - We perform a constant number of operations regardless of list size.
 * Space Complexity: O(1) - No extra memory is allocated.
 *
 * Edge Cases:
 * - Node is the tail: The problem constraints usually guarantee this won't happen, but the code handles it by attempting to nullify references.
 * - Node is null: Handled by the initial guard clause.
 *
 * Dry Run:
 * List: 4 -> 5 -> 1 -> 9, Node to delete: 5
 * 1. node.val = node.next.val (1) -> List effectively becomes 4 -> 1 -> 1 -> 9
 * 2. node.next = node.next.next (9) -> List becomes 4 -> 1 -> 9
 *
 * Correctness Check:
 * The solution is correct for non-tail nodes. Note that in Java, setting the local variable 'node = null' 
 * does not affect the actual object in the heap or the previous node's reference to it.
 */
public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        // checking if the node is null or if it is the last element
        // Important guard: this branch handles a boundary or constraint-critical condition.
        // Note: In standard LeetCode constraints, the node is guaranteed not to be the tail.
        if (node == null || node.next == null) {
            // making its next node null and the node itself null
            node.next = null;
            node = null;
        }
        // Overwrite the current node's value with the value of the next node.
        // This effectively "moves" the next node's data into the current position.
        node.val = node.next.val;
        
        // Bypass the next node by pointing the current node's next pointer to the one after it.
        // This removes the original next node from the list structure.
        node.next = node.next.next;

        // Time Complexity : O(1)
        // Space Complexity : O(1)

    }
}
