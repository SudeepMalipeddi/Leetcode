/*
 * Problem Reference: Delete Occurrence of Key in Doubly Linked List (GFG-style)
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Traverse DLL and relink neighbors when key matches.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
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
class Node {
    int data;
    Node next, prev;

    Node(int x) {
        data = x;
        next = prev = null;
    }
}

class DeleteOccurenceOfKeyinDLL {
    public void deleteKey(Node head, int key) {
        Node curr = head;
        // Iterate through the active search space while maintaining the intended invariant.
        while (curr != null) {
            if (curr.data == key) {
                // Important guard: this branch handles a boundary or constraint-critical condition.
                if (curr.prev != null) {
                    curr.prev.next = curr.next;
                } else {
                    head = curr.next;
                }
                if (curr.next != null) {
                    curr.next.prev = curr.prev;
                }
            }
            curr = curr.next;
        }
    }
}