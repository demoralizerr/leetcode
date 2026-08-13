class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        char[] arr = s.toCharArray();

        TreeMap<Integer, Integer> start = new TreeMap<>();   // runStart -> runLength
        TreeMap<Integer, Integer> lenCount = new TreeMap<>(); // runLength -> count of runs

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && arr[j] == arr[i]) j++;
            int length = j - i;
            start.put(i, length);
            lenCount.merge(length, 1, Integer::sum);
            i = j;
        }

        int k = queryCharacters.length();
        int[] result = new int[k];

        for (int q = 0; q < k; q++) {
            int idx = queryIndices[q];
            char c = queryCharacters.charAt(q);

            if (arr[idx] != c) {
                Map.Entry<Integer, Integer> entry = start.floorEntry(idx);
                int runStart = entry.getKey();
                int runLen = entry.getValue();
                int runEnd = runStart + runLen - 1;

                removeLen(lenCount, runLen);
                start.remove(runStart);

                if (idx > runStart) {
                    int leftLen = idx - runStart;
                    start.put(runStart, leftLen);
                    lenCount.merge(leftLen, 1, Integer::sum);
                }
                if (idx < runEnd) {
                    int rightLen = runEnd - idx;
                    start.put(idx + 1, rightLen);
                    lenCount.merge(rightLen, 1, Integer::sum);
                }

                arr[idx] = c;
                int newStart = idx, newEnd = idx;

                Map.Entry<Integer, Integer> leftEntry = start.lowerEntry(idx);
                if (leftEntry != null) {
                    int lStart = leftEntry.getKey();
                    int lLen = leftEntry.getValue();
                    if (lStart + lLen - 1 == idx - 1 && arr[lStart] == c) {
                        removeLen(lenCount, lLen);
                        start.remove(lStart);
                        newStart = lStart;
                    }
                }

                if (start.containsKey(idx + 1) && arr[idx + 1] == c) {
                    int rLen = start.get(idx + 1);
                    removeLen(lenCount, rLen);
                    start.remove(idx + 1);
                    newEnd = idx + 1 + rLen - 1;
                }

                int newLen = newEnd - newStart + 1;
                start.put(newStart, newLen);
                lenCount.merge(newLen, 1, Integer::sum);
            }

            result[q] = lenCount.lastKey();
        }

        return result;
    }

    private void removeLen(TreeMap<Integer, Integer> lenCount, int length) {
        int cnt = lenCount.get(length);
        if (cnt == 1) lenCount.remove(length);
        else lenCount.put(length, cnt - 1);
    }
}