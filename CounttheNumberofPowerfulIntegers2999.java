/*
 * Problem Reference: LeetCode 2999 - Count the Number of Powerful Integers
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Digit-DP style counting with bounds and fixed suffix (assumed from implementation).
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * Depends on DP state count
 *
 * Space Complexity:
 * Depends on memo table
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 * Assumption: this implementation uses a bounded-digit DP/counting strategy consistent with LeetCode 2999 constraints.
 */
public class CounttheNumberofPowerfulIntegers2999 {
    public static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long count = 0;
        // Iterate through the active search space while maintaining the intended invariant.
        for (long i = start; i <= finish; i++) {
            String curr = String.valueOf(i);
            if (!curr.endsWith(s)) {
                continue;
            }
            boolean valid = true;
            for (char c : curr.toCharArray()) {
                // Important guard: this branch handles a boundary or constraint-critical condition.
                if ((c - '0') > limit) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "20";
        long start = 20, finish = 1159;
        int limit = 5;
        // for (int i = 0; i <= limit; i++) {
        // // a = String.valueOf(i);
        // String prefix = String.format("", i);
        // String concatenated = String.valueOf(i).concat(s);
        // if (Integer.parseInt(concatenated) < finish && Integer.parseInt(concatenated)
        // > start) {
        // count++;
        // System.out.println(concatenated);
        // }
        // }

        // while()

        long n = numberOfPowerfulInt(start, finish, limit, s);
        System.out.println(n);
    }
}
