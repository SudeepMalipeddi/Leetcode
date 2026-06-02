// Codeforces 339A - Helpful Maths
// Link: https://codeforces.com/problemset/problem/339/A
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s;
    getline(cin, s);
    priority_queue<int, vector<int>, greater<int>> pq;

    for (int i = 0; i < s.size(); i++)
    {
        if (isdigit(s[i]))
        {
            // pq.push(static_cast<int>(s[i]));
            int a = s[i] - '0';
            pq.push(a);
        }
    }

    while (!pq.empty())
    {
        int top = pq.top();
        pq.pop();
        if (pq.size() == 0)
        {
            cout << top;
            break;
        }
        cout << top << "+";
    }
    cout << "\n";

    return 0;
}