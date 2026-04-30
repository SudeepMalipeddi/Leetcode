/*
 * Problem: LeetCode 2 - Add Two Numbers
 *
 * Problem Statement:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * Intuition:
 * Since the numbers are stored in reverse order, the head of each list represents the 
 * least significant digit (the ones place). This allows us to iterate through both 
 * lists simultaneously and perform addition digit-by-digit from left to right, 
 * carrying over any overflow to the next position, just like manual addition.
 *
 * Approach:
 * 1. Initialize a dummy head node to simplify the construction of the result list.
 * 2. Use a 'tail' pointer to keep track of the last node in the new list.
 * 3. Maintain a 'carry' variable (initially 0) to handle sums greater than 9.
 * 4. Loop while either list has nodes or a carry remains.
 * 5. In each iteration, add the current digits from l1 and l2 (if they exist) to the carry.
 * 6. Create a new node with (sum % 10) and update the carry to (sum / 10).
 * 7. Move the pointers forward and repeat.
 *
 * Time Complexity: O(max(N, M)) where N and M are the lengths of l1 and l2. We traverse each list at most once.
 * Space Complexity: O(max(N, M)) for the new linked list. The length is at most max(N, M) + 1.
 *
 * Edge Cases:
 * - Lists of different lengths (e.g., 1 + 999).
 * - A carry generated at the very last addition (e.g., 5 + 5 = 10).
 * - Input lists containing only a single zero node.
 *
 * Dry Run:
 * l1 = [2, 4, 3], l2 = [5, 6, 4]
 * 1. sum = 2 (l1) + 5 (l2) + 0 (carry) = 7. New node: 7, carry: 0.
 * 2. sum = 4 (l1) + 6 (l2) + 0 (carry) = 10. New node: 0, carry: 1.
 * 3. sum = 3 (l1) + 4 (l2) + 1 (carry) = 8. New node: 8, carry: 0.
 * Result: 7 -> 0 -> 8 (representing 807).
 *
 * Correctness Check:
 * The solution correctly handles the carry propagation and varying list lengths. 
 * The dummy head pattern ensures the return pointer is easily accessible.
 */
public class AddTwoNumbers2 {
    /**
     * Time Complexity: O(max(N, M)) where N and M are the number of nodes in l1 and
     * l2.
     * Space Complexity: O(max(N, M)) for the new resulting linked list.
     */
    public ListNode addTwonumbers(ListNode l1, ListNode l2) {
        // Initializing a dummy head node to simplify the result list creation
        // The dummy node acts as a placeholder so we don't have to handle the head as a special case.
        ListNode dummyhead = new ListNode(0);
        // Initializing a tail node
        // This pointer moves forward as we append new digits to the result list.
        ListNode tail = dummyhead;
        // Initializing a carry variable
        // This stores the 'tens' place of the sum to be added to the next digit.
        int carry = 0;
        // While loop to iterate through the linked list
        // We continue if there are digits left in l1 OR l2.
        while (l1 != null || l2 != null) {
            // Initializing a sum variable
            // Every new digit sum starts with the carry from the previous column.
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
            // sum % 10 isolates the ones digit (e.g., 13 % 10 = 3).
            tail.next = new ListNode(sum % 10);
            // Move the tail node to the next node
            tail = tail.next;
            // Update the carry variable
            // sum / 10 isolates the tens digit (e.g., 13 / 10 = 1).
            carry = sum / 10;
        }
        // If carry is greater than 0, then add a new node to the tail node
        // This handles cases like 5 + 5 where the loop ends but a carry of 1 remains.
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        // Return the next node of the dummy head node
        // dummyhead.next is the actual start of our calculated sum list.
        return dummyhead.next;
    }
}
