#include <iostream>
#include <vector>

using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> b(n);
    for (int i = 0; i < n; ++i) {
        cin >> b[i];
    }

    vector<int> a;
    a.push_back(b[0]);

    for (int i = 1; i < n; ++i) {
        if (b[i - 1] > b[i]) {
            a.push_back(b[i]);
        }
        a.push_back(b[i]);
    }

    cout << a.size() << "\n";
    for (int i = 0; i < a.size(); ++i) {
        cout << a[i] << (i == a.size() - 1 ? "" : " ");
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        solve();
    }

    return 0;
}