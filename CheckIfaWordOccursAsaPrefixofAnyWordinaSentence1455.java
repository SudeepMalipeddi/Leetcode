/*
 * Problem: LeetCode 1455 - Check If a Word Occurs As a Prefix of Any Word in a Sentence
 * Problem Statement: Given a sentence and a searchWord, return the 1-based index
 *   of the first word that has searchWord as a prefix, or -1 if none.
 * Intuition: Split the sentence into words and test each word's prefix.
 * Approach:
 *   1) Split sentence by spaces into an array of words.
 *   2) Scan in order; the first word that startsWith(searchWord) yields the answer.
 * Time Complexity: O(n * m) where n is number of words and m is prefix length.
 * Space Complexity: O(n) for the split array.
 * Edge Cases: No match, searchWord longer than a word, single-word sentence.
 * Dry Run: sentence="i love leetcode", searchWord="le" -> match at "leetcode" => 3.
 * Correctness Check: The scan preserves order, so the first prefix match is found.
 */
public class CheckIfaWordOccursAsaPrefixofAnyWordinaSentence1455 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" "); // tokenize by spaces
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i + 1; // 1-based index as required
            }
        }
        return -1;
    }
}
