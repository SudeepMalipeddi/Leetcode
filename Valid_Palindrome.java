/*
 * Problem: 125. Valid Palindrome
 *
 * Problem Statement:
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters 
 * and ignoring cases. A palindrome reads the same forward and backward.
 *
 * Intuition:
 * A palindrome is defined by its symmetry. Instead of creating a new filtered string (which 
 * uses extra space), we can use two pointers to compare characters from both ends moving 
 * towards the center, skipping any characters that aren't letters or digits.
 *
 * Approach:
 * 1. Initialize two pointers: 'start' at index 0 and 'end' at the last index.
 * 2. Use a while loop to move pointers toward each other until they meet.
 * 3. In each iteration, check if the character at 'start' is alphanumeric. If not, increment 'start'.
 * 4. Check if the character at 'end' is alphanumeric. If not, decrement 'end'.
 * 5. If both are alphanumeric, compare their lowercase versions. If they don't match, return false.
 * 6. If they match, move both pointers inward and continue.
 *
 * Time Complexity: O(n), where n is the length of the string. We traverse the string at most once.
 * Space Complexity: O(1), as we perform the check in-place using only two integer pointers.
 *
 * Edge Cases:
 * - Empty string: Handled by the initial check (returns true).
 * - String with only non-alphanumeric characters: Pointers will cross, returning true.
 * - Single character string: Loop condition (start <= end) handles this, returning true.
 *
 * Dry Run:
 * Input: "A, bA"
 * 1. start=0 ('A'), end=4 ('A') -> Match! start=1, end=3.
 * 2. start=1 (','), end=3 ('b') -> ',' is not alphanumeric, start=2.
 * 3. start=2 (' '), end=3 ('b') -> ' ' is not alphanumeric, start=3.
 * 4. start=3 ('b'), end=3 ('b') -> Match! start=4, end=2.
 * 5. start > end -> Loop terminates, return true.
 *
 * Correctness Check:
 * The solution is correct. The use of Character.isLetterOrDigit and Character.toLowerCase 
 * correctly handles the problem constraints.
 */
public class Valid_Palindrome {
    public boolean isPalindrome(String s){
        // An empty string is considered a valid palindrome per problem constraints.
        if(s.isEmpty()){
            return true;
        }
        
        int start = 0;
        int end = s.length() - 1;
        
        // Continue until the pointers meet or cross in the middle.
        while(start<=end){
            char first = s.charAt(start);
            char last = s.charAt(end);
            
            // If the left character is not alphanumeric, skip it and move right.
            if(!Character.isLetterOrDigit(first)){
                start++;
            }
            // If the right character is not alphanumeric, skip it and move left.
            else if(!Character.isLetterOrDigit(last)){
                end--;
            }
            // Both characters are alphanumeric; now we compare them.
            else{
                // Convert to lowercase to ensure the comparison is case-insensitive.
                if(Character.toLowerCase(first) != Character.toLowerCase(last)){
                    return false;
                }
                // If they match, move both pointers closer to the center.
                start++;
                end--;
            }
        }
        // If we complete the loop without returning false, the string is a palindrome.
        return true;
    }
}
