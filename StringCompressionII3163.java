/*
 * Problem: 3163. String Compression III
 *
 * Problem Statement:
 * Given a string 'word', compress it into a new string 'comp'. For every prefix of identical 
 * characters (up to length 9), append the length of the prefix followed by the character itself.
 *
 * Intuition:
 * The core constraint is the maximum count of 9. This means even if we have 10 'a's, 
 * we must represent them as "9a" followed by "1a". A simple greedy approach works: 
 * keep track of the current character and its consecutive count, resetting whenever 
 * the character changes or the count hits the limit of 9.
 *
 * Approach:
 * 1. Use a StringBuilder for efficient string construction.
 * 2. Maintain a 'cnt' variable for the current run of identical characters and 'ch' for the character itself.
 * 3. Iterate through the string starting from the second character.
 * 4. If the current character matches 'ch' AND the count is less than 9, increment the count.
 * 5. If the character changes OR the count reaches 9, append the current count and character to the result, 
 *    then reset the count to 1 and update 'ch' to the current character.
 * 6. Append the final remaining count and character after the loop finishes.
 *
 * Time Complexity: O(n), where n is the length of the input string, as we traverse the string exactly once.
 * Space Complexity: O(n) in the worst case to store the compressed string in the StringBuilder.
 *
 * Edge Cases:
 * - Single character string: Loop won't execute, final append handles it.
 * - String with exactly 9 identical characters: Loop finishes, final append handles it.
 * - String with more than 9 identical characters: The 'cnt < 9' condition forces a split.
 *
 * Dry Run:
 * word = "aaaaaaaaaaaaaabb" (14 'a's, 2 'b's)
 * 1. Initial: ch='a', cnt=1
 * 2. i=1 to 8: word.charAt(i) is 'a', cnt increments to 9.
 * 3. i=9: word.charAt(9) is 'a', but cnt is 9. Else block: append "9a", ch='a', cnt=1.
 * 4. i=10 to 13: word.charAt(i) is 'a', cnt increments to 5.
 * 5. i=14: word.charAt(14) is 'b'. Else block: append "5a", ch='b', cnt=1.
 * 6. i=15: word.charAt(15) is 'b', cnt increments to 2.
 * 7. Loop ends. Final append: "2b".
 * Result: "9a5a2b"
 *
 * Correctness Check:
 * The solution correctly handles the length-9 constraint and character transitions. 
 * Note: The class name says II but the logic matches LeetCode 3163 (III).
 */
public class StringCompressionII3163 {
    public static String compressedString(String word) {
        // StringBuilder is used to avoid O(n^2) complexity from repeated String concatenation
        StringBuilder comp = new StringBuilder();
        int cnt = 1, n = word.length();
        
        // Track the first character to start the compression process
        char ch = word.charAt(0);
        
        for (int i = 1; i < n; i++) {
            // We continue the current run only if the character matches AND 
            // we haven't reached the compression limit of 9.
            if (word.charAt(i) == ch && cnt < 9) {
                cnt++;
            } else {
                // Once the run breaks (different char or count=9), commit the current chunk
                comp.append(cnt).append(ch);
                
                // Reset for the next chunk of characters
                ch = word.charAt(i);
                cnt = 1;
            }
        }
        
        // The loop finishes before appending the very last sequence tracked
        comp.append(cnt).append(ch);
        
        return comp.toString();
    }

    public static void main(String[] args) {
        String comp = "aaaaaaaaaaaaaabb";

        System.out.println(compressedString(comp));
    }
}
