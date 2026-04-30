/*
 * Problem Statement: Find the maximum length subarray whose sum equals target s, assuming positive integers.
 * Intuition: Check every possible [i..j] subarray and record the longest one with exact sum s.
 * Approach: Triple nested loops: choose start i, end j, recompute sum over i..j, then update best length.
 * Time Complexity: O(n^3) due to explicit recomputation of each subarray sum.
 * Space Complexity: O(1) extra space.
 * Edge Cases handled: No matching subarray (returns 0), multiple valid windows (keeps max length), and short arrays.
 * Dry Run: For [1,2,3,1,1,1,1], s=3 -> valid windows [1,2], [3], [1,1,1], [1,1,1], best length = 3.
 * LeetCode matching/assumption: Similar to longest subarray with sum K; this file explicitly assumes positive numbers.
 * Correctness Check: Correct for positives, but slow; with positive arrays an O(n) sliding-window optimization is possible and preferable.
 */

public class LongestSubarraywithgivenSumKPositives {
    public static int longestSubArray(int[] arr, int s) {
        int n = arr.length;
        int len = 0;
        // Pick each possible start index.
        for (int i = 0; i < n; i++) {
            // Pick each possible end index for current start.
            for (int j = i; j < n; j++) {
                int sum = 0;
                // Recompute sum of subarray [i..j].
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                // Keep longest window that exactly hits target sum.
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
