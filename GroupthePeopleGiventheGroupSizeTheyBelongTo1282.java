/*
 * Problem Reference: LeetCode 1282 - Group the People Given the Group Size They Belong To
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Bucket indices by required size and flush when bucket fills.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
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

public class GroupthePeopleGiventheGroupSizeTheyBelongTo1282 {
    public List<List<Integer>> groupThePeople(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < n; i++) {
            int groupSize = nums[i];
            if (!map.containsKey(groupSize)) {
                map.put(groupSize, new ArrayList<>());
            }
            map.get(groupSize).add(i);
            if (map.get(groupSize).size() == groupSize) {
                res.add(new ArrayList<>(map.get(groupSize)));
                map.remove(groupSize);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GroupthePeopleGiventheGroupSizeTheyBelongTo1282 solution = new GroupthePeopleGiventheGroupSizeTheyBelongTo1282();
        int[] nums = { 3, 3, 3, 3, 3, 1, 3 };
        List<List<Integer>> result = solution.groupThePeople(nums);
        for (List<Integer> group : result) {
            System.out.println(group);
        }
    }
}
