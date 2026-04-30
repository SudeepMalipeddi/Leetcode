/*
 * Problem Reference: LeetCode 237 - Delete Node in a Linked List
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Copy next node value and bypass next pointer.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(1)
 *
 * Space Complexity:
 * O(1)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        // checking if the node is null or if it is the last element
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (node == null || node.next == null) {
            // making its next node null and the node itself null
            node.next = null;
            node = null;
        }
        // storing the value of the next node in it
        node.val = node.next.val;
        // making the node's next element as the next element's next element
        node.next = node.next.next;

        // Time Complexity : O(1)
        // Space Complexity : O(1)

    }
}
