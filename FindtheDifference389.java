/*
 * Problem Reference: LeetCode 389 - Find the Difference
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * XOR or frequency cancellation leaves the extra character.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
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
class FindtheDifference389{
    public char findTheDifference(String s, String t) {
        long sum1=0,sum2=0;
        char[] arr1=s.toCharArray();
        char[] arr2=t.toCharArray();
        // Sum of ASCII codes cancels all common characters; only the extra character remains.
        for(char i:arr1){
            sum1+=(long)i;
        }
        for(char i:arr2){
            sum2+=(long)i;
        }
        return (char)(sum2-sum1);
    }
    public char findTheDifference1(String s, String t) {
        char c = 0;
        for(char cs : s.toCharArray()) c ^= cs;
        for(char ct : t.toCharArray()) c ^= ct;
        return c;
    }
}