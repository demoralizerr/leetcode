import java.util.*;

class Solution {

    static class Pair {
        int pos;
        boolean backUsed;

        Pair(int pos, boolean backUsed) {
            this.pos = pos;
            this.backUsed = backUsed;
        }
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {

        Set<Integer> forbiddenSet = new HashSet<>();
        for (int f : forbidden)
            forbiddenSet.add(f);

        Queue<Pair> queue = new LinkedList<>();

        // visited[position][0/1]
        boolean[][] visited = new boolean[6001][2];

        queue.offer(new Pair(0, false));
        visited[0][0] = true;

        int jumps = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                Pair curr = queue.poll();

                int pos = curr.pos;
                boolean backUsed = curr.backUsed;

                if (pos == x)
                    return jumps;

                // forward jump
                int forward = pos + a;

                if (forward <= 6000 &&
                        !forbiddenSet.contains(forward) &&
                        !visited[forward][0]) {

                    visited[forward][0] = true;
                    queue.offer(new Pair(forward, false));
                }

                // backward jump
                int backward = pos - b;

                if (!backUsed &&
                        backward >= 0 &&
                        !forbiddenSet.contains(backward) &&
                        !visited[backward][1]) {

                    visited[backward][1] = true;
                    queue.offer(new Pair(backward, true));
                }
            }

            jumps++;
        }

        return -1;
    }
}