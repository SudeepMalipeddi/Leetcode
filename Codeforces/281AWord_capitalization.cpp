#include <iostream>
#include <string>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 281A - Word Capitalization
// Link: https://codeforces.com/problemset/problem/281/A
// Problem: capitalize only the first letter of a word, leave the rest unchanged.
int main()
{
    string s;
    cin >> s;
    s[0] = toupper(s[0]);
    cout << s << endl;
    return 0;
}