/*
 * Problem: 342. Power of Four
 *
 * Problem Statement:
 * Given an integer n, return true if it is a power of four. An integer n is a power of four 
 * if there exists an integer x such that n == 4^x.
 *
 * Intuition:
 * A power of four must be a positive integer. If we repeatedly divide a power of four by 4, 
 * we will eventually reach 1. If at any point the number is not divisible by 4 before 
 * reaching 1, it cannot be a power of four.
 *
 * Approach:
 * 1. Handle the base case where n is 0 (not a power of 4).
 * 2. Use a while loop to divide n by 4 repeatedly as long as the remainder is 0.
 * 3. After the loop, check if the remaining value of n is 1. If it is, the original number was a power of 4.
 *
 * Time Complexity: O(log4 n) because the value of n is divided by 4 in each iteration.
 * Space Complexity: O(1) as we only use a constant amount of extra space.
 *
 * Edge Cases:
 * - n = 0: Returns false.
 * - n = 1: Returns true (4^0).
 * - n = -16: Returns false (the loop reduces it to -1, which is not 1).
 * - n = 8: Returns false (divisible by 4 once, then becomes 2, which is not divisible by 4).
 *
 * Dry Run:
 * n = 16
 * 1. 16 != 0, proceed.
 * 2. 16 % 4 == 0 is true. n becomes 16 / 4 = 4.
 * 3. 4 % 4 == 0 is true. n becomes 4 / 4 = 1.
 * 4. 1 % 4 == 0 is false. Exit loop.
 * 5. n == 1 is true. Return true.
 *
 * Correctness Check:
 * The solution is correct. It correctly identifies powers of four using an iterative reduction method. 
 * Note: A more optimized O(1) solution exists using bit manipulation: (n > 0 && (n & (n - 1)) == 0 && (n & 0xAAAAAAAA) == 0).
 */
public class PowerOfFour342 {
    public boolean isPowerOfFour(int n) {
        
        // Zero is not a power of any positive integer base.
        if (n == 0)
            return false;
        
        // Keep dividing by 4; only exact powers finish at 1.
        // The loop continues as long as n is divisible by 4.
        while (n % 4 == 0) {
            n /= 4;
        }
        // If the original number was a power of 4, the final value must be 1.
        // For other numbers, the loop will terminate at a value like 2, 3, or -1.
        return n == 1;
    }
}
