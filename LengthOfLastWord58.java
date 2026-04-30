/*
 * Problem: 58. Length of Last Word
 *
 * Problem Statement:
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 *
 * Intuition:
 * The last word is located at the end of the string. However, there might be trailing spaces.
 * We can either use built-in string manipulation to clean the input or manually scan from 
 * right to left to find the first word encountered.
 *
 * Approach:
 * 1. Method 1 (trim): Remove leading/trailing whitespace using trim(). The last word then 
 *    ends at the string's end. Its length is the distance from the last space to the end.
 * 2. Method 2 (Manual Scan): Iterate backwards from the end of the string.
 * 3. Skip any trailing spaces by checking if the character is ' ' while the count is 0.
 * 4. Once a non-space character is found, increment the length counter.
 * 5. As soon as another space is hit after counting has started, the last word is complete.
 *
 * Time Complexity: O(n) for both methods, where n is the length of the string. We visit 
 * each character at most a constant number of times.
 * Space Complexity: O(1) for the manual scan (lastwordlength). O(n) for the trim() method 
 * because trim() creates a new String object in memory.
 *
 * Edge Cases:
 * - String with trailing spaces: "Hello World  " -> should return 5.
 * - String with only one word: "Hello" -> should return 5.
 * - String with only spaces: "   " -> should return 0.
 *
 * Dry Run:
 * s = "a apple "
 * 1. i = 7 (space): length = 0.
 * 2. i = 6 ('e'): length = 1.
 * 3. i = 5 ('l'): length = 2.
 * 4. i = 4 ('p'): length = 3.
 * 5. i = 3 ('p'): length = 4.
 * 6. i = 2 ('a'): length = 5.
 * 7. i = 1 (' '): length is 5 (> 0), so return 5.
 *
 * Correctness Check:
 * The solution is correct. The trim() method is concise but uses more memory. The 
 * manual scan is more efficient for large strings with many trailing spaces as it 
 * can exit early.
 */

class Length_of_Last_Word58{
    public int lengthOfLastWord(String s) {
        // Remove boundary spaces so the last character belongs to the last word.
        // Note: trim() creates a new string, which takes O(n) space.
        s = s.trim();
        // Last word starts right after the final space in the trimmed string.
        // If lastIndexOf returns -1 (no spaces), the math becomes: length - (-1) - 1 = length.
        return s.length()-s.lastIndexOf(" ")-1;
    }
    public int lastwordlength(String s){
        int length = 0;
        // Walk backwards: count letters in the suffix word.
        // This approach is O(1) space as it doesn't create new strings.
        for(int i = s.length()-1; i>=0; i--){
            if(s.charAt(i)!=' '){
                // Found a character; increment the current word length.
                length++;
            }
            else{
                // Once counting has started (length > 0), the first space we hit 
                // marks the boundary of the last word.
                if(length > 0){
                    return length;
                }
                // If length is still 0, we are just skipping trailing spaces.
            }
        }
        // If the loop finishes, it means we hit the start of the string 
        // (e.g., the string was just one word or all spaces).
        return length;
    }
}
