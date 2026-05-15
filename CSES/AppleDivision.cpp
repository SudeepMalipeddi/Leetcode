#include <iostream>
#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

void divide_apples(vector<ll> &weights, ll &ans, int index, ll g1, ll g2)
{
    if (index == weights.size())
    {
        ans = min(ans, abs(g1 - g2));
        return;
    }

    divide_apples(weights, ans, index + 1, g1 + weights[index], g2);

    divide_apples(weights, ans, index + 1, g1, g2 + weights[index]);
}

int main()
{
    int n;
    cin >> n;
    vector<ll> weights(n);
    for (int i = 0; i < n; i++)
    {
        cin >> weights[i];
    }

    ll ans = 1e18;
    divide_apples(weights, ans, 0, 0, 0);

    cout << ans << endl;
    return 0;
}