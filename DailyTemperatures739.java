/*
 * Problem Reference: LeetCode 739 - Daily Temperatures
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Monotonic decreasing stack stores unresolved day indices.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
import java.util.*;

public class DailyTemperatures739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];

        // we use a stack to store the index of the temperatures
        Stack<Integer> stack = new Stack<>();
        
        // we iterate through the temperatures array
        for(int i = 0; i < temperatures.length; i++){
            // if the stack is not empty and the current temperature is greater than the temperature at the index of the top of the stack
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                // we pop the top of the stack and set the answer at the index of the popped element to the difference between the current index and the popped index
                answer[stack.peek()] = i - stack.pop();
            }
            // we push the current index to the stack
            stack.push(i);
        }
        return answer;
    }

    public int[] dailyTemperatures1(int[] temperatures){
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for(int i = temperatures.length - 1; i >= 0; i--){
            while(!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]){
                stack.pop();
            }
            answer[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);

        }
        return answer;
    }
    public int[] dailyTemperatures2(int[] temperatures) {

        int n = temperatures.length;
        
        int hottest = 0;
        
        int answer[] = new int[n];
        
        // Iterate through the active search space while maintaining the intended invariant.
        for (int currDay = n - 1; currDay >= 0; currDay--) {

            int currentTemp = temperatures[currDay];

            // hottest temp seen so far moving from the right
            // Important guard: this branch handles a boundary or constraint-critical condition.
            if (currentTemp >= hottest) {
                hottest = currentTemp;
                continue;
            }
            
            int days = 1;

            while (temperatures[currDay + days] <= currentTemp) {
                // Use information from answer to search for the next warmer day
                days += answer[currDay + days];
            }

            answer[currDay] = days;
        }
        
        return answer;
    }
}
