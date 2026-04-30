/*
 * Problem: 2130. Maximum Twin Sum of a Linked List
 *
 * Problem Statement:
 * In a linked list of even length n, the i-th node is the twin of the (n-1-i)-th node.
 * We need to find the maximum sum of all such twin pairs in the list.
 *
 * Intuition:
 * Twin nodes are symmetric around the center (like a palindrome). To sum them efficiently 
 * without extra space, we need to access the second half of the list in reverse order. 
 * By reversing the second half, the "twin" of the first node becomes the head of the 
 * reversed second half, allowing a single linear pass to calculate all sums.
 *
 * Approach:
 * 1. Use the "Slow and Fast Pointer" technique to find the midpoint of the linked list.
 * 2. Reverse the second half of the list starting from the slow pointer.
 * 3. Iterate simultaneously through the first half (starting from head) and the 
 *    reversed second half, tracking the maximum sum encountered.
 *
 * Time Complexity: O(n)
 * - Finding the middle takes O(n/2).
 * - Reversing the second half takes O(n/2).
 * - Calculating twin sums takes O(n/2).
 * All operations are linear relative to the number of nodes.
 *
 * Space Complexity: O(1)
 * The reversal is done in-place by modifying node pointers, requiring no extra data structures.
 *
 * Edge Cases:
 * - List with exactly 2 nodes: Handled by the initial check and the general logic.
 * - Long even-length lists: Handled by the O(n) traversal.
 *
 * Dry Run:
 * Input: 5 -> 4 -> 2 -> 1
 * 1. Slow/Fast: Slow ends at node '2', Fast ends at null.
 * 2. Reverse second half: 2 -> 1 becomes 1 -> 2.
 * 3. Summing:
 *    - Pair 1: 5 (head) + 1 (reversed head) = 6. Max = 6.
 *    - Pair 2: 4 (next) + 2 (next) = 6. Max = 6.
 * Result: 6.
 *
 * Correctness Check:
 * The solution correctly identifies the start of the second half for even-length lists.
 * Note: The original list structure is modified (reversed) during the process.
 */

public class MaximumTwinSumofaLinkedList2130 {
    public static int PairSum(ListNode head) {
        // Direct answer for smallest valid even-length list (n=2).
        if (head.next.next == null) {
            return head.val + head.next.val;
        }
        int res = 0;
        ListNode ptr = head, cptr = head;

        // Tortoise and Hare: 'ptr' (slow) moves 1 step, 'cptr' (fast) moves 2 steps.
        // When 'cptr' reaches the end, 'ptr' will be at the start of the second half.
        while (cptr != null && cptr.next != null) {
            ptr = ptr.next;
            cptr = cptr.next.next;
        }

        // Reverse the second half of the list starting from the midpoint.
        // 'cptr' now points to the head of the reversed second half.
        cptr = reverse(ptr); 
        ptr = head; // Reset 'ptr' to the start of the first half.

        // Iterate through both halves. Since the second half is reversed, 
        // ptr and cptr now point to 'twins' (i and n-1-i).
        while (cptr != null) {
            res = Math.max(res, ptr.val + cptr.val);
            ptr = ptr.next;
            cptr = cptr.next;
        }
        return res;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode ptr = head;

        // Standard iterative linked-list reversal logic.
        while (ptr != null) {
            ListNode temp = ptr.next; // Temporarily store the next node.
            ptr.next = prev;          // Flip the current node's pointer to the previous node.
            prev = ptr;               // Move 'prev' forward to the current node.
            ptr = temp;               // Move 'ptr' forward to the next node in the original sequence.
        }
        // 'prev' ends up as the new head of the reversed segment.
        return prev;
    }
}
