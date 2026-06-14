class Solution {
    public int pairSum(ListNode head) {
        int len = 0;
        ListNode curr = head;

        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int[] firstHalf = new int[len / 2];

        curr = head;
        for (int i = 0; i < len / 2; i++) {
            firstHalf[i] = curr.val;
            curr = curr.next;
        }

        int max = 0;
        int j = len / 2 - 1;

        while (curr != null) {
            max = Math.max(max, curr.val + firstHalf[j--]);
            curr = curr.next;
        }

        return max;
    }
}