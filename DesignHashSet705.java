/*
 * Problem Reference: LeetCode 705 - Design HashSet
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Bucketed hashing for set membership operations.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * Amortized O(1)
 *
 * Space Complexity:
 * O(capacity + entries)
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
import java.util.*;

public class DesignHashSet705 {
    // Direct-address table trades memory for truly constant-time lookups in key range [0, 1_000_000].
    public boolean[] arr = new boolean[1000001];

    public DesignHashSet705() {
        Arrays.fill(arr, false);
    }

    public void add(int key) {
        if (!arr[key]) {
            arr[key] = true;
        }
    }

    public void remove(int key) {
        arr[key] = false;
    }

    public boolean contains(int key) {
        return arr[key];
    }

}
