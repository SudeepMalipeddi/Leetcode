/*
Problem Statement:
- Determine whether n is an exact power of 2.

Intuition:
- Repeated division by 2 should end at 1 for powers of two.

Approach:
- Reject 0, divide while even, and verify final value equals 1.
- Improvement idea: n>0 && (n & (n-1))==0 avoids iterative division.

Time Complexity:
- O(log n).

Space Complexity:
- O(1).

Edge Cases:
- n=1 returns true.

Dry Run:
- 8 -> 4 -> 2 -> 1 => true.
*/
public class PowerofTwo231 {
    public boolean isPowerOfTwo(int n){
        
        if(n == 0) return false;
        
        // Repeated halving validates power-of-two structure.
        while(n % 2 == 0){
            n /= 2;
        }
        return n == 1;
    }   
}
