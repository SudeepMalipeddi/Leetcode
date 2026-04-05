/*
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
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
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int frequency = entry.getValue();
            buckets.get(frequency).add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;
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

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer> bucket[] = new ArrayList[nums.length + 1];

        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        for (int key : count.keySet()) {
            int freq = count.get(key);

            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(key);
        }

        int index = 0;
        int[] res = new int[k];

        for (int i = nums.length; i >= 0; i--) {
            if (bucket[i] != null) {
                for (int val : bucket[i]) {
                    res[index++] = val;

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

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int frequency = entry.getValue();
            buckets.get(frequency).add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;
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