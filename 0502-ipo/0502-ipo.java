class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        //min heap by capital
        Queue<Integer> minheap = new PriorityQueue<>((a, b) -> Integer.compare(capital[a], capital[b]));
        //maxheap by profits can be chosen for current capital w
        Queue<Integer> maxheap = new PriorityQueue<>((a, b) -> Integer.compare(profits[b], profits[a]));
        int n = capital.length;
        for (int i = 0; i < n; i++) {
            minheap.offer(i);
        }

        while (k-- > 0) {
            while (!minheap.isEmpty() && capital[minheap.peek()] <= w)
                maxheap.offer(minheap.poll());

            if (maxheap.isEmpty())
                break;
            w += profits[maxheap.poll()];
        }
        return w;
    }
}
