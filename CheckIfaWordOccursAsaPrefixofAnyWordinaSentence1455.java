/*
 * Problem: 1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence
 *
 * Problem Statement:
 * Given a sentence consisting of words separated by single spaces and a searchWord,
 * return the 1-based index of the first word that has searchWord as a prefix.
 * If no such word exists, return -1.
 *
 * Intuition:
 * The problem requires identifying a specific prefix within a sequence of words.
 * By tokenizing the sentence into individual words, we can check each word
 * sequentially. The first word that satisfies the condition is the answer,
 * ensuring we meet the "first occurrence" requirement.
 *
 * Approach:
 * 1. Split the input sentence into an array of strings using the space character (" ") as a delimiter.
 * 2. Iterate through the array of words using a standard for-loop to maintain the word count.
 * 3. For each word, use the built-in String.startsWith() method to check if it begins with searchWord.
 * 4. If a match is found, return the current index plus one (to convert from 0-based to 1-based indexing).
 * 5. If the loop completes without finding a match, return -1.
 *
 * Time Complexity: O(N), where N is the number of characters in the sentence. 
 * Splitting the sentence takes O(N), and iterating through the words to check 
 * prefixes also takes O(N) in total across all comparisons.
 * Space Complexity: O(N) because the split() method creates a new array of 
 * strings that stores all characters of the original sentence.
 *
 * Edge Cases:
 * - searchWord is longer than any word in the sentence.
 * - searchWord matches the entire word.
 * - No word in the sentence starts with searchWord.
 * - The sentence contains only one word.
 *
 * Dry Run:
 * sentence = "i love leetcode", searchWord = "le"
 * words = ["i", "love", "leetcode"]
 * i = 0: "i".startsWith("le") -> false
 * i = 1: "love".startsWith("le") -> false
 * i = 2: "leetcode".startsWith("le") -> true
 * Result: return 2 + 1 = 3.
 *
 * Correctness Check:
 * The solution is correct. It properly handles the 1-based indexing requirement
 * and correctly identifies the first word that matches the prefix condition.
 */
public class CheckIfaWordOccursAsaPrefixofAnyWordinaSentence1455 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        // Split the sentence into an array of individual words using the space character as a delimiter.
        String[] words = sentence.split(" "); // tokenize by spaces
        
        // Iterate through the words in their original order to find the first prefix match.
        for (int i = 0; i < words.length; i++) {
            // Use the startsWith method to check if the current word begins with the target searchWord.
            if (words[i].startsWith(searchWord)) {
                // Return the 1-based index by adding 1 to the current 0-based loop index.
                return i + 1; // 1-based index as required
            }
        }
        
        // If the loop finishes without returning, no word in the sentence starts with the searchWord.
        return -1;
    }
}
