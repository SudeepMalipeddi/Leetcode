/*
 * Problem: 3066. Minimum Operations to Exceed Threshold Value II
 *
 * Problem Statement:
 * Given an array of integers 'nums' and an integer 'k', you want to make all elements in the array 
 * greater than or equal to 'k'. In one operation, you take the two smallest elements x and y, 
 * remove them, and add (min(x, y) * 2 + max(x, y)) back to the array. Return the minimum 
 * number of operations needed.
 *
 * Intuition:
 * To minimize the number of operations, we should greedily combine the smallest available 
 * values first. This ensures that we "grow" the smallest numbers as efficiently as possible 
 * toward the threshold 'k'. A Min-Priority Queue is the perfect data structure for this 
 * because it allows us to retrieve and remove the two smallest elements in logarithmic time.
 *
 * Approach:
 * 1. Use a PriorityQueue of Longs to store all elements. We use Long to prevent integer 
 *    overflow during the calculation (2 * x + y).
 * 2. While the smallest element in the heap (the root) is less than 'k', perform the operation:
 *    a. Extract the two smallest elements (temp1 and temp2).
 *    b. Calculate the new value: (smaller * 2) + larger.
 *    c. Push the new value back into the heap.
 *    d. Increment the operation counter.
 * 3. Once the smallest element is >= k, all elements are guaranteed to be >= k.
 *
 * Time Complexity: O(N log N), where N is the length of the array. Building the heap takes O(N), 
 * and each of the at most N operations involves polling and offering, which take O(log N).
 * Space Complexity: O(N) to store the elements in the Priority Queue.
 *
 * Edge Cases:
 * - All elements are already >= k: The loop won't execute, returns 0.
 * - Large values: The formula 2 * x + y can exceed 2^31 - 1, so Long is mandatory.
 * - Minimum array size: The problem constraints usually ensure we have enough elements 
 *   to perform the operation if an element is still below k.
 *
 * Dry Run:
 * nums = [2, 11, 10, 1, 3], k = 10
 * 1. Heap: [1, 2, 3, 10, 11]
 * 2. Peek (1) < 10: Poll 1, 2. New = 1*2 + 2 = 4. Heap: [3, 4, 10, 11]. Ops = 1.
 * 3. Peek (3) < 10: Poll 3, 4. New = 3*2 + 4 = 10. Heap: [10, 10, 11]. Ops = 2.
 * 4. Peek (10) >= 10: Stop. Return 2.
 *
 * Correctness Check:
 * The solution correctly uses a Min-Heap to maintain the greedy property. The use of Long 
 * handles potential overflow. The loop condition correctly terminates as soon as the 
 * smallest element meets the threshold.
 */
import java.util.*;

public class MinimumOperationstoExceedThresholdValueII3066 {
    public int minOperations(int[] nums, int k) {
        // Use a Min-PriorityQueue to always access the two smallest elements efficiently.
        // We use Long to handle potential overflow during the (2*x + y) calculation.
        Queue<Long> q = new PriorityQueue<>();
        
        // Initial heap construction: O(N log N)
        for (int x : nums) {
            q.offer((long) x);
        }
        int res = 0;
        
        // Always combine the two smallest values to minimize future operations.
        // The smallest value is always at the head of the PriorityQueue.
        while (q.peek() < k) {
            // Extract the two smallest elements. 
            // Since it's a min-heap, temp1 <= temp2.
            long temp1 = q.poll();
            long temp2 = q.poll();
            
            // Apply the transformation rule and put the result back into the heap.
            // Math.min/max is used here, though temp1 is already <= temp2.
            q.offer(Math.min(temp1, temp2) * 2 + Math.max(temp1, temp2));
            
            // Increment the operation count.
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
