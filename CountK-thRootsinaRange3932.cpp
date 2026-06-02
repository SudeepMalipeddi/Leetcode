/*
 * Problem: LeetCode 3932 - Count K-th Roots in a Range (or similar title/Weekly Contest)
 *
 * Problem Statement:
 * You are given three integers l, r, and k.
 * An integer y is said to be a perfect kth power if there exists an integer x such that y = x^k.
 * Return the number of integers y in the range [l, r] (inclusive) that are perfect kth powers.
 *
 * Intuition:
 * Instead of checking each number in the range [l, r], we can find the bounds of their k-th roots.
 * The smallest x such that x^k >= l is ceil(l^(1/k)).
 * The largest x such that x^k <= r is floor(r^(1/k)).
 * The number of perfect k-th powers in the range is simply the number of integers between these two bounds.
 *
 * Approach:
 * 1. Find the k-th root of l and take the ceiling: min_x = ceil(pow(l, 1.0/k)).
 * 2. Find the k-th root of r and take the floor: max_x = floor(pow(r, 1.0/k)).
 * 3. The count of perfect k-th powers is max(0, max_x - min_x + 1). (Though floating point precision might require care in competitive programming).
 *
 * Time Complexity: O(1)
 * Using math functions typically evaluates in constant time.
 * (Note: precise integer arithmetic or binary search might be needed to avoid floating point issues for large values.)
 *
 * Space Complexity: O(1)
 * Only constant extra space is used.
 *
 * Edge Cases:
 * - l > r: Returns 0.
 * - K-th root produces a slight floating point inaccuracies: We should rely on integer boundaries or binary search for robust bounds.
 *
 * Dry Run:
 * Input: l = 8, r = 30, k = 2
 * min_x = ceil(sqrt(8)) = ceil(2.828) = 3
 * max_x = floor(sqrt(30)) = floor(5.477) = 5
 * Evaluated perfectly! The roots inside bounds: 3, 4, 5. So numbers are 9, 16, 25.
 * Output: 3
 *
 * Correctness Check:
 * By converting the range [l, r] in the exponents' domain to corresponding range [ceil(l^(1/k)), floor(r^(1/k))] in the base element domain, we cover all available values accurately.
 */

#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int countKthRoots(int l, int r, int k)
{
    cout << ceil(pow(l, 1 / k)) << "\n";
    cout << floor(pow(r, 1 / k)) << "\n";
}

int main()
{
    countKthRoots(1, 9, 3);
    return 0;
}