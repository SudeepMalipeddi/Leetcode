/*
 * Problem: 443. String Compression
 *
 * Problem Statement:
 * Given an array of characters, compress it in-place. The compressed version should 
 * consist of the character followed by the count of consecutive repeating characters 
 * (only if the count is greater than 1).
 *
 * Intuition:
 * The core idea is to identify "runs" of identical characters. By iterating through 
 * the array and keeping a running count, we can determine the compressed representation. 
 * Once a character changes, we record the previous character and its frequency.
 *
 * Approach:
 * 1. Handle the base case where the array length is 1.
 * 2. Iterate through the array starting from the second character, comparing each 
 *    element with its predecessor to count consecutive occurrences.
 * 3. When a mismatch is found, append the previous character and its count (if > 1) 
 *     to a StringBuilder.
 * 4. After the loop, handle the final group of characters.
 * 5. Copy the characters from the StringBuilder back into the original array and 
 *    return the length of the compressed string.
 *
 * Time Complexity: O(N), where N is the length of the input array. We traverse the 
 * array once to build the string and once more to copy it back.
 * Space Complexity: O(N) in this specific implementation because a StringBuilder 
 * is used to store the compressed result before copying. 
 *
 * Edge Cases:
 * - Array with a single character: Returns 1 immediately.
 * - Array with all unique characters: Compressed length equals original length.
 * - Array with all identical characters: Compressed length is 1 + digits of count.
 *
 * Dry Run:
 * Input: ['a', 'a', 'b', 'b', 'c']
 * 1. i=1: 'a' == 'a', count becomes 2.
 * 2. i=2: 'b' != 'a', append 'a' and '2' to sb. sb="a2", count reset to 1.
 * 3. i=3: 'b' == 'b', count becomes 2.
 * 4. i=4: 'c' != 'b', append 'b' and '2' to sb. sb="a2b2", count reset to 1.
 * 5. Loop ends. Append last char 'c'. sb="a2b2c".
 * 6. Copy "a2b2c" back to chars. Return 5.
 *
 * Correctness Check:
 * The solution is logically correct for generating the compressed string. However, 
 * note that many interviewers expect an O(1) space solution using two pointers 
 * (read/write) directly on the array instead of using a StringBuilder.
 */

public class StringCompression443 {
    public int compress(char[] chars) {
        int n = chars.length;
        // Base case: a single character cannot be compressed further
        if (n == 1) {
            return 1;
        }
        
        int count = 1;
        StringBuilder sb = new StringBuilder();
        
        // Iterate through the array to find groups of identical characters
        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) {
                // Increment count if current character matches the previous one
                count++;
            } else {
                // Character changed: append the previous character to the result
                sb.append(chars[i - 1]);
                // Only append the count if it is greater than 1
                if (count > 1) {
                    sb.append(count);
                }
                // Reset count for the new character group
                count = 1;
            }
        }
        
        // The loop finishes before processing the very last group, so we append it here
        sb.append(chars[n - 1]);
        if (count > 1) {
            sb.append(count);
        }
        
        int len = sb.length();
        // The problem requires modifying the input array in-place
        for (int i = 0; i < len; i++) {
            chars[i] = sb.charAt(i);
        }
        
        // Return the length of the compressed portion of the array
        return len;
    }
}
