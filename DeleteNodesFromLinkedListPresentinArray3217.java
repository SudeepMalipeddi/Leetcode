/*
 * Problem: 3217. Delete Nodes From Linked List Present in Array
 *
 * Problem Statement:
 * Given an array of integers 'nums' and the 'head' of a linked list, remove all nodes 
 * from the linked list that have a value that exists in 'nums'. Return the new head 
 * of the modified linked list.
 *
 * Intuition:
 * Searching for an element in an array takes O(M) time. By transferring the array 
 * elements into a HashSet, we reduce the lookup time to O(1) on average. This allows 
 * us to iterate through the linked list once and decide instantly whether to keep 
 * or discard each node.
 *
 * Approach:
 * 1. Convert the 'nums' array into a HashSet for efficient O(1) lookups.
 * 2. Use a 'dummy' node pointing to the head. This is a standard technique to handle 
 *    cases where the head node itself needs to be deleted, avoiding null pointer 
 *    checks for the head.
 * 3. Maintain a 'prev' pointer (the last node confirmed to be kept) and a 'ptr' 
 *    pointer (the current node being evaluated).
 * 4. If 'ptr.val' is in the set, skip the node by linking 'prev.next' to 'ptr.next'.
 * 5. If 'ptr.val' is not in the set, move 'prev' forward to 'ptr'.
 * 6. Always advance 'ptr' to the next node in the original sequence.
 *
 * Time Complexity: O(N + M)
 * - O(M) to iterate through the 'nums' array and populate the HashSet.
 * - O(N) to traverse the linked list once, where N is the number of nodes.
 *
 * Space Complexity: O(M)
 * - We store the M unique elements of 'nums' in a HashSet.
 *
 * Edge Cases:
 * - The head node needs to be removed.
 * - All nodes in the list need to be removed (returns null).
 * - None of the nodes in the list are in 'nums'.
 * - The list contains duplicate values that are all in 'nums'.
 *
 * Dry Run:
 * nums = [1], head = [1, 2, 1, 3]
 * Set = {1}
 * dummy -> 1 -> 2 -> 1 -> 3
 * ptr=1: In set? Yes. dummy.next = 2. ptr moves to 2.
 * ptr=2: In set? No. prev moves to 2. ptr moves to 1.
 * ptr=1: In set? Yes. prev.next (2.next) = 3. ptr moves to 3.
 * ptr=3: In set? No. prev moves to 3. ptr moves to null.
 * Result: 2 -> 3
 *
 * Correctness Check:
 * The solution correctly uses a dummy node to manage head deletions. The 'prev' 
 * pointer only advances when a node is kept, ensuring that if multiple consecutive 
 * nodes are deleted, the 'prev.next' pointer is updated correctly to skip all of them.
 */
import java.util.HashSet;

public class DeleteNodesFromLinkedListPresentinArray3217 {
    public ListNode modifiedList(int[] nums, ListNode head) {
        // Use a HashSet to achieve O(1) lookup time for values that need to be deleted.
        HashSet<Integer> set = new HashSet<Integer>();
        // Iterate through the active search space while maintaining the intended invariant.
        for (int x : nums) {
            set.add(x);
        }

        // A dummy node is used to simplify logic when the head node itself must be removed.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 'prev' tracks the last node that is guaranteed to stay in the final list.
        // 'ptr' is the iterator for the current node being checked.
        ListNode ptr = head, prev = dummy;

        while (ptr != null) {
            // Store the next node before potentially breaking the link.
            ListNode temp = ptr.next;
            
            if (set.contains(ptr.val)) {
                // If the value is in the set, skip this node by linking the previous node to the next one.
                prev.next = temp;
            } else {
                // If the value is not in the set, this node is kept; move 'prev' forward.
                prev = ptr;
            }
            // Move to the next node in the original list.
            ptr = ptr.next;
        }
        // Return the actual head, which is the node following our dummy.
        return dummy.next;
    }
}
