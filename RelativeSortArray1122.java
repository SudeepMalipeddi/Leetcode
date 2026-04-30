/*
 * Problem: 1122. Relative Sort Array
 *
 * Problem Statement:
 * Given two arrays arr1 and arr2, sort the elements of arr1 such that the relative ordering
 * of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should
 * be placed at the end of arr1 in ascending order.
 *
 * Intuition:
 * The problem requires a custom sort order followed by a natural sort order. Since the
 * values are within a small range (0-1000), we can use a frequency array (Counting Sort)
 * to track occurrences. This allows us to reconstruct the array by iterating through
 * arr2 first, then iterating through the remaining counts in ascending order.
 *
 * Approach:
 * 1. Count occurrences of each number in arr1 using a frequency array (or Map).
 * 2. Iterate through arr2, and for each number, add it to the result array as many times
 *    as it appeared in arr1, then clear its count.
 * 3. Iterate through the frequency array from 0 to 1000. For any non-zero counts,
 *    add those numbers to the result array in ascending order.
 *
 * Time Complexity: O(N + M + K) where N is arr1.length, M is arr2.length, and K is the range of values (1001).
 * Space Complexity: O(K) to store the frequency counts.
 *
 * Edge Cases:
 * - arr2 is a subset of arr1: Some elements in arr1 will need ascending sort at the end.
 * - All elements in arr1 are in arr2: The entire array follows arr2's order.
 * - arr1 contains duplicates: Handled by frequency counting.
 *
 * Dry Run:
 * arr1 = [2, 1, 2, 5, 3], arr2 = [2, 1]
 * 1. Counts: {1:1, 2:2, 3:1, 5:1}
 * 2. Process arr2:
 *    - '2': add twice -> [2, 2]
 *    - '1': add once -> [2, 2, 1]
 * 3. Process remaining (3, 5):
 *    - '3': add once -> [2, 2, 1, 3]
 *    - '5': add once -> [2, 2, 1, 3, 5]
 *
 * Correctness Check:
 * - relativeSortArray2 is correct and efficient for the given constraints.
 * - The main method logic is flawed: it only adds leftover keys once regardless of frequency,
 *   and the 'sub' array may contain default zeros that interfere with sorting if the number
 *   of unique leftover keys is less than the number of leftover elements.
 */
import java.util.*;

public class RelativeSortArray1122 {
    
    
    
    
    

    

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr1 = { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 };
        int[] arr2 = { 2, 1, 4, 3, 9, 6 };
        
        // Step 1: Build a frequency map of all elements in arr1.
        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }
        System.out.println(map);
        int[] res = new int[arr1.length];
        int j = 0;
        
        // Step 2: Iterate through arr2 to maintain its relative order in the result.
        for (int i : arr2) {
            
            // Place the element 'i' into the result as many times as it occurred in arr1.
            while (map.getOrDefault(i, 0) > 0) {
                map.put(i, map.getOrDefault(i, 1) - 1);
                
                // Once all occurrences of 'i' are processed, remove it from the map.
                if (map.get(i) == 0) {
                    map.remove(i);
                }
                res[j++] = i;
            }
        }
        // Step 3: Collect elements that were not present in arr2.
        int[] sub = new int[arr1.length - j];
        int indx = 0;
        
        // Note: This logic only captures unique keys once, losing frequency data for leftovers.
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            sub[indx++] = m.getKey();
        }
        // Sort the remaining elements in ascending order as per requirements.
        Arrays.sort(sub);

        
        indx = 0;
        
        // Append the sorted leftover elements to the end of the result array.
        while (j < arr1.length) {
            res[j++] = sub[indx++];

        }
        
        for (int i : res) {
            System.out.print(i + " ");
        }
        
    }

    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        // Use a fixed-size array for counting since values are constrained between 0 and 1000.
        int[] count = new int[1001];
        
        for (int i : arr1) {
            count[i]++;
        }
        int i = 0;
        
        // First pass: Reconstruct arr1 based on the relative order in arr2.
        for (int j : arr2) {
            
            // Emit each arr2 value according to its frequency in arr1.
            while (count[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        
        // Second pass: Fill the rest of arr1 with remaining elements in ascending order.
        // Since we iterate from index 0 to 1000, the elements are naturally sorted.
        for (int j = 0; j < count.length; j++) {
            
            while (count[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        return arr1;
    }
}
