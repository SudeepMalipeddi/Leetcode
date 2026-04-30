/*
 * Problem: 326. Power of Three
 *
 * Problem Statement:
 * Given an integer n, return true if it is a power of three. An integer n is a power of three 
 * if there exists an integer x such that n == 3^x.
 *
 * Intuition:
 * A power of three is a number whose only prime factor is 3. If we repeatedly divide 
 * the number by 3, we should eventually reach 1. If we encounter any other factor 
 * or reach a number not divisible by 3 before reaching 1, the number is not a power of three.
 *
 * Approach:
 * 1. Iterative: Use a while loop to divide n by 3 as long as n is divisible by 3 (n % 3 == 0).
 *    After the loop, check if the remaining value is 1.
 * 2. Recursive-style: Check base cases (n=1 is true, n<=0 or n%3!=0 is false), 
 *    then reduce the problem by dividing by 3.
 *
 * Time Complexity: O(log3 n)
 * Each division by 3 reduces the input size by a factor of 3. The number of 
 * iterations is proportional to the exponent x in 3^x = n.
 *
 * Space Complexity: O(1)
 * The iterative approach uses constant extra space. The recursive-style method 
 * provided here delegates to the iterative one, maintaining O(1) space.
 *
 * Edge Cases:
 * - n = 0: Not a power of 3 (returns false).
 * - n = 1: 3^0 is 1 (returns true).
 * - n < 0: Negative numbers cannot be powers of 3 with integer exponents (returns false).
 *
 * Dry Run:
 * n = 9
 * 9 % 3 == 0? Yes. n = 9 / 3 = 3.
 * 3 % 3 == 0? Yes. n = 3 / 3 = 1.
 * 1 % 3 == 0? No. Loop ends.
 * return 1 == 1 -> true.
 *
 * Correctness Check:
 * The logic is correct. Note that isPowerOfThree1 is a hybrid; it performs one 
 * check and then calls the iterative isPowerOfThree method instead of recursing on itself.
 */

public class IsPowerfThree326 {
    public boolean isPowerOfThree(int n) {
        // 0 cannot be represented as 3^x.
        if (n == 0)
            return false;
        // Remove one factor of 3 each iteration.
        // We use the modulo operator (%) to check for divisibility. 
        // If n % 3 is 0, 3 is a factor we can "peel off".
        while (n % 3 == 0) {
            n /= 3;
        }
        // True only if all factors were exactly 3 and final residue is 1.
        // If the original number was a power of 3, the only remaining value must be 1.
        return n == 1;
    }

    public boolean isPowerOfThree1(int n) {
        // Base case: 3^0 = 1.
        if (n == 1) {
            return true;
        }
        // Reject non-positive numbers and numbers with a non-3 prime factor.
        // Any power of 3 (other than 1) must be divisible by 3 and greater than 0.
        if (n <= 0 || n % 3 != 0) {
            return false;
        }
        // Reduce problem size by dividing by 3.
        // Note: This implementation delegates the remaining work to the iterative method.
        return isPowerOfThree(n / 3);
    }
}
