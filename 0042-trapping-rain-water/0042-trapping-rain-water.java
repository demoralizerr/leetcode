class Solution {
    public int trap(int[] height) {
        Stack<Integer> s = new Stack<>();
        int waterTrapped = 0;
        int n = height.length;
        for (int i = 0; i < n; i++) {

            while (!s.isEmpty() && height[i] > height[s.peek()]) {

                int mididx = s.pop();

                if (s.isEmpty())
                    break; // no left wall exists to trap more water

                int leftidx = s.peek();
                int rightidx = i;
                int width = rightidx - leftidx - 1;
                int boundedheight = Math.min(height[leftidx], height[rightidx]) - height[mididx];

                if (boundedheight > 0)
                    waterTrapped += (width * boundedheight);
                System.out.println(waterTrapped);
            }
            s.push(i);

        }
        //once loop ends means no more right wall exists to trap water
        return waterTrapped;
    }
}