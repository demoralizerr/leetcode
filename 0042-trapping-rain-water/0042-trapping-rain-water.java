class Solution {
    public int trap(int[] height) {
        Stack<Integer> s = new Stack<>();
        int n = height.length;
        int maxwater = 0;
        for (int i = 0; i < n; i++) {

            while (!s.isEmpty() && height[i] > height[s.peek()]) {
                int mididx = s.pop();
                if (s.isEmpty())
                    break; // no left wall exist 
                int leftidx = s.peek();
                int rightidx = i;
                int boundheight = Math.min(height[leftidx], height[rightidx]) - height[mididx];
                int width = rightidx - leftidx -1;
                if (boundheight > 0)
                    maxwater += (boundheight * width);
            }
            s.push(i);
        }
        return maxwater;
    }
}