/*
 * Problem: Reverse an Array (In-place)
 *
 * Problem Statement:
 * Given an array of integers, reverse the order of its elements. The reversal 
 * must be done in-place, meaning you should not allocate extra space for another array.
 *
 * Intuition:
 * To reverse an array, the element at the first index must swap with the element at the last index, 
 * the second with the second-to-last, and so on. By using two pointers that move toward 
 * each other from opposite ends, we can efficiently swap all symmetric pairs.
 *
 * Approach:
 * 1. Initialize a 'start' pointer at index 0 and an 'end' pointer at index (length - 1).
 * 2. Enter a loop that continues as long as 'start' is less than 'end'.
 * 3. In each iteration, swap the values at 'start' and 'end' using a temporary variable.
 * 4. Increment 'start' and decrement 'end' to move toward the center of the array.
 *
 * Time Complexity: O(n) where n is the number of elements in the array. We perform n/2 swaps.
 * Space Complexity: O(1) as we only use a constant amount of extra space for pointers and the temp variable.
 *
 * Edge Cases:
 * - Empty array: The loop condition (0 < -1) will be false, and the method returns immediately.
 * - Single element array: The loop condition (0 < 0) will be false, leaving the array unchanged.
 * - Even vs. Odd length: The 'start < end' condition correctly handles both (meeting in the middle or crossing).
 *
 * Dry Run:
 * Input: [1, 2, 3, 4, 5]
 * 1. start=0, end=4: Swap(1, 5) -> [5, 2, 3, 4, 1], start=1, end=3
 * 2. start=1, end=3: Swap(2, 4) -> [5, 4, 3, 2, 1], start=2, end=2
 * 3. start=2, end=2: Loop terminates (2 is not < 2).
 * Result: [5, 4, 3, 2, 1]
 *
 * Correctness Check:
 * The solution is correct. It handles in-place reversal efficiently without auxiliary data structures.
 */
public class ReverseArray {
    static void reversearr(int arr[]) {
        // Initialize pointers at the start and end of the array.
        int start = 0;
        int end = arr.length - 1;
        int temp;
        
        // The loop continues until pointers meet or cross at the center.
        // If start == end (odd length), the middle element is already in its correct position.
        while (start < end) {
            // Standard 3-step swap using a temporary variable to avoid overwriting data.
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            
            // Converge pointers toward the middle.
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5 };
        reversearr(arr);
        
        // Print the modified array to verify the in-place reversal.
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
