/*
 * Problem: 2942. Find Words Containing Character
 *
 * Problem Statement:
 * You are given a 0-indexed array of strings 'words' and a character 'character'.
 * Return an array of indices representing the words that contain the character 'character'.
 *
 * Intuition:
 * To solve this, we need to examine each word in the list one by one. If the target character 
 * exists anywhere within a word, that word's position (index) should be included in our 
 * final answer. A simple linear scan of the list combined with a string search is sufficient.
 *
 * Approach:
 * 1. Initialize an empty list 'result' to store the indices of the matching words.
 * 2. Iterate through the 'words' list using a standard for-loop (from i = 0 to words.size() - 1).
 *    We use an indexed loop because the problem specifically asks for the indices.
 * 3. For each word at index 'i', use the built-in 'indexOf' method to check if 'character' is present.
 * 4. If 'indexOf' returns any value other than -1, the character exists in the word.
 * 5. Add the current index 'i' to the 'result' list.
 * 6. Return the 'result' list after checking all words.
 *
 * Time Complexity: O(N * M), where N is the number of words in the input list and M is the 
 * average length of the strings. In the worst case, we check every character of every word.
 *
 * Space Complexity: O(N) in the worst case for the output list (if every word contains 
 * the character). The auxiliary space used (excluding the output) is O(1).
 *
 * Edge Cases:
 * - The character is not present in any word: Returns an empty list.
 * - The character is present in every word: Returns a list containing all indices [0, 1, ..., n-1].
 * - Words are empty strings: The indexOf method will correctly return -1.
 *
 * Dry Run:
 * words = ["abc", "bcd", "aaaa", "cbc"], character = 'a'
 * i = 0: "abc".indexOf('a') is 0. 0 != -1, result = [0]
 * i = 1: "bcd".indexOf('a') is -1. result = [0]
 * i = 2: "aaaa".indexOf('a') is 0. 0 != -1, result = [0, 2]
 * i = 3: "cbc".indexOf('a') is -1. result = [0, 2]
 * Final Result: [0, 2]
 *
 * Correctness Check:
 * The solution is correct. It properly iterates through the list and uses standard 
 * String API methods to identify the presence of the character.
 */
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
        // We use an ArrayList to store the indices of matching words since the number of matches is unknown upfront.
        List<Integer> result = new ArrayList<>();
        // Iterate through the active search space while maintaining the intended invariant.
        // We use a standard for-loop with an index 'i' because the problem requires returning the indices of the words.
        for (int i = 0; i < words.size(); i++) {
            // String.indexOf(char) is an efficient way to check for existence; it returns the first index found or -1 if not found.
            if (words.get(i).indexOf(character) != -1) {
                // If the character exists in the word, we record the word's position (index i) in our result list.
                result.add(i);
            }
        }
        return result;
    }
}
