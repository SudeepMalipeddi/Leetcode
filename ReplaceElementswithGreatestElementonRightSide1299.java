/*
 * Problem: 1299. Replace Elements with Greatest Element on Right Side
 *
 * Problem Statement:
 * Given an array arr, replace every element in that array with the greatest element among the elements 
 * to its right, and replace the last element with -1.
 *
 * Intuition:
 * A naive approach checks every element to the right for every index, leading to redundant work.
 * By scanning from right to left, we can track the maximum value seen so far (suffix maximum)
 * in a single pass, allowing us to update each element in O(1) time.
 *
 * Approach:
 * 1. Brute Force: Use nested loops. For each index i, scan i+1 to n-1 to find the maximum value.
 * 2. Optimal: Start from the end of the array. Initialize a 'max' variable to -1.
 * 3. Iterate backwards: save the current element's value, replace the element with the current 'max', 
 *    then update 'max' if the saved value is larger than the current 'max'.
 *
 * Time Complexity:
 * - Brute Force: O(N^2) because for each element, we potentially scan the rest of the array.
 * - Optimal: O(N) because we perform a single linear pass through the array.
 *
 * Space Complexity:
 * - O(1) as the transformation is performed in-place, requiring only a few auxiliary variables.
 *
 * Edge Cases:
 * - Array of length 1: The single element should be replaced with -1.
 * - Array with all identical elements: Max remains the same until the end.
 * - Array sorted in descending order: Max updates at every step.
 *
 * Dry Run:
 * Input: [17, 18, 5, 4, 6, 1]
 * 1. i=5: temp=1, arr[5]=-1, max=max(-1, 1)=1. Array: [17, 18, 5, 4, 6, -1]
 * 2. i=4: temp=6, arr[4]=1, max=max(1, 6)=6. Array: [17, 18, 5, 4, 1, -1]
 * 3. i=3: temp=4, arr[3]=6, max=max(6, 4)=6. Array: [17, 18, 5, 6, 1, -1]
 * 4. i=2: temp=5, arr[2]=6, max=max(6, 5)=6. Array: [17, 18, 6, 6, 1, -1]
 * 5. i=1: temp=18, arr[1]=6, max=max(6, 18)=18. Array: [17, 6, 6, 6, 1, -1]
 * 6. i=0: temp=17, arr[0]=18, max=max(18, 17)=18. Array: [18, 6, 6, 6, 1, -1]
 *
 * Correctness Check:
 * - The optimal method is correct.
 * - BUG ALERT: The brute force method (replaceElements) fails for arrays of length 1 because the 
 *   loop condition (i < arr.length - 1) is never met, leaving the array unchanged instead of returning [-1].
 */
public class ReplaceElementswithGreatestElementonRightSide1299 {
    
    public int[] replaceElements(int[] arr){
        
        // Iterate through each element except the last one
        for(int i = 0; i<arr.length-1; i++){
            int max = 0;
            
            // For every element, find the maximum of all elements to its right
            for(int j = i+1; j<arr.length; j++){
                
                if(arr[j] > max){
                    max = arr[j];
                }
            }
            arr[i] = max;
            
            // On the penultimate element, ensure the last element is set to -1
            if(i == arr.length-2){
                arr[arr.length-1] = -1;
            }
        }
        return arr;
    }

    
    public int[] replaceElementsOptimal(int[] arr){
        // Initialize max as -1 because the rightmost element has no elements to its right
        int max = -1;
        
        // Maintain running maximum to the right while scanning backward.
        // This allows us to update the array in a single O(N) pass.
        for(int i = arr.length-1; i>=0; i--){
            // Store the current value before it is overwritten
            int temp = arr[i];
            
            // Replace current element with the maximum found to its right
            arr[i] = max;
            
            // Update the running maximum for the next element to the left
            if(temp > max){
                max = temp;
            }
        }
        return arr;
    }
}
