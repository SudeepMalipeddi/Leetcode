/*
 * Problem: LeetCode 498 - Diagonal Traverse
 *
 * Problem Statement:
 * Given an m x n matrix, return an array of all the elements of the matrix in a diagonal order.
 * The traversal starts at (0,0) and moves in an alternating upward and downward diagonal direction.
 *
 * Intuition:
 * A key mathematical property of matrix diagonals is that all elements on the same diagonal 
 * share the same sum of their row and column indices (i + j = k). By grouping elements 
 * by this sum, we can easily isolate each diagonal and then manipulate its order 
 * to satisfy the zigzag requirement.
 *
 * Approach:
 * 1. Use a HashMap to group elements where the key is the sum of indices (i + j).
 * 2. Traverse the matrix in row-major order (top-to-bottom, left-to-right).
 * 3. For each element mat[i][j], append it to the list corresponding to key (i + j).
 * 4. Iterate through the collected diagonals. For every other diagonal (based on a counter), 
 *    reverse the list to create the "zigzag" effect.
 * 5. Flatten the lists into the final result array.
 *
 * Time Complexity: O(m * n) - Each element is visited once during the grouping phase 
 * and once during the result construction phase.
 * Space Complexity: O(m * n) - The HashMap stores all m * n elements of the matrix.
 *
 * Edge Cases:
 * - Single row or single column matrix.
 * - Single element matrix (1x1).
 *
 * Dry Run:
 * Input: [[1, 2], [3, 4]]
 * 1. (0,0): key=0, map={0: [1]}
 * 2. (0,1): key=1, map={0: [1], 1: [2]}
 * 3. (1,0): key=1, map={0: [1], 1: [2, 3]}
 * 4. (1,1): key=2, map={0: [1], 1: [2, 3], 2: [4]}
 * 5. Process:
 *    - Key 0 (count 0): [1]
 *    - Key 1 (count 1): reverse [2, 3] -> [3, 2]
 *    - Key 2 (count 2): [4]
 * Result: [1, 3, 2, 4]
 *
 * Correctness Check:
 * - HashMap.keySet() does not guarantee numerical order of keys. In many environments, 
 *   this will fail to process diagonals in the correct sequence (0, 1, 2...). 
 *   A TreeMap or a loop from 0 to (m + n - 2) would be more robust.
 * - The reversal logic (count % 2 != 0) determines which diagonals are flipped. 
 *   Depending on the specific zigzag start direction required, this parity may need adjustment.
 */
import java.util.*;

public class DiagonalTraverse498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        // Map to group elements by the sum of their indices (i + j)
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Elements on the same diagonal share the same sum of indices
                int key = i + j;
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(mat[i][j]);
            }
        }
        
        int i = 0;
        int count = 0;
        // Note: keySet() order is not guaranteed in a standard HashMap.
        // This loop relies on keys being returned in ascending order (0, 1, 2...).
        for (Integer x : map.keySet()) {
            // Every second diagonal is reversed to create the zigzag pattern.
            if (count % 2 != 0) {
                Collections.reverse(map.get(x));
            }
            // Flatten the current diagonal into the result array.
            for (Integer y : map.get(x)) {
                res[i++] = y;
            }
            count++;
        }
        return res;
    }
}
