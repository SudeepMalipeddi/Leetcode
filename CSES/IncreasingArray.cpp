/*
 * Problem: CSES - Increasing Array
 *
 * Problem Statement:
 * You are given an array of n integers. You want to modify the array so that it is
 * increasing, i.e., every element is at least as large as the previous element.
 * On each turn, you may increase the value of any element by one. What is the minimum
 * number of turns required?
 *
 * Intuition:
 * To minimize the number of turns, we should never increase an element more than strictly
 * necessary. Whenever we encounter an element that is smaller than the previous element,
 * we must increase it until it is exactly equal to the previous element. We keep a running
 * sum of the differences required over the entire array.
 *
 * Approach:
 * 1. Read `n` and process the array.
 * 2. Keep a `long long` variable `res` or `turns` initialized to 0 to store the minimum number of turns.
 * 3. Iterate starting from the second element.
 * 4. If the current element is less than the previous element, calculate the difference.
 * 5. Add the difference to `res` and update the current element to match the previous element.
 * 6. Return/print `res`.
 *
 * Time Complexity: O(N), as we iterate through the given array of size `N` exactly once.
 * Space Complexity: O(N) if we store the array, although an O(1) space optimization is possible
 * by checking inputs sequentially on the fly.
 *
 * Edge Cases:
 * - A strictly increasing array will require 0 turns.
 * - An array where elements are extremely small and drop heavily, creating large differences.
 *   The sum of turns could exceed the 32-bit int bounds, thus requiring a 64-bit integer (`long long`).
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for (int i = 0; i < n; i++)
    {
        cin >> v[i];
    }
    long long res = 0;
    for (int i = 1; i < n; i++)
    {
        if (v[i] < v[i - 1])
        {
            res += v[i - 1] - v[i];
            v[i] = v[i - 1];
        }
    }
    cout << res << endl;
    return 0;
}