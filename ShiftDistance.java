/*
 * Problem: 3361. Shift Distance Between Two Strings
 *
 * Problem Statement:
 * Given two strings s and t of the same length, find the minimum cost to transform s into t.
 * You can shift a character forward (a->b) with cost nextCost[i] or backward (a->z) with cost previousCost[i].
 *
 * Intuition:
 * Each character transformation is independent. For any character s[i] to become t[i],
 * there are only two directions: moving forward through the alphabet or moving backward.
 * Since we might traverse many characters, precomputing the cumulative costs (prefix sums)
 * allows us to calculate the cost of any shift sequence in O(1) time.
 *
 * Approach:
 * 1. Create prefix sum arrays for both nextCost and previousCost to handle range sum queries.
 * 2. For each character pair (s[i], t[i]), calculate the forward shift cost.
 *    If s[i] <= t[i], it's a simple range sum. If s[i] > t[i], it wraps around 'z' to 'a'.
 * 3. Calculate the backward shift cost. If s[i] >= t[i], it's a simple range sum.
 *    If s[i] < t[i], it wraps around 'a' to 'z'.
 * 4. Take the minimum of the two directions and add it to the total cost (using long to avoid overflow).
 *
 * Time Complexity: O(N + Σ) where N is the length of the string and Σ is the alphabet size (26).
 * Space Complexity: O(Σ) to store the prefix sum arrays.
 *
 * Edge Cases:
 * - s[i] == t[i]: The cost will be 0 as both forward and backward range sums will evaluate to 0.
 * - Wrapping: Moving from 'z' to 'a' or 'a' to 'z' is handled by splitting the cost into two segments.
 *
 * Dry Run:
 * s = "a", t = "c", nextCost = [1, 2, 3...], prevCost = [1, 1, 1...]
 * start = 0 ('a'), end = 2 ('c')
 * Forward: nextSum[2] - nextSum[0] = (nextCost[0] + nextCost[1]) = 1 + 2 = 3.
 * Backward: prevSum[1] + (prevSum[26] - prevSum[3]) = prevCost[0] + (sum of prevCost from 'z' down to 'd').
 * Min(3, backward_sum) = 3.
 *
 * Correctness Check:
 * The solution correctly uses long for cost accumulation and prefix sums for O(1) cost calculation.
 * The wrapping logic covers all circular transitions. The solution assumes lowercase English letters.
 */

public class ShiftDistance {

    /**
     * Calculates the minimum cost to transform string s to string t.
     */
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        // nextSum[i] stores the cumulative cost to shift from 'a' up to the i-th letter
        long[] nextSum = new long[27];
        // prevSum[i] stores the cumulative cost to shift from 'a' down to the i-th letter
        long[] prevSum = new long[27];

        // Precompute prefix sums for O(1) range queries
        for (int i = 0; i < 26; i++) {
            nextSum[i + 1] = nextSum[i] + nextCost[i];
            prevSum[i + 1] = prevSum[i] + previousCost[i];
        }

        long totalCost = 0;
        for (int i = 0; i < s.length(); i++) {
            int start = s.charAt(i) - 'a';
            int end = t.charAt(i) - 'a';

            // Calculate forward cost: start -> ... -> end
            long forward;
            if (start <= end) {
                // Direct path: e.g., 'a' (0) to 'c' (2) uses nextCost[0] and nextCost[1]
                forward = nextSum[end] - nextSum[start];
            } else {
                // Wrap around path: e.g., 'y' to 'b' (y->z, then a->b)
                forward = (nextSum[26] - nextSum[start]) + nextSum[end];
            }

            // Calculate backward cost: start -> ... -> end
            long backward;
            if (start >= end) {
                // Direct path: e.g., 'c' (2) to 'a' (0) uses prevCost[2] and prevCost[1]
                backward = prevSum[start + 1] - prevSum[end + 1];
            } else {
                // Wrap around path: e.g., 'b' to 'y' (b->a, then z->y)
                backward = prevSum[start + 1] + (prevSum[26] - prevSum[end + 1]);
            }

            // Accumulate the cheaper of the two directions
            totalCost += Math.min(forward, backward);
        }
        return totalCost;
    }

    public static void main(String[] args) {
        // Example usage for testing
        ShiftDistance solver = new ShiftDistance();
        String s = "abab";
        String t = "baba";
        int[] nextCost = new int[26];
        int[] prevCost = new int[26];
        for (int i = 0; i < 26; i++) {
            nextCost[i] = 1;
            prevCost[i] = 1;
        }
        System.out.println("Minimum Shift Distance: " + solver.shiftDistance(s, t, nextCost, prevCost));
    }
}
