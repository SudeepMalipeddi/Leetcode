/*
 * Problem: 151. Reverse Words in a String
 *
 * Problem Statement:
 * Given an input string s, reverse the order of the words. A word is defined as a sequence 
 * of non-space characters. The words in s will be separated by at least one space. 
 * The returned string should have a single space separating the words and no extra spaces.
 *
 * Intuition:
 * The simplest way to reverse words is to break the string into individual components 
 * and then reassemble them in reverse order. By splitting the string by spaces, we 
 * isolate the words. Since multiple spaces might exist, we must filter out empty 
 * strings during the reconstruction phase.
 *
 * Approach:
 * 1. Split the input string into an array using a single space " " as the delimiter.
 * 2. Use a StringBuilder to efficiently build the result string.
 * 3. Iterate through the array of words starting from the last index down to zero.
 * 4. For each element, check if it is a valid word (not an empty string).
 * 5. Append valid words to the StringBuilder followed by a space.
 * 6. Remove the trailing space from the final result before returning.
 *
 * Time Complexity: O(N), where N is the length of the input string. 
 * Splitting takes O(N), and iterating through the words takes O(N).
 *
 * Space Complexity: O(N), as we store the split words in an array and 
 * build the result in a StringBuilder, both of which scale linearly with input size.
 *
 * Edge Cases:
 * - Leading or trailing spaces: Handled by the empty string check in the loop.
 * - Multiple spaces between words: Handled by the empty string check.
 * - Single word: Loop runs once, returns the word without extra spaces.
 *
 * Dry Run:
 * Input: "  hello world  "
 * 1. split(" ") -> ["", "", "hello", "world"]
 * 2. i = 3: words[3] is "world", res = "world "
 * 3. i = 2: words[2] is "hello", res = "world hello "
 * 4. i = 1: words[1] is "", skip.
 * 5. i = 0: words[0] is "", skip.
 * 6. res.substring(0, 11) -> "world hello"
 *
 * Correctness Check:
 * The solution is correct. It effectively handles multiple spaces by checking 
 * for empty strings in the array produced by split().
 */

public class ReverseWordsinaString151 {
    public String reverseWords(String s) {
        // Split the string by every single space. Note: multiple spaces will 
        // result in empty strings ("") appearing in the resulting array.
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();

        // Iterate backwards through the array to reverse the word order.
        for (int i = words.length - 1; i >= 0; i--) {
            // If the element is not an empty string, it is a valid word.
            // This check filters out the results of leading, trailing, or multiple spaces.
            if (!words[i].equals("")) {
                res.append(words[i]).append(" ");
            }
        }

        // If the result is empty (input was only spaces), return empty string.
        // Otherwise, remove the very last space appended in the loop.
        return res.length() == 0 ? "" : res.substring(0, res.length() - 1);
    }

    public static void main(String[] args) {
        ReverseWordsinaString151 obj = new ReverseWordsinaString151();
        System.out.println(obj.reverseWords("the sky is blue")); // Output: "blue is sky the"
        System.out.println(obj.reverseWords("  hello world  ")); // Output: "world hello"
        System.out.println(obj.reverseWords("a good   example")); // Output: "example good a"
    }
}
