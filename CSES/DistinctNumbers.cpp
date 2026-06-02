/*
 * Problem: CSES - Distinct Numbers
 *
 * Problem Statement:
 * Given a list of n integers, calculate the number of distinct values.
 *
 * Intuition:
 * The simplest approach is to insert all numbers into a set, which automatically
 * removes duplicates. The size of the set is the answer. Since n <= 2*10^5,
 * a std::set (O(log n) per insertion) or std::unordered_set (O(1) average)
 * both work within time limits.
 *
 * Approach:
 * 1. Read n.
 * 2. Create a set<int>.
 * 3. Loop n times: read each integer and insert it into the set.
 * 4. Print s.size().
 *
 * Time Complexity: O(n log n) with std::set, or O(n) average with unordered_set.
 * Space Complexity: O(n) to store all distinct numbers.
 *
 * Edge Cases:
 * - n = 1: answer is always 1.
 * - All elements identical: answer is 1.
 * - All elements distinct: answer is n.
 *
 * Dry Run:
 * n = 5, arr = [2, 3, 2, 2, 3]
 * Insert 2 → set = {2}
 * Insert 3 → set = {2, 3}
 * Insert 2 → already present, set = {2, 3}
 * Insert 2 → already present
 * Insert 3 → already present
 * set.size() = 2
 * Output: 2
 *
 * Correctness Check:
 * A set by definition stores only unique values. Inserting all elements and
 * returning the size correctly counts distinct numbers.
 */

#include <iostream>
#include <set>

using namespace std;

int main()
{
    int n;
    cin >> n;
    set<int> s;
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        s.insert(x);
    }
    cout << s.size() << "\n";
}
