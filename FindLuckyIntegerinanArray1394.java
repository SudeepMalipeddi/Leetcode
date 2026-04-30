/*
 * Problem Reference: LeetCode 1394 - Find Lucky Integer in an Array
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Count frequencies, then pick largest value where value == frequency.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n) or O(1) bounded
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
import java.util.HashMap;

public class FindLuckyIntegerinanArray1394 {
    public int findLucky(int[] arr) {
        int res = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        // Iterate through the active search space while maintaining the intended invariant.
        for (int x : arr) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int x : map.keySet()) {
            if (map.get(x) == x) {
                res = Math.max(res, x);
            }
        }
        return res;
    }

    public int findLucky2(int[] arr) {
        int[] count = new int[501];
        for (int x : arr) {
            count[x]++;
        }
        for (int i = 500; i > 0; i--) {
            if (count[i] == i) {
                return i;
            }
        }
        return -1;
    }
}
