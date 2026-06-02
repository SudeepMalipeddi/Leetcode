/*
 * Problem: CSES / Codeforces 450A - Jzzhu and Children
 *
 * Problem Statement:
 * There are n children in a queue. Each child needs a certain number of candies.
 * In each round, m candies are given to the child at the front. If they still
 * need more, they go to the back. Find the 1-based index of the last child to leave.
 *
 * Intuition:
 * Straightforward queue simulation. Track each child's remaining demand as
 * (demand, original_index). Each round: dequeue front, subtract m, re-enqueue
 * if demand > 0. The last processed before the queue empties is the answer.
 *
 * Approach:
 * 1. Read n, m. Create queue of pairs (demand, 0-based index).
 * 2. While queue.size() > 1: pop front. If demand > m, push (demand-m, idx). Else discard.
 * 3. Print front.index + 1 as answer.
 *
 * Time Complexity: O(total ops) — each child cycles ceil(demand/m) times.
 * Space Complexity: O(n) for the queue.
 *
 * Edge Cases:
 * - All satisfied in one round: last index is n.
 * - m is very large (>= all demands): answer is n.
 *
 * Dry Run:
 * n=5, m=2, demands=[1,3,1,4,2]
 * Queue: (1,0),(3,1),(1,2),(4,3),(2,4)
 * (1,0)→discard. (3,1)→(1,1) push back. (1,2)→discard. (4,3)→(2,3) push. (2,4)→discard.
 * Queue: (1,1),(2,3). (1,1)→discard. (2,3)→discard. Last: index 3+1=4.
 *
 * Correctness Check:
 * FIFO queue exactly simulates the round-robin candy distribution. Tracking
 * original index preserves the answer after re-enqueueing.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n, m;
    queue<pair<int, int>> q;
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        pair<int, int> t(temp, i);
        q.push(t);
    }
    while (q.size() > 1)
    {
        pair<int, int> temp = q.front();
        if (temp.first > m)
        {
            temp.first -= m;
            q.pop();
            q.push(temp);
        }
        else
        {
            q.pop();
        }
    }
    pair<int, int> temp = q.front();
    cout << temp.second + 1 << '\n';
}