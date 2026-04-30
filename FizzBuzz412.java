/*
 * Problem: LeetCode 412 - Fizz Buzz
 *
 * Problem Statement:
 * Given an integer n, return a string array answer (1-indexed) where:
 * - answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 * - answer[i] == "Fizz" if i is divisible by 3.
 * - answer[i] == "Buzz" if i is divisible by 5.
 * - answer[i] == i (as a string) if none of the above conditions are true.
 *
 * Intuition:
 * The problem requires a linear scan from 1 to n. The key insight is the order of operations: 
 * since a number divisible by 15 is divisible by both 3 and 5, we must check for 15 first. 
 * If we checked for 3 first, a number like 15 would result in "Fizz" instead of "FizzBuzz".
 *
 * Approach:
 * 1. Initialize an ArrayList to store the result strings.
 * 2. Loop from 1 to n inclusive.
 * 3. Use the modulo operator (%) to determine divisibility.
 * 4. Use an if-else if ladder to ensure only one string is added per number.
 *
 * Time Complexity: O(n) - We perform a single pass from 1 to n, with constant time operations inside.
 * Space Complexity: O(1) - Ignoring the space required for the output list, we use no extra data structures.
 *
 * Edge Cases:
 * - n = 1: Should return ["1"].
 * - n = 15: Should correctly trigger the "FizzBuzz" condition at the final step.
 *
 * Dry Run:
 * Input: n = 5
 * i = 1: 1%15!=0, 1%3!=0, 1%5!=0 -> add "1"
 * i = 2: 2%15!=0, 2%3!=0, 2%5!=0 -> add "2"
 * i = 3: 3%15!=0, 3%3==0 -> add "Fizz"
 * i = 4: 4%15!=0, 4%3!=0, 4%5!=0 -> add "4"
 * i = 5: 5%15!=0, 5%3!=0, 5%5==0 -> add "Buzz"
 * Result: ["1", "2", "Fizz", "4", "Buzz"]
 *
 * Correctness Check:
 * The solution is correct. It correctly handles the priority of divisibility by checking 15 (3*5) first.
 */
import java.util.List;
import java.util.ArrayList;

public class FizzBuzz412 {
    public List<String> fizzBuzz(int n) {
        // Use ArrayList for O(1) amortized time complexity for additions.
        List<String> res = new ArrayList<>();
        
        // Iterate from 1 to n inclusive as the problem uses 1-based indexing.
        for (int i = 1; i <= n; i++) {
            // Check for divisibility by both 3 and 5 first. 
            // This is equivalent to checking divisibility by their Least Common Multiple (15).
            if (i % 15 == 0) {
                res.add("FizzBuzz");
            } 
            // If not divisible by 15, check if it's at least divisible by 3.
            else if (i % 3 == 0) {
                res.add("Fizz");
            } 
            // If not divisible by 3, check if it's divisible by 5.
            else if (i % 5 == 0) {
                res.add("Buzz");
            } 
            // Default case: the number is not a multiple of 3 or 5.
            else {
                // Convert the primitive integer to its String representation.
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
}
