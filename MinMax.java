/*
 * Problem Statement: Find both minimum and maximum element in an array.
 * Intuition: Keep track of current min and max while scanning values.
 * Approach: File contains two approaches: first sorts then picks ends; second does linear scan updates.
 * Time Complexity: Sort-based method is O(n log n); linear method is O(n).
 * Space Complexity: O(1) auxiliary space (ignoring sort internals).
 * Edge Cases handled: n == 1; first two elements initialization; general positive/negative/mixed values.
 * Dry Run: [1000, 11, 445, 1, 330, 3000] -> linear pass updates min to 1 and max to 3000.
 * LeetCode matching/assumption: Generic min-max array problem (not tied to a single LeetCode ID).
 * Correctness Check: Important caveat: first approach sorts the array, which mismatches "minimum comparisons" intent and mutates input as a side effect; second linear approach aligns better with prompt goals.
 */

// Given an array of size N. The task is to find the maximum and the
//minimum element of the array using the minimum number of comparisons.
// Input: arr[] = {3, 5, 4, 1, 9}
// Output: Minimum element is: 1
//               Maximum element is: 9
import java.util.Arrays;

class Pair {
	public int min;
	public int max;
}

class Main {
	static Pair getMinMax(int arr[], int n) {
		Pair minmax = new Pair();
		// Sort-based shortcut: mutates original array and uses more than minimum comparisons.
		Arrays.sort(arr);
		minmax.min = arr[0];
		minmax.max = arr[n - 1];
		return minmax;
	}

	public static void main(String[] args) {
		int arr[] = { 1000, 11, 445, 1, 330, 3000 };
		int arr_size = arr.length;
		Pair minmax = getMinMax(arr, arr_size);
		System.out.println("Minimum element is " + minmax.min);
		System.out.println("Maximum element is " + minmax.max);   
	}
}
// Using linear search approach
class GFG {
    static Pair getMinMax(int arr[], int n){
        Pair minmax = new Pair();
        int i;
        // Single element: both min and max are the same value.
        if(n==1){
            minmax.max = arr[0];
            minmax.min = arr[0];
            return minmax;
        }
        // Initialize min/max with first two elements to start comparisons from index 2.
        if(arr[0]>arr[1]){
            minmax.max=arr[0];
            minmax.min=arr[1];
        } 
        else{
            minmax.max=arr[1];
            minmax.min=arr[0];
        }
        // Update running min and max in one pass.
        for(i=2;i<n;i++){
            if(arr[i]>minmax.max){
                minmax.max=arr[i];
            }
            else if(arr[i]<minmax.min){
                minmax.min=arr[i];
            }
        }
        return minmax;
    }
}
