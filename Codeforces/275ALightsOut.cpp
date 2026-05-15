/*Lenny is playing a game on a 3 × 3 grid of lights.In the beginning of the game all lights are switched on.Pressing any of the lights will toggle it and all side - adjacent lights.The goal of the game is to switch all the lights off.We consider the toggling as follows : if the light was switched on then it will be switched off, if it was switched off then it will be switched on.

                                                                                                                                                                                                                                                                                                                                         Lenny has spent some time playing with the grid and by now he has pressed each light a certain number of times.Given the number of times each light is pressed,
    you have to print the current state of each light.

        Input
            The input consists of three rows.Each row contains three integers each between 0 to 100 inclusive.The j -
        th number in the i - th row is the number of times the j - th light of the i - th row of the grid is pressed.

                                                                                       Output Print three lines,
    each containing three characters.The j - th character of the i - th line is "1" if and only if the corresponding light is switched on, otherwise it's "0".
input:
1 0 0
0 0 0
0 0 1
output:
001
010
100



    */

#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 275A - Lights Out
// Link: https://codeforces.com/problemset/problem/275/A

int main()
{
    int arr[3][3];
    int res[3][3];
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cin >> arr[i][j];
        }
    }
    // For each cell, sum the press counts of itself and its side-adjacent neighbors.
    // A light toggles once per press on any adjacent cell (including itself).
    // All lights start ON. If a cell was toggled an odd number of times total, it is now OFF (0).
    // NOTE: corner/edge cells also include arr[i][j-1] or arr[i][j+1] even when j==0 or j==2,
    // which reads out-of-bounds — this is a bug for corner cells.
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            int sum = 0;
            if (i == 0)
            {
                // Top row: no row above; but still includes j-1/j+1 (buggy at corners)
                sum = arr[i][j] + arr[i + 1][j] + arr[i][j + 1] + arr[i][j - 1];
            }
            else if (i == 2)
            {
                // Bottom row: no row below
                sum = arr[i][j] + arr[i - 1][j] + arr[i][j + 1] + arr[i][j - 1];
            }
            else if (j == 0)
            {
                // Left column: no column to the left
                sum = arr[i][j] + arr[i + 1][j] + arr[i - 1][j] + arr[i][j + 1];
            }
            else if (j == 2)
            {
                // Right column: no column to the right
                sum = arr[i][j] + arr[i + 1][j] + arr[i - 1][j] + arr[i][j - 1];
            }
            else
            {
                // Interior cell (only (1,1)): all 4 neighbors exist
                sum = arr[i][j] + arr[i + 1][j] + arr[i - 1][j] + arr[i][j + 1] + arr[i][j - 1];
            }

            // Odd total toggles → light is OFF; even → still ON
            if (sum % 2 == 1)
            {
                cout << 0;
            }
            else
            {
                cout << 1;
            }
        }
        cout << endl;
    }

    return 0;
}
