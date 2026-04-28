/*
 * Problem: LeetCode 2 - Add Two Numbers
 * Problem Statement: Two non-empty linked lists represent non-negative integers
 *   in reverse digit order. Add the two numbers and return the sum as a linked list.
 * Intuition: Traverse both lists digit by digit, maintaining a carry as in
 *   elementary addition.
 * Approach:
 *   1) Use a dummy head to build the result list.
 *   2) While either list has nodes, add current digits plus carry.
 *   3) Append sum % 10 as the new digit and update carry = sum / 10.
 *   4) If a carry remains after traversal, append one more node.
 * Time Complexity: O(max(N, M)) for lengths of the lists.
 * Space Complexity: O(max(N, M)) for the output list.
 * Edge Cases: Different lengths, carry at the end, zeros in the input.
 * Dry Run: l1=2->4->3, l2=5->6->4 => 2+5=7, 4+6=10 carry1,
 *   3+4+1=8 => result 7->0->8 (807).
 * Correctness Check: Each output node encodes the correct digit for the running
 *   sum, and carry propagation ensures the full integer sum is represented.
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
        // While loop to iterate through the linked list
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
        // Return the next node of the dummy head node
        return dummyhead.next;
    }
}
