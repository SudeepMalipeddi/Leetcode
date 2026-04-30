/*
Problem Statement:
- Determine whether n is an exact power of 4.

Intuition:
- Repeatedly divide by 4 while divisible; valid powers end at 1.

Approach:
- Reject 0, loop n/=4 while n%4==0, then check n==1.
- Improvement idea: a bit-manipulation test can check this in constant operations.

Time Complexity:
- O(log_4 n).

Space Complexity:
- O(1).

Edge Cases:
- n=1 returns true (4^0).

Dry Run:
- 64 -> 16 -> 4 -> 1 => true.
*/
public class PowerOfFour342 {
    public boolean isPowerOfFour(int n) {
        
        if (n == 0)
            return false;
        
        // Keep dividing by 4; only exact powers finish at 1.
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}
