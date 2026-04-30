/*
 * Problem: LeetCode 1424 - Diagonal Traverse II
 *
 * Problem Statement:
 * Given a 2D integer array nums, return all elements of nums in diagonal order 
 * as shown in the explanation. The diagonals go from bottom-left to top-right.
 *
 * Intuition:
 * The key mathematical property of any diagonal in a 2D grid is that the sum of 
 * the indices (row + column) is constant for every element on that diagonal. 
 * For example, (0,2), (1,1), and (2,0) all belong to diagonal index 2.
 * By grouping elements by their (i + j) sum, we can easily collect all values 
 * belonging to the same diagonal.
 *
 * Approach:
 * 1. Create a Map where the key is the diagonal sum (i + j) and the value is a list of integers.
 * 2. Iterate through the 2D list row by row (top to bottom). For each element at (i, j), 
 *    add it to the list corresponding to key (i + j).
 * 3. Because we iterate rows from 0 to N, elements are added to the map in "top-to-bottom" order.
 * 4. The problem asks for "bottom-to-top" diagonal order, so when building the final array, 
 *    we iterate through the map's lists in reverse order.
 * 5. Use a counter to track the total number of elements for the final result array size.
 *
 * Time Complexity: O(N) where N is the total number of elements in the 2D list. 
 * We visit each element exactly once to populate the map and once to build the result.
 *
 * Space Complexity: O(N) to store all elements in the HashMap and the final result array.
 *
 * Edge Cases:
 * - Jagged arrays: Rows having significantly different lengths (handled naturally by i+j logic).
 * - Single element: [[1]] returns [1].
 * - Empty rows: Handled by the inner loop condition.
 *
 * Dry Run:
 * nums = [[1,2], [3,4,5]]
 * (0,0): sum=0, val=1 -> map: {0: [1]}
 * (0,1): sum=1, val=2 -> map: {0: [1], 1: [2]}
 * (1,0): sum=1, val=3 -> map: {0: [1], 1: [2, 3]}
 * (1,1): sum=2, val=4 -> map: {0: [1], 1: [2, 3], 2: [4]}
 * (1,2): sum=3, val=5 -> map: {0: [1], 1: [2, 3], 2: [4], 3: [5]}
 * Result construction:
 * d=0: reverse([1]) -> [1]
 * d=1: reverse([2, 3]) -> [3, 2]
 * d=2: reverse([4]) -> [4]
 * d=3: reverse([5]) -> [5]
 * Final: [1, 3, 2, 4, 5]
 *
 * Correctness Check:
 * The solution correctly handles the "bottom-left to top-right" requirement by 
 * reversing the lists populated during a top-down row traversal.
 */
import java.util.*;

public class DiagonalTraverseII1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // Map to group elements by their diagonal index (row + col)
        Map<Integer, List<Integer>> diagonals = new HashMap<>();
        int total = 0;
        
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                // Elements with the same i + j belong to the same diagonal.
                // computeIfAbsent ensures a list exists for every new diagonal sum encountered.
                diagonals.computeIfAbsent(i + j, k -> new ArrayList<>()).add(nums.get(i).get(j));
                total++;
            }
        }
        
        int[] result = new int[total];
        int idx = 0, d = 0;
        
        // Diagonals are processed in order from sum 0 upwards.
        // We stop when the map no longer contains the next diagonal index.
        while (diagonals.containsKey(d)) {
            List<Integer> diag = diagonals.get(d);
            // Since we added elements row-by-row (top to bottom), 
            // we must iterate backwards to achieve the bottom-to-top diagonal order.
            for (int i = diag.size() - 1; i >= 0; i--) {
                result[idx++] = diag.get(i);
            }
            d++;
        }
        return result;
    }
}
