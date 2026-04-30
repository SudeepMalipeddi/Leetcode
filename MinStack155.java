/*
 * Problem: 155. Min Stack
 *
 * Problem Statement:
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Intuition:
 * To retrieve the minimum in O(1), we must track the state of the minimum at every level of the stack. 
 * This approach uses a single stack and "checkpoints" the previous minimum value whenever a new 
 * minimum is encountered. By pushing the old minimum onto the stack before the new one, 
 * we can restore the previous state when the current minimum is popped.
 *
 * Approach:
 * 1. Maintain a variable `min` initialized to Integer.MAX_VALUE.
 * 2. Push: If the new value is <= current min, push the old min onto the stack first as a sentinel, then update min.
 * 3. Pop: If the value being popped is the current min, pop a second time to restore the previous min from the sentinel.
 * 4. Top/GetMin: Return the stack's top or the current min variable respectively.
 *
 * Time Complexity: O(1) for all operations (push, pop, top, getMin) as they involve basic stack operations.
 * Space Complexity: O(N) where N is the number of elements. In the worst case (strictly decreasing values), 
 *                  we store 2N elements on the stack.
 *
 * Edge Cases:
 * - Duplicate minimum values: Handled by the `<=` condition in push, ensuring the old min is saved even if the new value is equal.
 * - Empty stack: The problem constraints typically guarantee pop/top won't be called on an empty stack.
 *
 * Dry Run:
 * push(5): min=MAX, 5<=MAX is true -> stack=[MAX, 5], min=5
 * push(3): min=5, 3<=5 is true -> stack=[MAX, 5, 5, 3], min=3
 * push(7): min=3, 7<=3 is false -> stack=[MAX, 5, 5, 3, 7], min=3
 * pop(): 7 != 3 -> stack=[MAX, 5, 5, 3], min=3
 * pop(): 3 == 3 -> min = pop() (which is 5) -> stack=[MAX, 5], min=5
 *
 * Correctness Check:
 * The solution is correct. Using `<=` in the push condition is vital to handle cases where the same 
 * minimum value is pushed multiple times.
 */

import java.util.*;

public class MinStack155 {
    // Tracks the current minimum value in the stack
    int min = Integer.MAX_VALUE;
    // Standard stack to store both actual values and "previous minimum" sentinels
    Stack<Integer> stack = new Stack<Integer>();

    public void push(int x) {
        // If the new value is a new minimum (or equal to current min), 
        // we store the old minimum on the stack before pushing the new value.
        // This acts as a "restore point" for when this new minimum is eventually popped.
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    public void pop(){
        // If the value we are popping is the current minimum, it means the 
        // value immediately below it in the stack is the previous minimum sentinel.
        if(stack.pop() == min){
            // Restore the previous minimum by popping the sentinel value.
            min = stack.pop();
        }
    }
    public int top(){
        // Standard peek operation to see the actual top value.
        return stack.peek();
    }
    public int getMin(){
        // Returns the cached minimum value in constant time.
        return min;
    }
}
