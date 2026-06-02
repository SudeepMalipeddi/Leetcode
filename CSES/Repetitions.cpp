/*
 * Problem: CSES - Repetitions
 *
 * Problem Statement:
 * You are given a DNA sequence: a string consisting of characters A, C, G, and T.
 * Your task is to find the longest repetition in the sequence. This is a maximum-length
 * substring containing only one type of character.
 *
 * Intuition:
 * We can keep track of the current consecutive character count and the overall maximum count.
 * As we iterate through the given string, if the current character is the same as the previous one,
 * we increment the current count. Otherwise, we reset the count to 1.
 * We update the maximum count at each character.
 *
 * Approach:
 * 1. Read the input sequence as a string `s`.
 * 2. If the string is not empty, initialize the result and the current character count to 1 (or 0 and adjust logic).
 * 3. Loop through the string starting from the second character (`i = 1`).
 * 4. Check if `s[i] == s[i-1]`. If so, increment the current character count.
 * 5. If not, reset the current character count to 1.
 * 6. Make the result equal to `max(result, current logic count)`.
 * 7. After the loop, output the result.
 *
 * Time Complexity: O(N) where N is the length of the string, as we only loop over the string once.
 * Space Complexity: O(1) beyond the memory used to store the sequence string.
 *
 * Edge Cases:
 * - A string of length 1 (returns 1).
 * - A string where all characters are identical.
 * - A string where all characters are unique.
 */
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
