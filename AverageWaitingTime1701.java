/*
 * Problem: LeetCode 1701 - Average Waiting Time
 * Problem Statement: Given customers[i] = [arrival, time], compute the average
 *   time each customer waits for their order when a single chef processes orders
 *   sequentially.
 * Intuition: Track the current time when the chef finishes each order and sum
 *   (finish - arrival) for every customer.
 * Approach:
 *   1) Initialize the finish time to the first customer's arrival + cook time.
 *   2) For each subsequent customer, start at max(finish, arrival).
 *   3) Add (finish - arrival) to the running total and update finish.
 *   4) Return total / n as a double.
 * Time Complexity: O(n) for a single pass.
 * Space Complexity: O(1).
 * Edge Cases: Chef idle when next customer arrives later; single customer.
 * Dry Run: customers=[[1,2],[2,5]] -> finish=3, wait=2;
 *   next finish=max(3,2)+5=8, wait+=8-2=6 => avg=(2+6)/2=4.
 * Correctness Check: Each customer’s completion time is computed from the exact
 *   processing order, so the waiting time sum is accurate.
 */
class AverageWaitingTime1701 {
    public static void main(String args[]) {

        int[][] A = { { 5, 2 }, { 5, 4 }, { 10, 3 }, { 20, 1 } };
        double wait = 0, curr = 0;
        for (int[] a : A) {
            curr = Math.max(curr, 1.0 * a[0]) + a[1]; // chef starts at max(curr, arrival)
            wait += curr - a[0]; // waiting time for this customer
        }
        double avg = 1.0 * wait / A.length;
    }

    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        double sum = customers[0][1];
        int last = customers[0][0] + customers[0][1];
        for (int i = 1; i < n; i++) {
            if (last <= customers[i][0]) {
                // chef is idle; start at this arrival time
                last = customers[i][0] + customers[i][1];
                sum += customers[i][1];
            } else {
                // chef is busy; order starts at "last"
                last = last + customers[i][1];
                sum += last - customers[i][0];
            }
        }

        return (sum / n);

    }
}
