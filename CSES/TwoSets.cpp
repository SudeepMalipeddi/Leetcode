#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> arr1;
    vector<int> arr2;
    long long sum = 1LL * n * (n + 1) / 2;
    if (sum % 2 == 1)
    {
        cout << "NO" << endl;
    }
    else
    {
        sum = sum / 2;

        for (int i = n; i >= 1; i--)
        {
            // cout << i << endl;
            if (sum > 0 && i <= sum)
            {
                arr1.push_back(i);
                sum = sum - i;
            }
            else
            {
                arr2.push_back(i);
            }
        }
        cout << "YES" << endl;
        cout << arr1.size() << endl;
        for (int x : arr1)
        {
            cout << x << " ";
        }
        cout << endl;
        cout << arr2.size() << endl;
        for (int x : arr2)
        {
            cout << x << " ";
        }
    }
    return 0;
}