/*
 * Problem Statement: Return true if integer n can be written as 3^x for some integer x >= 0, otherwise false.
 * Intuition: A power of 3 stays divisible by 3 until it becomes exactly 1.
 * Approach: Iteratively divide n by 3 while divisible; separately provide a recursive variant using the same divisibility rule.
 * Time Complexity: O(log3 n) because each loop/recursive step divides n by 3, shrinking input multiplicatively.
 * Space Complexity: Iterative method O(1); recursive method O(log3 n) call stack depth.
 * Edge Cases handled: n==0 and n<0 return false; n==1 returns true in recursive base case.
 * Dry Run: n=27 -> 27%3==0 => 9 -> 3 -> 1, loop ends and returns true; n=45 -> 45->15->5 then 5%3!=0 so false.
 * LeetCode matching: Matches LeetCode 326 (Power of Three).
 * Correctness Check: isPowerOfThree1 calls isPowerOfThree(n/3) instead of itself; output remains correct but style is inconsistent.
 */

public class IsPowerfThree326 {
    public boolean isPowerOfThree(int n) {
        // 0 cannot be represented as 3^x.
        if (n == 0)
            return false;
        // Remove one factor of 3 each iteration.
        while (n % 3 == 0) {
            n /= 3;
        }
        // True only if all factors were exactly 3 and final residue is 1.
        return n == 1;
    }

    public boolean isPowerOfThree1(int n) {
        // Base case: 3^0 = 1.
        if (n == 1) {
            return true;
        }
        // Reject non-positive numbers and numbers with a non-3 prime factor.
        if (n <= 0 || n % 3 != 0) {
            return false;
        }
        // Reduce problem size by dividing by 3.
        return isPowerOfThree(n / 3);
    }
}
