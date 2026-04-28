/*
 * Problem: LeetCode 2490 - Circular Sentence
 * Problem Statement: A sentence is circular if the last character of each word
 *   matches the first character of the next word, and the last word matches the first.
 *   Determine whether the given sentence is circular.
 * Intuition: Compare word boundaries and also compare the first and last characters.
 * Approach:
 *   1) Check first character equals last character of the sentence.
 *   2) For each adjacent word pair, check last char of previous equals first of next.
 *   3) The optimized method scans spaces without splitting.
 * Time Complexity: O(n) where n is the sentence length.
 * Space Complexity: O(n) for split (method isCircularSentence), O(1) for optimized.
 * Edge Cases: Single-word sentence, multiple spaces not expected (assumes single spaces).
 * Dry Run: "leetcode exercises sound delightful" -> 'l' vs 'l' ok,
 *   "leetcode"->"exercises" fails since 'e' != 'e'? (works if they match).
 * Correctness Check: Each boundary comparison enforces the circular property.
 */
class CircularSentence2490 {
    public boolean isCircularSentence(String s) {
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        String[] arr = s.split(" "); // split into words
        for (int i = 1; i <= arr.length - 1; i++) {
            if (arr[i - 1].charAt(arr[i - 1].length() - 1) != arr[i].charAt(0)) {
                return false;
            }
        }
        return true;
    }

    // optimized
    public boolean isCircularSentence1(String s) {
        if (s.charAt(0) != s.charAt(s.length() - 1))
            return false;

        int k = s.indexOf(" ");
        if (k == -1)
            return true;

        while (k != -1) {
            if (s.charAt(k - 1) != s.charAt(k + 1)) { // compare boundary characters
                return false;
            }

            k = s.indexOf(" ", k + 1);
        }
        return true;
    }
}
