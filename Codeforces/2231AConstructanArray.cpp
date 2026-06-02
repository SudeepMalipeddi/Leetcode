#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t > 0)
    {

        int n;
        cin >> n;
        for (int i = 1; i <= n; i++)
        {
            cout << 2 * i - 1 << " ";
        }
        cout << "\n";

        t--;
    }
    return 0;
}