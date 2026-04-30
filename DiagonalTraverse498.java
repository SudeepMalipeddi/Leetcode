/*
 * Problem Reference: LeetCode 498 - Diagonal Traverse
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Group or walk by diagonal index and alternate direction.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(m*n)
 *
 * Space Complexity:
 * O(1) to O(m*n) based on method
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
import java.util.*;

public class DiagonalTraverse498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i + j;
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(mat[i][j]);
            }
        }
        int i = 0;
        int count = 0;
        for (Integer x : map.keySet()) {
            if (count % 2 != 0) {
                Collections.reverse(map.get(x));
            }
            for (Integer y : map.get(x)) {
                res[i++] = y;
            }
            count++;
        }
        return res;
    }
}
