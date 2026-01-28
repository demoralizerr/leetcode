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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = head;
        ListNode slow = dummy;
        //move fast pointer k times forward
        while (fast != null && n-- > 0) {
            fast = fast.next;
        }
        //move both pointers at same speed ,untill fast becomes
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
         //slow pointer is at nth node - 1 position  
        slow.next = slow.next.next;
          
        //return head  
        return dummy.next;
    }
}