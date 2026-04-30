/*
Problem Statement:
- Assign medal strings to top 3 scores and rank numbers to others preserving original order.

Intuition:
- Sorted score positions determine final rank labels.

Approach:
- Sort cloned scores, binary-search each original score position, map top 3 to medal names.

Time Complexity:
- O(n log n).

Space Complexity:
- O(n) for clone and output.

Edge Cases:
- Exactly three players still get all medals.

Dry Run:
- Scores [10,3,8] -> ranks [Gold,3,Bronze].
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
