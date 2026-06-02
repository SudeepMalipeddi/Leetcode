/*
 * Problem: LeetCode 1002 - Find Common Characters
 *
 * Problem Statement:
 * Given a string array words, return an array of all characters that appear in
 * all strings, including duplicates. A character that appears n times in every
 * word should appear n times in the result.
 *
 * Intuition:
 * Track the minimum frequency of each character ('a' to 'z') across all words.
 * The result contains each character repeated minFreq times — the guarantee
 * that it appears at least that many times in every word.
 *
 * Approach:
 *   1. Initialize arr[26] filled with Integer.MAX_VALUE. Create empty result list.
 *   2. For each word in words:
 *      a. Build temp[26] counting frequency of each char in the current word
 *         (temp[c - 'a']++ for each char c in word.toCharArray()).
 *      b. For i from 0 to 25: arr[i] = Math.min(arr[i], temp[i]).
 *   3. For i from 0 to 25:
 *      - While arr[i] > 0: add String.valueOf((char)('a' + i)) to result,
 *        then decrement arr[i].
 *   4. Return result.
 *
 * Time Complexity: O(W * L) where W = number of words, L = average word length
 * (each character is processed once per word, plus 26-element inner loop per word).
 * Space Complexity: O(1) — two fixed-size arrays of 26 integers, plus the output
 * list (which is not counted as auxiliary space).
 *
 * Edge Cases:
 * - Single word: arr becomes the frequency of that word, all its characters are
 *   returned (each repeated count times).
 * - Empty words array: outer loop never executes, arr stays at MAX_VALUE, but the
 *   final while-loop check arr[i] > 0 uses MAX_VALUE which is NOT > 0 — wait, the
 *   code sets arr to MAX_VALUE. If words is empty, arr stays MAX_VALUE and the
 *   loop while(arr[i] > 0) would actually run for MAX_VALUE iterations. In practice
 *   LeetCode guarantees at least 1 word.
 * - No common characters: arr[i] becomes 0 for all i, returns empty list.
 * - All words identical: arr tracks the exact frequency, returns all chars.
 *
 * Dry Run:
 * words = ["bella","label","roller"]
 * arr = [MAX_VALUE, ...] (26 elements)
 *
 * word "bella":
 *   temp after counting: a=1, b=1, e=1, l=2, rest 0
 *   arr = min(MAX, temp): arr = [1,1,0,0,1,0,0,0,0,0,0,2,0,...]
 *
 * word "label":
 *   temp: a=1, b=1, e=1, l=2
 *   arr = min(arr, temp): same → [1,1,0,0,1,0,0,0,0,0,0,2,0,...]
 *
 * word "roller":
 *   temp: e=1, l=2, o=1, r=2
 *   arr = min(arr, temp): [0,0,0,0,1,0,0,0,0,0,0,2,0,0,0,0,0,2→0,0,0,0,0,0,0]
 *   → arr[e]=1, arr[l]=2, rest 0
 *
 * Build result:
 *   i=4 (e): arr[4]=1, add "e", arr[4]=0
 *   i=11 (l): arr[11]=2, add "l","l", arr[11]=0
 * result = ["e","l","l"]
 *
 * Correctness Check:
 * Taking the minimum frequency across all words ensures that only characters
 * present in every word survive, and the multiplicity equals the guaranteed
 * minimum count across all words, satisfying the problem's duplicate requirement.
 */
import java.util.*;

public class FindCommonCharacters1002 {
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE);
        // Iterate through the active search space while maintaining the intended invariant.
        for (String word : words) {
            int[] temp = new int[26];
            for (char c : word.toCharArray()) {
                temp[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                arr[i] = Math.min(arr[i], temp[i]);
            }
        }
        for (int i = 0; i < 26; i++) {
            while (arr[i] > 0) {
                result.add("" + (char) ('a' + i));
                arr[i]--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindCommonCharacters1002 obj = new FindCommonCharacters1002();
        String[] words = { "bella", "label", "roller" };
        System.out.println(obj.commonChars(words));
    }
}
