/*
 * Problem: 347. Top K Frequent Elements
 *
 * Problem Statement:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Intuition:
 * A standard sorting approach takes O(N log N). A Heap approach takes O(N log K).
 * However, since the maximum possible frequency of any element is limited by the 
 * array size (N), we can use Bucket Sort to achieve linear time complexity. 
 * By using the frequency as an index in an array of lists, we can retrieve 
 * elements in descending order of their frequency.
 *
 * Approach:
 * 1. Count the frequency of each number using a HashMap.
 * 2. Create "buckets" where the index represents the frequency (from 0 to nums.length).
 * 3. Iterate through the frequency map and place each number into the bucket 
 *    corresponding to its frequency value.
 * 4. Traverse the buckets from the highest index (max frequency) down to 0, 
 *    collecting elements until we have gathered k elements.
 *
 * Time Complexity: O(N)
 * - Building the frequency map takes O(N).
 * - Initializing and filling the buckets takes O(N).
 * - Iterating through buckets to find the top K takes O(N) in the worst case.
 *
 * Space Complexity: O(N)
 * - The HashMap stores up to N unique elements.
 * - The bucket structure stores N elements across all lists.
 *
 * Edge Cases:
 * - k = 1: Only the single most frequent element is needed.
 * - All elements are unique: Every element has a frequency of 1.
 * - All elements are the same: One element has a frequency of N.
 *
 * Dry Run:
 * nums = [1, 1, 1, 2, 2, 3], k = 2
 * 1. Map: {1: 3, 2: 2, 3: 1}
 * 2. Buckets: [0:[], 1:[3], 2:[2], 3:[1], 4:[], 5:[], 6:[]]
 * 3. Iterate backwards:
 *    - i=6,5,4: Empty
 *    - i=3: Found [1]. Result = [1], count = 1.
 *    - i=2: Found [2]. Result = [1, 2], count = 2.
 *    - count == k, return [1, 2].
 *
 * Correctness Check:
 * The solution is correct. The use of Bucket Sort is the optimal way to solve 
 * this problem in linear time. Note that the order of elements with the same 
 * frequency does not matter per the problem description.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements347 {
    public int[] topKFrequent(int[] nums, int k) {
        // Map<Integer, Integer> map = new HashMap<>();

        // for(int n: nums){
        // map.put(n, map.getOrDefault(n,0)+1);
        // }

        // List<Integer>[] bucket = new List[nums.length+1];
        // for(int n: map.keySet()){
        // int freq = map.get(n);
        // if(bucket[freq] == null ){
        // bucket[freq] = new LinkedList<>();
        // }
        // bucket[freq].add(n);
        // }

        // List<Integer> result = new LinkedList<>();
        // for(int i = bucket.length-1; i>0 && k>0; --i){
        // if(bucket[i]!=null){
        // List<Integer> list = bucket[i];
        // result.addAll(list);
        // k-=list.size();
        // }
        // }
        // return result;
        
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create buckets where index = frequency. 
        // Using a List of Lists to avoid generic array creation issues.
        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }

        // Step 3: Group numbers by their frequency
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int frequency = entry.getValue();
            buckets.get(frequency).add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;
        // Step 4: Iterate from the highest possible frequency downwards
        for (int i = buckets.size() - 1; i >= 0 && index < k; i--) {
            List<Integer> bucket = buckets.get(i);
            if (!bucket.isEmpty()) {
                for (int num : bucket) {
                    result[index++] = num;
                    // Stop as soon as we have collected k elements
                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        // Using an array of Lists. Note: This may cause a "unchecked" warning in Java.
        List<Integer> bucket[] = new ArrayList[nums.length + 1];

        for (int num : nums) {
            // merge is a clean way to update counts: if key exists, apply sum; else insert 1.
            count.merge(num, 1, Integer::sum);
        }

        for (int key : count.keySet()) {
            int freq = count.get(key);

            // Lazy initialization of the list at a specific frequency index
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(key);
        }

        int index = 0;
        int[] res = new int[k];

        // Iterate from max frequency (nums.length) down to 0
        for (int i = nums.length; i >= 0; i--) {
            if (bucket[i] != null) {
                for (int val : bucket[i]) {
                    res[index++] = val;

                    // Early exit once the result array is filled
                    if (index == k) {
                        return res;
                    }
                }
            }
        }
        return res;
    }
}

class Solution1 {
    public int[] topKFrequent(int[] nums, int k) {

        // Step 1: Build frequency map
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Initialize buckets (List of Lists approach)
        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }

        // Step 3: Populate buckets based on frequency
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int frequency = entry.getValue();
            buckets.get(frequency).add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;
        // Step 4: Collect top K elements from buckets
        for (int i = buckets.size() - 1; i >= 0 && index < k; i--) {
            List<Integer> bucket = buckets.get(i);
            if (!bucket.isEmpty()) {
                for (int num : bucket) {
                    result[index++] = num;
                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
