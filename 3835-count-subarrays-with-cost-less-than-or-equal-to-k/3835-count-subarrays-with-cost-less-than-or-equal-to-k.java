class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long count = 0;
        int left = 0;
        
        // Monotonic deques storing indices
        Deque<Integer> maxDeque = new ArrayDeque<>();  // decreasing order
        Deque<Integer> minDeque = new ArrayDeque<>();  // increasing order
        
        for (int right = 0; right < n; right++) {
            // Add nums[right] to max deque (maintain decreasing order)
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(right);
            
            // Add nums[right] to min deque (maintain increasing order)
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(right);
            
            // Shrink window while cost > k
            while (left <= right) {
                // O(1) max and min access
                int currMax = nums[maxDeque.peekFirst()];
                int currMin = nums[minDeque.peekFirst()];
                long length = right - left + 1;
                long cost = (long)(currMax - currMin) * length;
                
                if (cost <= k) {
                    break;
                }
                
                // Remove left element from deques if needed
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++;
            }
            
            // All subarrays ending at right, starting from left to right
            count += right - left + 1;
        }
        
        return count;
    }
}