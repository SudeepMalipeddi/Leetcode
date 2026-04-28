/*
 * Problem: LeetCode 2. Add Two Numbers
 *
 * Problem Statement:
 * Two non-empty linked lists represent two non-negative integers in reverse order.
 * Each node contains a single digit. Add the two numbers and return the sum as a
 * linked list in the same reverse order.
 *
 * Intuition:
 * This mirrors grade-school addition: add digit by digit with a carry, moving forward
 * along both lists.
 *
 * Approach:
 * 1. Use a dummy head to build the result list.
 * 2. Traverse both lists simultaneously, summing digits and carry.
 * 3. Append sum % 10 as the current digit and carry = sum / 10.
 * 4. After traversal, append any remaining carry.
 *
 * Time Complexity: O(max(N, M)) because each node is processed once.
 * Space Complexity: O(max(N, M)) for the output list.
 *
 * Edge Cases handled:
 * - Lists of different lengths.
 * - Final carry producing an extra node (e.g., 5 + 5 = 10).
 *
 * Dry Run:
 * l1 = 2 -> 4 -> 3, l2 = 5 -> 6 -> 4
 * Step1: 2 + 5 = 7 -> node 7, carry 0
 * Step2: 4 + 6 = 10 -> node 0, carry 1
 * Step3: 3 + 4 + 1 = 8 -> node 8, carry 0
 * Result: 7 -> 0 -> 8
 *
 * Correctness Check:
 * This is the standard correct solution for LeetCode. Assumes a standard ListNode
 * definition with fields val and next.
 *
 * LeetCode Match:
 * LeetCode 2 - "Add Two Numbers".
 */
/**
 * LeetCode 2. Add Two Numbers
 * Add two numbers represented by linked lists (digits stored in reverse order)
 * and return the sum as a linked list.
 */
public class AddTwoNumbers2 {
    /**
     * Time Complexity: O(max(N, M)) where N and M are the number of nodes in l1 and
     * l2.
     * Space Complexity: O(max(N, M)) for the new resulting linked list.
     */
    public ListNode addTwonumbers(ListNode l1, ListNode l2) {
        // Initializing a dummy head node to simplify the result list creation
        ListNode dummyhead = new ListNode(0);
        // Initializing a tail node
        ListNode tail = dummyhead;
        // Initializing a carry variable
        int carry = 0;
        // Iterate until both lists are exhausted
        while (l1 != null || l2 != null) {
            // Initializing a sum variable
            int sum = carry;
            // If l1 is not null, add the value of l1 to sum and move l1 to the next node
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            // If l2 is not null, add the value of l2 to sum and move l2 to the next node
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // If sum is greater than 10, then carry is 1
            // Else carry is 0
            // Add the value of sum%10 to the tail node
            tail.next = new ListNode(sum % 10);
            // Move the tail node to the next node
            tail = tail.next;
            // Update the carry variable
            carry = sum / 10;
        }
        // If carry is greater than 0, then add a new node to the tail node
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        // Return the list after the dummy head
        return dummyhead.next;
    }
}
