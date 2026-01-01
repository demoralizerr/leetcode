class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>(); // stores INDICES
        int n = nums.length;
        int l = 0, r = 0;

        while (r < n) {

            // remove smaller elements from back
            while (!q.isEmpty() && nums[r] > nums[q.peekLast()]) {
                q.pollLast();
            }
            q.addLast(r);
            r++;

            // check window size
            if (r - l == k) {

                // front of deque is max
                list.add(nums[q.peekFirst()]);

                // remove element going out of window
                if (q.peekFirst() == l) {
                    q.pollFirst();
                }
                l++;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
