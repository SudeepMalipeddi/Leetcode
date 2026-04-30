/*
 * Problem: 78. Subsets
 *
 * Problem Statement:
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets.
 *
 * Intuition:
 * This is a classic combinatorial search problem. Since we need to find all possible combinations
 * of elements, we can treat this as a decision tree where at each step we decide which element
 * to include next. By using a "start" index, we ensure we only move forward in the array,
 * which naturally prevents duplicate combinations (like [1,2] and [2,1]).
 *
 * Approach:
 * 1. Initialize a result list to store all subsets.
 * 2. Use a recursive backtracking helper function that maintains the current subset (temp) and a starting index.
 * 3. In each call, add a copy of the current subset to the result (every node in the recursion tree is a valid subset).
 * 4. Loop through the remaining elements starting from 'start', add an element, recurse to the next index, and then backtrack.
 *
 * Time Complexity: O(N * 2^N). There are 2^N possible subsets for a set of size N. For each subset, 
 * we perform a copy operation into the result list, which takes O(N) time.
 * Space Complexity: O(N). The recursion stack depth is at most N, and the temporary list 'temp' 
 * stores at most N elements.
 *
 * Edge Cases:
 * - Empty input array: The code will return [[]].
 * - Array with one element: The code will return [[], [nums[0]]].
 *
 * Dry Run:
 * nums = [1, 2]
 * 1. backtrack(start=0, temp=[]) -> res = [[]]
 * 2. i=0: temp=[1], backtrack(start=1, temp=[1]) -> res = [[], [1]]
 * 3. i=1: temp=[1,2], backtrack(start=2, temp=[1,2]) -> res = [[], [1], [1,2]]
 * 4. i=2: loop ends, returns to step 3.
 * 5. temp.remove(2) -> temp=[1], loop ends, returns to step 2.
 * 6. temp.remove(1) -> temp=[], i=1: temp=[2], backtrack(start=2, temp=[2]) -> res = [[], [1], [1,2], [2]]
 * 7. i=2: loop ends, returns to step 6.
 * 8. temp.remove(2) -> temp=[], loop ends. Final result: [[], [1], [1,2], [2]]
 *
 * Correctness Check:
 * The solution is correct. Sorting the input is not strictly required for this problem since 
 * elements are unique, but it is a common practice in subset problems to ensure consistent output.
 */

import java.util.*;

public class Subsets78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // Sorting is optional here as elements are unique, but helps in maintaining order.
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> temp, int[] nums, int start) {
        // Every state reached in the recursion tree is a valid subset (including the empty set).
        // We must create a new ArrayList copy because 'temp' is a reference that will be modified.
        res.add(new ArrayList<>(temp));

        // Iterate through the array starting from 'start' to avoid duplicate combinations.
        for (int i = start; i < nums.length; i++) {
            // Choose: Add the current element to our candidate subset.
            temp.add(nums[i]);
            
            // Explore: Move to the next index to build further on this subset.
            backtrack(res, temp, nums, i + 1);
            
            // Un-choose (Backtrack): Remove the last element to explore other branches 
            // (subsets that don't include nums[i] at this position).
            temp.remove(temp.size() - 1);
        }
    }
}
