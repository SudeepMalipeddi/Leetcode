/*
 * Problem: LeetCode 231 - Power of Two
 *
 * Problem Statement:
 * Given an integer n, return true if it is a power of two. An integer is a power of two if there
 * exists an integer x such that n == 2^x.
 *
 * Intuition:
 * A power of two has exactly one bit set in its binary representation, and it is positive. Repeated
 * division by 2 should eventually reduce the number to exactly 1. Any odd number other than 1 (or n=0)
 * is not a power of two. The iterative approach directly mirrors the mathematical definition.
 *
 * Approach:
 *   1. If n == 0, return false (0 is not a power of two).
 *   2. While n % 2 == 0 (n is even), repeatedly divide n by 2: n /= 2.
 *   3. After the loop, return n == 1. If the original n was a power of two, all divisions by 2
 *      will eventually yield exactly 1. Any other result (odd > 1) means n is not a power of two.
 *
 * Time Complexity: O(log n) — each division by 2 reduces n by half, so at most ~log2(n) iterations.
 * Space Complexity: O(1) — only the input variable n is mutated, no additional data structures.
 *
 * Edge Cases:
 * - n = 0: immediately returns false.
 * - n = 1: the while loop condition n%2==0 is false (1 is odd), so skips to return n==1 → true.
 * - n negative: e.g., n = -16: n%2==0 is true, n/=-2→-8, ..., eventually n=-1, returns false.
 *   Correct because powers of two are positive.
 * - n = Integer.MIN_VALUE (-2^31): loop runs until n becomes -1, returns false. Correct.
 *
 * Dry Run:
 * n = 16:
 *   Step 1: n != 0, continue.
 *   Step 2: n%2==0 (16 is even), n = 16/2 = 8.
 *   Step 3: n%2==0 (8 is even), n = 8/2 = 4.
 *   Step 4: n%2==0 (4 is even), n = 4/2 = 2.
 *   Step 5: n%2==0 (2 is even), n = 2/2 = 1.
 *   Step 6: n%2==0 is false (1 is odd), exit loop.
 *   Return n == 1 → true.
 * n = 10:
 *   n=10→5→exit. n=5 != 1 → false. Correct.
 * n = 1:
 *   n%2==0 is false immediately, return n==1 → true. Correct.
 *
 * Correctness Check:
 * Every positive power of two has the form 2^k and will be reduced to 1 after exactly k divisions by 2.
 * Any number that is not a power of two will either be odd > 1 initially (loop doesn't run, returns
 * false) or will become an odd number > 1 after some divisions (returns false). The negative and zero
 * cases are correctly rejected. This exhaustive case analysis guarantees correctness.
 */
public class PowerofTwo231 {
    public boolean isPowerOfTwo(int n){
        
        if(n == 0) return false;
        
        // Repeated halving validates power-of-two structure.
        while(n % 2 == 0){
            n /= 2;
        }
        return n == 1;
    }   
}
