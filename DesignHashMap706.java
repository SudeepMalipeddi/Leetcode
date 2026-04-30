/*
 * Problem: 706. Design HashMap
 *
 * Problem Statement:
 * Design a HashMap without using any built-in hash table libraries. The map must support
 * the operations put(key, value), get(key), and remove(key) for integer keys and values.
 *
 * Intuition:
 * Given the constraints (keys range from 0 to 1,000,000), the most efficient approach
 * is a Direct Address Table. By using an array where the index corresponds directly to the key,
 * we achieve O(1) time complexity for all operations by trading off space.
 *
 * Approach:
 * 1. Initialize an integer array of size 1,000,001 to cover the entire range of possible keys.
 * 2. Fill the array with -1 to represent the absence of a mapping.
 * 3. For 'put', store the value at the index corresponding to the key.
 * 4. For 'get', retrieve the value at the index corresponding to the key.
 * 5. For 'remove', set the value at the index corresponding to the key back to -1.
 *
 * Time Complexity:
 * O(1) for put, get, and remove operations, as array indexing is a constant-time operation.
 *
 * Space Complexity:
 * O(N) where N is the maximum possible key value (1,000,000). This allocates a fixed
 * amount of memory regardless of the number of elements actually stored.
 *
 * Edge Cases:
 * - Key 0: Handled by array index 0.
 * - Key 1,000,000: Handled by array index 1,000,000 (requires size 1,000,001).
 * - Updating an existing key: The put operation naturally overwrites the existing value.
 * - Removing a non-existent key: Setting the value to -1 is idempotent.
 *
 * Dry Run:
 * 1. MyHashMap() -> data array initialized with -1s.
 * 2. put(5, 100) -> data[5] = 100.
 * 3. get(5) -> returns 100.
 * 4. get(10) -> returns -1.
 * 5. remove(5) -> data[5] = -1.
 *
 * Correctness Check:
 * The solution is correct for the specified constraints. It avoids collisions by
 * allocating a unique slot for every possible key.
 */
// Design a HashMap without using any built-in hash table libraries.

// Implement the MyHashMap class:

// MyHashMap() initializes the object with an empty map.
// void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
// int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
// void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
import java.util.Arrays;

public class DesignHashMap706 {
    // Direct-address table where the index represents the key and the value represents the mapped value.
    int[] data;

    public DesignHashMap706() {
        // Size 1,000,001 is used to accommodate keys from 0 up to 1,000,000 inclusive.
        data = new int[1000001];
        // Initialize all slots to -1 to indicate that no key-value pairs are present.
        Arrays.fill(data, -1);
    }

    public void put(int key, int value) {
        // Direct indexing: the key is the index. This handles both insertion and updates.
        data[key] = value;
    }

    public int get(int key) {
        // Return the value stored at the index; returns -1 if the key was never put or was removed.
        return data[key];
    }

    public void remove(int key) {
        // Reset the value at the index to -1 to signify the removal of the mapping.
        data[key] = -1;
    }
}
/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
