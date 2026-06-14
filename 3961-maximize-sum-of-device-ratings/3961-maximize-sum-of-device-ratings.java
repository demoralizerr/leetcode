class Solution {
    public long maxRatings(int[][] units) {
        long sumSecond = 0;
        long globalMin = Long.MAX_VALUE;
        int minSecond = Integer.MAX_VALUE;

        if (units[0].length == 1) {
            long ans = 0;
            for (int[] row : units) {
                ans += row[0];
            }
            return ans;
        }

        for (int[] row : units) {
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;

            for (int x : row) {
                if (x < min1) {
                    min2 = min1;
                    min1 = x;
                } else if (x < min2) {
                    min2 = x;
                }
            }

            globalMin = Math.min(globalMin, min1);
            minSecond = Math.min(minSecond, min2);
            sumSecond += min2;
        }

        return sumSecond - minSecond + globalMin;
    }
}