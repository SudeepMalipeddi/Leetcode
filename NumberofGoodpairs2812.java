/*
Problem Statement:
- Count pairs (i,j) with i<j and nums[i]==nums[j].

Intuition:
- For each number, current frequency tells how many new pairs it creates.

Approach:
- Brute-force method checks all pairs; hashmap method tries to accumulate frequencies.
- Correctness concern: numIdenticalPairs currently does not increment stored frequency (missing +1), so it undercounts.

Time Complexity:
- Brute force O(n^2); hashmap target approach O(n).

Space Complexity:
- O(u) for distinct values map.

Edge Cases:
- All unique values give 0.

Dry Run:
- For value seen 3 times, occurrences contribute 0+1+2 pairs.
*/
import java.util.HashMap;

public class NumberofGoodpairs2812 {
    
    public int numIdenticalPairs1(int[] nums){
        int ans = 0;
        
        for(int i = 0; i<nums.length;i++){
            
            for(int j = i+1;j<nums.length;j++){
                
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
            map.put(num, map.getOrDefault(num,0));
            
        }
        return ans;
    }
}
