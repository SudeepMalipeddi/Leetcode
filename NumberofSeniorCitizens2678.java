/*
 * Problem: 2678. Number of Senior Citizens
 *
 * Problem Statement:
 * You are given an array of strings where each string represents a passenger's details (phone number, gender, age, and seat).
 * The task is to count the number of passengers who are strictly older than 60 years old.
 *
 * Intuition:
 * The input strings follow a strict fixed-length format of 15 characters. Since the age is always 
 * located at the same relative position (indices 11 and 12), we can directly extract it 
 * without complex parsing or regular expressions.
 *
 * Approach:
 * 1. Initialize a counter variable to zero.
 * 2. Iterate through each string in the input array.
 * 3. Use substring(11, 13) to isolate the two characters representing the age.
 * 4. Convert this substring to an integer and compare it against the threshold of 60.
 * 5. Increment the counter if the condition is met and return the final count.
 *
 * Time Complexity: O(N), where N is the number of strings in the input array. We perform a 
 * constant-time substring and parsing operation for each element.
 * Space Complexity: O(1), as we only store a single integer counter and a temporary age variable.
 *
 * Edge Cases:
 * - Age is exactly 60: Should not be counted (strictly > 60).
 * - Age is 61: Should be counted.
 * - Minimum age (00) or maximum age (99): Handled correctly by the numeric comparison.
 *
 * Dry Run:
 * Input: ["7868190130M7522"]
 * 1. s = "7868190130M7522"
 * 2. s.substring(11, 13) -> "75"
 * 3. Integer.parseInt("75") -> 75
 * 4. 75 > 60 is true -> count = 1
 * Result: 1
 *
 * Correctness Check:
 * The solution correctly identifies the age based on the problem's fixed-width format specification.
 */
public class NumberofSeniorCitizens2678 {
    public static int countSeniors(String[] details) {
        // Initialize counter to track passengers older than 60.
        int count = 0;
        
        // Process each passenger record in the input array.
        for (String s : details) {
            // Input format fixes age at indices 11 and 12.
            // substring(11, 13) extracts characters at index 11 and 12 (13 is exclusive).
            int age = Integer.parseInt(s.substring(11, 13));
            
            // Check if the passenger meets the "senior citizen" criteria (strictly greater than 60).
            if (age > 60)
                count++;
        }
        // Return the total number of seniors found.
        return count;
    }

    public static void main(String[] args) {
        // Example test case with various ages (75, 92, 40).
        String[] deets = { "7868190130M7522", "5303914400F9211", "9273338290F4010" };
        System.out.println(countSeniors(deets));
    }
}
