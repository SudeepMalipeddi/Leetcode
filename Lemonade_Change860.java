/*
 * Problem: 860. Lemonade Change
 *
 * Problem Statement:
 * Each lemonade costs $5. Customers stand in a queue to buy one from you and order one at a time.
 * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer so that the net transaction is $5.
 *
 * Intuition:
 * This is a classic Greedy algorithm problem. The $5 bill is the most "flexible" currency because 
 * it can be used to provide change for both $10 and $20 bills. A $10 bill is less flexible as it 
 * can only help provide change for a $20 bill. Therefore, when a customer pays with $20, we should 
 * prioritize giving back a $10 and a $5 bill rather than three $5 bills. This "greedy" choice 
 * preserves our most flexible resource ($5 bills) for future customers who might pay with $10.
 *
 * Approach:
 * 1. Initialize two counters, count5 and count10, to track our current inventory of bills.
 * 2. Iterate through the array of bills provided by customers.
 * 3. If the bill is $5, simply increment count5.
 * 4. If the bill is $10, we must return a $5 bill. If count5 > 0, decrement count5 and increment count10.
 * 5. If the bill is $20, we need $15 in change. We have two options:
 *    a. One $10 bill and one $5 bill (Preferred/Greedy choice).
 *    b. Three $5 bills (Fallback choice).
 * 6. If at any point we cannot provide the required change, return false immediately.
 * 7. If we successfully process all customers, return true.
 *
 * Time Complexity: O(n)
 * We perform a single linear pass through the input array of size n. Each operation inside the loop 
 * (incrementing/decrementing counters) is O(1).
 *
 * Space Complexity: O(1)
 * We only use two integer variables (count5 and count10) to store the state, regardless of the 
 * number of customers.
 *
 * Edge Cases:
 * - The first customer pays with a $10 or $20: Handled by the check for available bills (returns false).
 * - Multiple $20 bills in a row: Tests the greedy logic of preserving $5 bills.
 * - Empty input: The loop won't execute, returning true (though constraints usually specify n >= 1).
 *
 * Dry Run:
 * bills = [5, 5, 10, 20]
 * 1. bill = 5: count5 = 1, count10 = 0.
 * 2. bill = 5: count5 = 2, count10 = 0.
 * 3. bill = 10: Needs $5 change. count5 becomes 1, count10 becomes 1.
 * 4. bill = 20: Needs $15 change. Greedy check: count10 > 0 and count5 > 0? Yes.
 *    count10 becomes 0, count5 becomes 0.
 * Result: true.
 *
 * Correctness Check:
 * The solution is correct because it prioritizes the use of $10 bills when giving change for $20. 
 * Since $10 bills cannot be used to give change for a $10 bill, but $5 bills can, using the $10 
 * first is always the optimal strategy.
 */

public class Lemonade_Change860 {
    public boolean lemonadeChange(int[] bills){
        // We only need to track $5 and $10 bills. 
        // $20 bills are never given out as change, so we don't need a counter for them.
        int count5 = 0;
        int count10 = 0;
        
        // Process customers in arrival order; change decisions are irreversible.
        for(int bill : bills){
            if(bill == 5){
                // No change needed; increase available $5 inventory.
                count5++;
            }
            else if(bill == 10){
                // Must return one $5 as change for a $10 payment.
                if(count5 > 0){
                    count10++;
                    count5--;
                }
                else{
                    // If we don't have a $5 bill, we cannot provide change.
                    return false;
                }
            }
            else{
                // For $20, we need to provide $15 in change.
                // Greedy Choice: Prefer $10 + $5 to conserve the more versatile $5 bills.
                if(count10 > 0 && count5 > 0){
                    count10--;
                    count5--;
                }
                else if(count5 > 2){
                    // Fallback: Use three $5 bills if no $10 is available.
                    count5-=3;
                }
                else{
                    // If neither combination is available, we fail.
                    return false;
                }
            }
        }
        // If we reach the end of the array, all customers received their change.
        return true;
    }
}
