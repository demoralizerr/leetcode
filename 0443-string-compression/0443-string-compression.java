class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1) return 1;

        int left = 0, right = 1;
        int write = 0;
        int count = 1;

        while (right < n) {
            if (chars[right] == chars[left]) {
                count++;
                right++;
            } else {
                // write character
                chars[write++] = chars[left];

                // write count if count > 1
                if (count > 1) {
                    for (char c : String.valueOf(count).toCharArray()) {
                        chars[write++] = c;
                    }
                }

                // reset for next group
                left = right;
                right++;
                count = 1;
            }
        }

        // handle last group
        chars[write++] = chars[left];
        if (count > 1) {
            for (char c : String.valueOf(count).toCharArray()) {
                chars[write++] = c;
            }
        }

        return write;
    }
}
