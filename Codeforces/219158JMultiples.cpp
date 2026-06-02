#include <iostream>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int x, y;
    cin >> x >> y;
    if (y % x == 0)
    {
        cout << "Multiples\n";
        exit(0);
    }
    if (x % y == 0)
    {
        cout << "Multiples\n";
        exit(0);
    }
    cout << "No Multiples\n";
    return 0;
}