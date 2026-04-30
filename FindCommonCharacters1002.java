/*
 * Problem Reference: LeetCode 1002 - Find Common Characters
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Track min frequency of each char across all words.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total characters)
 *
 * Space Complexity:
 * O(1) alphabet
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

public class FindCommonCharacters1002 {
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE);
        // Iterate through the active search space while maintaining the intended invariant.
        for (String word : words) {
            int[] temp = new int[26];
            for (char c : word.toCharArray()) {
                temp[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                arr[i] = Math.min(arr[i], temp[i]);
            }
        }
        for (int i = 0; i < 26; i++) {
            while (arr[i] > 0) {
                result.add("" + (char) ('a' + i));
                arr[i]--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindCommonCharacters1002 obj = new FindCommonCharacters1002();
        String[] words = { "bella", "label", "roller" };
        System.out.println(obj.commonChars(words));
    }
}
