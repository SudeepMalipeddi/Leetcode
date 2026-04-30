/*
 * Problem: LeetCode 1160 - Find Words That Can Be Formed by Characters
 *
 * Problem Statement:
 * Given an array of strings 'words' and a string 'chars', a string is "good" if it can be formed 
 * by characters from 'chars' (each character used at most once). Return the sum of lengths 
 * of all good strings in 'words'.
 *
 * Intuition:
 * This is a frequency counting problem. To determine if a word can be formed, we need to 
 * ensure that for every unique character in the word, the count of that character in the 
 * word is less than or equal to its count in the available 'chars' pool.
 *
 * Approach:
 * 1. Create a frequency array (size 26 for lowercase English letters) to store counts of characters in 'chars'.
 * 2. Iterate through each word in the 'words' array.
 * 3. For each word, create a temporary frequency array and check if it exceeds the available counts.
 * 4. If a word is valid, add its length to the running total.
 *
 * Time Complexity: O(N + M) where N is the total number of characters across all strings in 'words' 
 * and M is the length of the 'chars' string. We traverse each character a constant number of times.
 * Space Complexity: O(1) because the frequency arrays are always size 26, which is constant 
 * regardless of the input size.
 *
 * Edge Cases:
 * - 'chars' is empty: Result will be 0.
 * - 'words' contains strings longer than 'chars': These will be correctly skipped.
 * - 'words' contains characters not present in 'chars': Correctly handled by the frequency check.
 *
 * Dry Run:
 * words = ["cat"], chars = "atach"
 * 1. counts = {a:2, c:1, h:1, t:1, ...others:0}
 * 2. Process "cat":
 *    - 'c': temp['c']=1, counts['c']=1. (1 <= 1) OK.
 *    - 'a': temp['a']=1, counts['a']=2. (1 <= 2) OK.
 *    - 't': temp['t']=1, counts['t']=1. (1 <= 1) OK.
 * 3. "cat" is valid. sum = 3.
 *
 * Correctness Check:
 * The solution is correct. It uses a fixed-size array for character mapping which is 
 * more efficient than a HashMap for this specific constraint (lowercase English letters).
 */
// You are given an array of strings words and a string chars.

// A string is good if it can be formed by characters from chars (each character can only be used once).

// Return the sum of lengths of all good strings in words.
// Example 1:

// Input: words = ["cat","bt","hat","tree"], chars = "atach"
// Output: 6
// Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

public class FindWordsThatCanBeFormedbyCharacters1160 {
    public int countCharacters(String[] words, String chars){
        // Use an integer array as a fixed-size hash map for character frequencies (a-z)
        int[] counts = new int[26];
        for(int i=0; i<chars.length(); i++){
            // Map 'a' to index 0, 'b' to index 1, etc.
            counts[chars.charAt(i)-'a']++;
        }
        int sum = 0;

        for(String word: words){
            // Check each word against our available character pool
            if(canForm(word, counts)){
                sum += word.length();
            }
        }
        return sum;
    }
    public boolean canForm(String word, int[] counts){
        // Create a local frequency count for the current word to compare against the master pool
        int[] temp = new int[26];
        for(int i=0; i<word.length(); i++){
            int x = word.charAt(i)-'a';
            temp[x]++;
            // Optimization: If at any point the word requires more of a character 
            // than we have available, we can immediately reject it.
            if(temp[x]>counts[x]){
                return false;
            }
        }
        return true;
    }
}
