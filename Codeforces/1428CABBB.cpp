#include <iostream>
#include <string>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    string ab = "AB";
    string bb = "BB";

    while (t-- > 0)
    {
        string s;
        cin >> s;

        // In-place write-pointer: O(n) time, O(1) extra space
        int j = 0; // write index
        for (int i = 0; i < (int)s.size(); ++i)
        {
            s[j++] = s[i];
            if (j >= 2)
            {
                char a = s[j - 2];
                char b = s[j - 1];
                if ((a == 'A' && b == 'B') || (a == 'B' && b == 'B'))
                    j -= 2;
            }
        }

        cout << j << "\n";
    }

    return 0;
}