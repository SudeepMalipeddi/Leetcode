/*
 * Problem Reference: LeetCode 2942 - Find Words Containing Character
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Collect indices where word contains target character.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total characters)
 *
 * Space Complexity:
 * O(k) output
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

public class FindWordsContainingCharacter2942 {
    public List<Integer> findWordsContainingCharacter(List<String> words, char character) {
        List<Integer> result = new ArrayList<>();
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).indexOf(character) != -1) {
                result.add(i);
            }
        }
        return result;
    }
}
