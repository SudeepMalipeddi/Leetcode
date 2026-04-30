/*
 * Problem: LeetCode 1051 - Height Checker
 *
 * Problem Statement:
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a 
 * non-decreasing order by height. Given an integer array 'heights' representing the current order, 
 * return the number of indices where heights[i] != expected[i] (where expected is the sorted version).
 *
 * Intuition:
 * The "expected" order is simply the sorted version of the input array. By creating a sorted 
 * reference and comparing it index-by-index with the original, we can identify every student 
 * who is not in their correct relative position.
 *
 * Approach:
 * 1. Create a deep copy of the original 'heights' array to preserve the current order.
 * 2. Sort the copied array to determine the "expected" non-decreasing order.
 * 3. Iterate through both arrays simultaneously using a single pointer.
 * 4. Increment a counter whenever the value in the original array differs from the sorted array.
 *
 * Time Complexity: O(n log n) where n is the length of the array. This is dominated by the 
 * sorting algorithm (Arrays.sort uses Dual-Pivot Quicksort for primitives).
 * Space Complexity: O(n) because we allocate a new array 'expected' of size n to store the sorted values.
 *
 * Edge Cases:
 * - Single student: Result is always 0 as one element is technically sorted.
 * - Already sorted: Result is 0.
 * - Reverse sorted: Result will be the count of all elements that change position.
 * - All heights identical: Result is 0.
 *
 * Dry Run:
 * heights = [1, 1, 4, 2, 1, 3]
 * expected = [1, 1, 1, 2, 3, 4] (after sorting)
 * i=0: 1 == 1 (Match)
 * i=1: 1 == 1 (Match)
 * i=2: 4 != 1 (Mismatch, count = 1)
 * i=3: 2 == 2 (Match)
 * i=4: 1 != 3 (Mismatch, count = 2)
 * i=5: 3 != 4 (Mismatch, count = 3)
 * Result: 3
 *
 * Correctness Check:
 * The solution is correct. It accurately implements the comparison logic. Note that while 
 * O(n log n) is efficient, a Counting Sort approach could achieve O(n + k) time where k is 
 * the range of heights (1-100), but this sorting approach is more general-purpose.
 */
import java.util.Arrays;

public class HeightChecker1051 {
    public int heightChecker(int[] heights) {
        // We must clone the array because Arrays.sort() modifies the array in-place.
        // We need the original 'heights' to compare against the 'expected' sorted version.
        int[] expected = Arrays.copyOf(heights, heights.length);
        
        // Sort the copy to establish the target non-decreasing order.
        Arrays.sort(expected);
        
        int count = 0;
        // Iterate through the arrays. Since they are the same length, we use one index.
        for (int i = 0; i < heights.length; i++) {
            // If the student at index i is not the height we expect in a sorted line,
            // they are "out of place".
            if (heights[i] != expected[i]) {
                count++;
            }
        }
        
        // Return the total number of mismatches found.
        return count;
    }
}
