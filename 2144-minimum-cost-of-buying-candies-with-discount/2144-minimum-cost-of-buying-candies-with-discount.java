class Solution {
    public int minimumCost(int[] cost) {
        int mincost = 0;
        Arrays.sort(cost);
        int n = cost.length;
        if (n == 1)
            return cost[0];

        //atleast 2 items 
        int r = n - 1;

        while (r >= 0) {
            mincost += cost[r];
            if (r - 1 >= 0)
                mincost += cost[r - 1];

            r = r - 3;
        }
        return mincost;
    }
}