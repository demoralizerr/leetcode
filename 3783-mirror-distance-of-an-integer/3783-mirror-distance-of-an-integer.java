class Solution {
    public int mirrorDistance(int n) {

        int reversedNum = 0;
        int num=n;
        while (num != 0) {
            int digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            num /= 10;
        }
        return Math.abs(n - reversedNum);
    }
}