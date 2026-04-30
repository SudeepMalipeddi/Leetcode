/*
 * Problem Statement: Return all elements that appear more than n/3 times in the array.
 * Intuition: At most two numbers can exceed n/3 frequency, so solutions either count frequencies directly or track up to two candidates.
 * Approach: This class provides 3 variants: (1) map + set + second scan, (2) cleaner hashmap count, (3) Boyer-Moore two-candidate voting + validation.
 * Time Complexity: majorityElement -> O(n) avg; majorityElement1 -> O(n) avg; majorityElement2 -> O(n) deterministic two passes.
 * Space Complexity: majorityElement/majorityElement1 -> O(n) map storage; majorityElement2 -> O(1) extra space.
 * Edge Cases handled: Empty/small arrays, duplicate candidates, and arrays with 0, 1, or 2 valid answers.
 * Dry Run: For [3,2,3], threshold=1, valid answer is [3]. For [1,1,1,3,3,2,2,2], valid answers are [1,2].
 * LeetCode matching/assumption: Matches LeetCode 229 (more than floor(n/3)).
 * Correctness Check: Method 3 is the optimal interview solution: first pass finds candidates, second pass confirms actual counts.
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
        
        // Single pass frequency counting.
        for (int i = 0; i < nums.length; i++) {
            elementCountMap.put(nums[i], elementCountMap.getOrDefault(nums[i], 0) + 1);
        }
        
        List<Integer> majorityElements = new ArrayList<>();
        int threshold = nums.length / 3;
        
        // Filter entries that satisfy count > n/3.
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
            // Assign first empty slot.
            if (count1 == 0 && nums[i] != candidate2) {
                count1 = 1;
                candidate1 = nums[i];
            } 
            // Assign second empty slot.
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
            // Cancel votes when current number matches neither candidate.
            else {
                count1--;
                count2--;
            }
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3; // Required strict threshold.

        // Second pass: validate real frequencies of the two candidates.
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            }
        }

        // Add only truly valid majority elements.
        if (count1 > threshold) {
            result.add(candidate1);
        }
        if (count2 > threshold) {
            result.add(candidate2);
        }

        return result;
 
    }

}
