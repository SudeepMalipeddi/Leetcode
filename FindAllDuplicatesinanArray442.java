/*
 * Problem: LeetCode 442 - Find All Duplicates in an Array
 *
 * Problem Statement:
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] 
 * and each integer appears once or twice, return an array of all the integers that appears twice.
 *
 * Intuition:
 * The key constraint is that numbers are in the range [1, n]. This allows us to use the array 
 * itself as a hash map or use an auxiliary array of size n+1. By treating the values as 
 * indices, we can "mark" visited numbers.
 *
 * Approach:
 * 1. Method 1 (findDuplicates): Uses an auxiliary frequency array (count) to track occurrences.
 * 2. Method 2 (findDuplicates2): Uses the "sign-flip" trick. For each number x, we go to 
 *    index |x|-1 and flip the sign of the value there. If we encounter a value that is 
 *    already negative, it means we've seen the current number before.
 *
 * Time Complexity: O(n) - We traverse the input array a constant number of times.
 * Space Complexity: 
 * - Method 1: O(n) for the auxiliary count array.
 * - Method 2: O(1) extra space (excluding the result list) as we modify the input array in-place.
 *
 * Edge Cases:
 * - Empty array: Returns empty list.
 * - No duplicates: Returns empty list.
 * - All elements appear twice: Returns all unique elements.
 *
 * Dry Run (Method 2):
 * Input: [4, 3, 2, 7, 8, 2, 3, 1]
 * 1. i=0, val=4: index=3, nums[3]=7 (pos), flip nums[3] to -7.
 * 2. i=1, val=3: index=2, nums[2]=2 (pos), flip nums[2] to -2.
 * 3. i=2, val=2: index=1, nums[1]=3 (pos), flip nums[1] to -3.
 * 4. i=3, val=7: index=6, nums[6]=3 (pos), flip nums[6] to -3.
 * 5. i=4, val=8: index=7, nums[7]=1 (pos), flip nums[7] to -1.
 * 6. i=5, val=2: index=1, nums[1]=-3 (neg) -> Duplicate found: 2.
 * 7. i=6, val=3: index=2, nums[2]=-2 (neg) -> Duplicate found: 3.
 * 8. i=7, val=1: index=0, nums[0]=4 (pos), flip nums[0] to -4.
 * Result: [2, 3]
 *
 * Correctness Check:
 * The solution is correct. Method 2 satisfies the O(n) time and O(1) extra space requirement 
 * typically requested in interviews for this specific problem.
 */
import java.util.*;

public class FindAllDuplicatesinanArray442 {
    /**
     * Approach 1: Frequency Counting using an auxiliary array.
     * This is intuitive but uses O(n) extra space.
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // Since values are 1 to n, we need size n+1 to avoid IndexOutOfBounds
        int[] count = new int[nums.length + 1];
        
        // First pass: Count the frequency of each number
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        
        // Second pass: Identify numbers that appeared exactly twice
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 2) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * Approach 2: In-place marking (Sign Flip).
     * This achieves O(1) extra space by using the input array to store state.
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Use the absolute value because the element might have been flipped to negative
            // Subtract 1 to convert the 1-based value to a 0-based index
            int index = Math.abs(nums[i]) - 1;
            
            // If the value at the calculated index is already negative, 
            // it means we have encountered this 'index + 1' value before.
            if (nums[index] < 0) {
                res.add(index + 1);
            }
            
            // Flip the sign at the target index to "mark" that the value (index + 1) has been seen.
            // We flip it regardless of whether it's the first or second time; 
            // the 'if' check above catches the second occurrence.
            nums[index] = -nums[index];
        }
        return res;
    }
}
