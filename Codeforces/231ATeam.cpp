/*
 * Problem: Codeforces 231A - Team
 *
 * Problem Statement:
 * One day three best friends Petya, Vasya, and Tonya decided to form a team and take part in programming contests.
 * Participants are usually offered several problems during programming contests. Long before the start the friends decided that they will implement a problem if at least two of them are sure about the solution. Otherwise, the friends won't write the problem's solution.
 * Given n problems and the friends' sureness about them, find the number of problems they will implement.
 *
 * Intuition:
 * The problem requires us to count how many problems have at least two '1's among the three friends' responses. Since each response is either 0 or 1, we can simply sum them up. If the sum is >= 2, they will solve it.
 *
 * Approach:
 * 1. Read the number of problems `n`.
 * 2. Loop `n` times.
 * 3. In each iteration, read the three responses `a`, `b`, and `c`.
 * 4. Add them together. If the sum is >= 2, increment the result counter `res`.
 * 5. After the loop, print `res`.
 *
 * Time Complexity: O(N)
 * We process each of the n problems exactly once. Inside the loop, operations take O(1) time.
 *
 * Space Complexity: O(1)
 * We only use a few integer variables (`n`, `res`, `a`, `b`, `c`, `count`), which take constant space.
 *
 * Edge Cases:
 * - n = 0: Output 0.
 * - All responses are 0: Output 0.
 * - All responses are 1: Will count correctly.
 *
 * Dry Run:
 * Input:
 * 3
 * 1 1 0
 * 1 1 1
 * 1 0 0
 *
 * 1. Initial res = 0.
 * 2. i = 0: a=1, b=1, c=0. count = 2. count >= 2, so res = 1.
 * 3. i = 1: a=1, b=1, c=1. count = 3. count >= 2, so res = 2.
 * 4. i = 2: a=1, b=0, c=0. count = 1. count < 2, res remains 2.
 * Output: 2
 *
 * Correctness Check:
 * By checking if the sum of 0s and 1s is >= 2, we directly map to the condition "at least two people are sure".
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n, res = 0;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        int a, b, c, count = 0;
        cin >> a >> b >> c;
        count = a + b + c;
        if (count >= 2)
            res++;
    }
    cout << res << "\n";
    return 0;
}