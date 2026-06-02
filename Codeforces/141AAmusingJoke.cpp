#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string guest, residence, pile;
    cin >> guest;
    cin >> residence;
    cin >> pile;

    int arr[26] = {0};

    for (int i = 0; i < guest.size(); i++)
    {
        arr[guest[i] - 'A']++;
    }
    for (int i = 0; i < residence.size(); i++)
    {
        arr[residence[i] - 'A']++;
    }
    for (int i = 0; i < pile.size(); i++)
    {
        arr[pile[i] - 'A']--;
    }
    for (int i = 0; i < 26; i++)
    {
        if (arr[i] != 0)
        {
            cout << "NO\n";
            exit(0);
        }
    }

    cout << "YES\n";

    return 0;
}