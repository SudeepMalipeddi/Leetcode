#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n = 10;
        int res = 0;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                char ch;
                cin >> ch;

                if (ch == 'X')
                {
                    res += min({i, 9 - i, j, 9 - j}) + 1;
                }
            }
        }
        cout << res << '\n';
    }
    return 0;
}