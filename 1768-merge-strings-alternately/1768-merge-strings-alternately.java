class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder str = new StringBuilder();
        int left1 = 0; int left2 = 0;
        while(left1<word1.length() && left2<word2.length()){
              
              str.append(word1.charAt(left1)); left1++;
              str.append(word2.charAt(left2)); left2++;
              
        }

        if(left1<word1.length()){
            str.append(word1.substring(left1,word1.length()));
        }

         if(left2<word2.length()){
            str.append(word2.substring(left2,word2.length()));
        }

        return str.toString();
    }
}