/*
 * Problem: LeetCode 3042 - Count Prefix and Suffix Pairs I
 *
 * Problem Statement:
 * Given an array of strings 'words', count the number of index pairs (i, j) such that 
 * i < j and words[i] is both a prefix and a suffix of words[j].
 *
 * Intuition:
 * The constraints for this version of the problem (I) are small (n <= 50, length <= 50). 
 * A brute-force approach that checks every possible pair (i, j) where i < j is 
 * computationally feasible and straightforward to implement using built-in string methods.
 *
 * Approach:
 * 1. Initialize a counter to track valid pairs.
 * 2. Use a nested loop structure to iterate through all pairs (i, j) ensuring i < j.
 * 3. For each pair, extract the strings word1 (at index i) and word2 (at index j).
 * 4. Use String.startsWith() and String.endsWith() to verify if word1 satisfies the condition for word2.
 * 5. Increment the counter if both conditions are true.
 *
 * Time Complexity: O(n^2 * m) 
 * Where n is the number of words and m is the maximum length of a string. 
 * We perform n^2 comparisons, and each prefix/suffix check takes O(m) time.
 *
 * Space Complexity: O(1)
 * We only use a few integer variables and string references; no additional data 
 * structures proportional to the input size are created.
 *
 * Edge Cases:
 * - words.length < 2: The loops will not execute, returning 0 (correct).
 * - word1 is longer than word2: startsWith/endsWith will naturally return false.
 * - word1 and word2 are identical: The condition is satisfied.
 *
 * Dry Run:
 * words = ["a", "aba", "ababa"]
 * i=0 (word1="a"):
 *   j=1 (word2="aba"): "aba" starts/ends with "a"? Yes. count=1.
 *   j=2 (word2="ababa"): "ababa" starts/ends with "a"? Yes. count=2.
 * i=1 (word1="aba"):
 *   j=2 (word2="ababa"): "ababa" starts/ends with "aba"? Yes. count=3.
 * Result: 3
 *
 * Correctness Check:
 * The solution correctly implements the problem constraints. Note that for "Count Prefix 
 * and Suffix Pairs II", a more optimized approach like a Trie or String Hashing would be required.
 */
class CountPrefixandSuffixPairsI3042 {
    public static int countPrefixSuffixPairs(String[] words) {
        int count = 0;

        // Outer loop selects the potential prefix/suffix string (i).
        // We stop at length - 1 because j must always be greater than i.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            
            // Inner loop selects the target string (j) to be checked against.
            // Starting j at i + 1 enforces the i < j constraint.
            for (int j = i + 1; j < words.length; j++) {
                String word2 = words[j];
                
                // A string is a 'prefix-suffix' if it appears at both the start and the end.
                // Java's built-in methods handle the length comparison internally.
                if (word2.startsWith(word1) && word2.endsWith(word1)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] words = { "a", "aba", "ababa", "aa" };
        System.out.println(countPrefixSuffixPairs(words));
    }
}
