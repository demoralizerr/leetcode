class Solution {
    public int countMonobit(int n) {
        int count = 0;
        if(n==0) return 1;
        for (int i = 2; i <= n; i++) {
            boolean allones = (i & (i + 1)) == 0;
            if (allones)
                count++;
        }

        return 2 + count;
    }
}