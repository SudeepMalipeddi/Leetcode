/*
 * Problem: LeetCode 2405 - Optimal Partition of String
 *
 * Problem Statement:
 * Given a string s, partition the string into one or more substrings such that the characters 
 * in each substring are unique. Return the minimum number of substrings in such a partition.
 *
 * Intuition:
 * To minimize the total number of partitions, we should make each substring as long as possible. 
 * This is a greedy approach: we extend the current substring until we encounter a character 
 * that has already appeared in the current active segment. At that point, we must start a 
 * new partition beginning with that character.
 *
 * Approach:
 * 1. Use an integer array 'lastSeen' of size 26 to store the index of the most recent 
 *    occurrence of each character ('a' through 'z').
 * 2. Initialize 'lastSeen' with -1 and set the initial partition 'count' to 1.
 * 3. Maintain a 'substringStart' pointer to track where the current unique-character 
 *    partition begins.
 * 4. Iterate through the string. For each character, check if its last seen index is 
 *    greater than or equal to 'substringStart'.
 * 5. If it is, the character is a duplicate in the current window. Increment 'count' 
 *    and update 'substringStart' to the current index to begin a new partition.
 * 6. Always update the 'lastSeen' index for the current character.
 *
 * Time Complexity: O(n), where n is the length of the string. We traverse the string once.
 * Space Complexity: O(1), as the 'lastSeen' array is of fixed size (26) regardless of input.
 *
 * Edge Cases:
 * - String with all unique characters: Returns 1.
 * - String with all identical characters: Returns s.length().
 * - Single character string: Returns 1.
 *
 * Dry Run:
 * Input: s = "abacaba"
 * - i=0, 'a': lastSeen['a']=-1. substringStart=0, count=1. lastSeen['a']=0.
 * - i=1, 'b': lastSeen['b']=-1. substringStart=0, count=1. lastSeen['b']=1.
 * - i=2, 'a': lastSeen['a']=0. Since 0 >= substringStart(0), count=2, substringStart=2. lastSeen['a']=2.
 * - i=3, 'c': lastSeen['c']=-1. substringStart=2, count=2. lastSeen['c']=3.
 * - i=4, 'a': lastSeen['a']=2. Since 2 >= substringStart(2), count=3, substringStart=4. lastSeen['a']=4.
 * - i=5, 'b': lastSeen['b']=1. Since 1 < substringStart(4), no split. lastSeen['b']=5.
 * - i=6, 'a': lastSeen['a']=4. Since 4 >= substringStart(4), count=4, substringStart=6. lastSeen['a']=6.
 * Result: 4.
 *
 * Correctness Check:
 * The greedy strategy is optimal here because delaying a split as long as possible 
 * leaves the maximum possible remaining string to be partitioned.
 */
import java.util.Arrays;
class partitionStr{
    class Solution {
    public int partitionString(String s) {
        // Tracks the last index where each character ('a'-'z') was encountered.
        int[] lastSeen = new int[26];
        // Initialize with -1 to indicate no characters have been seen yet.
        Arrays.fill(lastSeen, -1);
        // count: total partitions; substringStart: the index where the current partition begins.
        int count = 1, substringStart = 0;
        
        // Iterate through the string once to determine partition boundaries.
        for (int i = 0; i < s.length(); i++) {
            
            // Character repeats in current segment, so start a new partition here.
            // If the character's last occurrence is within the current active substring window.
            if (lastSeen[s.charAt(i) - 'a'] >= substringStart) {
                count++;
                substringStart = i;
                }
            // Record the current index as the most recent occurrence of this character.
            lastSeen[s.charAt(i) - 'a'] = i;
            }
        return count;
        }
    }
}
