// #include <iostream>
// #include <bits/stdc++.h>
// using namespace std;

// int main()
// {
//     size_t res = 0;
//     string s;
//     cin >> s;

//     if (!s.empty())
//     {
//         res = 1;
//     }

//     size_t i = 0, j;
//     while (i + 1 < s.size())
//     {
//         j = i + 1;
//         while (j < s.size() && s[i] == s[j])
//         {
//             j++;
//         }
//         res = max(res, j - i);
//         i = j;
//     }
//     cout << res << endl;

//     return 0;
// }

// another way to solve
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    size_t res = 0, count = 0;
    string s;
    cin >> s;

    if (!s.empty())
    {
        res = 1;
    }

    char c = 'A';
    for (char d : s)
    {
        if (d == c)
        {
            ++count;
            res = max(res, count);
        }
        else
        {
            c = d;
            count = 1;
        }
    }
    cout << res << endl;

    return 0;
}
