/*
 * Problem: 1352. Product of the Last K Numbers
 *
 * Problem Statement:
 * Design a data structure that supports adding integers to a stream and retrieving the product
 * of the last k integers added. The operations must be efficient (O(1) time).
 *
 * Intuition:
 * This problem is solved using a "Prefix Product" array, similar to the Prefix Sum technique.
 * The product of elements in a range [i, j] can be calculated as PrefixProduct[j] / PrefixProduct[i-1].
 * The main challenge is handling zeros, as they invalidate the prefix product chain (division by zero).
 *
 * Approach:
 * 1. Maintain a list of prefix products.
 * 2. When a 0 is added, the entire prefix history is invalidated because any product involving 
 *    that zero will be 0. We clear the list and reset the running product.
 * 3. When a non-zero number is added, we multiply it by the current running product and 
 *    store the result in the list.
 * 4. To get the product of the last k numbers:
 *    - If k is greater than the number of elements stored since the last zero, the product 
 *      must include a zero, so we return 0.
 *    - If k equals the number of elements since the last zero, the product is the last 
 *      prefix product in the list.
 *    - Otherwise, we divide the latest prefix product by the prefix product that existed 
 *      just before the last k elements were added.
 *
 * Time Complexity:
 * - add(num): O(1) amortized. ArrayList addition is O(1), and clearing the list is O(1) 
 *   (re-assigning the reference).
 * - getProduct(k): O(1) as it only involves list indexing and a single division.
 *
 * Space Complexity:
 * - O(N) where N is the number of elements added, to store the prefix products.
 *
 * Edge Cases:
 * - Adding 0: Resets the prefix product list.
 * - k equals the size of the current prefix list: Returns the last element directly.
 * - k exceeds the size of the current prefix list: Returns 0 (implies a zero was encountered).
 *
 * Dry Run:
 * add(3) -> list=[3], prod=3
 * add(4) -> list=[3, 12], prod=12
 * add(2) -> list=[3, 12, 24], prod=24
 * getProduct(2) -> list.size()=3, k=2. 24 / list.get(3-1-2) = 24 / list.get(0) = 24 / 3 = 8.
 * add(0) -> list=[], prod=1 (Reset)
 * add(5) -> list=[5], prod=5
 * getProduct(2) -> list.size()=1, k=2. 1 < 2, return 0.
 *
 * Correctness Check:
 * The solution correctly handles the zero-reset logic and uses the prefix product formula 
 * to achieve O(1) retrieval.
 */
import java.util.*;

public class ProductoftheLastKNumbers1352 {
    // Stores the cumulative product of numbers added since the last zero.
    ArrayList<Integer> list = new ArrayList<>();
    // Running product tracker.
    int prod = 1;

    public void add(int num) {
        
        // Zero resets prefix history because any crossing product becomes zero.
        if (num == 0) {
            // We discard the history because we cannot divide by zero in the prefix formula.
            // Any getProduct(k) call that would have included this zero will be handled by size check.
            list = new ArrayList<>();
            prod = 1;
            return;
        }
        prod = prod * num;
        
        list.add(prod);
    }

    public int getProduct(int k) {
        
        // If the number of elements since the last zero is less than k, 
        // it means a zero was part of the last k numbers.
        if (list.size() < k)
            return 0;
        int ans = list.get(list.size() - 1);
        
        // If k covers exactly all elements since the last zero, the last prefix is the answer.
        if (list.size() == k)
            return ans;
        // Divide two prefixes to isolate product of last k values.
        // Formula: Product(last k) = PrefixProduct(total) / PrefixProduct(total - k)
        // The index (size - 1 - k) points to the prefix product just before the window of k.
        return ans / list.get(list.size() - 1 - k);
    }
}
