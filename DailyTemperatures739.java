/*
 * Problem: 739. Daily Temperatures
 *
 * Problem Statement:
 * Given an array of integers temperatures representing daily temperatures, return an array answer 
 * such that answer[i] is the number of days you have to wait after the i-th day to get a warmer 
 * temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Intuition:
 * This is a "Next Greater Element" problem. We need to efficiently find the first index to the right 
 * with a larger value. A monotonic stack is ideal because it allows us to store indices of 
 * temperatures we haven't found a "warmer day" for yet. As we iterate, any temperature warmer 
 * than the stack's top "resolves" that day's wait time.
 *
 * Approach:
 * 1. Method 1 (Forward Stack): Iterate left-to-right. Maintain a stack of indices in decreasing 
 *    order of temperature. When the current temperature is warmer than the stack top, pop and 
 *    calculate the index difference.
 * 2. Method 2 (Backward Stack): Iterate right-to-left. Maintain a stack of indices that could 
 *    be the "next warmer day" for elements to the left.
 * 3. Method 3 (Space Optimized): Iterate right-to-left. Use the already computed values in the 
 *    result array to "jump" over days that are colder than the current day, avoiding a stack.
 *
 * Time Complexity: O(n) for all methods. In stack approaches, each element is pushed and popped 
 * at most once. In the optimized approach, the "jumps" ensure we don't re-examine every element.
 * Space Complexity: O(n) for methods 1 and 2 (stack). O(1) extra space for method 3 (excluding 
 * the output array).
 *
 * Edge Cases:
 * - Strictly decreasing temperatures: All answers will be 0.
 * - Strictly increasing temperatures: All answers will be 1 (except the last).
 * - Single day: Answer is 0.
 *
 * Dry Run:
 * Input: [73, 74, 75, 71, 69, 72]
 * i=0 (73): Stack=[0]
 * i=1 (74): 74 > 73? Yes. Pop 0, answer[0] = 1-0 = 1. Stack=[1]
 * i=2 (75): 75 > 74? Yes. Pop 1, answer[1] = 2-1 = 1. Stack=[2]
 * i=3 (71): 71 > 75? No. Stack=[2, 3]
 * i=4 (69): 69 > 71? No. Stack=[2, 3, 4]
 * i=5 (72): 72 > 69? Yes. Pop 4, answer[4] = 5-4 = 1. 
 *          72 > 71? Yes. Pop 3, answer[3] = 5-3 = 2.
 *          72 > 75? No. Stack=[2, 5]
 * Result: [1, 1, 0, 2, 1, 0]
 *
 * Correctness Check:
 * The logic is correct. Method 3 relies on the 'hottest' check to prevent infinite loops when 
 * jumping through the answer array.
 */
import java.util.*;

public class DailyTemperatures739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];

        // we use a stack to store the index of the temperatures
        // This stack will maintain a monotonic decreasing order of temperatures.
        Stack<Integer> stack = new Stack<>();
        
        // we iterate through the temperatures array
        for(int i = 0; i < temperatures.length; i++){
            // if the stack is not empty and the current temperature is greater than the temperature at the index of the top of the stack
            // This means we've found the "next warmer day" for the day stored at stack.peek().
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                // we pop the top of the stack and set the answer at the index of the popped element to the difference between the current index and the popped index
                answer[stack.peek()] = i - stack.pop();
            }
            // we push the current index to the stack because its warmer day hasn't been found yet
            stack.push(i);
        }
        return answer;
    }

    public int[] dailyTemperatures1(int[] temperatures){
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        // Iterating backwards allows us to maintain a stack of "future" candidates.
        for(int i = temperatures.length - 1; i >= 0; i--){
            // Pop elements that are colder than or equal to the current temperature.
            // They cannot be the "next warmer day" for any day to the left of 'i'.
            while(!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]){
                stack.pop();
            }
            // If stack is empty, no warmer day exists to the right.
            // Otherwise, the top of the stack is the closest warmer day.
            answer[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);

        }
        return answer;
    }
    public int[] dailyTemperatures2(int[] temperatures) {

        int n = temperatures.length;
        
        // Track the maximum temperature seen from the right to handle the "no warmer day" case efficiently.
        int hottest = 0;
        
        int answer[] = new int[n];
        
        // Iterate through the active search space while maintaining the intended invariant.
        for (int currDay = n - 1; currDay >= 0; currDay--) {

            int currentTemp = temperatures[currDay];

            // hottest temp seen so far moving from the right
            // Important guard: this branch handles a boundary or constraint-critical condition.
            // If current is the hottest, answer[currDay] remains 0 (default).
            if (currentTemp >= hottest) {
                hottest = currentTemp;
                continue;
            }
            
            int days = 1;

            // Instead of a stack, we use the 'answer' array to jump.
            // If temperatures[currDay + days] is not warmer, we jump to its next warmer day.
            while (temperatures[currDay + days] <= currentTemp) {
                // Use information from answer to search for the next warmer day.
                // This effectively skips days that we already know are not warmer than the day at (currDay + days).
                days += answer[currDay + days];
            }

            answer[currDay] = days;
        }
        
        return answer;
    }
}
