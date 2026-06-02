/*
 * Problem: LeetCode 3120 - Count the Number of Special Characters I
 *
 * Problem Statement:
 * Given a string word, a letter is special if both its lowercase and uppercase forms
 * appear in word. Return the number of special letters in word.
 *
 * Intuition:
 * Track the first occurrence of each letter (both lowercase and uppercase) using two
 * 26-element arrays. A letter is special if both its lowercase and uppercase variants
 * appear at least once somewhere in the string.
 *
 * Approach:
 *   1. Initialize two vectors firstLower and firstUpper of size 26 with -1 (meaning unseen).
 *   2. Iterate through word character by character (index i):
 *      - If ch is a lowercase letter ('a'-'z'), set firstLower[ch - 'a'] = i if it was -1.
 *      - If ch is an uppercase letter ('A'-'Z'), set firstUpper[ch - 'A'] = i if it was -1.
 *   3. Initialize res = 0. Loop i from 0 to 25:
 *      - If both firstLower[i] != -1 and firstUpper[i] != -1, increment res.
 *   4. Return res.
 *
 * Time Complexity: O(n) — single pass over the string of length n plus a 26-iteration loop.
 * Space Complexity: O(1) — two fixed-size arrays of 26 integers each.
 *
 * Edge Cases:
 * - Empty string: both arrays remain all -1, returns 0.
 * - Only lowercase or only uppercase letters: no letter has both forms, returns 0.
 * - Multiple occurrences of the same letter: only first occurrence index is recorded,
 *   but the count is per letter (both forms present), so duplicates don't affect result.
 *
 * Dry Run:
 * word = "aaAbcC"
 * n = 6
 * i=0, ch='a', idx=0, firstLower[0] = 0
 * i=1, ch='a', idx=0, firstLower[0] already set, skip
 * i=2, ch='A', idx=0, firstUpper[0] = 2
 * i=3, ch='b', idx=1, firstLower[1] = 3
 * i=4, ch='c', idx=2, firstLower[2] = 4
 * i=5, ch='C', idx=2, firstUpper[2] = 5
 * After loop: firstLower[0]=0, firstLower[1]=3, firstLower[2]=4, others -1
 *             firstUpper[0]=2, firstUpper[2]=5, others -1
 * i=0: both != -1 → res=1 (a/A)
 * i=1: upper=-1 → skip (b has no B)
 * i=2: both != -1 → res=2 (c/C)
 * i=3..25: skip
 * Returns 2.
 *
 * Correctness Check:
 * The algorithm correctly identifies special letters because a letter is special iff both
 * its lowercase and uppercase forms appear; tracking first occurrence of each form ensures
 * presence detection without false negatives or false positives.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    int numberOfSpecialChars(string word)
    {
        int n = word.size();
        vector<int> firstLower(26, -1), firstUpper(26, -1);

        for (int i = 0; i < n; i++)
        {
            char ch = word[i];
            if (ch >= 'a' && ch <= 'z')
            {
                int idx = ch - 'a';
                if (firstLower[idx] == -1)
                    firstLower[idx] = i;
            }
            else if (ch >= 'A' && ch <= 'Z')
            {
                int idx = ch - 'A';
                if (firstUpper[idx] == -1)
                    firstUpper[idx] = i;
            }
        }

        int res = 0;
        for (int i = 0; i < 26; i++)
        {
            if (firstLower[i] != -1 && firstUpper[i] != -1)
                res++;
        }

        return res;
    }
};
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    return 0;
}