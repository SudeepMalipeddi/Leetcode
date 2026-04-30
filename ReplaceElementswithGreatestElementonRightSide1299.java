/*
Problem Statement:
- Replace each element with greatest element to its right; last element becomes -1.

Intuition:
- Right-to-left traversal can maintain suffix maximum incrementally.

Approach:
- File includes brute-force nested-loop method and optimal reverse scan method.
- Improvement idea: use replaceElementsOptimal as the default implementation for linear runtime.

Time Complexity:
- Brute O(n^2); optimal method O(n).

Space Complexity:
- O(1).

Edge Cases:
- Single-element array becomes [-1].

Dry Run:
- [17,18,5,4] -> [18,5,4,-1].
*/
public class ReplaceElementswithGreatestElementonRightSide1299 {
    
    public int[] replaceElements(int[] arr){
        
        for(int i = 0; i<arr.length-1; i++){
            int max = 0;
            
            for(int j = i+1; j<arr.length; j++){
                
                if(arr[j] > max){
                    max = arr[j];
                }
            }
            arr[i] = max;
            
            if(i == arr.length-2){
                arr[arr.length-1] = -1;
            }
        }
        return arr;
    }

    
    public int[] replaceElementsOptimal(int[] arr){
        int max = -1;
        
        // Maintain running maximum to the right while scanning backward.
        for(int i = arr.length-1; i>=0; i--){
            int temp = arr[i];
            arr[i] = max;
            
            if(temp > max){
                max = temp;
            }
        }
        return arr;
    }
}
