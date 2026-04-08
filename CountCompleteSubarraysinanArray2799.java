import java.util.*;

public class CountCompleteSubarraysinanArray2799 {
    public static int countCompleteSubarrays(int[] nums) {
        int totalDistinct = countDistinct(nums);
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                seen.add(nums[j]);
                if (seen.size() == totalDistinct) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countDistinct(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size();
    }

    public static List<List<Integer>> findAllSubarrays(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = i; j < n; j++) {
                sub.add(arr[j]);
                result.add(new ArrayList<>(sub));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 1, 2, 2 };
        countCompleteSubarrays(nums);
    }
}
