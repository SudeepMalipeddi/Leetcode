#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string s1, s2;
    cin >> s1;
    cin >> s2;
    int n = s1.size();
    bool flag = false;
    for (int i = 0; i < n; i++)
    {
        char c1 = tolower(s1[i]);
        char c2 = tolower(s2[i]);
        if (c1 < c2)
        {
            cout << -1;
            flag = true;
            break;
        }
        else if (c1 > c2)
        {
            cout << 1;
            flag = true;
            break;
        }
    }
    if (flag == false)
    {
        cout << 0;
    }
    return 0;
}