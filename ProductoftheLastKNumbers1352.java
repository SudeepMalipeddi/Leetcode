/*
Problem Statement:
- Support add(num) and getProduct(k) for last k numbers in stream.

Intuition:
- Prefix products let range product be lastPrefix / prefixBeforeRange.

Approach:
- Reset prefix list when zero arrives; otherwise append cumulative product.

Time Complexity:
- add O(1), getProduct O(1).

Space Complexity:
- O(n) for stored prefixes since last zero.

Edge Cases:
- If k exceeds stored count since last zero, product is 0.

Dry Run:
- Add 3,4,2 => prefixes [3,12,24], getProduct(2)=24/3=8.
*/
import java.util.*;

public class ProductoftheLastKNumbers1352 {
    ArrayList<Integer> list = new ArrayList<>();
    int prod = 1;

    public void add(int num) {
        
        // Zero resets prefix history because any crossing product becomes zero.
        if (num == 0) {
            list = new ArrayList<>();
            prod = 1;
            return;
        }
        prod = prod * num;
        
        list.add(prod);
    }

    public int getProduct(int k) {
        
        if (list.size() < k)
            return 0;
        int ans = list.get(list.size() - 1);
        
        if (list.size() == k)
            return ans;
        // Divide two prefixes to isolate product of last k values.
        return ans / list.get(list.size() - 1 - k);
    }
}
