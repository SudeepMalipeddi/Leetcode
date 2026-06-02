/*
 * Problem: LeetCode 506 - Relative Ranks
 *
 * Problem Statement:
 * Given an integer array `score` of unique positive integers representing
 * athletes' scores, return an array where the athletes with the top three scores
 * are assigned "Gold Medal", "Silver Medal", "Bronze Medal" respectively, and
 * all others receive their rank as a string (e.g., "4", "5", ...). The output
 * must preserve the original order of athletes.
 *
 * Intuition:
 * The rank of each athlete is determined by their score's position in the sorted
 * descending order. The highest score gets rank 1 ("Gold Medal"), second highest
 * gets rank 2 ("Silver Medal"), third gets rank 3 ("Bronze Medal"), and so on.
 * Two approaches are provided: sorting + binary search, and selection sort on
 * an index array.
 *
 * Approach:
 *   Method 1 — findRelativeRanks (sort + binary search):
 *   1. Store `n = score.length` and create result array `res` of size n.
 *   2. Clone `score` into `scoreclone` and sort it in ascending order.
 *   3. For each index i from 0 to n-1:
 *      - Use `Arrays.binarySearch(scoreclone, score[i])` to find position `pos`.
 *      - Since `scoreclone` is sorted ascending, `n - 1 - pos` gives the rank
 *        (largest score is at pos n-1, rank 1; smallest at pos 0, rank n).
 *      - If `pos == n-1`: assign `res[i] = "Gold Medal"`.
 *      - Else if `pos == n-2`: assign `res[i] = "Silver Medal"`.
 *      - Else if `pos == n-3`: assign `res[i] = "Bronze Medal"`.
 *      - Else: assign `res[i] = String.valueOf(n - pos)`.
 *   4. Return `res`.
 *
 *   Method 2 — findRelativeRanks1 (selection sort on indices):
 *   1. Store `n = score.length`, create `res` of size n, and `index` array [0..n-1].
 *   2. Sort `index` in descending order of `scores[index[i]]` using nested loops
 *      (selection sort): for each i, find largest remaining and swap.
 *   3. For each i from 0 to n-1:
 *      - The athlete at `index[i]` has rank i+1.
 *      - If `i == 0`: `res[index[i]] = "Gold Medal"`.
 *      - Else if `i == 1`: `res[index[i]] = "Silver Medal"`.
 *      - Else if `i == 2`: `res[index[i]] = "Bronze Medal"`.
 *      - Else: `res[index[i]] = String.valueOf(i + 1)`.
 *   4. Return `res`.
 *
 * Time Complexity: O(n log n) for method 1 (sorting dominates), O(n²) for method 2
 *                  due to selection sort.
 * Space Complexity: O(n) — `scoreclone` array in method 1, `index` array in
 *                   method 2, plus the result array in both.
 *
 * Edge Cases:
 * - n = 1: only Gold Medal assigned.
 * - n = 2: Gold and Silver assigned.
 * - n = 3: all three medals assigned.
 * - All scores are unique (guaranteed by problem), so no ties to handle.
 *
 * Dry Run:
 * Input: score = [10, 3, 8, 5]
 *
 * Method 1 (findRelativeRanks):
 *   scoreclone = [3, 5, 8, 10] (sorted ascending)
 *   i=0, score[0]=10, binarySearch returns pos=3 (n-1) -> "Gold Medal"
 *   i=1, score[1]=3,  binarySearch returns pos=0 -> n-pos=4 -> "4"
 *   i=2, score[2]=8,  binarySearch returns pos=2 (n-2) -> "Silver Medal"
 *   i=3, score[3]=5,  binarySearch returns pos=1 (n-3) -> "Bronze Medal"
 *   Output: ["Gold Medal", "4", "Silver Medal", "Bronze Medal"]
 *
 * Correctness Check:
 * Both methods correctly map each score to its rank by comparing against all
 * other scores. Method 1 is correct because sorting + binary search gives the
 * exact position in O(log n) per element. Method 2 is correct because selection
 * sort arranges indices in descending score order, and the loop index directly
 * corresponds to rank.
 */
import java.util.Arrays;

public class RelativeRanks506 {
    
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] res = new String[n];
        int[] scoreclone = score.clone();
        Arrays.sort(scoreclone);
        
        for (int i = 0; i < n; i++) {
            // Position in sorted scores determines rank label for this athlete.
            int pos = Arrays.binarySearch(scoreclone, score[i]);
            
            if (pos == n - 1) {
                res[i] = "Gold Medal";
            } else if (pos == n - 2) {
                res[i] = "Silver Medal";
            } else if (pos == n - 3) {
                res[i] = "Bronze Medal";
            } else {
                res[i] = String.valueOf(n - pos);
            }
        }
        return res;

    }

    public String[] findRelativeRanks1(int[] scores) {
        int n = scores.length;
        String[] res = new String[n];
        int[] index = new int[n];
        
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            
            for (int j = i + 1; j < n; j++) {
                
                if (scores[index[i]] < scores[index[j]]) {
                    int temp = index[i];
                    index[i] = index[j];
                    index[j] = temp;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            
            if (i == 0) {
                res[index[i]] = "Gold Medal";
            } else if (i == 1) {
                res[index[i]] = "Silver Medal";
            } else if (i == 2) {
                res[index[i]] = "Bronze Medal";
            } else {
                res[index[i]] = String.valueOf(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RelativeRanks506 rr = new RelativeRanks506();
        int[] score = { 5, 4, 3, 2, 1 };
        String[] res = rr.findRelativeRanks(score);
        
        for (String s : res) {
            System.out.println(s);
        }
    }
}
