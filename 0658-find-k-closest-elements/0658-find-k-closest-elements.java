class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        List<Integer> res = new ArrayList<>();
        int startPosMin = 0; int startPosMax=n-k;
        while(startPosMin<startPosMax){ //0(n-k)
             int leftBoundary = arr[startPosMin];
             int rightBoundary = arr[startPosMax+k-1];
             if( Math.abs(leftBoundary-x)> Math.abs(rightBoundary-x))
                startPosMin++;
             else
                 startPosMax--;  
        } 

        for(int i=startPosMin; i<startPosMin+k; i++){  //0(k)
            res.add(arr[i]);
        }
        return res;
        //total complexity = 0(1)+0(n-k)+0(k) = 0(n-k+k) = 0(n)
    }
}