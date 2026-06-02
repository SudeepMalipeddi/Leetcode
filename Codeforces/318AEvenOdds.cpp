#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    long long n, k;
    cin >> n >> k;

    long long midpt = 0;
    if (n % 2 == 0)
        midpt = n / 2;
    else if (n % 2 == 1)
        midpt = (n + 1) / 2;
    // cout << "midpoint is " << midpt << '\n';

    if (k <= midpt)
    {
        cout << (k * 2) - 1 << '\n';
    }
    else
    {
        k = k - midpt;
        cout << (k * 2) << '\n';
    }

    return 0;
}