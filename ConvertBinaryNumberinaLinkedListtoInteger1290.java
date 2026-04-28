/*
 * Problem: LeetCode 1290 - Convert Binary Number in a Linked List to Integer
 * Problem Statement: A linked list stores a binary number with the most
 *   significant bit first. Return its decimal value.
 * Intuition: Reading bits left-to-right, the current value doubles each step,
 *   then add the next bit.
 * Approach:
 *   1) Initialize result = 0.
 *   2) For each node, result = result * 2 + node.val.
 * Time Complexity: O(n) for n nodes.
 * Space Complexity: O(1).
 * Edge Cases: Single node, all zeros, leading zeros.
 * Dry Run: 1->0->1 => ((0*2+1)*2+0)*2+1 = 5.
 * Correctness Check: The recurrence matches binary positional value accumulation.
 */
public class ConvertBinaryNumberinaLinkedListtoInteger1290 {
    public int getDecimalValue(ListNode head){
        int result = 0;
        while(head != null){
            result *= 2; // shift left by one bit
            result += head.val; // add current bit (0 or 1)
            head = head.next;
        }
        return result;
    }
}
