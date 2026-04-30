/*
 * Problem: 1512. Number of Good Pairs (Filename: NumberofGoodpairs2812.java)
 *
 * Problem Statement:
 * Given an array of integers nums, return the number of good pairs.
 * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
 *
 * Intuition:
 * If a number has appeared 'k' times previously in the array, the current occurrence 
 * of that number can form 'k' new pairs with the previous occurrences. By keeping 
 * track of frequencies as we iterate, we can calculate the total pairs in a single pass.
 *
 * Approach:
 * 1. Brute Force (numIdenticalPairs1): Use nested loops to compare every possible pair (i, j) 
 *    where i < j. If nums[i] == nums[j], increment the answer.
 * 2. Frequency Map (numIdenticalPairs): Use a HashMap to store the count of each number 
 *    encountered. For every number, add its current frequency to the total count, 
 *    then update its frequency in the map.
 *
 * Time Complexity: 
 * - Brute Force: O(n^2) because of the nested loops iterating through the array.
 * - Frequency Map: O(n) because we traverse the array once and HashMap operations are O(1) on average.
 *
 * Space Complexity: 
 * - Brute Force: O(1) as no extra data structures are used.
 * - Frequency Map: O(n) in the worst case where all elements are unique.
 *
 * Edge Cases:
 * - Array with all unique elements: Result should be 0.
 * - Array with all identical elements: Result should be n*(n-1)/2.
 * - Array with only one element: Result should be 0.
 *
 * Dry Run (Frequency Map Approach):
 * Input: [1, 2, 1, 1]
 * 1. num=1: ans += 0 (map empty), map={1:1}
 * 2. num=2: ans += 0 (2 not in map), map={1:1, 2:1}
 * 3. num=1: ans += 1 (1 seen once), map={1:2, 2:1}
 * 4. num=1: ans += 2 (1 seen twice), map={1:3, 2:1}
 * Total ans = 3.
 *
 * Correctness Check:
 * - The brute force method (numIdenticalPairs1) is correct.
 * - BUG ALERT: In numIdenticalPairs, the line `map.put(num, map.getOrDefault(num,0));` 
 *   fails to increment the count. It should be `+ 1`. As written, the frequency 
 *   never increases beyond 0 or its initial state, leading to an incorrect result of 0.
 */
import java.util.HashMap;

public class NumberofGoodpairs2812 {
    
    public int numIdenticalPairs1(int[] nums){
        int ans = 0;
        
        // Outer loop selects the first element of the potential pair
        for(int i = 0; i<nums.length;i++){
            
            // Inner loop selects the second element, ensuring the index j is always greater than i
            for(int j = i+1;j<nums.length;j++){
                
                // If the values at the two indices match, we found a "good pair"
                if(nums[i] == nums[j]){
                    ans++;
                }
            }
        }
        return ans;
    }



    
    public int numIdenticalPairs(int[] nums){
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        
        for(int num: nums){
            // Existing frequency of num equals number of new good pairs with this element.
            ans += map.getOrDefault(num, 0);
            // BUG: This line does not increment the count. It should be map.getOrDefault(num, 0) + 1.
            // Currently, it retrieves the value and puts the same value back, so frequencies never grow.
            map.put(num, map.getOrDefault(num,0));
            
        }
        return ans;
    }
}
