class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        int n = intervals.length;
        List<int[]> merged = new ArrayList<>();

        while (i < n && intervals[i][1] < newInterval[0]) {
            merged.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        merged.add(new int[] { newInterval[0], newInterval[1] });

        while (i < n) {
            merged.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }

        return merged.toArray(new int[merged.size()][]);
    }
}