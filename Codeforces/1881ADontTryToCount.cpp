#include<iostream>
#include<string>

using namespace std;

void solve(){
    int n,m;
    cin >> n >> m;
    string x,s;
    cin >> x >> s;
    
    int operations = 0;
    
    while(operations <= 6){
        if(x.find(s) != string::npos){
            cout << operations << "\n";
            return;
        }
        x += x;
        operations++;
    }
    
    cout << -1 << "\n";
}


int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while(t-- > 0){
        solve();
    }
    return 0;
}