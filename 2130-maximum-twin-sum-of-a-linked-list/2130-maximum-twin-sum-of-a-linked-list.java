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

        ListNode slow = head;
        ListNode fast = head;

        //find middle node
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //reverse second half 
        ListNode curr = slow;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // prev = head of reversed second half
        int max = 0;

        ListNode p1 = head;
        ListNode p2 = prev;

        while (p2 != null) {
            max = Math.max(max, p1.val + p2.val);
            p1 = p1.next;
            p2 = p2.next;
        }

        return max;

    }
}