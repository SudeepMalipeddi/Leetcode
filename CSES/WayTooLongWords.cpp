/*
 * Problem: CSES / Codeforces 71A - Way Too Long Words
 *
 * Problem Statement:
 * Given n words, if a word's length exceeds 10, abbreviate it as:
 * first_letter + (length - 2) + last_letter. Otherwise, print the word as-is.
 *
 * Intuition:
 * Straightforward string manipulation. For each word, check length. If <= 10,
 * print original. If > 10, construct the abbreviation: s[0] + to_string(len-2) + s[len-1].
 *
 * Approach:
 * 1. Read n.
 * 2. For each word: read string s.
 * 3. If s.size() > 10: print s[0] << s.size()-2 << s[s.size()-1].
 * 4. Else: print s.
 *
 * Time Complexity: O(n * L) where L is the string length (processing each char).
 * Space Complexity: O(1) extra.
 *
 * Edge Cases:
 * - Empty string: per constraints, words are non-empty.
 * - Word exactly 10 chars: printed as-is.
 *
 * Dry Run:
 * n=4
 * "word" → len=4 ≤10 → "word"
 * "localization" → len=12 > 10 → l + 10 + n → "l10n"
 * "internationalization" → len=20 → i + 18 + n → "i18n"
 * "pneumonoultramicroscopicsilicovolcanoconiosis" → len=45 → p + 43 + s → "p43s"
 *
 * Correctness Check:
 * The abbreviation formula exactly matches the problem specification. First char,
 * count of middle chars (len-2), last char are correctly extracted via indexing.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n;
    cin >> n;
    while (n--)
    {
        string s;
        cin >> s;
        if (s.size() > 10)
        {
            cout << s[0] << s.size() - 2 << s[s.size() - 1] << '\n';
        }
        else
        {
            cout << s << '\n';
        }
    }
    return 0;
}