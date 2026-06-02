#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int arr[26] = {0};

    string s;
    cin >> s;

    for (int i = 0; i < s.size(); i++)
    {
        int curr = s[i] - 'a';
        arr[curr]++;
    }

    int count = 0;

    for (int i = 0; i < 26; i++)
    {
        if (arr[i] != 0)
        {
            // cout << arr[i] << "\n";
            count++;
        }
    }

    if (count % 2 == 0)
    {
        cout << "CHAT WITH HER!\n";
    }

    else
    {
        cout << "IGNORE HIM!\n";
    }

    return 0;
}