class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int ans = 0;
        int max = Integer.MIN_VALUE;
        for (int cost : costs)
            max = Math.max(max, cost);

        int[] count = new int[max + 1];
        for (int cost : costs)
            count[cost]++;

        for (int cost = 1; cost <= max; cost++) {

            while (count[cost] > 0 && coins >= cost) {
                coins-= cost;
                ans++;
                count[cost]--;
            }

        }
        return ans;
    }
}