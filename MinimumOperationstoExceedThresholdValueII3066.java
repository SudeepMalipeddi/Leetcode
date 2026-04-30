/*
Problem Statement:
- Repeatedly combine the two smallest values until all values are at least k, minimizing operations.

Intuition:
- A min-heap always exposes the best pair to combine next.

Approach:
- Push all numbers to PriorityQueue<Long>; pop two mins, push 2*min+max, increment steps.

Time Complexity:
- O(m log n), where m is number of combine operations.

Space Complexity:
- O(n) for the heap.

Edge Cases:
- Handles large values using long arithmetic.

Dry Run:
- [1,3,10], k=7 -> combine 1 and 3 => 5, heap [5,10], then combine 5 and 10 => 20.
*/
import java.util.*;

public class MinimumOperationstoExceedThresholdValueII3066 {
    public int minOperations(int[] nums, int k) {
        Queue<Long> q = new PriorityQueue<>();
        
        for (int x : nums) {
            q.offer((long) x);
        }
        int res = 0;
        
        // Always combine the two smallest values to minimize future operations.
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
