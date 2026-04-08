import java.util.*;

public class MinimumOperationstoExceedThresholdValueII3066 {
    public int minOperations(int[] nums, int k) {
        Queue<Long> q = new PriorityQueue<>();
        for (int x : nums) {
            q.offer((long) x);
        }
        int res = 0;
        while (q.peek() < k) {
            long temp1 = q.poll();
            long temp2 = q.poll();
            q.offer(Math.min(temp1, temp2) * 2 + Math.max(temp1, temp2));
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        MinimumOperationstoExceedThresholdValueII3066 sol = new MinimumOperationstoExceedThresholdValueII3066();
        System.out.println(sol.minOperations(new int[]{2, 11, 10, 1, 3}, 10));
        System.out.println(sol.minOperations(new int[]{1000000000, 999999999, 1000000000, 999999999, 1000000000, 999999999}, 1000000000));
    }
}
