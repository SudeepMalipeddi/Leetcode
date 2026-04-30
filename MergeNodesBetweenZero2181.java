/*
 * Problem Statement:
 * Given a linked list whose values are separated by zero-valued nodes,
 * merge each segment between two zeros into one node containing the segment sum.
 *
 * Intuition:
 * Accumulate values while moving forward; when a zero boundary is reached,
 * write the accumulated sum into the output chain and reset.
 *
 * Approach:
 * Use two pointers on the same list:
 * - fast walks every node to accumulate segment sum,
 * - slow marks where next merged sum should be written.
 * On each zero boundary, store sum at slow and move slow forward.
 *
 * Time Complexity:
 * O(n), single traversal.
 *
 * Space Complexity:
 * O(1), in-place reuse of existing nodes.
 *
 * Edge Cases handled:
 * - Multiple segments.
 * - Segment sums that include many interior nodes.
 * - Final tail is trimmed with slow.next = null.
 *
 * Dry Run:
 * 0 -> 3 -> 1 -> 0 -> 4 -> 5 -> 2 -> 0
 * segment sums: 4 and 11
 * output list becomes: 4 -> 11
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 2181 constraints where list starts and ends with 0.
 * Assumes this project's ListNode uses field name 'data' (not 'val').
 *
 * Correctness Check:
 * Every non-zero node contributes to exactly one segment sum, and each zero closes a segment.
 */

public class MergeNodesBetweenZero2181 {
    public ListNode mergeNodes(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        int sum = 0;

        // Traverse until fast is at last zero node (fast.next becomes null after it).
        while (fast.next != null) {
            sum += fast.data; // Add current node into running segment sum.

            if (fast.next.data == 0) { // Next node closes the current segment.
                slow.data = sum; // Write merged segment sum to output position.
                if (fast.next.next != null)
                    slow = slow.next; // Advance output pointer only if another segment exists.
                sum = 0; // Reset for next segment.
            }
            fast = fast.next;
        }
        slow.next = null; // Cut off leftover original nodes beyond merged output.
        return head;
    }
}
