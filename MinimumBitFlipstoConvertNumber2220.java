/*
 * Problem: 2220. Minimum Bit Flips to Convert Number
 *
 * Problem Statement:
 * Given two integers start and goal, return the minimum number of bit flips to convert start to goal.
 * A bit flip is defined as changing a bit from 0 to 1 or 1 to 0 in the binary representation of the number.
 *
 * Intuition:
 * To find how many bits differ between two numbers, we use the XOR (^) operator. XOR results in a 1 
 * only when the corresponding bits of the two operands are different. The problem then reduces to 
 * counting the number of set bits (1s) in the result of (start ^ goal).
 *
 * Approach:
 * 1. Perform start ^ goal to get a bitmask where every '1' represents a position that must be flipped.
 * 2. Use a while loop to iterate through the bits of the XOR result.
 * 3. In each iteration, check the parity of the current number (xor & 1) to see if the LSB is 1.
 * 4. Right-shift the number to move to the next bit until no set bits remain.
 *
 * Time Complexity: O(log N), where N is the maximum value of start or goal. For a 32-bit integer, 
 * this is at most 31 iterations for positive numbers, making it effectively O(1).
 * Space Complexity: O(1), as we only use a constant amount of extra space for primitive variables.
 *
 * Edge Cases:
 * - start == goal: XOR result is 0, the loop never executes, and it returns 0 (Correct).
 * - start or goal is 0: The XOR result is simply the non-zero number, and we count its set bits.
 *
 * Dry Run:
 * start = 10 (1010), goal = 7 (0111)
 * 1. xor = 1010 ^ 0111 = 1101 (decimal 13)
 * 2. xor (1101) & 1 = 1 -> res = 1, xor becomes 0110
 * 3. xor (0110) & 1 = 0 -> res = 1, xor becomes 0011
 * 4. xor (0011) & 1 = 1 -> res = 2, xor becomes 0001
 * 5. xor (0001) & 1 = 1 -> res = 3, xor becomes 0000
 * 6. Loop ends. Return 3.
 *
 * Correctness Check:
 * The solution is correct for non-negative integers. Note: 'xor > 0' would fail to count the 
 * 31st bit (sign bit) if the result were negative. However, since start and goal are 
 * non-negative (0 to 10^9 per LeetCode constraints), the XOR result will always be non-negative.
 */

/*
 * Problem Statement: Count minimum bit flips needed to convert integer `start` into integer `goal`.
 * Intuition: Bits that differ between start and goal are exactly the bits that must be flipped.
 * Approach: XOR start and goal, then count set bits in the XOR result.
 * Time Complexity: O(B), where B is number of bits processed (up to 31/32 for int).
 * Space Complexity: O(1).
 * Edge Cases handled: start == goal (0 flips); multiple differing bits; values with leading zeros in binary form.
 * Dry Run: start=10(1010), goal=7(0111), xor=1101 -> set bits count is 3 -> answer 3.
 * LeetCode matching/assumption: Matches LeetCode 2220 (Minimum Bit Flips to Convert Number).
 * Correctness Check: XOR marks all and only mismatched bit positions; summing its set bits yields exact flip count.
 */

public class MinimumBitFlipstoConvertNumber2220 {
    public static int minBitFlips(int start, int goal) {
        int res = 0;
        // XOR keeps 1 at positions where bits differ.
        // The XOR operator (^) is the fundamental tool for identifying bit mismatches.
        int xor = start ^ goal;
        // Count differing bits one by one from least significant bit upward.
        // We process the XOR result bit by bit until no more set bits (1s) remain.
        while (xor > 0) {
            // Use bitwise AND with 1 to check if the current rightmost bit is set (1).
            res += xor & 1;
            // Logical right shift to move the next bit into the least significant position.
            xor >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 10, b = 7;
        System.out.println(minBitFlips(a, b));
    }

}
