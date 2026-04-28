/*
 * Problem: LeetCode 1752 - Check if Array Is Sorted and Rotated
 * Problem Statement: Determine if the array can be obtained by taking a
 *   non-decreasing sorted array and rotating it some number of positions.
 * Intuition: A valid rotated array has at most one "drop" where nums[i] > nums[i+1],
 *   considering the wrap from end to start as well.
 * Approach (method checking):
 *   1) Count drops between adjacent elements.
 *   2) Also compare nums[0] with nums[n-1] to account for wraparound.
 *   3) If more than one drop exists, it's not sorted-and-rotated.
 * Time Complexity: O(n) for a single pass.
 * Space Complexity: O(1).
 * Edge Cases: Already sorted (0 rotation), all equal elements, n=1.
 * Dry Run: nums=[3,4,5,1,2] -> wrap drop count=0, internal drop at 5>1 => 1 => true.
 * Correctness Check: checking() matches the standard drop-count criterion.
 *   Note: check() is marked "doesn't work" and mutates the array; it also fails
 *   to update min inside its loop, so it is not a correct solution.
 */
import java.util.Arrays;

public class CheckifArrayIsSortedandRotated1752 {

    // working code
    public boolean checking(int[] nums) {
        int count = 0, n = nums.length;
        if (nums[0] < nums[n - 1]) {
            count++;
        }
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    // doesn't work
    public static boolean check(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length); // keep a sorted reference
        int min = Integer.MAX_VALUE;
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                ind = i;
            }
        }
        ind = ind - 1;
        helper(0, nums.length - 1, nums); // reverse whole array
        int resind = nums.length - ind;
        helper(0, resind - 1, nums); // reverse first segment
        helper(resind, nums.length - 1, nums); // reverse second segment
        Arrays.sort(sorted);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != sorted[i]) {
                System.out.println("Not a rotated array");
                return false;
            }
        }
        return true;
    }

    public static void helper(int start, int end, int[] arr) {
        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
