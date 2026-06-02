#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int x, y, z;
    if (!(cin >> x >> y >> z))
        return 0;

    vector<int> vec;
    vec.push_back(x);
    vec.push_back(y);
    vec.push_back(z);

    sort(vec.begin(), vec.end());

    cout << vec[0] << '\n';
    cout << vec[1] << '\n';
    cout << vec[2] << '\n';
    cout << '\n';

    cout << x << '\n';
    cout << y << '\n';
    cout << z << '\n';

    return 0;
}