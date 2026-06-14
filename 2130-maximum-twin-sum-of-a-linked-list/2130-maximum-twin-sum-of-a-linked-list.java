/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {
        int len = 0;
        ListNode curr = head;
        int max = Integer.MIN_VALUE;

        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int[] arr = new int[len / 2];
        curr = head;
        int idx = 0;
        int mid = arr.length - 1;

        while (curr != null) {
            if (idx < len / 2) {
                arr[idx++] = curr.val;
                curr = curr.next;
            } else {
                max = Math.max(curr.val + arr[mid], max);
                mid--;
                curr = curr.next;
            }
        }

        return max;

    }
}