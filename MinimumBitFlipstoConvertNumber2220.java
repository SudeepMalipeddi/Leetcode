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
        int xor = start ^ goal;
        // Count differing bits one by one from least significant bit upward.
        while (xor > 0) {
            res += xor & 1;
            xor >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 10, b = 7;
        System.out.println(minBitFlips(a, b));
    }

}
