/*
 * Problem Reference: LeetCode 38 - Count and Say
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Build each term from previous term using run-length encoding.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total output size)
 *
 * Space Complexity:
 * O(current string size)
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
 */
public class CountandSay38 {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String previous = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 1; i < previous.length(); i++) {
            if (previous.charAt(i) == previous.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(previous.charAt(i - 1));
                count = 1;
            }
        }
        sb.append(count).append(previous.charAt(previous.length() - 1));
        return sb.toString();
    }
}
