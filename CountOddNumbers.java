/*
 * Problem: LeetCode 1523 - Count Odd Numbers in an Interval Range
 *
 * Problem Statement:
 * Given two non-negative integers low and high, return the count of odd numbers 
 * between low and high (inclusive).
 *
 * Intuition:
 * In any sequence of integers starting from 0 to N, exactly half (if N is odd) 
 * or slightly less than half (if N is even) of the numbers are odd. 
 * The mathematical formula for the count of odds in [0, N] is (N + 1) / 2 
 * using integer division. To find the count in a specific range [low, high], 
 * we can use the prefix sum property: Count(low, high) = Count(0, high) - Count(0, low - 1).
 *
 * Approach:
 * 1. Calculate the total number of odd integers from 0 up to 'high' using (high + 1) / 2.
 * 2. Calculate the total number of odd integers from 0 up to 'low - 1' using (low - 1 + 1) / 2, which simplifies to low / 2.
 * 3. Subtract the second value from the first to get the count within the inclusive range.
 *
 * Time Complexity: O(1) as it involves a constant number of arithmetic operations.
 * Space Complexity: O(1) as no extra memory is allocated.
 *
 * Edge Cases:
 * - low == high: If both are odd, result is 1; if even, result is 0.
 * - low and high are both odd: Both endpoints are counted.
 * - low and high are both even: Neither endpoint is counted.
 *
 * Dry Run:
 * low = 3, high = 7
 * 1. Odds in [0, 7]: (7 + 1) / 2 = 4  ({1, 3, 5, 7})
 * 2. Odds in [0, 2]: 3 / 2 = 1        ({1})
 * 3. Result: 4 - 1 = 3                ({3, 5, 7})
 *
 * Correctness Check:
 * The solution is correct. Using integer division effectively handles the 
 * "rounding up" needed for odd counts.
 */
public class CountOddNumbers {
    public int countOdds(int low, int high) {
        // In Java, integer division truncates toward zero. 
        // (high + 1) / 2 correctly counts odds in [0, high].
        // For example, if high is 3, (3+1)/2 = 2 (odds are 1, 3).
        // If high is 4, (4+1)/2 = 2 (odds are 1, 3).
        
        // low / 2 calculates the count of odds in the range [0, low - 1].
        // This is because ( (low - 1) + 1 ) / 2 simplifies to low / 2.
        
        // The difference gives us the count of odd numbers strictly within [low, high].
        return (high + 1) / 2 - low / 2;
    }
}
