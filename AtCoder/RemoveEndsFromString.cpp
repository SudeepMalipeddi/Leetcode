#include <iostream>
using namespace std;

/*
Problem Statement
You are given a string
S consisting of lowercase English letters and a positive integer
N. The length of
S is at least
2N+1.

Find the string obtained by removing
N characters from the beginning and
N characters from the end of
S.

Constraints
S is a string consisting of lowercase English letters.
N is an integer.
2N+1≤∣S∣≤30
1≤N≤10
Input
The input is given from Standard Input in the following format:

S
N
Output
Output the answer.

Sample Input 1
chemotherapy
3
Sample Output 1
mother
Removing the first three characters (che) and the last three characters (apy) from chemotherapy gives mother.

Sample Input 2
thermometer
4
Sample Output 2
mom
Sample Input 3
burger
1
Sample Output 3
urge
*/

int main()
{
    string s;
    int n;
    cin >> s;
    cin >> n;
    int size = s.size();
    string first = s.substr(0 + n, size - n);
    // cout << first;
    string res = first.substr(0, first.size() - n);
    cout << res;
}