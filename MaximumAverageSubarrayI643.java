public class MaximumAverageSubarrayI643 {
    public static double findMaxAverage(int[] nums, int k) {
        double res = 0;
        double currSum = 0;
        for (int i = 0; i < k; i++) {
            currSum += nums[i];
        }
        res = Math.max(currSum / k, res);
        for (int i = k; i < nums.length; i++) {
            currSum += nums[i] - nums[i - k];
            res = Math.max(currSum / k, res);
        }
        return res;
    }
}
