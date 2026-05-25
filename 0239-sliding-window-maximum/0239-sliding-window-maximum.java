class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //heap will store[value,index]
        PriorityQueue<int[]> maxheap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        int left = 0;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            maxheap.add(new int[] { nums[i], i });
        }
        res[0] = maxheap.peek()[0];

        for (int right = k; right < n; right++) {
            //keep adding new item in maxheap
            maxheap.add(new int[] { nums[right], right });

            while (maxheap.peek()[1] <= right - k) {
                maxheap.poll();
            }

            res[right - k + 1] = maxheap.peek()[0];

        }
        return res;

    }
}