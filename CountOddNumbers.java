/*
 * Problem: LeetCode 1523 - Count Odd Numbers in an Interval Range
 * Problem Statement: Return the count of odd numbers between low and high (inclusive).
 * Intuition: The count of odd numbers up to x is (x + 1) / 2 using integer division.
 * Approach:
 *   1) Compute odds up to high: (high + 1) / 2.
 *   2) Subtract odds up to low - 1: low / 2.
 * Time Complexity: O(1).
 * Space Complexity: O(1).
 * Edge Cases: low=high, range starting/ending on odd or even.
 * Dry Run: low=3, high=7 -> (7+1)/2=4, low/2=1 => 4-1=3 odds (3,5,7).
 * Correctness Check: The arithmetic counts exactly the odd integers in the range.
 */
public class CountOddNumbers {
    public int countOdds(int low, int high) {
        // the count of odd numbers between 1 and low - 1 is low / 2
        // the count of odd numbers between 1 and high is (high + 1 ) / 2
        return (high + 1) / 2 - low / 2;
    }
}
