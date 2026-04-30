/*
 * Problem: 1910. Remove All Occurrences of a Substring
 *
 * Problem Statement:
 * Given two strings 's' and 'part', perform the following operation until all occurrences of 'part' 
 * are removed from 's': Find the leftmost occurrence of the substring 'part' and remove it from 's'.
 * Return the final string after all such removals are made.
 *
 * Intuition:
 * The problem explicitly defines a sequential process: find the leftmost match, remove it, and repeat.
 * Since the removal of one instance of 'part' can create a new instance of 'part' (e.g., "aba" in "aabb" 
 * after removing "ab"), we must re-scan the string after every modification.
 *
 * Approach:
 * 1. Use a while loop to check if 'part' exists within the current string 's' using indexOf().
 * 2. If found, use replaceFirst() to remove only the first (leftmost) occurrence.
 * 3. Update 's' with the modified string and repeat until indexOf() returns -1.
 *
 * Time Complexity: O(N^2 / M) where N is the length of 's' and M is the length of 'part'.
 * In the worst case, we perform N/M removals. Each removal involves searching (O(N)) and 
 * creating a new string (O(N)).
 *
 * Space Complexity: O(N) because strings are immutable in Java; every call to replaceFirst() 
 * creates a new string object in memory.
 *
 * Edge Cases:
 * - 'part' is longer than 's': Loop never executes, returns original 's'.
 * - 'part' is not in 's': Loop never executes, returns original 's'.
 * - 's' becomes empty: Loop terminates, returns empty string.
 *
 * Dry Run:
 * s = "daabcbaabcbc", part = "abc"
 * 1. "daabcbaabcbc".indexOf("abc") = 2. s becomes "dabaabcbc"
 * 2. "dabaabcbc".indexOf("abc") = 4. s becomes "dababc"
 * 3. "dababc".indexOf("abc") = 3. s becomes "dab"
 * 4. "dab".indexOf("abc") = -1. Return "dab".
 *
 * Correctness Check:
 * The solution is correct based on the problem constraints. Note that replaceFirst() treats 
 * the first argument as a Regular Expression. If 'part' contained special regex characters 
 * (like '.', '*', '+'), this would fail. For this specific LeetCode problem, 'part' consists 
 * of lowercase English letters, so it is safe.
 */
public class RemoveAllOccurrencesofaSubstring1910 {

    
    public String removeOccurrences(String s, String part) {
        
        // Remove one occurrence at a time until the substring disappears.
        // indexOf returns the starting index of the first occurrence, or -1 if not found.
        while (s.indexOf(part) != -1) {
            // replaceFirst is used to ensure we only remove the leftmost occurrence per iteration.
            // Note: In Java, Strings are immutable, so we must reassign the result back to 's'.
            s = s.replaceFirst(part, "");
        }
        // Once the loop terminates, no more occurrences of 'part' exist in 's'.
        return s;
    }
}
