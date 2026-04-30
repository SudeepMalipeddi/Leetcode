/*
 * Problem: 1652. Defuse the Bomb
 *
 * Problem Statement:
 * You have a circular array 'code' and a key 'k'. To decrypt the code:
 * - If k > 0, replace the i-th number with the sum of the next k numbers.
 * - If k < 0, replace the i-th number with the sum of the previous |k| numbers.
 * - If k = 0, replace the i-th number with 0.
 *
 * Intuition:
 * The "circular" nature of the array means that after the last element, we wrap around to the first. 
 * This behavior is mathematically modeled using the modulo operator (%). By calculating 
 * indices as (current + offset) % length, we can traverse the array infinitely in a circle.
 *
 * Approach:
 * 1. Initialize a result array of the same length, filled with zeros (default in Java).
 * 2. Handle the k = 0 case immediately by returning the zeroed array.
 * 3. For k > 0, use a nested loop to sum the next k elements for each index i.
 * 4. For k < 0, convert k to positive and sum the previous k elements using (i - m + n) % n 
 *    to ensure the index stays within positive bounds before applying modulo.
 *
 * Time Complexity: O(n * |k|) where n is the length of the code. For each element, we perform |k| additions.
 * Space Complexity: O(n) to store the decrypted result array.
 *
 * Edge Cases:
 * - k = 0: All elements become 0.
 * - |k| = n - 1: Summing almost the entire array for each element.
 * - n = 1: Circular logic still applies (index always 0).
 *
 * Dry Run:
 * code = [5, 7, 1, 4], k = 3
 * i = 0: sum = code[1]+code[2]+code[3] = 7+1+4 = 12
 * i = 1: sum = code[2]+code[3]+code[0] = 1+4+5 = 10
 * i = 2: sum = code[3]+code[0]+code[1] = 4+5+7 = 16
 * i = 3: sum = code[0]+code[1]+code[2] = 5+7+1 = 13
 * Result: [12, 10, 16, 13]
 *
 * Correctness Check:
 * The solution correctly handles the circular wrap-around in both directions. 
 * Note: While O(n) is possible via a sliding window, this O(n*k) approach is 
 * well within limits given the constraints (n, k <= 100).
 */
public class DefusetheBomb1652 {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];
        
        // If k is 0, the bomb is defused by replacing everything with 0.
        // Since int arrays in Java default to 0, we can return immediately.
        if (k == 0) {
            return res;
        } else if (k > 0) {
            // For each position i, look forward k steps.
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int m = 1; m <= k; m++) {
                    // Use modulo n to wrap around to the start of the array if index exceeds n-1.
                    sum += code[(i + m) % n];
                }
                res[i] = sum;
            }
            return res;
        } else {
            // For k < 0, we look backward. We work with the absolute value of k.
            k = -k;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int m = 1; m <= k; m++) {
                    // To wrap backward: subtract m, then add n before modulo.
                    // Adding n ensures the numerator is positive, as (-1 % 4) is -1 in Java, not 3.
                    sum += code[(i - m + n) % n];
                }
                res[i] = sum;
            }
            return res;
        }
    }
}
