/*
 * Problem: 1290. Convert Binary Number in a Linked List to Integer
 *
 * Problem Statement:
 * Given the head of a singly linked list where each node contains a binary digit (0 or 1),
 * the list represents a binary number with the most significant bit at the head.
 * Convert this binary representation into its decimal integer equivalent.
 *
 * Intuition:
 * Binary numbers are base-2 positional systems. As we traverse the list from left to right 
 * (Most Significant Bit to Least Significant Bit), each new bit we encounter effectively 
 * doubles the value of the bits we've already processed. This is similar to how reading 
 * "12" in decimal is (1 * 10) + 2.
 *
 * Approach:
 * 1. Initialize an integer 'result' to 0 to store the accumulated decimal value.
 * 2. Traverse the linked list from head to tail using a while loop.
 * 3. In each iteration, multiply the current 'result' by 2 (shifting the binary representation 
 *    left by one position) and then add the current node's value (0 or 1).
 * 4. Move the pointer to the next node and repeat until the end of the list.
 *
 * Time Complexity: O(N), where N is the number of nodes in the linked list. We visit each node exactly once.
 * Space Complexity: O(1), as we only store the result in a single integer variable.
 *
 * Edge Cases:
 * - Single node: The loop runs once, result becomes 0*2 + val, correctly returning the value.
 * - All zeros: Result remains 0 throughout the traversal.
 * - Maximum length: The problem constraints (up to 30 nodes) ensure the result fits in a 32-bit signed integer.
 *
 * Dry Run:
 * Input: 1 -> 0 -> 1
 * 1. result = 0
 * 2. Node(1): result = (0 * 2) + 1 = 1
 * 3. Node(0): result = (1 * 2) + 0 = 2
 * 4. Node(1): result = (2 * 2) + 1 = 5
 * Result: 5
 *
 * Correctness Check:
 * The solution is correct. It uses the standard iterative approach for base conversion 
 * (Horner's Method), which avoids the need to know the length of the list beforehand 
 * or use expensive power functions.
 */
public class ConvertBinaryNumberinaLinkedListtoInteger1290 {
    public int getDecimalValue(ListNode head){
        // Initialize the accumulator to 0
        int result = 0;
        
        // Iterate through the linked list until we reach the end (null)
        while(head != null){
            // Multiply current result by 2 to shift bits left.
            // This is equivalent to (result << 1) in bitwise operations.
            result *= 2; 
            
            // Add the current bit (0 or 1) to the result.
            // Since we just shifted left, the last bit of 'result' is currently 0.
            result += head.val; 
            
            // Advance the pointer to the next node in the list
            head = head.next;
        }
        
        // Return the final decimal representation
        return result;
    }
}
