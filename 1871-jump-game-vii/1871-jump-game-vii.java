class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        // If the last character is '1', we can never reach it
        if (s.charAt(n - 1) == '1') {
            return false;
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        
        // 'far' tracks the furthest index we have already added to the queue
        int far = 0; 

        while (!q.isEmpty()) {
            int currindex = q.poll();
            
            if (currindex == n - 1) {
                return true;
            }

            // Calculate the valid window for the next jump
            int leftLimit = currindex + minJump;
            int rightLimit = Math.min(currindex + maxJump, n - 1);

            // Start from leftLimit, but skip anything <= far to avoid duplicate work
            int start = Math.max(leftLimit, far + 1);

            for (int j = start; j <= rightLimit; j++) {
                if (s.charAt(j) == '0') {
                    q.offer(j);
                }
            }

            // Update far so the next iterations don't re-examine these indices
            far = Math.max(far, rightLimit);
        }

        return false;
    }
}
