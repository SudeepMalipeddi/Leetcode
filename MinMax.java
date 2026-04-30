/*
 * Problem: Find Minimum and Maximum Element in an Array
 *
 * Problem Statement:
 * Given an array of integers, find the smallest and largest values present in the array. 
 * The goal is to achieve this efficiently, ideally with a minimum number of comparisons.
 *
 * Intuition:
 * To find the extremes, we don't need to know the relative order of every element. 
 * By maintaining two variables (min and max) and updating them as we scan the array, 
 * we can identify the boundaries in a single linear pass.
 *
 * Approach:
 * 1. Handle the base case: If the array has only one element, it is both the min and max.
 * 2. Initialization: Compare the first two elements to set the initial min and max.
 * 3. Linear Scan: Iterate from the third element to the end. For each element, 
 *    check if it's greater than the current max or smaller than the current min.
 *
 * Time Complexity: 
 * - Sorting Approach: O(n log n) due to the dual-pivot Quicksort used by Arrays.sort().
 * - Linear Approach: O(n) because we visit each element exactly once.
 *
 * Space Complexity: 
 * - O(1) auxiliary space as we only use a fixed amount of extra space (the Pair object).
 *
 * Edge Cases:
 * - Array with 1 element: min and max should be identical.
 * - Array with 2 elements: requires exactly one comparison.
 * - Sorted or reverse-sorted arrays.
 *
 * Dry Run:
 * Input: [1000, 11, 445, 1]
 * 1. n=4, not 1.
 * 2. Compare arr[0] (1000) and arr[1] (11). 1000 > 11, so max=1000, min=11.
 * 3. i=2: arr[2]=445. Is 445 > 1000? No. Is 445 < 11? No.
 * 4. i=3: arr[3]=1. Is 1 > 1000? No. Is 1 < 11? Yes. Update min=1.
 * Result: min=1, max=1000.
 *
 * Correctness Check:
 * The solution is correct. Note that the sorting approach in the 'Main' class 
 * modifies the input array (side effect) and is less efficient than the linear scan.
 */

import java.util.Arrays;

// Helper class to return two values from a single function call.
class Pair {
	public int min;
	public int max;
}

class Main {
	static Pair getMinMax(int arr[], int n) {
		Pair minmax = new Pair();
		// Sort-based shortcut: mutates original array and uses more than minimum comparisons.
		// Sorting takes O(n log n) time, which is overkill for finding just two values.
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

// Using linear search approach - more efficient than sorting.
class GFG {
    static Pair getMinMax(int arr[], int n){
        Pair minmax = new Pair();
        int i;
        
        // Base Case: If there is only one element, it is both min and max.
        if(n==1){
            minmax.max = arr[0];
            minmax.min = arr[0];
            return minmax;
        }
        
        // Initialize min and max by comparing the first two elements.
        // This reduces the number of comparisons needed inside the loop.
        if(arr[0]>arr[1]){
            minmax.max=arr[0];
            minmax.min=arr[1];
        } 
        else{
            minmax.max=arr[1];
            minmax.min=arr[0];
        }
        
        // Start loop from index 2 since 0 and 1 are already processed.
        for(i=2;i<n;i++){
            // If current element is greater than current max, update max.
            if(arr[i]>minmax.max){
                minmax.max=arr[i];
            }
            // Optimization: If it's the new max, it cannot be the new min, 
            // so we use 'else if' to skip the second check.
            else if(arr[i]<minmax.min){
                minmax.min=arr[i];
            }
        }
        return minmax;
    }
}
