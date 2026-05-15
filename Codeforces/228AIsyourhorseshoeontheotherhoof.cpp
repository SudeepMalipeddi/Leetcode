#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 228A - Is your horseshoe on the other hoof?
// Link: https://codeforces.com/problemset/problem/228/A

int main()
{
    long long temp = 0;
    unordered_set<long long> set1;
    set1.reserve(4);
    vector<int> vec;
    vec.assign(set1.begin(), set1.end());
    for (int i = 0; i < 4; i++)
    {
        cin >> temp;
        set1.insert(temp);
    }
    cout << 4 - set1.size() << endl;
    return 0;
}
