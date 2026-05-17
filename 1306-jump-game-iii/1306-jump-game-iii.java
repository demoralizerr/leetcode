class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 1; i <= size; i++) {
                int index = q.poll();

                if (arr[index] < 0)
                    continue;

                if (arr[index] == 0)
                    return true;

                int right = index + arr[index];
                int left =  index - arr[index];
                if (right < n)
                    q.offer(right);

                if (left >= 0)
                    q.offer(left);

                arr[index] = -arr[index];    
            }
        }

        return false;

    }
}