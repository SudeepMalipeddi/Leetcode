/*
 * Problem: LeetCode 9 - Palindrome Number
 *
 * Problem Statement:
 * Determine whether an integer reads the same forward and backward. 
 * For example, 121 is a palindrome, while 123 is not.
 *
 * Intuition:
 * A palindrome is symmetrical. If we reverse the digits of the number, a palindromic 
 * number will remain identical to its original form. By mathematically reversing 
 * the integer, we can perform a direct comparison.
 *
 * Approach:
 * 1. Handle negative numbers: Any number with a negative sign is not a palindrome.
 * 2. Store the original value of x in a variable 'var' because x will be reduced to 0 during processing.
 * 3. Use a while loop to extract digits from the end of x using the modulo operator (%).
 * 4. Construct the 'reverse' number by shifting existing digits left (multiply by 10) and adding the new digit.
 * 5. Use 'long' for the reverse variable to prevent overflow if the reversed number exceeds the integer range.
 * 6. Compare the final reversed value with the stored original value.
 *
 * Time Complexity: O(log10(n)) where n is the input integer. We iterate once for every digit in the number.
 * Space Complexity: O(1) as we only use a fixed amount of extra space for primitive variables.
 *
 * Edge Cases:
 * - Negative numbers: Always false (e.g., -121 becomes 121-).
 * - Single-digit numbers: Always true (0-9).
 * - Multiples of 10: False (except 0) because they would start with a 0 when reversed.
 *
 * Dry Run:
 * Input: x = 121
 * 1. var = 121, reverse = 0
 * 2. Iteration 1: temp = 121 % 10 = 1; reverse = (0 * 10) + 1 = 1; x = 12
 * 3. Iteration 2: temp = 12 % 10 = 2; reverse = (1 * 10) + 2 = 12; x = 1
 * 4. Iteration 3: temp = 1 % 10 = 1; reverse = (12 * 10) + 1 = 121; x = 0
 * 5. Comparison: 121 == 121 returns true.
 *
 * Correctness Check:
 * The solution correctly identifies palindromes. Using 'long' for 'reverse' and 'var' 
 * is a safe practice to handle potential overflow during the digit-shifting process.
 */
public class Palindrome {
    public boolean isPalindrome(int x) {
        
        // Negative numbers cannot be palindromes because the '-' sign only appears at the start.
        if (x < 0) {
            return false;
        }
        // Store the original value to compare against the reversed version later.
        long var = x;
        long temp = 0;
        // Use long to prevent overflow when reversing large integers.
        long reverse = 0;
        
        // Build reversed digits from least significant to most significant.
        while (x > 0) {
            // Extract the last digit.
            temp = x % 10;
            // Shift the current reverse value left by one decimal place and add the new digit.
            reverse = ((reverse * 10) + temp);
            // Remove the last digit from x.
            x = x / 10;
        }
        // If the reversed number matches the original, it is a palindrome.
        return (reverse == var);
    }
}
