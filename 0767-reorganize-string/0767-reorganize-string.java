class Solution {
    class Pair {
        int count;
        char ch;

        public Pair(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }
    }

    public String reorganizeString(String s) {
        Queue<Pair> maxheap = new PriorityQueue<>((a, b) -> Integer.compare(b.count, a.count));
        StringBuilder res = new StringBuilder();
        int[] mp = new int[26];
        for (char ch : s.toCharArray()) {
            mp[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (mp[i] > 0) {
                char ch = (char) ('a' + i);
                maxheap.offer(new Pair(mp[i], ch));
            }

        }

        while (maxheap.size() > 1) {

            Pair pair1 = maxheap.poll();
            int count1 = pair1.count;
            char ch1 = pair1.ch;
            Pair pair2 = maxheap.poll();
            int count2 = pair2.count;
            char ch2 = pair2.ch;

            res.append(ch1).append(ch2);
            count1--;
            count2--;
            if (count1 > 0)
                maxheap.offer(new Pair(count1, ch1));

            if (count2 > 0)
                maxheap.offer(new Pair(count2, ch2));
        }

        while (!maxheap.isEmpty()) {
            Pair pair = maxheap.poll();

           if (res.length() > 0 && res.charAt(res.length() - 1) == pair.ch)
                   return "";
            else
                res.append(pair.ch);

             if(pair.count-1>0)   
                 maxheap.offer(new Pair(pair.count - 1, pair.ch));
        }

        return res.toString();

    }
}