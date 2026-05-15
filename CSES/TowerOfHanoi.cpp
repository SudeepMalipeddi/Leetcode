#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void toh(int n, int src, int middle, int right)
{
    if (n == 0)
        return;
    toh(n - 1, src, right, middle);

    cout << src << " " << right << endl;

    toh(n - 1, middle, src, right);
}

int main()
{
    int n;
    cin >> n;

    cout << (1 << n) - 1 << endl;

    toh(n, 1, 2, 3);

    return 0;
}