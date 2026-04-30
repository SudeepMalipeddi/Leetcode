/*
 * Problem Statement:
 * Each lemonade costs $5. Customers pay in order with bills 5, 10, or 20.
 * Starting with no cash, determine if exact change can be given to every customer.
 *
 * Intuition:
 * Greedy works: keep counts of $5 and $10 bills.
 * For a $20 payment, prefer giving $10+$5 (saves more $5 bills for future $10 customers).
 *
 * Approach:
 * - bill==5: just collect one $5.
 * - bill==10: must give one $5.
 * - bill==20: try ($10+$5) first, otherwise three $5.
 * - If required change is unavailable at any step, return false.
 *
 * Time Complexity (with concrete justification):
 * O(n): one pass over customers, constant work per customer.
 *
 * Space Complexity (with concrete justification):
 * O(1): only two counters (count5, count10) are maintained.
 *
 * Edge Cases handled:
 * - First customer pays 10 or 20 -> immediate false.
 * - Long runs of 20s where $5 bills become insufficient.
 * - Mixed sequences where preserving $5 bills matters.
 *
 * Dry Run (concrete example with state):
 * bills = [5,5,10,20]
 * start count5=0,count10=0
 * 5  -> count5=1
 * 5  -> count5=2
 * 10 -> give one 5 => count5=1,count10=1
 * 20 -> give 10+5  => count5=0,count10=0
 * success -> true
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 860. Assumes bill values are restricted to 5/10/20 as in constraints.
 *
 * Correctness Check:
 * Choosing 10+5 before 5+5+5 is the key greedy invariant to avoid starving future $10 transactions.
 * No code fix made; comments reflect current branch ordering exactly.
 */

public class Lemonade_Change860 {
    public boolean lemonadeChange(int[] bills){
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
                    return false;
                }
            }
            else{
                // For $20, prefer $10+$5 to conserve three $5 bills.
                if(count10 > 0 && count5 > 0){
                    count10--;
                    count5--;
                }
                else if(count5 > 2){
                    // Fallback: three $5 bills if no $10 is available.
                    count5-=3;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
}
