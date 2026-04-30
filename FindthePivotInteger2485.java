/*
 * Problem: LeetCode 2485 - Find the Pivot Integer
 *
 * Problem Statement:
 * Given a positive integer n, find the pivot integer x such that the sum of all integers 
 * from 1 to x is equal to the sum of all integers from x to n. If no such integer exists, 
 * return -1.
 *
 * Intuition:
 * As x increases, the prefix sum (1 to x) increases and the suffix sum (x to n) decreases. 
 * There is a unique point where these two sums might cross. Mathematically, the pivot x 
 * satisfies x(x+1)/2 = [n(n+1)/2] - [x(x-1)/2]. This can be solved using the arithmetic 
 * series formula for both sides.
 *
 * Approach:
 * 1. Handle the base case where n is 1 (the pivot is 1).
 * 2. Iterate through potential candidates for the pivot starting from n/2. 
 *    (Optimization: The pivot must be greater than n/2 for n > 1).
 * 3. For each candidate i, calculate the prefix sum (sumb) using the formula n(n+1)/2.
 * 4. Calculate the suffix sum (sumf) from i to n.
 * 5. If the sums are equal, store the result and return it.
 *
 * Time Complexity: O(n) because we iterate from n/2 to n in the worst case.
 * Space Complexity: O(1) as we only use a constant amount of extra space for variables.
 *
 * Edge Cases:
 * - n = 1: Handled by the initial if-statement (returns 1).
 * - No pivot exists: The loop finishes and returns the default res = -1.
 *
 * Dry Run:
 * Input: n = 8
 * - i = 4: sumb = 10, sumf = 4+5+6+7+8 = 30 (No match)
 * - i = 5: sumb = 15, sumf = 5+6+7+8 = 26 (No match)
 * - i = 6: sumb = 21, sumf = 6+7+8 = 21 (Match! res = 6)
 * - i = 7: sumb = 28, sumf = 7+8 = 15 (No match)
 * Result: 6
 *
 * Correctness Check:
 * The solution is correct. The loop range i < n is safe because for n > 1, n itself 
 * can never be the pivot (sum 1..n will always be greater than sum n..n).
 */
public class FindthePivotInteger2485 {
    public int pivotInteger(int n) {
        // Base case: If n is 1, the only integer is the pivot itself.
        if (n == 1) {
            return 1;
        }
        int res = -1;
        // Iterate through the active search space while maintaining the intended invariant.
        // We start at n/2 because the pivot point mathematically must be in the upper half for n > 1.
        for (int i = n / 2; i < n; i++) {
            // sumb: Sum of integers from 1 to i using the arithmetic series formula: i*(i+1)/2.
            int sumb = ((i) * (i + 1)) / 2;
            
            // num: The count of integers in the range [i, n].
            int num = n - i + 1;
            
            // sumf: Sum of integers from i to n. 
            // This is calculated as (count * start_value) + sum of offsets (0 to count-1).
            // Equivalent to the arithmetic series sum: (num / 2) * (first_term + last_term).
            int sumf = (num) * (i) + (num - 1) * (num) / 2;
            
            // If prefix sum equals suffix sum, we have found our pivot.
            if (sumb == sumf) {
                res = i;
            }
        }
        return res;
    }
}
