#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, count = 0;
    cin >> n >> m;

    while (m != 0)
    {
        int rem = n % m;
        count++;
        m = rem;
    }
    cout << count << '\n';
    return 0;
}