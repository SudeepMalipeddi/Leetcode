/*
 * Problem: Longest Subarray with given Sum K (Positives)
 *
 * Problem Statement:
 * Given an array of positive integers and a target sum 's', find the length of the 
 * longest contiguous subarray that sums up exactly to 's'.
 *
 * Intuition:
 * This approach uses a brute-force strategy to explore every possible contiguous subarray. 
 * By checking every start and end index pair, we ensure no valid subarray is missed, 
 * though it is computationally expensive.
 *
 * Approach:
 * 1. Initialize 'len' to 0 to track the maximum length found.
 * 2. Use an outer loop 'i' to fix the starting point of a subarray.
 * 3. Use a nested loop 'j' to fix the ending point of the subarray.
 * 4. Use a third nested loop 'k' to iterate from 'i' to 'j' and calculate the sum of elements.
 * 5. If the calculated sum equals 's', update 'len' if the current subarray length (j - i + 1) is greater.
 *
 * Time Complexity: O(n^3) because there are three nested loops: two to define the boundaries 
 * and one to calculate the sum for each boundary pair.
 * Space Complexity: O(1) as we only use a few integer variables for tracking sum and length.
 *
 * Edge Cases:
 * - No subarray sums to 's': Returns 0.
 * - Multiple subarrays sum to 's': Returns the length of the longest one.
 * - Target 's' is smaller than any single element: Returns 0.
 *
 * Dry Run:
 * arr = [1, 2, 1], s = 3
 * i=0, j=0: sum=[1]=1
 * i=0, j=1: sum=[1,2]=3 -> len = max(0, 2) = 2
 * i=0, j=2: sum=[1,2,1]=4
 * i=1, j=1: sum=[2]=2
 * i=1, j=2: sum=[2,1]=3 -> len = max(2, 2) = 2
 * i=2, j=2: sum=[1]=1
 * Result: 2
 *
 * Correctness Check:
 * The solution is logically correct for finding the longest subarray. However, it is 
 * highly inefficient. For an array with only positive integers, an O(n) sliding window 
 * approach is the optimal standard.
 */

public class LongestSubarraywithgivenSumKPositives {
    public static int longestSubArray(int[] arr, int s) {
        int n = arr.length;
        int len = 0;
        // Pick each possible start index of a subarray.
        for (int i = 0; i < n; i++) {
            // Pick each possible end index for the current start index.
            for (int j = i; j < n; j++) {
                int sum = 0;
                // Recompute the sum of the subarray from index i to j.
                // This third loop is what makes the complexity O(n^3).
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                // If the current window's sum matches the target, check if it's the longest.
                if (sum == s) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        // int arr[] = { 2, 3, 5, 1, 9 };
        int arr[] = { 1, 2, 3, 1, 1, 1, 1 };
        int k = 3;

        System.out.println(longestSubArray(arr, k));

        // int res = 0;
        // int left = 0, right = 1;
        // int sum = 0;
        // while (right < n) {
        // sum += arr[left];
        // System.out.println("At the start sum is" + sum);
        // while (sum < k) {
        // sum += arr[right];
        // System.out.println(sum);
        // if (sum == k) {
        // int temp = right - left + 1;
        // if (temp > res) {
        // res = temp;
        // }
        // left = left + 1;
        // right = left + 1;
        // }
        // right++;
        // }
        // System.out.println(res);
        // }

        // System.out.println(longestSubArray(arr, k, n));
    }
}
