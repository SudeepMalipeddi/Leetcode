import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15. 3Sum
 * Finds all unique triplets in the array which gives the sum of zero.
 */
class sum15 {
    /**
     * Time Complexity: O(n^2), where n is the length of the array.
     * Space Complexity: O(1) or O(n) depending on the sorting algorithm.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // We will be using two pointers like the solution to the question of Valid
        // Palindrome question
        if (nums.length < 3) {
            return result;
        }

        // Sort the array to easily manage duplicates and use the two-pointer technique
        Arrays.sort(nums);
        int i = 0;

        // Loop through the array, stopping 2 elements before the end
        while (i < nums.length - 2) {
            // If the current element is positive, the sum can never be zero since array is
            // sorted
            if (nums[i] > 0)
                break;

            // Initialize two pointers, one just after i and one at the end
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                // If we found a triplet, add it to the result
                if (sum == 0)
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                // If sum <= 0, we need a larger value, so we move the left pointer to the right
                // Skipping duplicates for the second element
                if (sum <= 0)
                    while (nums[j] == nums[++j] && j < k)
                        ;

                // If sum >= 0, we need a smaller value, so we move the right pointer to the
                // left
                // Skipping duplicates for the third element
                if (sum >= 0)
                    while (nums[k--] == nums[k] && j < k)
                        ;
            }
            // Skip duplicates for the first element
            while (nums[i] == nums[++i] && i < nums.length - 2)
                ;
        }
        return result;
    }
}
