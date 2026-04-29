/*
 * Problem: LeetCode 121 - Best Time to Buy and Sell Stock
 * Problem Statement: Given prices where prices[i] is the stock price on day i,
 *   find the maximum profit from one buy and one sell (sell after buy).
 * Intuition: Track the lowest price so far and maximize profit by selling today.
 * Approach:
 *   1) Maintain the minimum price seen so far (buy).
 *   2) For each price, compute profit = price - buy and update max profit.
 * Time Complexity: O(n) for a single pass.
 * Space Complexity: O(1).
 * Edge Cases: Monotonically decreasing prices (profit 0), single day.
 * Dry Run: prices=[7,1,5,3,6,4] -> buy=7, profit=0; buy=1; profit=4 at 5,
 *   profit=5 at 6 => answer 5.
 * Correctness Check: The best sell day for any day uses the smallest price
 *   before it, which this scan always maintains.
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
