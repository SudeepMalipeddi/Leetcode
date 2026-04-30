/*
 * Problem: 50. Pow(x, n)
 *
 * Problem Statement:
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 *
 * Intuition:
 * A naive approach of multiplying x by itself n times takes O(n) time, which is too slow for large n.
 * Binary Exponentiation (Exponentiation by Squaring) reduces this to O(log n) by using the property:
 * x^n = (x^2)^(n/2) if n is even, and x^n = x * (x^2)^((n-1)/2) if n is odd.
 *
 * Approach:
 * 1. Convert the exponent n to a long (nn) to handle the edge case where n = Integer.MIN_VALUE, 
 *    as negating -2147483648 in a 32-bit signed integer causes overflow.
 * 2. If the exponent is negative, work with its absolute value and handle the reciprocal at the end.
 * 3. Use a loop to process the exponent:
 *    - If the current exponent is odd, multiply the result (ans) by the current base (x) and decrement the exponent.
 *    - If the current exponent is even, square the base (x = x * x) and halve the exponent.
 * 4. After the loop, if the original n was negative, return 1.0 / ans.
 *
 * Time Complexity: O(log |n|) because the exponent is halved in every "even" step.
 * Space Complexity: O(1) as we only use a constant amount of extra space.
 *
 * Edge Cases:
 * - n = 0: The loop won't run, returns initial ans = 1.0 (Correct: x^0 = 1).
 * - n < 0: Handled by taking absolute value and returning reciprocal.
 * - n = Integer.MIN_VALUE: Handled by using long to avoid overflow.
 * - x = 0, 1, or -1: Handled correctly by the squaring logic.
 *
 * Dry Run:
 * x = 2.0, n = 10
 * 1. nn = 10. nn > 0.
 * 2. nn is even: x = 2.0 * 2.0 = 4.0, nn = 5.
 * 3. nn is odd: ans = 1.0 * 4.0 = 4.0, nn = 4.
 * 4. nn is even: x = 4.0 * 4.0 = 16.0, nn = 2.
 * 5. nn is even: x = 16.0 * 16.0 = 256.0, nn = 1.
 * 6. nn is odd: ans = 4.0 * 256.0 = 1024.0, nn = 0.
 * 7. Loop ends. n (10) was not < 0. Return 1024.0.
 *
 * Correctness Check:
 * The solution is correct. The use of 'long' for the exponent safely handles the Integer.MIN_VALUE edge case.
 * The iterative approach correctly implements binary exponentiation.
 */
class Powxn50 {
    public double myPow1(double x, int n) {
        double ans = 1.0;
        // Use long to prevent overflow when n is Integer.MIN_VALUE (-2147483648)
        long nn = n;
        
        // Convert negative exponent to positive for calculation
        if (nn < 0)
            nn = -nn;
        
        // Binary Exponentiation loop
        while (nn > 0) {
            
            // Multiply answer when current exponent bit is 1.
            if (nn % 2 == 1) {
                // If odd, extract one factor of x and make the exponent even
                ans = ans * x;
                nn--;
            } else {
                // Square base each step while halving exponent.
                // This is the core optimization: x^10 becomes (x^2)^5
                x *= x;
                nn /= 2;
            }
        }
        
        // If the original exponent was negative, return the reciprocal
        if (n < 0)
            ans = (double) (1.0) / (double) (ans);
        return ans;
    }
}
