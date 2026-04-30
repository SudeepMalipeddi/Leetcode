/*
 * Problem Reference: LeetCode 1424 - Diagonal Traverse II
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Collect values by i+j diagonal and output in required reverse row order.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total elements)
 *
 * Space Complexity:
 * O(total elements)
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

public class DiagonalTraverseII1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> diagonals = new HashMap<>();
        int total = 0;
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                diagonals.computeIfAbsent(i + j, k -> new ArrayList<>()).add(nums.get(i).get(j));
                total++;
            }
        }
        int[] result = new int[total];
        int idx = 0, d = 0;
        while (diagonals.containsKey(d)) {
            List<Integer> diag = diagonals.get(d);
            for (int i = diag.size() - 1; i >= 0; i--) {
                result[idx++] = diag.get(i);
            }
            d++;
        }
        return result;
    }
}
