class Solution {
    public String largestEven(String s) {
        int n = s.length();
        int lastnum = 0;int index = 0;
        for(int i=n-1;i>=0;i--){
            lastnum = Integer.valueOf(s.charAt(i));
           if(lastnum%2==0){
             return s.substring(0,i+1);
           }
           
        }

        return s.substring(0,index);
        
    }
}