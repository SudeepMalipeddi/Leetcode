#include <iostream>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    long long x, y;
    cin >> x >> y;

    int res = x % 10;
    res += y % 10;
    cout << res << "\n";
    return 0;
}