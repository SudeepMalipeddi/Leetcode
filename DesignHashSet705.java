/*
 * Problem: 705. Design HashSet
 *
 * Problem Statement:
 * Design a HashSet without using any built-in hash table libraries. 
 * The implementation must support add(key), remove(key), and contains(key) operations 
 * for integer keys in the range [0, 1,000,000].
 *
 * Intuition:
 * Since the range of possible keys is relatively small and fixed (0 to 10^6), we can use 
 * a Direct-Address Table. By using a boolean array where the index represents the key, 
 * we achieve the fastest possible lookup, insertion, and deletion times.
 *
 * Approach:
 * 1. Initialize a boolean array of size 1,000,001 (to include the index 1,000,000).
 * 2. add(key): Set the value at index 'key' to true.
 * 3. remove(key): Set the value at index 'key' to false.
 * 4. contains(key): Return the boolean value stored at index 'key'.
 *
 * Time Complexity: O(1) for all operations (add, remove, contains) because array indexing is constant time.
 * Space Complexity: O(M) where M is the maximum possible key value (1,000,001), as we pre-allocate the entire range.
 *
 * Edge Cases:
 * - Key = 0: The smallest possible index.
 * - Key = 1,000,000: The largest possible index.
 * - Adding an existing key: The state remains 'true' (idempotent).
 * - Removing a non-existent key: The state remains 'false'.
 *
 * Dry Run:
 * 1. add(5): arr[5] becomes true.
 * 2. contains(5): returns arr[5] -> true.
 * 3. contains(10): returns arr[10] -> false.
 * 4. remove(5): arr[5] becomes false.
 * 5. contains(5): returns arr[5] -> false.
 *
 * Correctness Check:
 * The solution is correct for the given constraints. The use of a boolean array is 
 * memory-intensive but provides optimal time complexity.
 */
import java.util.*;

public class DesignHashSet705 {
    // Direct-address table: The index represents the key itself.
    // Size 1,000,001 handles the inclusive range [0, 1,000,000].
    public boolean[] arr = new boolean[1000001];

    public DesignHashSet705() {
        // Explicitly initialize the array with false (though Java does this by default for booleans).
        Arrays.fill(arr, false);
    }

    public void add(int key) {
        // If the key is not already present, mark its corresponding index as true.
        if (!arr[key]) {
            arr[key] = true;
        }
    }

    public void remove(int key) {
        // Simply set the index to false. If it was already false, no change occurs.
        arr[key] = false;
    }

    public boolean contains(int key) {
        // Direct O(1) lookup to check if the key exists in the set.
        return arr[key];
    }

}
