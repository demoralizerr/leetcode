class Solution {
    public int findJudge(int n, int[][] trust) {

        int[][] graph = new int[n][n];
        for (int i = 0; i < trust.length; i++) {
            int p1 = trust[i][0];
            int p2 = trust[i][1];
            graph[p1 - 1][p2 - 1] = 1;
        }
        int judge = -1;

        //find person on who trust nobody
        for (int i = 0; i < n; i++) {
            boolean canbeJudge = true;
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1)
                    canbeJudge = false;
            }

            if (canbeJudge)
                judge = i;
        }

        if (judge == -1)
            return judge;

        //verify all others trusts chosen person then only he is judge
        for (int i = 0; i < n; i++) {
            if (i != judge) {
                if (graph[i][judge] == 0)
                    return -1;
            }
        }

        return judge + 1;
    }
}