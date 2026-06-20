class Solution {
    public int maxBuilding(int n, int[][] restrictions) {

        List<int[]> list = new ArrayList<>();

        list.add(new int[]{1, 0});

        for (int[] r : restrictions)
            list.add(new int[]{r[0], r[1]});

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        // Left -> Right
        for (int i = 1; i < list.size(); i++) {
            int d = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(list.get(i)[1], list.get(i - 1)[1] + d);
        }

        // Right -> Left
        for (int i = list.size() - 2; i >= 0; i--) {
            int d = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(list.get(i)[1], list.get(i + 1)[1] + d);
        }

        int ans = 0;

        // Maximum peak between consecutive restrictions
        for (int i = 1; i < list.size(); i++) {
            int[] a = list.get(i - 1);
            int[] b = list.get(i);

            int dist = b[0] - a[0];
            int peak = Math.max(a[1], b[1]) + (dist - Math.abs(a[1] - b[1])) / 2;

            ans = Math.max(ans, peak);
        }

        // After the last restriction
        int[] last = list.get(list.size() - 1);
        ans = Math.max(ans, last[1] + (n - last[0]));

        return ans;
    }
}