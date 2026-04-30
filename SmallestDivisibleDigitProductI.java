/*
 * Problem: 3370. Smallest Divisible Digit Product I
 *
 * Problem Statement:
 * Given two integers n and t, find the smallest integer x such that x >= n 
 * and the product of the digits of x is divisible by t.
 *
 * Intuition:
 * The search space for this problem is very small. Since any number containing the digit '0' 
 * will have a digit product of 0 (which is divisible by any non-zero t), we are guaranteed 
 * to find a valid number within at most 10 consecutive integers. A simple brute-force 
 * simulation starting from n is efficient enough.
 *
 * Approach:
 * 1. Start with the current number 'num' equal to n.
 * 2. Calculate the product of the digits of 'num' using a helper function.
 * 3. Check if this product is divisible by t (product % t == 0).
 * 4. If it is, return 'num'.
 * 5. If not, increment 'num' by 1 and repeat the process.
 *
 * Time Complexity: O(k * log10(n)), where k is the number of integers checked until 
 * a valid one is found. Since we find a multiple of 10 (product 0) within 10 steps, 
 * k is at most 10. log10(n) represents the number of digits in n.
 * Space Complexity: O(1) as we only use a constant amount of extra space for variables.
 *
 * Edge Cases:
 * - n is already divisible by t: The initial check handles this.
 * - t = 1: Every number's digit product is divisible by 1.
 * - n contains a 0: The product becomes 0, which is divisible by any t.
 *
 * Dry Run:
 * n = 15, t = 3
 * 1. num = 15, product = 1 * 5 = 5. 5 % 3 != 0.
 * 2. num = 16, product = 1 * 6 = 6. 6 % 3 == 0.
 * 3. Return 16.
 *
 * Correctness Check:
 * The solution is correct for n >= 1. Note: If n = 0, the multiply function returns 1 
 * because the while loop (n > 0) never executes. In most digit-product problems, 
 * the product of digits for 0 is considered 0. However, for the constraints of this 
 * specific problem (n >= 1), the current logic works perfectly.
 */
// You are given two integers n and t. Return the smallest number greater than or equal to n such that the product of its digits is divisible by t.
// input = 10,t = 2 output = 10
// input = 15,t = 3 output = 16
public class SmallestDivisibleDigitProductI {
    public static void main(String[] args) {

        int n = 10, t = 2;
        System.out.println(smallestNumber(n, t));

    }

    public static int smallestNumber(int n, int t) {
        int num = n;
        int res = 1;
        
        // Calculate the digit product of the starting number n
        res = multiply(n);
        
        // If the starting number already satisfies the condition, return it immediately
        if (res % t == 0) {
            return num;
        }
        
        // Linear search: increment num one by one until the digit product is divisible by t
        while (res % t != 0) {
            num++;
            res = multiply(num);
        }
        return num;
    }

    // Helper method to calculate the product of all digits in a given number
    public static int multiply(int n) {
        int res = 1;
        // Standard digit extraction loop
        while (n > 0) {
            // Extract the last digit using modulo 10 and multiply it to the result
            res *= n % 10;
            // Remove the last digit using integer division by 10
            n /= 10;
        }
        return res;
    }
}
