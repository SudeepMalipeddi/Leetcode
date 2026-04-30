/*
 * Problem Statement: Design a stack supporting push, pop, top, and retrieving minimum in constant time.
 * Intuition: When a new minimum appears, preserve the previous minimum on stack so it can be restored on pop.
 * Approach: Use one stack and a `min` variable; push old min before pushing a new min value.
 * Time Complexity: O(1) for push, pop, top, and getMin.
 * Space Complexity: O(n) due to stored values and historical minimum sentinels.
 * Edge Cases handled: Duplicate minimum values; alternating pushes/pops that change minimum repeatedly.
 * Dry Run: push 3, push 1, push 2 -> stack keeps previous min before 1; pop 2 keeps min=1; pop 1 restores min=3.
 * LeetCode matching/assumption: Matches LeetCode 155 (Min Stack) operation contract.
 * Correctness Check: pop()/top() can throw on empty stack in this implementation; correctness relies on valid call sequence guaranteed by the problem constraints.
 */

import java.util.*;

public class MinStack155 {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();

    public void push(int x) {
        // New minimum: store previous min first so pop can restore it later.
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    public void pop(){
        // If popped value is current min, the next pop reveals previous min sentinel.
        if(stack.pop() == min){
            min = stack.pop();
        }
    }
    public int top(){
        return stack.peek();
    }
    public int getMin(){
        return min;
    }
}
