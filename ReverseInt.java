/*
 * Problem: LeetCode 7. Reverse Integer
 *
 * Problem Statement:
 * Given a signed 32-bit integer x, return x with its digits reversed. 
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], return 0.
 *
 * Intuition:
 * We can reverse an integer by repeatedly extracting the last digit using the modulo operator (%) 
 * and appending it to a new number. By using a 64-bit 'long' for the accumulator, we can 
 * safely store the reversed value and check if it exceeds the 32-bit 'int' limits before returning.
 *
 * Approach:
 * 1. Initialize a long variable 'sum' to 0 to store the reversed digits.
 * 2. Use a variable 'k' to track the remaining magnitude of the number to process.
 * 3. In a loop, extract the last digit of 'x' using 'x % 10', add it to 'sum' (after shifting 'sum' left by multiplying by 10), and divide 'x' by 10.
 * 4. After processing all digits, check if 'sum' falls outside the range of a 32-bit signed integer.
 * 5. Return the result as an int, or 0 if an overflow occurred.
 *
 * Time Complexity: O(log10(x)) - We iterate through each digit of the number. The number of digits is proportional to the logarithm of the number in base 10.
 * Space Complexity: O(1) - We only use a constant amount of extra space for primitive variables.
 *
 * Edge Cases:
 * - Negative numbers: Handled correctly because Java's % operator preserves the sign of the dividend.
 * - Numbers ending in zero: The leading zeros in the reversed result are naturally handled (e.g., 120 becomes 21).
 * - Overflow: Handled by the long accumulator and the range check at the end.
 *
 * Dry Run:
 * Input: x = -123
 * 1. sum = 0, k = 123 (since x < 0)
 * 2. Loop 1: sum = (0 * 10) + (-123 % 10) = -3. x = -12, k = 12.
 * 3. Loop 2: sum = (-3 * 10) + (-12 % 10) = -32. x = -1, k = 1.
 * 4. Loop 3: sum = (-32 * 10) + (-1 % 10) = -321. x = 0, k = 0.
 * 5. sum (-321) is within range. Return -321.
 *
 * Correctness Check:
 * The solution is logically sound for reversing digits. Note that the overflow check 'sum > Math.pow(2, 31)' 
 * uses 2^31 (2147483648) as the upper bound, whereas the maximum 32-bit signed integer is actually 
 * 2^31 - 1 (2147483647). However, since we are reversing integers, the first value that would 
 * trigger this specific overflow check is indeed 2147483648 or larger.
 */
public class ReverseInt {
    public int reverse(int x) {
        // Use a long to store the result to prevent overflow during the calculation phase.
        long sum = 0;
        int k = x;
        
        // Ensure k is positive to use it as a simple iteration counter for the number of digits.
        if (x < 0) {
            k = (-1) * k;
        }
        
        // Extract and append digits to build reversed number.
        // x % 10 gets the last digit (preserving sign), sum * 10 shifts existing digits to the left.
        while (k > 0) {
            sum = sum * 10 + x % 10;
            x = x / 10;
            k = k / 10;
        }
        
        // Overflow guard before narrowing long to int.
        // Checks if the accumulated sum exceeds the 32-bit signed integer boundaries.
        if (sum < Math.pow(-2, 31) || sum > Math.pow(2, 31))
        
            return 0;
        else
            return (int) sum;
    }
}
