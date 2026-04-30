/*
 * Problem Reference: LeetCode 278 - First Bad Version
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Binary search first index where predicate becomes true.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(log n)
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
public class First_Bad_Version278 {
    public boolean isBadVersion(int n){
        // dummy function
        return true;
    }
    public int firstBadVersion(int n) {
        int start = 0;
        int end = n;
        while(start < end){
            int mid = start + (end-start)/2;
            if(!isBadVersion(mid)){
                start = mid + 1;
            }
            else end = mid;
        }
        return start;
    }
}
