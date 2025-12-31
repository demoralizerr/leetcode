
class pair{
    int index;
    int height;

    public pair(int index,int height){
        this.index=index;
         this.height=height;
    }
}
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<pair> s = new Stack<>();
        int maxarea=0;
         int n = heights.length;
        for(int i =0; i<heights.length;i++){
            int start = i;

            while(!s.isEmpty() && s.peek().height>heights[i]){
                int index = s.peek().index;
                int height = s.peek().height;
                maxarea =Math.max(maxarea, height *(i-index));
                start = index;
                s.pop();
            }
            s.push(new pair(start,heights[i]));
        }

        while(!s.isEmpty()){
             int index = s.peek().index;
             int height = s.peek().height;
             maxarea = Math.max(maxarea, height*(n-index));
             s.pop();
        }
        return maxarea;
    }
}