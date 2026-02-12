class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        //min heap by capital
        Queue<int[]> minheap = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        //maxheap by profits can be chosen for current capital w
        Queue<Integer> maxheap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int n = capital.length;
        for (int i = 0; i < n; i++) {
            minheap.offer(new int[] { capital[i], profits[i] });
        }

        while (k-- > 0) {
            while (!minheap.isEmpty() && minheap.peek()[0] <= w)
                maxheap.offer(minheap.poll()[1]);

            if (maxheap.isEmpty())
                break;
            w += maxheap.poll();
        }
        return w;
    }
}
