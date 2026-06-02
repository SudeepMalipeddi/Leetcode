/*
 * Problem: LeetCode 1143 - Longest Common Subsequence
 *
 * Problem Statement:
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence is a sequence that appears in the same relative order but not necessarily contiguous.
 *
 * Intuition:
 * This is a classic 2D dynamic programming problem. At each pair of indices (i,j), if the characters
 * match, we can extend a subsequence; otherwise we carry forward the best result from skipping one
 * character from either string. The optimal substructure makes DP the natural choice.
 *
 * Approach:
 *   1. Let m = text1.size(), n = text2.size().
 *   2. Create a 2D dp table of size (m+1) x (n+1) initialized to 0, where dp[i][j] represents
 *      the LCS length for prefixes text1[0..i-1] and text2[0..j-1].
 *   3. Iterate i from 1 to m and j from 1 to n:
 *      - If text1[i-1] == text2[j-1], then dp[i][j] = 1 + dp[i-1][j-1] (extend the match).
 *      - Else dp[i][j] = max(dp[i-1][j], dp[i][j-1]) (carry forward the best so far).
 *   4. Return dp[m][n] as the final answer.
 *
 * Time Complexity: O(m * n) — we fill every cell of the (m+1)*(n+1) dp table once.
 * Space Complexity: O(m * n) — the 2D dp table stores intermediate results for all prefix pairs.
 *
 * Edge Cases:
 * - Empty string(s): dp table handles them via the 0-initialized first row/column, returning 0.
 * - No common characters: dp stays 0 throughout, correct output 0.
 * - Identical strings: dp[m][n] will equal m (or n), the full string length.
 * - Large strings: O(m*n) space may be high; could be optimized to O(min(m,n)) with 1D DP.
 *
 * Dry Run:
 * text1 = "abcde", text2 = "ace"
 * m=5, n=3, dp table (6x4):
 * Initial: all 0.
 * i=1 (text1[0]='a'), j=1 (text2[0]='a'): match → dp[1][1] = 1 + dp[0][0] = 1
 * i=1, j=2 (text2[1]='c'): no match → dp[1][2] = max(dp[0][2]=0, dp[1][1]=1) = 1
 * i=1, j=3 (text2[2]='e'): no match → dp[1][3] = max(dp[0][3]=0, dp[1][2]=1) = 1
 * i=2 (text1[1]='b'), j=1: no match → dp[2][1] = max(dp[1][1]=1, dp[2][0]=0) = 1
 * i=2, j=2 ('b' vs 'c'): no match → dp[2][2] = max(dp[1][2]=1, dp[2][1]=1) = 1
 * i=2, j=3: no match → dp[2][3] = max(dp[1][3]=1, dp[2][2]=1) = 1
 * i=3 (text1[2]='c'), j=1: no match → dp[3][1] = max(dp[2][1]=1, dp[3][0]=0) = 1
 * i=3, j=2 ('c' vs 'c'): match → dp[3][2] = 1 + dp[2][1] = 2
 * i=3, j=3: no match → dp[3][3] = max(dp[2][3]=1, dp[3][2]=2) = 2
 * i=4 (text1[3]='d'), j=1: no match → dp[4][1]=1
 * i=4, j=2: no match → dp[4][2]=max(dp[3][2]=2, dp[4][1]=1)=2
 * i=4, j=3: no match → dp[4][3]=max(dp[3][3]=2, dp[4][2]=2)=2
 * i=5 (text1[4]='e'), j=1: no match → dp[5][1]=1
 * i=5, j=2: no match → dp[5][2]=max(dp[4][2]=2, dp[5][1]=1)=2
 * i=5, j=3 ('e' vs 'e'): match → dp[5][3] = 1 + dp[4][2] = 3
 * Output: 3 (LCS = "ace").
 *
 * Correctness Check:
 * The recurrence covers all cases: match extends the subsequence, mismatch takes the max of skipping
 * one char from either string. Since we exhaustively compute all prefix pairs, the optimal answer is
 * guaranteed at dp[m][n].
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    int longestCommonSubsequence(string text1, string text2)
    {
        int m = text1.size(), n = text2.size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (text1[i - 1] == text2[j - 1])
                {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else
                {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
};

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    return 0;
}