/*
 * Problem Reference: LeetCode 2215 - Find the Difference of Two Arrays
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Use sets to compute unique-only elements in each array.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n+m)
 *
 * Space Complexity:
 * O(n+m)
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

public class FindtheDifferenceofTwoArrays2215 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        // Iterate through the active search space while maintaining the intended invariant.
        for (int n : nums1) {
            set1.add(n);
        }
        for (int n : nums2) {
            set2.add(n);
        }
        for (int n : set1) {
            if (set2.contains(n) == false) {
                ans1.add(n);
            }
        }
        for (int n : set2) {
            if (set1.contains(n) == false) {
                ans2.add(n);
            }
        }
        result.add(ans1);
        result.add(ans2);
        return result;
    }
}
