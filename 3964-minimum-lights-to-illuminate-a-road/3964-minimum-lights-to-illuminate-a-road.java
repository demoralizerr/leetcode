class Solution {
    public int minLights(int[] lights) {
        int n = lights.length;

        int[] diff = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (lights[i] == 0)
                continue;

            int l = Math.max(0, i - lights[i]);
            int r = Math.min(n - 1, i + lights[i]);

            diff[l]++;
            if (r + 1 < n)
                diff[r + 1]--;
        }

        boolean[] visible = new boolean[n];

        int currsum = 0;
        for (int i = 0; i < n; i++) {
            currsum += diff[i];
            visible[i] = currsum > 0;
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (visible[i] == true)
                continue;

            int pos = Math.min(i + 1, n - 1);
            ans++;

            if (pos - 1 > 0)
                visible[pos - 1] = true;
            visible[pos] = true;
            if (pos + 1 < n)
                visible[pos + 1] = true;
        }
        return ans;
    }
}