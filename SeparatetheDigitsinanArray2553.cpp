#include <iostream>
#include <vector>
using namespace std;

vector<int> separateDigits(vector<int> &nums)
{
    vector<int> res;
    for (int a : nums)
    {
        vector<int> temp;
        while (a > 0)
        {
            temp.insert(temp.begin(), a % 10);
            a = a / 10;
        }
        for (int i : temp)
        {
            res.push_back(i);
        }
    }
    return res;
}