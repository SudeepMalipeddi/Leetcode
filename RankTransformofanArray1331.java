/*
 * Problem: 1331. Rank Transform of an Array
 *
 * Problem Statement:
 * Given an array of integers, replace each element with its rank. The rank represents
 * the relative size of the element (1 for the smallest, 2 for the next smallest, etc.).
 * Identical elements must have the same rank, and the next rank should be the next consecutive integer.
 *
 * Intuition:
 * To assign ranks, we need to see the elements in sorted order. By sorting a copy of the array,
 * we can iterate through the values from smallest to largest and assign incremental ranks
 * only to new (unique) values we encounter. A HashMap allows us to store these ranks and 
 * retrieve them efficiently when updating the original array.
 *
 * Approach:
 * 1. Create a copy of the input array to avoid losing the original order.
 * 2. Sort the copied array in ascending order.
 * 3. Use a HashMap to map each unique number to its rank (starting from 1).
 * 4. Iterate through the original array and replace each value with its corresponding rank from the map.
 *
 * Time Complexity: O(N log N) where N is the length of the array. This is dominated by the sorting step.
 * Space Complexity: O(N) to store the copied array and the HashMap containing up to N entries.
 *
 * Edge Cases:
 * - Empty array: Should return an empty array.
 * - Array with all identical elements: All elements should get rank 1.
 * - Array already sorted: Ranks will match the sorted order.
 *
 * Dry Run:
 * Input: [40, 10, 20, 20]
 * 1. Copy and Sort: [10, 20, 20, 40]
 * 2. Map Ranks:
 *    - 10 -> Rank 1
 *    - 20 -> Rank 2
 *    - 20 -> Already in map, skip incrementing rank
 *    - 40 -> Rank 3
 * 3. Transform Original: [40->3, 10->1, 20->2, 20->2] -> [3, 1, 2, 2]
 *
 * Correctness Check:
 * The solution correctly handles duplicates by checking if the key exists in the map before
 * incrementing the rank counter. The logic is sound.
 */
import java.util.Arrays;
import java.util.HashMap;

public class RankTransformofanArray1331 {
    public int[] arrayRankTransform(int[] arr) {
        // Map to store the unique value as key and its calculated rank as value.
        HashMap<Integer, Integer> map = new HashMap<>();
        // Create a deep copy to sort without losing the original element positions.
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        // Sorting allows us to process elements in increasing order of magnitude.
        Arrays.sort(arr1);
        // Counter to track the current rank to be assigned.
        int count = 1;
        
        // Iterate through the sorted array to assign ranks sequentially.
        for (int x : arr1) {
            
            // Duplicate values must reuse the same rank.
            if (map.containsKey(x)) {
                // If the value is already in the map, it's a duplicate; it keeps its existing rank.
                map.put(x, map.get(x));
            } else {
                // Assign the current rank to the new unique value.
                map.put(x, count);
                // Increment rank for the next unique value encountered.
                count++;
            }
        }
        
        // Final pass: replace original values with their ranks using the map.
        for (int i = 0; i < arr.length; i++) {
            // Look up the rank for the original value and update the array in-place.
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
}
