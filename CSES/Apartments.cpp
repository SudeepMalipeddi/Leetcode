#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/*
 * Problem: CSES - Apartments
 *
 * Problem Statement:
 * There are n applicants and m free apartments. Each applicant has a desired
 * apartment size, and they will accept any apartment whose size is within k
 * of their desired size. Distribute the apartments to maximize the number of
 * applicants who get one.
 *
 * Intuition:
 * This is a greedy two-pointer problem on sorted arrays. By sorting both the
 * applicant desires and apartment sizes, we can efficiently match the smallest
 * unassigned applicant to the smallest suitable apartment, ensuring optimal
 * assignment. If an apartment is too small for the current applicant, it's too
 * small for all future applicants — discard it. If it's too big, no remaining
 * apartment will satisfy this applicant — skip them.
 *
 * Approach:
 * 1. Read n, m, k and the two arrays a (applicants) and b (apartments).
 * 2. Sort both arrays.
 * 3. Use two pointers ptr1 (applicants) and ptr2 (apartments).
 * 4. While both pointers are in bounds:
 *    - If b[ptr2] < a[ptr1] - k: apartment too small, advance ptr2.
 *    - If b[ptr2] > a[ptr1] + k: apartment too big, advance ptr1.
 *    - Else: match found, increment res, advance both pointers.
 * 5. Print res.
 *
 * Time Complexity: O(n log n + m log m) for sorting, O(n + m) for two-pointer scan.
 * Space Complexity: O(n + m) to store inputs, or O(1) extra beyond input arrays.
 *
 * Edge Cases:
 * - No possible matches (all apartments too small / too big).
 * - Multiple applicants with the same desired size.
 * - k = 0: exact match required.
 *
 * Dry Run:
 * n=4, m=3, k=5
 * a = [45,60,60,80] (sorted), b = [30,60,75] (sorted)
 * ptr1=0(45), ptr2=0(30): 30 < 40? 30 < 45-5=40 → ptr2++
 * ptr1=0(45), ptr2=1(60): 60 in [40,50]? 60 > 50 → ptr1++
 * ptr1=1(60), ptr2=1(60): 60 in [55,65]? Yes → res=1, ptr1++, ptr2++
 * ptr1=2(60), ptr2=2(75): 75 in [55,65]? No, 75 > 65 → ptr1++
 * ptr1=3(80), ptr2=2(75): 75 in [75,85]? Yes → res=2
 * Output: 2
 *
 * Correctness Check:
 * The greedy two-pointer approach yields the optimal assignment because both
 * arrays are sorted, and we always pair the smallest feasible apartment with
 * the smallest feasible applicant, leaving more options for remaining applicants.
 */

int main()
{
    int n, m, k, res = 0;
    cin >> n >> m >> k;
    vector<int> a(n), b(m);
    for (int i = 0; i < n; i++)
        cin >> a[i];

    for (int i = 0; i < m; i++)
        cin >> b[i];

    sort(a.begin(), a.end());
    sort(b.begin(), b.end());

    // We use a two-pointer approach since both arrays are sorted.
    // ptr1 tracks the current applicant, ptr2 tracks the current apartment.
    int ptr1 = 0, ptr2 = 0;

    while (ptr1 < n && ptr2 < m)
    {
        // Case 1: The apartment is TOO SMALL for the current applicant.
        // Because the applicants array (a) is sorted, if this apartment
        // is too small for this applicant, it will be too small for ALL
        // remaining applicants. So, we discard it and move to the next apartment.
        if (b[ptr2] < a[ptr1] - k)
        {
            ptr2++;
        }
        // Case 2: The apartment is TOO BIG for the current applicant.
        // Because the apartments array (b) is sorted, if this apartment
        // is too big for this applicant, all remaining apartments will also
        // be too big for them. So, we can't satisfy this applicant and move on.
        else if (b[ptr2] > a[ptr1] + k)
        {
            ptr1++;
        }
        // Case 3: The apartment is a MATCH (within the allowed difference k).
        // It provides the best optimal match right now (smallest suitable
        // apartment for the smallest suitable applicant). We assign it,
        // increment our count, and move BOTH pointers forward.
        else
        {
            res++;
            ptr1++;
            ptr2++;
        }
    }

    cout << res << endl;
}