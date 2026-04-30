/*
 * Problem: 2094. Finding 3-Digit Even Numbers
 *
 * Problem Statement:
 * Given an integer array 'digits', find all unique 3-digit even numbers that can be formed 
 * using the digits in the array. The numbers must not have leading zeros and must be even.
 *
 * Intuition:
 * Instead of generating all permutations of the input array (which could be very large), 
 * it is more efficient to iterate through all possible 3-digit even numbers (100 to 998) 
 * and check if the available digits in the input array can form that specific number.
 *
 * Approach:
 * 1. Count the frequency of each digit (0-9) present in the input 'digits' array.
 * 2. Use a triple-nested loop to simulate the hundreds (i), tens (j), and units (k) places.
 * 3. The hundreds digit 'i' starts from 1 to 9 (ensuring no leading zeros).
 * 4. The tens digit 'j' ranges from 0 to 9.
 * 5. The units digit 'k' ranges from 0 to 8, incrementing by 2 (ensuring the number is even).
 * 6. For every combination (i, j, k), verify if the frequency map has enough of each digit.
 * 7. If valid, construct the number and add it to a Set to handle uniqueness.
 * 8. Convert the Set to a sorted array for the final result.
 *
 * Time Complexity: O(N + 10^3) where N is the length of the input array. 
 * We iterate through the array once to count digits, then perform a constant 450 checks (9 * 10 * 5).
 *
 * Space Complexity: O(1) auxiliary space. The frequency array is fixed size (10), 
 * and the result set stores at most 450 integers.
 *
 * Edge Cases:
 * - Input array with fewer than 3 digits: The loops will run, but the frequency check will fail.
 * - Input array with no even digits: The inner loop (k) will never satisfy the frequency check.
 * - Input array with only zeros: The outer loop (i) starts at 1, so no numbers will be formed.
 *
 * Dry Run:
 * digits = [2, 1, 3, 0]
 * count = {0:1, 1:1, 2:1, 3:1}
 * i=1, j=0, k=2: tempcount[1]=0, tempcount[0]=0, tempcount[2]=0. All >= 0. Add 102.
 * i=1, j=0, k=4: tempcount[1]=0, tempcount[0]=0, tempcount[4]=-1. Invalid.
 *
 * Correctness Check:
 * The solution is correct. Using a Set and then sorting ensures the output meets the 
 * "unique and sorted" requirement. Cloning the array inside the loop is safe but 
 * slightly less efficient than manual backtracking (decrementing and then incrementing).
 */
import java.util.Set;
import java.util.HashSet;

public class Finding3DigitEvenNumbers2094 {
    public static int[] findEvenNumbers(int[] digits) {
        Set<Integer> res = new HashSet<>();
        // Frequency array to store the count of each digit (0-9) available in the input.
        int[] count = new int[10];
        
        // Populate the frequency map.
        for (int digit : digits) {
            count[digit]++;
        }

        // Outer loop: Hundreds digit. Must be 1-9 to avoid leading zeros.
        for (int i = 1; i <= 9; i++) {
            // Middle loop: Tens digit. Can be any digit from 0-9.
            for (int j = 0; j <= 9; j++) {
                // Inner loop: Units digit. Must be even (0, 2, 4, 6, 8) for the number to be even.
                for (int k = 0; k <= 8; k += 2) {
                    // Clone the frequency map to simulate picking digits for the current number.
                    int[] tempcount = count.clone();
                    
                    // Attempt to use one instance of digit i, j, and k.
                    tempcount[i]--;
                    tempcount[j]--;
                    tempcount[k]--;
                    
                    // If all counts remain non-negative, we had enough of these digits in the original array.
                    if (tempcount[i] >= 0 && tempcount[j] >= 0 && tempcount[k] >= 0) {
                        int num = i * 100 + j * 10 + k;
                        res.add(num);
                    }
                }
            }
        }
        
        // Convert the Set of unique numbers into a sorted primitive array.
        return res.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    public static void main(String[] args) {
        int[] digits = { 2, 1, 3, 0 };
        findEvenNumbers(digits);
        // Output: [102, 120, 132, 210, 230, 302, 320]
    }
}
