/*
 * Problem: 796. Rotate String
 *
 * Problem Statement:
 * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 *
 * Intuition:
 * A rotation is a cyclic shift. If we concatenate a string with itself (s + s), the resulting string 
 * contains every possible cyclic rotation of s as a contiguous substring. For example, if s is "abc", 
 * s + s is "abcabc", which contains "abc", "bca", and "cab".
 *
 * Approach:
 * 1. Simulation (rotateString): Manually perform the shift by removing the first character and 
 *    appending it to the end using a StringBuilder, checking for equality at each step.
 * 2. Concatenation (rotateString1): First, ensure the lengths are equal. Then, check if 'goal' 
 *    is a substring of 's' concatenated with itself.
 *
 * Time Complexity: 
 * - Method 1: O(N^2) because we iterate N times, and in each iteration, we perform string 
 *   manipulation and comparison, both of which take O(N) time.
 * - Method 2: O(N) where N is the length of the string. Concatenation takes O(N) and the 
 *   'contains' method (substring search) typically runs in O(N) or O(N+M) time.
 *
 * Space Complexity: 
 * - Method 1: O(N) to store the StringBuilder.
 * - Method 2: O(N) to store the concatenated string (which has length 2N).
 *
 * Edge Cases:
 * - Strings of different lengths: Immediately false as a rotation cannot change the length.
 * - Empty strings: Should be handled based on problem constraints (usually true if both are empty).
 * - Identical strings: True (0 rotations).
 *
 * Dry Run:
 * s = "abcde", goal = "cdeab"
 * Method 2:
 * 1. Lengths are both 5 (equal).
 * 2. s + s = "abcdeabcde"
 * 3. Does "abcdeabcde" contain "cdeab"? Yes, at index 2.
 * 4. Return true.
 *
 * Correctness Check:
 * The solution is correct. Method 1 correctly simulates the rotation process, and Method 2 
 * leverages the mathematical property of cyclic shifts efficiently.
 */
public class RotateString796 {

    // My method
    public static boolean rotateString(String s, String goal) {
        // Use StringBuilder for efficient character manipulation compared to immutable Strings
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            // Simulate a single shift: remove the character at the front
            sb.deleteCharAt(0);
            // Append the character that was originally at index 'i' to the end
            sb.append(s.charAt(i));
            // Check if the current rotation matches the target goal
            if (sb.toString().equals(goal)) {
                return true;
            }
        }
        return false;
    }

    // Easy and optimal method
    public static boolean rotateString1(String s, String goal) {
        // If lengths are different, s can never be rotated to match goal
        if (s.length() != goal.length()) {
            return false;
        }
        // The "Double String" trick: s + s contains all possible rotations of s
        // Example: "abc" -> "abcabc" contains "abc", "bca", "cab"
        return s.concat(s).contains(goal);
    }

    public static void main(String[] args) {
        String s = "abcde";
        String goal = "cdeab";

        // Test the optimal concatenation approach
        System.out.println(rotateString1(s, goal));
    }
}
