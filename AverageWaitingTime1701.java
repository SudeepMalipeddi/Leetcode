/*
 * Problem: LeetCode 1701. Average Waiting Time
 *
 * Problem Statement:
 * You are given an array customers where customers[i] = [arrival_i, time_i].
 * A single chef processes customers in order of arrival. Each customer waits until
 * the chef is free, then takes time_i to finish. Return the average waiting time.
 *
 * Intuition:
 * Track when the chef becomes free (current finish time). For each customer, the
 * waiting time is finish_time - arrival_time after accounting for possible idle time.
 *
 * Approach:
 * 1. Maintain "last" as the finish time of the previous customer.
 * 2. For each customer:
 *    - If last <= arrival, chef is idle; start at arrival.
 *    - Else, start at last.
 *    - Update last by adding preparation time.
 *    - Add (last - arrival) to total waiting time.
 * 3. Divide total waiting time by number of customers.
 *
 * Time Complexity: O(n) because each customer is processed once.
 * Space Complexity: O(1).
 *
 * Edge Cases handled:
 * - Chef idle between customers (arrival after last finish).
 * - Back-to-back customers with no idle time.
 * - Single customer.
 *
 * Dry Run:
 * customers = [[1,2], [2,5], [4,3]]
 * last = 1+2=3, wait += 3-1=2
 * next: last=3 > arrival 2 => last=3+5=8, wait += 8-2=6
 * next: last=8 > arrival 4 => last=8+3=11, wait += 11-4=7
 * avg = (2+6+7)/3 = 5.0
 *
 * Correctness Check:
 * The logic matches the simulation of a single server. Using double avoids integer division.
 *
 * LeetCode Match:
 * LeetCode 1701 - "Average Waiting Time".
 */
class AverageWaitingTime1701 {
    public static void main(String args[]) {

        int[][] A = { { 5, 2 }, { 5, 4 }, { 10, 3 }, { 20, 1 } };
        double wait = 0, curr = 0;
        // Simple simulation for a sample array
        for (int[] a : A) {
            // Chef starts at max(current time, arrival), then adds cook time
            curr = Math.max(curr, 1.0 * a[0]) + a[1];
            // Waiting time is finish time minus arrival time
            wait += curr - a[0];
        }
        double avg = 1.0 * wait / A.length;
    }

    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        // Sum of waiting times (as double to keep fractional average)
        double sum = customers[0][1];
        // last is the finish time of the previous customer
        int last = customers[0][0] + customers[0][1];
        for (int i = 1; i < n; i++) {
            // If chef is idle, start at arrival; otherwise start at last finish
            if (last <= customers[i][0]) {
                last = customers[i][0] + customers[i][1];
                // Waiting time equals just the cooking time
                sum += customers[i][1];
            } else {
                // Chef is busy, so customer waits until last finish
                last = last + customers[i][1];
                // Waiting time is finish time minus arrival time
                sum += last - customers[i][0];
            }
        }

        return (sum / n);

    }
}