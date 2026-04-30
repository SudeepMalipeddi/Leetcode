/*
 * Problem: 2181. Merge Nodes in Between Zeros
 *
 * Problem Statement:
 * Given a linked list where nodes are separated by zeros, merge all nodes between two zeros
 * into a single node containing their sum. The list starts and ends with a zero.
 *
 * Intuition:
 * The problem asks us to transform segments of nodes into single nodes. Since the output
 * size is always smaller than or equal to the input size, we can perform an in-place
 * modification. By using a 'slow' pointer to write sums and a 'fast' pointer to read
 * and aggregate values, we avoid allocating new nodes.
 *
 * Approach:
 * 1. Initialize 'slow' and 'fast' pointers at the head node (the first zero).
 * 2. Iterate through the list with 'fast'. Accumulate 'fast.data' into a running 'sum'.
 * 3. When 'fast.next' is a zero, it marks the end of a segment.
 * 4. Write the 'sum' into the 'slow' node's data field.
 * 5. If there's another segment to process (fast.next.next != null), move 'slow' to its next node.
 * 6. Reset 'sum' to 0 for the next segment.
 * 7. After the loop, set 'slow.next' to null to truncate the original list.
 *
 * Time Complexity: O(n), where n is the number of nodes in the linked list. We visit each node once.
 * Space Complexity: O(1), as we modify the existing list in-place and use only a few pointers.
 *
 * Edge Cases:
 * - Minimum list: 0 -> 5 -> 0 => Output: 5
 * - Multiple segments: 0 -> 1 -> 2 -> 0 -> 3 -> 0 => Output: 3 -> 3
 *
 * Dry Run:
 * Input: 0 -> 3 -> 1 -> 0 -> 4 -> 5 -> 2 -> 0
 * 1. fast=0, sum=0. fast.next=3.
 * 2. fast=3, sum=3. fast.next=1.
 * 3. fast=1, sum=4. fast.next=0. slow.data=4. fast.next.next is 4, so slow moves. sum=0.
 * 4. fast=0, sum=0. fast.next=4.
 * 5. fast=4, sum=4. fast.next=5.
 * 6. fast=5, sum=9. fast.next=2.
 * 7. fast=2, sum=11. fast.next=0. slow.data=11. fast.next.next is null, slow stays. sum=0.
 * 8. Loop ends. slow.next=null. Result: 4 -> 11.
 *
 * Correctness Check:
 * The solution is correct. It reuses the head node and subsequent nodes to store sums.
 * The check `fast.next.next != null` correctly prevents the slow pointer from moving
 * to a node that won't be used, allowing for clean truncation at the end.
 */

public class MergeNodesBetweenZero2181 {
    public ListNode mergeNodes(ListNode head) {
        // 'slow' tracks the node where we will write the next merged sum.
        ListNode slow = head;
        // 'fast' traverses the list to read values and identify segment boundaries.
        ListNode fast = head;
        int sum = 0;

        // Traverse until fast is at last zero node (fast.next becomes null after it).
        while (fast.next != null) {
            sum += fast.data; // Add current node into running segment sum.

            if (fast.next.data == 0) { // Next node closes the current segment.
                slow.data = sum; // Write merged segment sum to output position.
                // Only advance 'slow' if there is another segment to process.
                // This ensures 'slow' stops at the last node of our new merged list.
                if (fast.next.next != null)
                    slow = slow.next; // Advance output pointer only if another segment exists.
                sum = 0; // Reset for next segment.
            }
            fast = fast.next;
        }
        // Terminate the list at the 'slow' pointer to remove the remaining original nodes.
        slow.next = null; // Cut off leftover original nodes beyond merged output.
        return head;
    }
}
