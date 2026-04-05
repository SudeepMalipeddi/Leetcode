public class max_sum_k_subarray {
    public int maxSubArray(int[] nums,int n,int k){
        // Brute force approach
        int max_sum = Integer.MIN_VALUE;
        for(int i = 0;i<n-k+1;i++){
            int curr_sum=0;
            for(int j=0;j<k;j++){
                curr_sum+=nums[i+j];
            }
            max_sum=Math.max(curr_sum,max_sum);
        }
        return max_sum;
    }
    // Sliding Window approach 
    static int maxSum(int arr[], int n, int k)
    {
        // n must be greater
        if (n < k) {
            System.out.println("Invalid");
            return -1;
        }
 
        // Compute sum of first window of size k
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];
 
        // Compute sums of remaining windows by
        // removing first element of previous
        // window and adding last element of
        // current window.
        int window_sum = max_sum;
        for (int i = k; i < n; i++) {
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }
 
        return max_sum;
    }
}
