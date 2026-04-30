/*
 * Problem: 344. Reverse String
 *
 * Problem Statement:
 * Write a function that reverses a string. The input string is given as an array of characters `char[] s`.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Intuition:
 * To reverse an array, the first element must swap with the last, the second with the second-to-last, 
 * and so on. A two-pointer approach meeting in the middle is the most efficient way to perform these swaps.
 *
 * Approach:
 * 1. Initialize two pointers: 'start' at the beginning (index 0) and 'end' at the end (index length - 1).
 * 2. Iterate while 'start' is less than 'end'.
 * 3. Swap the characters at 'start' and 'end' using a temporary variable.
 * 4. Move 'start' forward and 'end' backward to process the next pair.
 *
 * Time Complexity: O(N) where N is the length of the array. We perform N/2 swaps, which simplifies to linear time.
 * Space Complexity: O(1) as we modify the input array in-place and use a constant amount of extra space for pointers and the temp variable.
 *
 * Edge Cases:
 * - Single character array: Loop condition (0 < 0) is false, array remains unchanged (correct).
 * - Even length array: Pointers will cross (e.g., start becomes 2, end becomes 1).
 * - Odd length array: Pointers will meet at the middle element (e.g., 2 < 2 is false), which doesn't need to be swapped.
 *
 * Dry Run:
 * Input: s = ['h','e','l','l','o']
 * 1. start=0, end=4: swap 'h' and 'o' -> ['o','e','l','l','h'], start=1, end=3
 * 2. start=1, end=3: swap 'e' and 'l' -> ['o','l','l','e','h'], start=2, end=2
 * 3. start=2, end=2: start < end is false. Loop terminates.
 *
 * Correctness Check:
 * The solution is correct and follows the in-place requirement.
 */
public class ReverseString344 {
    public void reverseString(char[] s) {
        // Initialize two pointers at the opposite ends of the array
        int start = 0;
        int end = s.length - 1;

        // Continue swapping until the pointers meet or cross in the middle
        while (start < end) {
            // Standard swap logic using a temporary variable to prevent data loss
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            // Move pointers inward to the next pair of characters
            start++;
            end--;
        }
        return;
    }
}
