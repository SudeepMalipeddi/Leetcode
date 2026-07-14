#include <iostream>
#include <vector>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t-- > 0)
    {
        int n, k;
        cin >> n >> k;
        vector<int> arr(n);
        for (int i = 0; i < n; i++)
        {
            cin >> arr[i];
        }

        // check if already sorted
        bool flag = true;
        for (int i = 1; i < n; i++)
        {
            if (arr[i] >= arr[i - 1])
                continue;
            else
            {
                flag = false;
                break;
            }
        }

        if (flag)
        {
            cout << "YES\n";
            continue;
        }

        if (k == 1)
        {
            cout << "NO\n";
            continue;
        }

        if (k >= 2)
        {
            cout << "YES\n";
        }
    }
    return 0;
}