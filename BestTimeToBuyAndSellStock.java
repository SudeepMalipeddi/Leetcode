/*
 * Problem: LeetCode 121. Best Time to Buy and Sell Stock
 *
 * Problem Statement:
 * Given an array prices where prices[i] is the price on day i, choose one day to
 * buy and a later day to sell to maximize profit. Return the max profit (0 if none).
 *
 * Intuition:
 * The best profit at each day depends on the lowest price seen so far (best buy)
 * and the profit if sold today.
 *
 * Approach:
 * 1. Track the minimum price seen so far (buy).
 * 2. For each price, compute profit = price - buy and update max profit.
 *
 * Time Complexity: O(n) because each price is processed once.
 * Space Complexity: O(1).
 *
 * Edge Cases handled:
 * - Monotonically decreasing prices (profit remains 0).
 * - Single-day input.
 *
 * Dry Run:
 * prices = [7,1,5,3,6,4]
 * buy=7, profit=0; buy=1; profit=max(0,5-1)=4; profit=max(4,6-1)=5 -> answer 5
 *
 * Correctness Check:
 * Tracking the minimum price so far guarantees the best earlier buy for any sell day.
 *
 * LeetCode Match:
 * LeetCode 121 - "Best Time to Buy and Sell Stock".
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        // initialize buy and sell
        int buy = Integer.MAX_VALUE;
        // sell is 0 because we can't sell before we buy
        int sell = 0;
        // loop through prices
        for (int i = 0; i < prices.length; i++) {
            // buy is the minimum of buy and prices[i]
            buy = Math.min(buy, prices[i]);
            // sell is the maximum of sell and prices[i]-buy
            // this is because we want to maximize profit
            // and we can't sell before we buy
            sell = Math.max(sell, prices[i] - buy);
        }
        // return sell
        return sell;
    }
}
