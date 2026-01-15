class Solution {
    public int maxProfit(int[] prices) {
        int buy = 0;
        int sell = 1;
        int n = prices.length;
        int maxProfit = 0;
        while (sell < n) {
            if (prices[buy] < prices[sell]) {
                int profit = prices[sell] - prices[buy];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                buy = sell;

            }
            sell++;
        }
        return maxProfit;
    }
}