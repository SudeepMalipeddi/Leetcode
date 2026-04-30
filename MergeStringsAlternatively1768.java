/*
 * Problem: 1768. Merge Strings Alternately
 *
 * Problem Statement:
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, 
 * starting with word1. If a string is longer than the other, append the additional letters onto the end 
 * of the merged string.
 *
 * Intuition:
 * Since we need to alternate characters, we can use a single pointer to traverse both strings 
 * simultaneously. By checking the length of each string inside the loop, we can safely access 
 * characters from the shorter string until it's exhausted, and then continue with only the longer string.
 *
 * Approach:
 * 1. Use a StringBuilder for efficient string construction (avoiding the O(N^2) cost of repeated String concatenation).
 * 2. Maintain a pointer 'i' starting at 0.
 * 3. Use a while loop that continues as long as 'i' is less than the length of word1 OR word2.
 * 4. In each iteration, append the character at index 'i' from word1 (if it exists).
 * 5. Then append the character at index 'i' from word2 (if it exists).
 * 6. Increment 'i' and repeat until both strings are fully processed.
 *
 * Time Complexity: O(N + M) where N and M are the lengths of word1 and word2. We iterate through every character exactly once.
 * Space Complexity: O(N + M) to store the resulting merged string in the StringBuilder.
 *
 * Edge Cases:
 * - word1 is much longer than word2: word2 characters finish early, word1 suffix is appended.
 * - word2 is much longer than word1: word1 characters finish early, word2 suffix is appended.
 * - Empty strings: The loop condition handles this gracefully (though constraints usually guarantee length >= 1).
 *
 * Dry Run:
 * word1 = "abc", word2 = "pq"
 * i = 0: word1[0]='a', word2[0]='p'. res = "ap"
 * i = 1: word1[1]='b', word2[1]='q'. res = "apbq"
 * i = 2: word1[2]='c', word2[2] is out of bounds. res = "apbqc"
 * i = 3: Loop terminates (3 is not < 3 and 3 is not < 2).
 *
 * Correctness Check:
 * The logic correctly alternates characters starting with word1 and handles unequal lengths by 
 * using independent bounds checks for each string within the loop.
 */

public class MergeStringsAlternatively1768 {
    public String mergeAlternately(String word1, String word2) {
        // StringBuilder is used because Strings in Java are immutable; 
        // appending to a String in a loop would create many intermediate objects.
        StringBuilder res = new StringBuilder();
        int i = 0;

        // The OR condition ensures we don't stop until the longest string is fully traversed.
        while (i < word1.length() || i < word2.length()) {
            // If word1 still has characters at this index, append the current one.
            if (i < word1.length()) {
                res.append(word1.charAt(i)); // Append from first word when available.
            }
            // If word2 still has characters at this index, append the current one.
            // This happens immediately after word1's character, creating the "alternating" effect.
            if (i < word2.length()) {
                res.append(word2.charAt(i)); // Append from second word when available.
            }
            i++;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String word1 = "abc";
        String word2 = "pqrstuz";
        MergeStringsAlternatively1768 test = new MergeStringsAlternatively1768();
        System.out.println(test.mergeAlternately(word1, word2));
    }
}
