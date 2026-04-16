class Solution {
    public int firstMatchingIndex(String s) {
        int n = s.length();
        for(int i=0;i<n; i++){
             char ch = s.charAt(i);
             char ch1 = s.charAt(n-i-1);

             if(ch==ch1)
               return i;
        }
        return -1;
    }
}