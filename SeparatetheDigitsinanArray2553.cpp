/*
 * Problem: LeetCode 2553 - Separate the Digits in an Array
 *
 * Problem Statement:
 * Given an array of positive integers `nums`, return an array `answer` that
 * consists of the digits of each integer in `nums` in order. For example,
 * separating the digits of 13 produces [1, 3] and 25 produces [2, 5].
 *
 * Intuition:
 * To extract digits from a number in left-to-right order, we can repeatedly
 * take the last digit (using modulo 10) and insert it at the front of a
 * temporary list, then remove that digit (using integer division by 10). This
 * builds the digit sequence in correct order without needing a reverse step.
 *
 * Approach:
 *   1. Create an empty result vector `res`.
 *   2. For each integer `a` in `nums`:
 *      - Create an empty temporary vector `temp`.
 *      - While `a > 0`:
 *        - Compute `a % 10` to get the last digit.
 *        - Insert this digit at the beginning of `temp` using
 *          `temp.insert(temp.begin(), a % 10)`.
 *        - Update `a = a / 10` to remove the last digit.
 *      - After extracting all digits of `a`, iterate over `temp` and append
 *        each digit `i` to `res` using `res.push_back(i)`.
 *   3. Return `res`.
 *
 * Time Complexity: O(D) where D is the total number of digits across all
 *                   numbers. Each digit is processed once. The `insert` at
 *                   position 0 is O(k) per insert where k is the current size
 *                   of `temp`, so worst case O(d^2) per number with d digits,
 *                   but since d <= 10 for 32-bit ints, this is effectively O(D).
 * Space Complexity: O(D) — `res` stores all digits, and `temp` stores up to
 *                    the maximum number of digits in a single number (bounded
 *                    by ~10 for 32-bit ints).
 *
 * Edge Cases:
 * - Single-digit numbers: while loop runs once, `temp` gets one element.
 * - Numbers with trailing zeros: e.g., 100 -> digits [1, 0, 0] because
 *   `a % 10` extracts 0, then `a/10` reduces to 10, etc.
 * - Large numbers: works for any positive int within 32-bit range.
 * - Empty input array: for loop doesn't execute, returns empty vector.
 *
 * Dry Run:
 * Input: nums = [13, 25, 7]
 *
 * a = 13:
 *   temp = []
 *   a=13: temp.insert(begin, 3) -> temp=[3], a=13/10=1
 *   a=1:  temp.insert(begin, 1) -> temp=[1,3], a=1/10=0
 *   a=0, exit while
 *   Append to res: res = [1, 3]
 *
 * a = 25:
 *   temp = []
 *   a=25: temp.insert(begin, 5) -> temp=[5], a=2
 *   a=2:  temp.insert(begin, 2) -> temp=[2,5], a=0
 *   Append to res: res = [1, 3, 2, 5]
 *
 * a = 7:
 *   temp = []
 *   a=7: temp.insert(begin, 7) -> temp=[7], a=0
 *   Append to res: res = [1, 3, 2, 5, 7]
 *
 * Output: [1, 3, 2, 5, 7]
 *
 * Correctness Check:
 * The algorithm correctly separates digits by repeatedly extracting the least
 * significant digit via modulo and prepending to maintain left-to-right order.
 * Each number's digits are appended contiguously to the result, preserving the
 * original array order. This matches the problem definition exactly.
 */
#include <iostream>
#include <vector>
using namespace std;

vector<int> separateDigits(vector<int> &nums)
{
    vector<int> res;
    for (int a : nums)
    {
        vector<int> temp;
        while (a > 0)
        {
            temp.insert(temp.begin(), a % 10);
            a = a / 10;
        }
        for (int i : temp)
        {
            res.push_back(i);
        }
    }
    return res;
}