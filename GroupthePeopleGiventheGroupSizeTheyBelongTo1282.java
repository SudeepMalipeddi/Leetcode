/*
 * Problem: 1282. Group the People Given the Group Size They Belong To
 *
 * Problem Statement:
 * There are n people that are split into some unknown number of groups. Each person is connected 
 * with a unique ID from 0 to n - 1. You are given an integer array groupSizes, where groupSizes[i] 
 * is the size of the group that person i is in. Return a list of groups such that each person i 
 * is in a group of size groupSizes[i].
 *
 * Intuition:
 * People who belong to groups of the same size can be processed together. We can use a "bucket" 
 * strategy: collect people of the same group size in a temporary list. As soon as that list 
 * reaches the required size, it forms a complete group, and we can move it to our final result.
 *
 * Approach:
 * 1. Initialize a result list and a HashMap to store temporary groups (key: group size, value: list of indices).
 * 2. Iterate through the input array. For each person at index i:
 *    a. Retrieve or create the list corresponding to their required group size.
 *    b. Add the current index i to that list.
 *    c. If the list's size equals the required group size, the group is full.
 *    d. Add the full group to the result and remove the entry from the map to start fresh for the next group of that size.
 *
 * Time Complexity: O(N) where N is the length of the input array. We visit each person once and perform O(1) map operations.
 * Space Complexity: O(N) to store the map and the final result containing all N indices.
 *
 * Edge Cases:
 * - groupSizes = [1, 1, 1]: Each person is in their own group.
 * - groupSizes = [3, 3, 3]: All people are in one single group.
 * - Multiple groups of the same size: Handled by clearing the map entry once a group is filled.
 *
 * Dry Run:
 * Input: nums = [3, 3, 3, 3, 3, 1, 3]
 * i=0: size=3, map={3: [0]}
 * i=1: size=3, map={3: [0, 1]}
 * i=2: size=3, map={3: [0, 1, 2]} -> Full! res=[[0,1,2]], map={}
 * i=3: size=3, map={3: [3]}
 * i=4: size=3, map={3: [3, 4]}
 * i=5: size=1, map={3: [3, 4], 1: [5]} -> Full! res=[[0,1,2], [5]], map={3: [3, 4]}
 * i=6: size=3, map={3: [3, 4, 6]} -> Full! res=[[0,1,2], [5], [3, 4, 6]], map={}
 *
 * Correctness Check:
 * The solution correctly partitions indices into groups of the specified sizes. By removing the 
 * key from the map once a group is full, it correctly handles cases where multiple groups 
 * share the same size requirement.
 */
import java.util.*;

public class GroupthePeopleGiventheGroupSizeTheyBelongTo1282 {
    public List<List<Integer>> groupThePeople(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // Map to track people currently waiting to fill a group of a specific size.
        // Key: The required group size. Value: List of person IDs (indices).
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < n; i++) {
            int groupSize = nums[i];
            
            // If no one is currently waiting for this group size, initialize a new bucket.
            if (!map.containsKey(groupSize)) {
                map.put(groupSize, new ArrayList<>());
            }
            
            // Add the current person to the bucket corresponding to their required size.
            map.get(groupSize).add(i);
            
            // Once the bucket reaches the required capacity, it's a complete group.
            if (map.get(groupSize).size() == groupSize) {
                // Add a copy of the current full group to the final result list.
                res.add(new ArrayList<>(map.get(groupSize)));
                
                // Remove the entry from the map so that the next person with this 
                // group size starts a brand new group.
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
