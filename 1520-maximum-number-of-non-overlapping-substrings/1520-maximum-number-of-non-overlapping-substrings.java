class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> res = new ArrayList<>();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (first[ch - 'a'] == -1) {
                first[ch - 'a'] = i;
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (last[ch - 'a'] == -1) {
                last[ch - 'a'] = i;
            }
        }

        List<int[]> minwin = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i == first[ch - 'a']) {
                int start = first[ch - 'a'];
                int end = last[ch - 'a'];
                int j = i + 1;
                boolean flag = true;
                while (j <= end) {
                    char ch1 = s.charAt(j);
                    if (last[ch1 - 'a'] > end) {
                        end = last[ch1 - 'a'];
                    }

                    if (start > first[ch1 - 'a']) {
                        flag = false;
                        break;
                    }
                    j++;
                }
                if (flag) {
                    minwin.add(new int[] { start, end });
                }
            }
        }

        minwin.sort((a, b) -> a[1] - b[1]);
        int prevend = -1;
        for (int[] interval : minwin) {
            int currstart = interval[0];
            if (currstart > prevend) {
                int st = interval[0];
                int end = interval[1];
                res.add(s.substring(st, end + 1));
                 prevend = interval[1];
            }
           
        }
        return res;
    }
}