/*
Problem Statement:
- In a circular binary array, compute minimum swaps to group all 1s contiguously.

Intuition:
- Best block of size equal to count(1s) should contain maximum 1s.

Approach:
- Use circular sliding window of size k=sum(nums) and maximize ones inside window.
- Improvement idea: minSwaps2 solves non-circular windowing; circular variant is in minSwaps.

Time Complexity:
- O(n) for window traversal.

Space Complexity:
- O(1) extra space.

Edge Cases:
- k=0 or k=1 returns 0 naturally.

Dry Run:
- If k=3 and best window has 2 ones, swaps needed = 3-2 = 1.
*/
import java.util.Arrays;

public class MinimumSwapstoGroupAll1sTogetherII2134 {
    
    public int minSwaps(int[] nums) {
        int k = Arrays.stream(nums).sum();
        int n = nums.length, cnt = 0;
        
        for (int i = 0; i < k; i++) {
            cnt += nums[i];
        }
        int mx = cnt;
        
        // Slide a circular window of size k and track max number of ones.
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = Math.max(mx, cnt);
        }
        return k - mx;
    }

    
    public int minSwaps2(int[] data) {
    
        int ones = 0;
        
        for (int i : data) {
            ones += i;
        }
        int k = ones;
        int n = data.length;
        int left = 0;
        int right = 0;
        int maxOnes = 0;
        int windowOnes = 0;
        
        while (right < n) {
            windowOnes += data[right];
            
            if (right - left + 1 > k) {
                windowOnes -= data[left];
                left++;
            }
            maxOnes = Math.max(maxOnes, windowOnes);
            right++;
        }
        return k - maxOnes;
    }
}
