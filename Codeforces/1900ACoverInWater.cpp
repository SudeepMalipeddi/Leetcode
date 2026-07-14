#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t-- > 0)
    {
        int n;
        cin >> n;

        string str;
        cin >> str;
        // int number_of_free_cells = 0;
        int res = 0;
        int contigious_cells = 0;
        bool can = false;
        for (int i = 0; i < n; i++)
        {
            // cout << "contigious ceels at index i = " << i << " are " << contigious_cells << "\n";
            if (str[i] == '.' && contigious_cells == 0)
            {
                contigious_cells = 1;
                res++;
            }
            else if (str[i] == '.' && contigious_cells != 0)
            {
                // check if contigious_cells == 3
                contigious_cells++;
                res++;
                if (contigious_cells == 3)
                {
                    can = true;
                    break;
                }
            }
            else if (str[i] == '#' && contigious_cells != 0)
            {
                contigious_cells = 0;
            }
        }

        if (can)
        {
            cout << 2 << '\n';
        }
        else
        {
            cout << res << '\n';
        }
        // .1.2.3

        // int consec = 0;
        // bool flag = false;

        // for (int i = 0; i < n; i++)
        // {
        //     if (str[i] == '.' && consec == 0)
        //     {
        //         consec = 1;
        //     }
        //     else if (str[i] == '.' && consec != 0)
        //     {
        //         consec++;
        //         if (consec == 3)
        //         {
        //             flag = true;
        //             cout << 2 << '\n';
        //             break;
        //         }
        //     }
        //     else if (str[i] == '#' && consec != 0)
        //     {
        //         consec = 0;
        //     }
        // }
        // if (!flag)
        // {
        //     int res = 0;
        //     for (int i = 0; i < n; i++)
        //     {
        //         if (str[i] == '.')
        //         {
        //             res++;
        //         }
        //     }
        //     cout << res << '\n';
        // }
    }
    return 0;
}