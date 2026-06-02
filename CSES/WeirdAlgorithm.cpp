/*
 * Problem: CSES - Weird Algorithm
 *
 * Problem Statement:
 * Consider an algorithm that takes as input a positive integer n. If n is even,
 * the algorithm divides it by two, and if n is odd, the algorithm multiplies it
 * by three and adds one. The algorithm repeats this, until n is one.
 * Given n, simulate the execution of the algorithm.
 *
 * Intuition:
 * The problem is a direct simulation of the Collatz conjecture. Since we are simply
 * required to execute the steps exactly as stated and print the intermediate values,
 * a simple while loop will suffice. We must use a 64-bit integer (long long) because
 * the intermediate values can exceed the 32-bit integer limit.
 *
 * Approach:
 * 1. Read the input integer `n` as a `long long`.
 * 2. Loop while `n` is greater than 1.
 * 3. Inside the loop, print the current value of `n`.
 * 4. Check if `n` is even using `n % 2 == 0`. If so, update `n = n / 2`.
 * 5. If `n` is odd, update `n = (n * 3) + 1`.
 * 6. Once the loop terminates (when `n == 1`), print the final value `1`.
 *
 * Time Complexity: O(log N) on average, but bounded by the number of steps in the
 * Collatz sequence for n.
 * Space Complexity: O(1), as we only use a single variable for simulation.
 *
 * Edge Cases:
 * - n = 1: The loop shouldn't execute, it should just print `1` and exit.
 * - Large values of n where `n * 3 + 1` exceeds 32-bit int bounds.
 *
 * Dry Run:
 * n = 3
 * 1. n=3 (odd) -> print 3, n = 3*3+1 = 10
 * 2. n=10 (even) -> print 10, n = 10/2 = 5
 * 3. n=5 (odd) -> print 5, n = 5*3+1 = 16
 * 4. n=16 (even) -> print 16, n = 16/2 = 8
 * 5. n=8 (even) -> print 8, n = 8/2 = 4
 * 6. n=4 (even) -> print 4, n = 4/2 = 2
 * 7. n=2 (even) -> print 2, n = 2/2 = 1
 * 8. n=1 -> loop ends, print 1
 * Result: 3 10 5 16 8 4 2 1
 *
 * Correctness Check:
 * Using `long long` correctly prevents overflow issues. The logic strictly follows
 * the problem rules.
 */
#include <iostream>
// #include <bits/stdc++.h>
using namespace std;

int main()
{
    long long n;
    cin >> n;
    while (n > 1)
    {
        cout << n << " ";
        if (n % 2 == 0)
        {
            n = n / 2;
        }
        else
        {
            n = (n * 3) + 1;
        }
    }
    cout << n << " ";

    return 0;
}
