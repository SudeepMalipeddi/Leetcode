public class Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        // initialize buy and sell
        int buy = Integer.MAX_VALUE;
        // sell is 0 because we can't sell before we buy
        int sell = 0;
        // loop through prices
        for(int i = 0; i<prices.length; i++){
            // buy is the minimum of buy and prices[i]
            buy = Math.min(buy,prices[i]);
            // sell is the maximum of sell and prices[i]-buy
            // this is because we want to maximize profit
            // and we can't sell before we buy
            sell = Math.max(sell, prices[i]-buy);
        }        
        // return sell
        return sell;
    }
}
