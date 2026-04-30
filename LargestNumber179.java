/*
 * Problem Statement: Rearrange non-negative integers so their concatenation forms the numerically largest possible string.
 * Intuition: For two numbers a and b, order should depend on which concatenation (ab or ba) is larger.
 * Approach: Convert numbers to strings, sort by descending (b+a) vs (a+b), then concatenate sorted strings.
 * Time Complexity: O(n log n * m) where m is average digit length used in comparator concatenation/compare.
 * Space Complexity: O(n*m) for string array and output builder (excluding sort internals).
 * Edge Cases handled: If largest sorted token is "0", all numbers are zero, so return single "0".
 * Dry Run: nums=[10,2] -> compare "210" vs "102", so order is ["2","10"], output "210".
 * LeetCode matching: Matches LeetCode 179 (Largest Number).
 * Correctness Check: Core solution logic is correct; unrelated main method prints 4%3 and is harmless to solver.
 */

import java.util.Arrays;

public class LargestNumber179 {
    public static String largestNumber(int[] nums) {
        // Convert every integer to string for custom concatenation-based ordering.
        String[] arr = new String[nums.length];
        // Build string representation array.
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        // Place x before y if xy yields larger number than yx.
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
        // Highest token "0" implies all tokens are "0".
        if (arr[0].equals("0")) {
            return "0";
        }
        // Join ordered tokens into final answer.
        StringBuilder sb = new StringBuilder();
        // Append in sorted order to maximize resulting concatenation.
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Unrelated local print; does not affect largestNumber logic.
        System.out.println(4 % 3);
    }
}
