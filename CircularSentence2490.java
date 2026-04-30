/*
 * Problem: 2490. Circular Sentence
 *
 * Problem Statement:
 * A sentence is circular if the last character of every word is equal to the first character 
 * of the next word, and the last character of the last word is equal to the first character 
 * of the first word. Given a string 's', return true if it is circular.
 *
 * Intuition:
 * The "circular" property is defined by local constraints at word boundaries (spaces) 
 * and a global constraint connecting the very end of the string to the very beginning. 
 * We only need to verify these specific points of contact.
 *
 * Approach:
 * 1. Verify the global circularity: the first character of the sentence must match the last.
 * 2. Verify internal circularity:
 *    - Method 1 (Split): Break the string into words and compare the tail of word[i-1] to the head of word[i].
 *    - Method 2 (Optimized): Scan the string for spaces. When a space is found at index 'k', 
 *      compare the character at 'k-1' (end of previous word) with 'k+1' (start of next word).
 *
 * Time Complexity: O(n) where n is the length of the string. We traverse the string once.
 * Space Complexity: 
 * - Method 1: O(n) to store the array of words resulting from the split.
 * - Method 2: O(1) as we only use a few integer pointers.
 *
 * Edge Cases:
 * - Single word sentence: Only the first and last character comparison matters.
 * - Words with single characters: The logic still holds as charAt(0) and charAt(length-1) will point to the same char.
 *
 * Dry Run:
 * Input: "leetcode exercises sound delightful"
 * 1. 'l' (start) == 'l' (end)? Yes.
 * 2. Find space after "leetcode": char at index 7 ('e') == char at index 9 ('e')? Yes.
 * 3. Find space after "exercises": char at index 18 ('s') == char at index 20 ('s')? Yes.
 * 4. Find space after "sound": char at index 25 ('d') == char at index 27 ('d')? Yes.
 * Result: true.
 *
 * Correctness Check:
 * The solution correctly implements the logic. Note that the split method is less memory 
 * efficient than the pointer-based method but more readable.
 */
class CircularSentence2490 {
    public boolean isCircularSentence(String s) {
        // Global check: The last character of the last word must match the first character of the first word.
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        
        // Split approach: Create an array of strings. This uses O(n) extra space.
        String[] arr = s.split(" "); 
        
        // Iterate through the words starting from the second word (index 1).
        for (int i = 1; i <= arr.length - 1; i++) {
            // Compare the last character of the previous word with the first character of the current word.
            if (arr[i - 1].charAt(arr[i - 1].length() - 1) != arr[i].charAt(0)) {
                return false;
            }
        }
        return true;
    }

    // Optimized approach: O(1) extra space
    public boolean isCircularSentence1(String s) {
        // Global check: First and last characters of the entire string.
        if (s.charAt(0) != s.charAt(s.length() - 1))
            return false;

        // Find the first occurrence of a space.
        int k = s.indexOf(" ");
        
        // If no space exists, it's a single word; we already checked the circularity above.
        if (k == -1)
            return true;

        // Continue finding spaces and checking the characters immediately surrounding them.
        while (k != -1) {
            // s.charAt(k - 1) is the last character of the word before the space.
            // s.charAt(k + 1) is the first character of the word after the space.
            if (s.charAt(k - 1) != s.charAt(k + 1)) { 
                return false;
            }

            // Search for the next space starting from the position after the current space.
            k = s.indexOf(" ", k + 1);
        }
        return true;
    }
}
