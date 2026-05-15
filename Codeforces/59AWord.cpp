#include <iostream>
#include <bits/stdc++.h>
#include <string>
using namespace std;

// Codeforces 59A - Word
// Link: https://codeforces.com/problemset/problem/59/A

// Problem: capitalize a word — if uppercase count > lowercase, convert all to uppercase;
// otherwise convert all to lowercase (ties go to lowercase).
int main()
{
    string s;
    cin >> s;
    char res[s.size() + 1];
    int lc = 0, uc = 0;

    // Count uppercase vs lowercase letters
    // Note: `s.at(i) - 'A' > 25` is true for lowercase letters (their ASCII > 'A'+25 = 'Z')
    for (int i = 0; i < s.size(); i++)
    {
        if (s.at(i) - 'A' > 25)
        {
            lc++;
        }
        else
        {
            uc++;
        }
    }
    if (uc > lc)
    {
        // Majority uppercase — convert everything to uppercase
        for (int i = 0; i < s.size(); i++)
        {
            if (islower(s[i]))
            {
                res[i] = toupper(s[i]);
            }
            else
            {
                res[i] = s[i];
            }
        }
    }
    else if (uc <= lc)
    {
        // Majority lowercase (or tie) — convert everything to lowercase
        for (int i = 0; i < s.size(); i++)
        {
            if (isupper(s[i]))
            {
                res[i] = tolower(s[i]);
            }
            else
            {
                res[i] = s[i];
            }
        }
    }
    for (int i = 0; i < s.size(); i++)
    {
        cout << res[i];
    }
    res[s.size()] = '\0';
    cout << endl;
    return 0;
}
