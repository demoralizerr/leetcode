class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int totalPushes = 0;
        
        // Loop through each character based on its index
        for (int i = 0; i < n; i++) {
            // i / 8 determines the position on the key (0-indexed)
            // (i / 8) + 1 gives the actual number of pushes for that letter
            totalPushes += (i / 8) + 1;
        }
        
        return totalPushes;
    }
}
