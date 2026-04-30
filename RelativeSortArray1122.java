/*
Problem Statement:
- Sort arr1 so elements in arr2 appear first in arr2 order, remaining values ascending.

Intuition:
- Frequency counting makes ordered reconstruction straightforward.

Approach:
- relativeSortArray2 uses count[0..1000] for arr2-order fill then leftover ascending fill.
- Correctness concern: main() demonstration fills leftover values once per key, ignoring frequencies >1.

Time Complexity:
- O(n + K), K=1001.

Space Complexity:
- O(K).

Edge Cases:
- Values not in arr2 are appended in sorted order.

Dry Run:
- arr1=[2,3,1], arr2=[2] -> [2,1,3].
*/
import java.util.*;

public class RelativeSortArray1122 {
    
    
    
    
    

    

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr1 = { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 };
        int[] arr2 = { 2, 1, 4, 3, 9, 6 };
        
        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }
        System.out.println(map);
        int[] res = new int[arr1.length];
        int j = 0;
        
        for (int i : arr2) {
            
            while (map.getOrDefault(i, 0) > 0) {
                map.put(i, map.getOrDefault(i, 1) - 1);
                
                if (map.get(i) == 0) {
                    map.remove(i);
                }
                res[j++] = i;
            }
        }
        int[] sub = new int[arr1.length - j];
        int indx = 0;
        
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            sub[indx++] = m.getKey();
        }
        Arrays.sort(sub);

        
        indx = 0;
        
        while (j < arr1.length) {
            res[j++] = sub[indx++];

        }
        
        for (int i : res) {
            System.out.print(i + " ");
        }
        
    }

    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        
        for (int i : arr1) {
            count[i]++;
        }
        int i = 0;
        
        for (int j : arr2) {
            
            // Emit each arr2 value according to its frequency in arr1.
            while (count[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        
        for (int j = 0; j < count.length; j++) {
            
            while (count[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        return arr1;
    }
}
