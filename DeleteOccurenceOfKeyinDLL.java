/*
 * Problem: Delete all occurrences of a given key in a Doubly Linked List
 *
 * Problem Statement:
 * Given the head of a Doubly Linked List and an integer 'key', delete all nodes 
 * that contain the value 'key'. Modify the list in-place.
 *
 * Intuition:
 * In a Doubly Linked List (DLL), each node has pointers to both its predecessor and successor. 
 * To delete a node, we simply need to "stitch" its neighbors together, effectively 
 * bypassing the current node so it is no longer part of the chain.
 *
 * Approach:
 * 1. Initialize a pointer 'curr' to the head of the list.
 * 2. Traverse the list node by node.
 * 3. If curr.data matches the key:
 *    a. If the node is not the head (prev != null), set the previous node's 'next' to curr's 'next'.
 *    b. If the node is the head, update the head pointer to the next node.
 *    c. If the node is not the tail (next != null), set the next node's 'prev' to curr's 'prev'.
 * 4. Move 'curr' to the next node and repeat until the end of the list.
 *
 * Time Complexity: O(n) where n is the number of nodes in the DLL, as we visit each node exactly once.
 * Space Complexity: O(1) as we only use a single pointer for traversal and modify the list in-place.
 *
 * Edge Cases:
 * - Key is at the head: Must update the head reference.
 * - Key is at the tail: Must update the previous node's next pointer to null.
 * - All nodes contain the key: List becomes empty.
 * - Key not present: List remains unchanged.
 *
 * Dry Run:
 * List: 1 <-> 2 <-> 2 <-> 3, Key: 2
 * 1. curr at 1: 1 != 2, move curr.
 * 2. curr at 2: match! 2.prev (1).next = 2.next (2). 2.next (2).prev = 2.prev (1).
 * 3. curr at 2: match! 2.prev (1).next = 2.next (3). 2.next (3).prev = 2.prev (1).
 * 4. curr at 3: 3 != 2, move curr.
 * Result: 1 <-> 3
 *
 * Correctness Check:
 * In Java, objects are passed by value (the reference value). Updating 'head = curr.next' 
 * inside this method only changes the local 'head' variable. To reflect the change 
 * to the caller, this method should ideally return the new head or use a wrapper class.
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
        // Iterate through the list until we reach the end (null).
        while (curr != null) {
            if (curr.data == key) {
                // If the node to delete is NOT the head, link the previous node to the next node.
                if (curr.prev != null) {
                    curr.prev.next = curr.next;
                } else {
                    // If the node to delete IS the head, move the head pointer forward.
                    // Note: This change is local to this method scope in Java.
                    head = curr.next;
                }
                
                // If the node to delete is NOT the tail, link the next node back to the previous node.
                if (curr.next != null) {
                    curr.next.prev = curr.prev;
                }
            }
            // Move to the next node in the original sequence.
            // Note: curr.next still points to the original next node even if we unlinked curr.
            curr = curr.next;
        }
    }
}
