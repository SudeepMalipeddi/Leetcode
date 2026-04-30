/*
Problem Statement:
- Reverse an array in-place.

Intuition:
- Swap symmetric positions while shrinking from both ends.

Approach:
- Use start/end pointers, swap values, then move inward until pointers cross.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Length 0 or 1 arrays remain unchanged.

Dry Run:
- [1,2,3,4] -> swap (1,4), then (2,3) => [4,3,2,1].
*/
public class ReverseArray {
    static void reversearr(int arr[]) {
        int start = 0;
        int end = arr.length - 1;
        int temp;
        
        // Swap symmetric elements and converge toward the center.
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5 };
        reversearr(arr);
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
