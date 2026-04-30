/*
 * Problem: 2185. Counting Words With a Given Prefix
 *
 * Problem Statement:
 * You are given an array of strings 'words' and a string 'pref'.
 * Return the number of strings in 'words' that contain 'pref' as a prefix.
 *
 * Intuition:
 * A prefix is a substring that appears at the very beginning of a string (starting at index 0).
 * To solve this, we simply need to check the start of each word against the given prefix string.
 * Java's String class provides optimized built-in methods like startsWith() and indexOf() for this purpose.
 *
 * Approach:
 * 1. Initialize a counter variable 'count' to zero.
 * 2. Iterate through each string 'word' in the input array 'words'.
 * 3. For each word, check if it starts with the string 'pref'.
 * 4. If it does, increment the counter.
 * 5. Return the final count after checking all words.
 *
 * Time Complexity: O(N * M)
 * Where N is the number of strings in the 'words' array and M is the length of the 'pref' string.
 * In the worst case, the startsWith() method compares M characters for each of the N words.
 *
 * Space Complexity: O(1)
 * We only use a single integer variable to store the count, regardless of the input size.
 *
 * Edge Cases:
 * - Prefix is longer than the word: startsWith() correctly returns false.
 * - Word is exactly equal to the prefix: startsWith() correctly returns true.
 * - No words match the prefix: The function returns 0.
 * - All words match the prefix: The function returns words.length.
 *
 * Dry Run:
 * words = ["pay", "attention", "practice", "attend"], pref = "at"
 * 1. "pay".startsWith("at") -> false
 * 2. "attention".startsWith("at") -> true (count = 1)
 * 3. "practice".startsWith("at") -> false
 * 4. "attend".startsWith("at") -> true (count = 2)
 * Result: 2
 *
 * Correctness Check:
 * The solution is correct. Both implementations (using startsWith and indexOf) accurately 
 * identify if a string begins with the specified prefix.
 */
/*
 * Problem Reference: LeetCode 2185 - Counting Words With a Given Prefix
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Scan words and count matches with startsWith.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total characters)
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
public class CountingWordsWithaGivenPrefix2185 {
    public int countWordsWithGivenPrefix(String[] words, String prefix) {
        // Initialize a counter to track how many words satisfy the prefix condition.
        int count = 0;
        // Iterate through the active search space while maintaining the intended invariant.
        for (String word : words) {
            // The startsWith(String prefix) method returns true if the string starts with the specified prefix.
            // It internally handles cases where the prefix is longer than the word itself.
            if (word.startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    public int countWordsWithGivenPrefix1(String[] words, String prefix) {
        // Alternative approach using the indexOf method.
        int count = 0;
        for (String word : words) {
            // indexOf returns the index of the first occurrence of the substring.
            // If the substring starts at index 0, it is by definition a prefix.
            if (word.indexOf(prefix) == 0) {
                count++;
            }
        }
        return count;
    }
}
