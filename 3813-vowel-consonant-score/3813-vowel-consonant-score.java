class Solution {
    public int vowelConsonantScore(String s) {
        int v = 0;
        int c = 0;

         for(char ch : s.toCharArray()){
            if(Character.isDigit(ch) || ch==' ') continue;  
            if(isVowel(ch)) v++;
            else c++;

         }
        if(c>0) return (int) Math.floor(v/c) ;
        else return 0;
    }

    private boolean isVowel(char ch){
      if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') return true;
      else return false;

    }
}