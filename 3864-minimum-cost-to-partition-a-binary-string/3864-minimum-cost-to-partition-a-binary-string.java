class Solution {
    private String s;
    private int encCost;
    private int flatCost;
    private long[] prefixsum;

    public long minCost(String s, int encCost, int flatCost) {
         int n = s.length();
         this.s = s;
         this.encCost=encCost;
         this.flatCost= flatCost;
         this.prefixsum = new long[n+1];
        
        for(int i=0;i<n;i++){
             prefixsum[i+1] = prefixsum[i] + (s.charAt(i)=='1'? 1 : 0) ;
        }
         
         return solve(0,n-1);
    }

    public long solve(int l,int r){
        long L = r-l+1;
        long X = prefixsum[r+1] - prefixsum[l];
        if(X==0) return flatCost;

        if(L==1) return encCost;

        if(L%2!=0)
           return L*X*encCost;

        int mid = l+(r-l)/2;  
        return Math.min(L*X*encCost , solve(l,mid)+solve(mid+1,r));  
    }
}