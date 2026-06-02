#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int median(vector<int> &arr, int x, int y)
{
    // Find the correct positions to keep the vector sorted
    arr.insert(upper_bound(arr.begin(), arr.end(), x), x);
    arr.insert(upper_bound(arr.begin(), arr.end(), y), y);
    
    // The total size is always odd, so the middle element is the median
    return arr[arr.size() / 2];
}

int main()
{
    int x;
    int q;
    cin >> x;
    cin >> q;
    multiset<int> sorted_list;
    sorted_list.insert(x);
    for (int i = 0; i < q; i++)
    {
        int a, b;
        cin >> a;
        cin >> b;
    }
}