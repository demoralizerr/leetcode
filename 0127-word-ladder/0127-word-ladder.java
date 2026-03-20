class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord))
            return 0;

        Map<String, List<String>> nei = new HashMap<>();
        wordList.add(beginWord);
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                nei.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        Set<String> visit = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int res = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();

                if (word.equals(endWord)) {
                    return res;
                }
                for (int j = 0; j < word.length(); j++) {
                    String pattern = word.substring(0, j) + "*" + word.substring(j + 1);
                    for (String neiWord : nei.getOrDefault(pattern, Collections.emptyList())) {
                        if (!visit.contains(neiWord)) {
                            visit.add(neiWord);
                            q.offer(neiWord);
                        }
                    }
                }
            }
             res++;
        }
        return 0;
    }
}