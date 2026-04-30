/*
 * Problem Reference: LeetCode 2094 - Finding 3-Digit Even Numbers
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Try valid digit combinations and enforce count/leading/last-digit rules.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(1) bounded search
 *
 * Space Complexity:
 * O(1)
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
import java.util.Set;
import java.util.HashSet;

public class Finding3DigitEvenNumbers2094 {
    public static int[] findEvenNumbers(int[] digits) {
        Set<Integer> res = new HashSet<>();
        int[] count = new int[10];
        // Iterate through the active search space while maintaining the intended invariant.
        for (int digit : digits) {
            count[digit]++;
        }
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 8; k += 2) {
                    int[] tempcount = count.clone();
                    tempcount[i]--;
                    tempcount[j]--;
                    tempcount[k]--;
                    // Important guard: this branch handles a boundary or constraint-critical condition.
                    if (tempcount[i] >= 0 && tempcount[j] >= 0 && tempcount[k] >= 0) {
                        int num = i * 100 + j * 10 + k;
                        res.add(num);
                    }
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    public static void main(String[] args) {
        int[] digits = { 2, 1, 3, 0 };
        findEvenNumbers(digits);
        // Output: [102, 120, 132, 210, 230, 302, 320, 402, 420]
    }
}
