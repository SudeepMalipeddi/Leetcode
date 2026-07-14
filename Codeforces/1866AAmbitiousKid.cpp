#include <iostream>
#include <bits/stdc++.h>
#include <climits>
using namespace std;

int main() {
    int n;
    cin >> n;
    
    int min = INT_MAX;
    for(int i = 0; i < n; i++) {
        int temp;
        cin >> temp;
        if(abs(temp) < min){
            min = abs(temp);
        }
    }

    cout << min << "\n";
    
    return 0;
}