class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }

        int[] scenario1 = {cnt[0], cnt[1], cnt[2]};
        int[] scenario2 = {cnt[0], cnt[2], cnt[1]};

        return check(scenario1) || check(scenario2);
    }

    private boolean check(int[] c) {
        c[1]--;
        if (c[1] < 0) {
            return false;
        }

        int totalMoves = 1 + Math.min(c[1], c[2]) * 2 + c[0];

        if (c[1] > c[2]) {
            c[1]--;
            totalMoves++;
        }

        return totalMoves % 2 == 1 && c[1] != c[2];
    }
}