/*
 * Problem: LeetCode 287 - Find the Duplicate Number
 *
 * Problem Statement:
 * Given an array of integers 'nums' containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in 'nums', return this repeated number.
 *
 * Intuition:
 * Since the numbers are in the range [1, n] and the array has n+1 elements, we can treat the values 
 * as pointers to indices within the same array. A duplicate value means two different indices 
 * point to the same location. We can detect this by marking visited indices (using negation) 
 * or by using a Set to track seen values.
 *
 * Approach:
 * 1. Method 1 (HashSet): Use a Set to store elements as we iterate. If an element is already in the set, it's the duplicate.
 * 2. Method 2 (Negative Marking): Iterate through the array. For each value, treat its absolute value as an index. 
 *    If the value at that index is already negative, we have encountered this index before, meaning the current 
 *    value is the duplicate. Otherwise, negate the value at that index to mark it as visited.
 *
 * Time Complexity: 
 * - findDuplicate: O(n) because we traverse the array once.
 * - findDuplicate1: O(n) because we traverse the array once.
 *
 * Space Complexity: 
 * - findDuplicate: O(n) to store elements in the HashSet.
 * - findDuplicate1: O(1) as we modify the input array in-place (no extra data structures).
 *
 * Edge Cases:
 * - Array with only two elements [1, 1].
 * - Duplicate appearing more than twice.
 * - Duplicate being the smallest (1) or largest (n) possible value.
 *
 * Dry Run:
 * nums = [1, 3, 2, 2]
 * 1. x = 1: idx = 1. nums[1] is 3 (>0). Set nums[1] = -3. Array: [1, -3, 2, 2]
 * 2. x = -3: idx = 3. nums[3] is 2 (>0). Set nums[3] = -2. Array: [1, -3, 2, -2]
 * 3. x = 2: idx = 2. nums[2] is 2 (>0). Set nums[2] = -2. Array: [1, -3, -2, -2]
 * 4. x = -2: idx = 2. nums[2] is -2 (<0). Duplicate found! Return idx = 2.
 *
 * Correctness Check:
 * The negative marking approach (findDuplicate1) modifies the input array. In some interview 
 * constraints, modifying the input is forbidden. If modification is not allowed, Floyd's 
 * Cycle-Finding Algorithm (Tortoise and Hare) would be the preferred O(1) space solution.
 */
import java.util.HashSet;

class FindtheDuplicateNumber287{
    // Hashset approach: Uses extra space to remember which numbers we have seen.
    public int findDuplicate(int[] nums) {
        HashSet<Integer> map = new HashSet<Integer>();
        for(int x:nums){
            // add() returns false if the element was already present in the set
            if(!map.add(x)){
                return x;
            } 
        }
        return 0;
    }
    
    // Negative marking approach: Uses the array itself as a hash table.
    public int findDuplicate1(int[] nums) {
        int len = nums.length;
        for(int x:nums){
            // Use Math.abs because the value at this index might have been negated by a previous step
            int idx = Math.abs(x);
            
            // If the value at the target index is already negative, it means we've 
            // processed a number with this value before.
            if(nums[idx]<0){
                return idx;
            }
            
            // Mark the index 'idx' as visited by negating the value stored there.
            nums[idx] =-nums[idx];
        }
        return len;
    }
    
}
