class Solution {
    class Data {
        int cap;
        int start;
        int end;

        public Data(int cap, int start, int end) {
            this.cap = cap;
            this.start = start;
            this.end = end;
        }
    }

    public boolean carPooling(int[][] trips, int capacity) {
        Queue<Data> minheap = new PriorityQueue<>((a, b) -> Integer.compare(a.start, b.start));
        int n = trips.length;
        boolean possible = true;
        for (int i = 0; i < n; i++) {
            minheap.offer(new Data(trips[i][0], trips[i][1], trips[i][2]));
        }

        Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        int dist = -1;
        int currCap = 0;
        while (true) {
            dist++;

            while (!q.isEmpty() && q.peek()[1] <= dist) {
                currCap = currCap - q.poll()[0]; //when trip ends , get back the car capacity
            }

            while (!minheap.isEmpty() && minheap.peek().start == dist) {
                Data d = minheap.poll();
                currCap = currCap + d.cap; // when adding new passenger at dist ,increase count of curr people in car
                if (currCap > capacity) { //at any point current capcity greater then max allowed capacity , trip not possible
                    return false;
                }
                q.offer(new int[] { d.cap, d.end });
            }

            if (q.isEmpty() && minheap.isEmpty()) {
                return true;
            }

        }
    }
}