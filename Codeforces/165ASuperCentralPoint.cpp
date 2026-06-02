#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/*
One day Vasya painted a Cartesian coordinate system on a piece of paper and marked some set of points (x1, y1), (x2, y2), ..., (xn, yn). Let's define neighbors for some fixed point from the given set (x, y):

point (x', y') is (x, y)'s right neighbor, if x' > x and y' = y
point (x', y') is (x, y)'s left neighbor, if x' < x and y' = y
point (x', y') is (x, y)'s lower neighbor, if x' = x and y' < y
point (x', y') is (x, y)'s upper neighbor, if x' = x and y' > y
We'll consider point (x, y) from the given set supercentral, if it has at least one upper, at least one lower, at least one left and at least one right neighbor among this set's points.

Vasya marked quite many points on the paper. Analyzing the picture manually is rather a challenge, so Vasya asked you to help him. Your task is to find the number of supercentral points in the given set.

Input
The first input line contains the only integer n (1 ≤ n ≤ 200) — the number of points in the given set. Next n lines contain the coordinates of the points written as "x y" (without the quotes) (|x|, |y| ≤ 1000), all coordinates are integers. The numbers in the line are separated by exactly one space. It is guaranteed that all points are different.

Output
Print the only number — the number of supercentral points of the given set.

*/
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<pair<int,int>> pts(n);
    for (auto& [x, y] : pts) cin >> x >> y;

    int count = 0;
    for (auto& [x, y] : pts) {
        bool up = false, down = false, left = false, right = false;
        for (auto& [x2, y2] : pts) {
            if (x2 == x && y2 > y) up = true;
            if (x2 == x && y2 < y) down = true;
            if (y2 == y && x2 < x) left = true;
            if (y2 == y && x2 > x) right = true;
        }
        if (up && down && left && right) count++;
    }

    cout << count << "\n";
    return 0;
}