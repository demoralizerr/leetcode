class Solution {
    public long minEnergy(int n, int brightness, int[][] intervals) {

        //merge intervals 
        List<int[]> merged = new ArrayList<>();
        // sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int a = intervals[0][0];
        int b = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            int c = curr[0];
            int d = curr[1];

            if (c <= b) {
                b = Math.max(b, d);
            } else {
                merged.add(new int[] { a, b });
                a = c;
                b = d;
            }

        }

        merged.add(new int[] { a, b });

        long active = 0;
        for (int i = 0; i < merged.size(); i++) {
            int[] time = merged.get(i);
            active += time[1] - time[0] + 1;
        }

        int k = 1;
        long pos = 0;
        while (brightness > 0 && k <= n) {
            k += 2;
            brightness = brightness - 3;
            pos++;
        }

        return pos * active;
    }
}