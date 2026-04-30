/*
Problem Statement:
- Replace each value with its rank in sorted unique order.

Intuition:
- Sorting a clone reveals ascending unique values for rank assignment.

Approach:
- Map each distinct sorted value to incremental rank, then rewrite original array via map.

Time Complexity:
- O(n log n) from sorting.

Space Complexity:
- O(n) for clone and hashmap.

Edge Cases:
- Equal values receive same rank.

Dry Run:
- [40,10,20,20] -> ranks {10:1,20:2,40:3} -> [3,1,2,2].
*/
import java.util.Arrays;
import java.util.HashMap;

public class RankTransformofanArray1331 {
    public int[] arrayRankTransform(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr1);
        int count = 1;
        
        for (int x : arr1) {
            
            // Duplicate values must reuse the same rank.
            if (map.containsKey(x)) {
                map.put(x, map.get(x));
            } else {
                map.put(x, count);
                count++;
            }
        }
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
}
