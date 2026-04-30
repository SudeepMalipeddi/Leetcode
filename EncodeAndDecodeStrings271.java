/*
 * Problem: 271. Encode and Decode Strings
 *
 * Problem Statement:
 * Design an algorithm to encode a list of strings to a single string. The encoded string is 
 * then sent over the network and decoded back to the original list of strings.
 *
 * Intuition:
 * The core challenge is "delimiter collision"—if we use a comma to separate strings, what 
 * happens if a string contains a comma? The "Length-Prefix" approach solves this by 
 * storing the length of each string followed by a special separator (e.g., "5#hello"). 
 * This tells the decoder exactly how many characters to read, regardless of the content.
 *
 * Approach:
 * 1. Encoding: For each string in the list, append its length, a delimiter (like '#'), 
 *    and the string itself to a StringBuilder.
 * 2. Decoding: Use a pointer to iterate through the encoded string. Parse the length 
 *    until the delimiter is reached, then extract the substring of that specific length.
 * 3. The provided snippet demonstrates the fundamental concept of tracking string 
 *    lengths to facilitate this process.
 *
 * Time Complexity: O(N) where N is the total number of characters across all strings. 
 * Each character is processed a constant number of times during encoding and decoding.
 * 
 * Space Complexity: O(N) to store the resulting encoded string or the list of decoded strings.
 *
 * Edge Cases:
 * - Empty list: Should be representable (e.g., as an empty string).
 * - Empty strings in the list: Encoded as "0#", allowing the decoder to identify an empty entry.
 * - Strings containing the delimiter: Safe because the prefix length dictates exactly where to stop.
 *
 * Dry Run:
 * Input: ["hi", "bye"]
 * 1. Encode "hi": length 2 + '#' + "hi" -> "2#hi"
 * 2. Encode "bye": length 3 + '#' + "bye" -> "2#hi3#bye"
 * 3. Decode: Read '2', skip '#', take next 2 chars ("hi"). Read '3', skip '#', take next 3 chars ("bye").
 *
 * Correctness Check:
 * The provided code is a minimal demonstration of the length-prefix concept using a HashMap. 
 * While it doesn't implement the full LeetCode interface, the logic of associating a 
 * string with its length is the key insight for the actual problem.
 */
import java.util.HashMap;

public class EncodeAndDecodeStrings271 {
    public static void main(String[] args) {
        // Using a HashMap to demonstrate the association between a string and its metadata (length).
        // In the actual problem, this length would be prepended to the string in a single serialized output.
        HashMap<String, Integer> map = new HashMap<>();
        String s = "wotah";
        
        // Capturing the length is the "Prefix" part of the Length-Prefix encoding strategy.
        // This ensures that even if 's' contained special characters, we know its exact boundaries.
        map.put(s, s.length());
        
        // Printing the length to simulate the metadata that would be used by a decoder to 
        // determine how many characters to consume from a stream.
        System.out.println(s.length());
    }
}
