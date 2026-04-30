/*
 * Problem: 260. Single Number III
 *
 * Problem Statement:
 * Given an integer array nums, in which exactly two elements appear only once and all the other 
 * elements appear exactly twice, find the two elements that appear only once. You may return 
 * the answer in any order.
 *
 * Intuition:
 * The most straightforward way to identify elements with a specific frequency is to count 
 * occurrences using a hash-based data structure. Since we are looking for elements that 
 * appear exactly once while others appear twice, a frequency map allows us to filter 
 * the unique elements efficiently.
 *
 * Approach:
 * 1. Initialize a HashMap to store the frequency of each integer in the input array.
 * 2. Traverse the input array 'nums' and update the count for each element in the map.
 * 3. Iterate through the keys of the HashMap.
 * 4. If a key has a value (frequency) of 1, add it to the result array.
 * 5. Return the result array containing the two unique numbers.
 *
 * Time Complexity: O(N), where N is the length of the input array. We iterate through 
 * the array once to build the map and then iterate through the map keys (at most N elements).
 * Space Complexity: O(N) because in the worst case, the HashMap will store approximately 
 * (N/2) + 2 unique keys.
 *
 * Edge Cases:
 * - Array with only two elements: Both will be unique and returned.
 * - Large positive/negative integers: HashMap handles these correctly as keys.
 *
 * Dry Run:
 * Input: nums = [1, 2, 1, 3, 2, 5]
 * 1. Map building:
 *    - 1 -> 1
 *    - 2 -> 1
 *    - 1 -> 2
 *    - 3 -> 1
 *    - 2 -> 2
 *    - 5 -> 1
 *    Final Map: {1:2, 2:2, 3:1, 5:1}
 * 2. Key iteration:
 *    - Key 1: count 2 (skip)
 *    - Key 2: count 2 (skip)
 *    - Key 3: count 1 (add to res[0])
 *    - Key 5: count 1 (add to res[1])
 * Result: [3, 5]
 *
 * Correctness Check:
 * The solution correctly identifies the two unique elements. Note that while this 
 * solution is functionally correct, the problem on LeetCode often suggests an 
 * O(1) space complexity constraint using Bit Manipulation (XOR), which this 
 * specific implementation does not satisfy.
 */

import java.util.HashMap;

public class SingleNumberIII260 {
    public int[] singleNumber(int[] nums) {
        // The problem guarantees exactly two elements appear once
        int[] res = new int[2];
        
        // Use a HashMap to track the frequency of each number
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // First pass: Populate the frequency map
        for (int x : nums) {
            // getOrDefault handles the case where the key is seen for the first time
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        
        int i = 0;
        // Second pass: Iterate through the unique keys in the map
        for (int x : map.keySet()) {
            // If the frequency is 1, it is one of our target numbers
            if (map.get(x) == 1) {
                res[i++] = x;
            }
            
            // Optimization: Stop once we've found both numbers
            if (i == 2) break;
        }
        
        return res;
    }
}
