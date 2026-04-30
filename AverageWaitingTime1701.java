/*
 * Problem: 1701 - Average Waiting Time
 *
 * Problem Statement:
 * A chef processes orders one by one in the order they arrive. Given an array where 
 * customers[i] = [arrival_i, time_i], calculate the average waiting time for all 
 * customers. Waiting time is defined as (finish_time - arrival_time).
 *
 * Intuition:
 * The chef's availability is the bottleneck. If a customer arrives while the chef is 
 * busy, they must wait for the chef to finish the previous order. If the chef is 
 * idle, the order starts immediately upon arrival. By tracking the "finish time" 
 * of the current order, we can determine the start time of the next one.
 *
 * Approach:
 * 1. Maintain a variable 'last' to track when the chef will be free.
 * 2. For each customer, determine if they arrive before or after 'last'.
 * 3. If arriving after 'last', the chef starts at 'arrival' and finishes at 'arrival + cook_time'.
 * 4. If arriving before 'last', the chef starts at 'last' and finishes at 'last + cook_time'.
 * 5. Accumulate the difference between finish time and arrival time into a sum.
 * 6. Return the sum divided by the number of customers.
 *
 * Time Complexity: O(n) because we iterate through the customers array exactly once.
 * Space Complexity: O(1) as we only store a few scalar variables (sum, last, n).
 *
 * Edge Cases:
 * - Single customer: The loop for i=1 won't execute; initial values handle it.
 * - Large gaps between customers: The 'if (last <= customers[i][0])' handles chef idle time.
 * - Customers arriving simultaneously: Handled by the 'else' logic.
 *
 * Dry Run:
 * customers = [[5,2], [5,4], [10,3]]
 * 1. Init: sum = 2, last = 5+2 = 7.
 * 2. i=1 (5,4): 7 > 5 (busy). last = 7+4 = 11. sum += 11-5 = 6 (sum=8).
 * 3. i=2 (10,3): 11 > 10 (busy). last = 11+3 = 14. sum += 14-10 = 4 (sum=12).
 * Result: 12 / 3 = 4.0.
 *
 * Correctness Check:
 * The solution correctly handles the transition between the chef being idle and busy. 
 * Using 'double' for the sum prevents integer overflow during accumulation for large inputs.
 */
class AverageWaitingTime1701 {
    public static void main(String args[]) {

        int[][] A = { { 5, 2 }, { 5, 4 }, { 10, 3 }, { 20, 1 } };
        double wait = 0, curr = 0;
        for (int[] a : A) {
            // The chef starts working at either the current time (if busy) or the arrival time (if idle)
            curr = Math.max(curr, 1.0 * a[0]) + a[1]; 
            // The waiting time is the difference between when the food is ready and when the customer arrived
            wait += curr - a[0]; 
        }
        double avg = 1.0 * wait / A.length;
    }

    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        // Initialize sum with the first customer's waiting time (which is just their cook time)
        double sum = customers[0][1];
        // 'last' tracks the timestamp when the chef finishes the current order
        int last = customers[0][0] + customers[0][1];
        
        for (int i = 1; i < n; i++) {
            if (last <= customers[i][0]) {
                // Case: Chef is idle. The order starts exactly when the customer arrives.
                last = customers[i][0] + customers[i][1];
                sum += customers[i][1];
            } else {
                // Case: Chef is busy. The order starts only after the previous order is finished.
                last = last + customers[i][1];
                // Waiting time = (time spent waiting for chef) + (time spent cooking)
                sum += last - customers[i][0];
            }
        }

        // Return the average by dividing the total accumulated wait time by the number of customers
        return (sum / n);

    }
}
