/*
 * Problem: 1394. Find Lucky Integer in an Array
 *
 * Problem Statement:
 * A lucky integer is an integer that has a frequency in the array equal to its value.
 * Given an array of integers, return the largest lucky integer in the array. 
 * If there is no lucky integer, return -1.
 *
 * Intuition:
 * The core requirement is to map values to their occurrences. Once we have the frequency 
 * of each number, we simply compare the number itself to its count. To find the "largest" 
 * lucky integer, we can either track a maximum during iteration or iterate through 
 * possible values in descending order.
 *
 * Approach:
 * 1. Method 1 (HashMap): Use a HashMap to store the frequency of each integer. 
 *    Iterate through the map's keys and update a result variable if key == frequency.
 * 2. Method 2 (Frequency Array): Since the problem constraints (usually 1-500) 
 *    allow it, use a fixed-size array where the index represents the number and 
 *    the value at that index represents the count. Iterate backwards to find the 
 *    first (largest) lucky integer.
 *
 * Time Complexity: O(N) where N is the length of the input array. We traverse the 
 * array once to count frequencies and then traverse the unique elements.
 * Space Complexity: O(N) for the HashMap approach, or O(1) for the frequency array 
 * approach (since the array size is fixed at 501 regardless of input size N).
 *
 * Edge Cases:
 * - No lucky integer exists: Should return -1.
 * - Multiple lucky integers: Should return the largest one.
 * - Array contains only one element: Check if that element is 1.
 *
 * Dry Run:
 * Input: arr = [2, 2, 3, 3, 3]
 * 1. Count frequencies: {2: 2, 3: 3}
 * 2. Check 2: frequency is 2. 2 == 2, so lucky. res = 2.
 * 3. Check 3: frequency is 3. 3 == 3, so lucky. res = max(2, 3) = 3.
 * Output: 3
 *
 * Correctness Check:
 * The solution is correct. Method 1 handles any integer range, while Method 2 
 * is optimized for the specific constraint that values are between 1 and 500.
 */
import java.util.HashMap;

public class FindLuckyIntegerinanArray1394 {
    /**
     * Approach 1: Using a HashMap.
     * This is a general-purpose solution that works regardless of the range of values in arr.
     */
    public int findLucky(int[] arr) {
        int res = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Step 1: Build the frequency map.
        // getOrDefault handles the case where the key is not yet in the map.
        for (int x : arr) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        
        // Step 2: Iterate through unique numbers to find the largest lucky integer.
        for (int x : map.keySet()) {
            // A lucky integer's value must equal its frequency.
            if (map.get(x) == x) {
                res = Math.max(res, x);
            }
        }
        return res;
    }

    /**
     * Approach 2: Using a Frequency Array (Counting Sort style).
     * This is more space-efficient and faster if the range of values is small and known.
     */
    public int findLucky2(int[] arr) {
        // Constraint: 1 <= arr[i] <= 500. We use size 501 to include index 500.
        int[] count = new int[501];
        
        // Step 1: Increment the count at the index corresponding to the value.
        for (int x : arr) {
            count[x]++;
        }
        
        // Step 2: Iterate backwards from the maximum possible value.
        // This ensures the first lucky integer we find is the largest one.
        for (int i = 500; i > 0; i--) {
            if (count[i] == i) {
                return i;
            }
        }
        
        // If no lucky integer is found after checking all indices.
        return -1;
    }
}
