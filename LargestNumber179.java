/*
 * Problem: 179. Largest Number
 *
 * Problem Statement:
 * Given a list of non-negative integers, arrange them such that they form the largest possible number.
 * Since the result may be very large, return a string instead of an integer.
 *
 * Intuition:
 * This is a sorting problem where the standard numerical order doesn't work (e.g., 10 vs 2). 
 * The core insight is that for any two numbers 'a' and 'b', we should place 'a' before 'b' 
 * if the concatenation "ab" is greater than "ba". This comparison rule is transitive, 
 * allowing us to sort the entire array to find the global maximum.
 *
 * Approach:
 * 1. Convert the integer array into a string array to facilitate concatenation-based comparison.
 * 2. Sort the string array using a custom comparator: compare (b + a) with (a + b).
 *    Sorting in descending order ensures the "largest" combinations come first.
 * 3. Handle the edge case where the input consists only of zeros (e.g., [0, 0]). 
 *    If the largest element after sorting is "0", the entire result should be "0".
 * 4. Concatenate the sorted strings using a StringBuilder for efficiency.
 *
 * Time Complexity: O(n log n * k)
 * - n is the number of integers in the array.
 * - k is the average number of digits in the integers.
 * - Sorting takes O(n log n) comparisons, and each comparison involves string concatenation 
 *   and comparison of length O(k).
 *
 * Space Complexity: O(n * k)
 * - We store n strings of average length k in a new array.
 * - The StringBuilder also stores the final result of length n * k.
 *
 * Edge Cases:
 * - All zeros: [0, 0] should return "0", not "00".
 * - Numbers that are prefixes of each other: [3, 30] should return "330", not "303".
 *
 * Dry Run:
 * nums = [3, 30, 34, 5, 9]
 * 1. Convert to Strings: ["3", "30", "34", "5", "9"]
 * 2. Sort with (b+a).compareTo(a+b):
 *    - "9" vs "5": "95" > "59" -> ["9", "5", ...]
 *    - "34" vs "3": "343" > "334" -> ["9", "5", "34", "3", ...]
 *    - "3" vs "30": "330" > "303" -> ["9", "5", "34", "3", "30"]
 * 3. Join: "9534330"
 *
 * Correctness Check:
 * The solution correctly implements the greedy sorting strategy. The edge case for 
 * leading zeros is handled by checking if the first element of the sorted array is "0".
 */

import java.util.Arrays;

public class LargestNumber179 {
    public static String largestNumber(int[] nums) {
        // Convert every integer to string for custom concatenation-based ordering.
        // We use strings because numerical sorting (9 > 10) is different from 
        // lexicographical/concatenation sorting (910 > 109).
        String[] arr = new String[nums.length];
        // Build string representation array.
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        // Place x before y if xy yields larger number than yx.
        // We use (b + a).compareTo(a + b) for descending order (largest combinations first).
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // Highest token "0" implies all tokens are "0" because the array is sorted.
        // Without this check, [0, 0] would return "00" instead of "0".
        if (arr[0].equals("0")) {
            return "0";
        }

        // Join ordered tokens into final answer.
        // StringBuilder is used to avoid O(n^2) string concatenation overhead in a loop.
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
