/*
 * Problem: 2134. Minimum Swaps to Group All 1's Together II
 *
 * Problem Statement:
 * Given a circular binary array `nums`, find the minimum number of swaps required to group all 1s 
 * present in the array together at any location.
 *
 * Intuition:
 * The total number of 1s in the array, let's call it `k`, is fixed. To group all 1s together, 
 * we need to find a contiguous block of size `k` that already contains the maximum number of 1s. 
 * The number of swaps needed for any window of size `k` is simply `k - (number of 1s in that window)`.
 *
 * Approach:
 * 1. Calculate the total number of 1s (`k`) in the array using a stream or a loop. This defines our window size.
 * 2. Initialize the first window of size `k` and count the 1s within it.
 * 3. Use a sliding window technique to check every possible contiguous block of size `k`.
 * 4. Since the array is circular, the window can wrap around. We simulate this by iterating up to `n + k` 
 *    and using the modulo operator `% n` to access indices.
 * 5. Track the maximum number of 1s (`mx`) found in any window.
 * 6. The result is `k - mx`.
 *
 * Time Complexity: O(n) where n is the length of the array. We perform one pass to sum the 1s 
 * and another pass to slide the window.
 * Space Complexity: O(1) as we only use a constant amount of extra space for variables.
 *
 * Edge Cases:
 * - Array with all 0s: `k` will be 0, result will be 0.
 * - Array with all 1s: `k` will be `n`, `mx` will be `n`, result will be 0.
 * - Array with only one 1: `k` will be 1, `mx` will be 1, result will be 0.
 *
 * Dry Run:
 * nums = [0, 1, 0, 1, 1, 0, 0], n = 7
 * 1. k = sum(nums) = 3.
 * 2. Initial window (indices 0, 1, 2): [0, 1, 0], cnt = 1, mx = 1.
 * 3. Slide window (circularly):
 *    - i=3: add nums[3]=1, remove nums[0]=0 -> cnt=2, mx=2
 *    - i=4: add nums[4]=1, remove nums[1]=1 -> cnt=2, mx=2
 *    - i=5: add nums[5]=0, remove nums[2]=0 -> cnt=2, mx=2
 *    - i=6: add nums[6]=0, remove nums[3]=1 -> cnt=1, mx=2
 *    - i=7: add nums[0]=0, remove nums[4]=1 -> cnt=0, mx=2
 *    - i=8: add nums[1]=1, remove nums[5]=0 -> cnt=1, mx=2
 *    - i=9: add nums[2]=0, remove nums[6]=0 -> cnt=1, mx=2
 * 4. Result: k - mx = 3 - 2 = 1.
 *
 * Correctness Check:
 * The `minSwaps` method correctly handles the circular property by iterating up to `n + k` 
 * and using modulo indexing. The `minSwaps2` method is a standard sliding window for 
 * linear arrays and does NOT correctly handle the circular requirement of this problem.
 */
import java.util.Arrays;

public class MinimumSwapstoGroupAll1sTogetherII2134 {
    
    public int minSwaps(int[] nums) {
        // Calculate the total number of 1s in the array. This is our fixed window size.
        int k = Arrays.stream(nums).sum();
        int n = nums.length, cnt = 0;
        
        // Initialize the count of 1s for the first window of size k.
        for (int i = 0; i < k; i++) {
            cnt += nums[i];
        }
        int mx = cnt;
        
        // Slide a circular window of size k and track max number of ones.
        // We iterate up to n + k to ensure every possible wrap-around window is checked.
        for (int i = k; i < n + k; ++i) {
            // Add the element entering the window (circularly) and subtract the one leaving.
            cnt += nums[i % n] - nums[(i - k + n) % n];
            // Update the maximum number of 1s found in any window so far.
            mx = Math.max(mx, cnt);
        }
        // The minimum swaps needed is the total number of 1s minus the max 1s already in a window.
        return k - mx;
    }

    
    public int minSwaps2(int[] data) {
    
        int ones = 0;
        
        // Count total ones to determine window size.
        for (int i : data) {
            ones += i;
        }
        int k = ones;
        int n = data.length;
        int left = 0;
        int right = 0;
        int maxOnes = 0;
        int windowOnes = 0;
        
        // Standard sliding window for a linear (non-circular) array.
        while (right < n) {
            windowOnes += data[right];
            
            // Maintain window size k by shrinking from the left when the window grows too large.
            if (right - left + 1 > k) {
                windowOnes -= data[left];
                left++;
            }
            maxOnes = Math.max(maxOnes, windowOnes);
            right++;
        }
        // Note: This method will fail on circular test cases as it doesn't wrap around the end.
        return k - maxOnes;
    }
}
