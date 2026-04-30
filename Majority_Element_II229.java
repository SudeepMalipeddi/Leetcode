/*
 * Problem: 229. Majority Element II
 *
 * Problem Statement:
 * Given an integer array of size n, find all elements that appear more than floor(n/3) times.
 * The algorithm should ideally run in linear time and in O(1) space.
 *
 * Intuition:
 * This is an extension of the Boyer-Moore Voting Algorithm. In any array, there can be at most 
 * two elements that appear more than n/3 times (since 3 * (n/3 + 1) > n). By maintaining two 
 * potential candidates and their respective "votes," we can eliminate non-majority elements 
 * by canceling out triplets of distinct numbers.
 *
 * Approach:
 * 1. Initialize two candidates and two counters to zero.
 * 2. First Pass (Voting): 
 *    - If the current number matches candidate 1 or 2, increment the corresponding counter.
 *    - If a counter is zero, set the current number as the new candidate (ensuring it's not already the other candidate).
 *    - If the current number matches neither, decrement both counters (representing a "triplet" of distinct numbers being removed).
 * 3. Second Pass (Verification): Since the voting phase only guarantees candidates, we must 
 *    manually count their occurrences to ensure they strictly exceed n/3.
 *
 * Time Complexity: O(n) because we iterate through the array twice (or once for Map approaches).
 * Space Complexity: O(1) for the Boyer-Moore approach (Method 3), and O(n) for the Map approaches (Methods 1 & 2).
 *
 * Edge Cases:
 * - Empty array: Returns an empty list.
 * - Array with no element appearing > n/3: Returns an empty list.
 * - Array where all elements are the same: Returns a list with one element.
 *
 * Dry Run:
 * Input: [3, 2, 3], n=3, threshold=1
 * 1. num=3: count1=0, so candidate1=3, count1=1.
 * 2. num=2: count2=0, so candidate2=2, count2=1.
 * 3. num=3: matches candidate1, so count1=2.
 * Verification: 3 appears twice (2 > 1), 2 appears once (1 is not > 1).
 * Result: [3]
 *
 * Correctness Check:
 * The logic is correct. Method 3 correctly implements the generalized Boyer-Moore algorithm. 
 * The check `nums[i] != candidate2` when assigning `candidate1` is crucial to prevent 
 * both candidate variables from tracking the same value.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Majority_Element_II229 {

    // Method 1: frequency map + set; simple but uses extra memory and two scans over data keys/elements.
    public List<Integer> majorityElement(int[] nums) {
        int n = (nums.length)/3;
        List<Integer> res = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // Using a HashSet to store results ensures that even if we iterate over the 
        // original array again, we don't add the same majority element multiple times.
        HashSet<Integer> set = new HashSet<Integer>();
        // Count frequency of each value.
        for (int num : nums) {
            // Increment existing count or initialize.
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // Collect values whose counts exceed n/3.
        for(int num: nums){
            int count = map.get(num);
            // The problem asks for elements appearing MORE than n/3 times.
            if(count>n){
                set.add(num);
            }
        }
        // Convert set to list for return type compatibility.
        for (int num : set) {
            res.add(num);
        }
        return res;
    }

    // Method 2: concise hashmap counting; still O(n) space.
    public List<Integer> majorityElement1(int[] nums) {
        // Data structure stores each number and its frequency.
        Map<Integer, Integer> elementCountMap = new HashMap<>();
        
        // Single pass frequency counting using getOrDefault for cleaner syntax.
        for (int i = 0; i < nums.length; i++) {
            elementCountMap.put(nums[i], elementCountMap.getOrDefault(nums[i], 0) + 1);
        }
        
        List<Integer> majorityElements = new ArrayList<>();
        int threshold = nums.length / 3;
        
        // Filter entries that satisfy count > n/3 by iterating over the map's entry set.
        // This is more efficient than iterating over the original array again.
        for (Map.Entry<Integer, Integer> entry : elementCountMap.entrySet()) {
            int element = entry.getKey();
            int count = entry.getValue();
            
            // Check if the element count is greater than the threshold
            if (count > threshold) {
                majorityElements.add(element);
            }
        }
        
        return majorityElements;
    }

    // Method 3: Boyer-Moore (two candidates) + verification; optimal O(1) extra space.
    public List<Integer> majorityElement2(int[] nums) {
        int count1 = 0, count2 = 0; // Vote counters for two candidates.
        int candidate1 = 0, candidate2 = 0; // Current candidate values.

        // First pass: maintain up to two potential majority candidates.
        for (int i = 0; i < nums.length; i++) {
            // Assign first empty slot, ensuring we don't duplicate candidate2.
            if (count1 == 0 && nums[i] != candidate2) {
                count1 = 1;
                candidate1 = nums[i];
            } 
            // Assign second empty slot, ensuring we don't duplicate candidate1.
            else if (count2 == 0 && nums[i] != candidate1) {
                count2 = 1;
                candidate2 = nums[i];
            } 
            // Strengthen matching candidate vote.
            else if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            } 
            // If the current number is different from both candidates, it "cancels" 
            // one vote from each, effectively removing a triplet of distinct numbers.
            else {
                count1--;
                count2--;
            }
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3; // Required strict threshold.

        // Second pass: validate real frequencies of the two candidates.
        // Boyer-Moore only guarantees that IF there are majority elements, they are these candidates.
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            }
        }

        // Add only truly valid majority elements that pass the > n/3 threshold.
        if (count1 > threshold) {
            result.add(candidate1);
        }
        // Note: candidate1 and candidate2 are guaranteed to be distinct by the logic in the first pass.
        if (count2 > threshold) {
            result.add(candidate2);
        }

        return result;
 
    }

}
