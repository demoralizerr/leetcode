class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        List<int[]> finals = new ArrayList<>();

        for (int[] i : intervals) {
            merged.add(i);
        }
        merged.add(newInterval);

        merged.sort((a, b) -> Integer.compare(a[0], b[0]));

        int[] prev = merged.get(0);
        int start = prev[0];
        int end = prev[1];

        for (int i = 1; i < merged.size(); i++) {
            int[] next = merged.get(i);
            int newstart = next[0];
            int newend = next[1];
            if (newstart <= end) {
                end = Math.max(end, newend);
            } else {
                finals.add(new int[] { start, end });
                start = newstart;
                end = newend;
            }
        }

        finals.add(new int[] { start, end });

        return finals.toArray(new int[finals.size()][]);

    }
}