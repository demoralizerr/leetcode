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
    public ListNode swapPairs(ListNode head) {

        //empty list or single node check   
        if(head==null || head.next==null) return head;

       ListNode dummy = new ListNode(0,head); 

       ListNode prev = dummy , curr = head;

       while(curr!=null && curr.next!=null){
         ListNode nxtpair = curr.next.next;
         ListNode second = curr.next;

         //reverse
         second.next = curr;
         curr.next = nxtpair;
         prev.next = second;

         //update ptrs
         prev = curr;
         curr = nxtpair;

       }
       return dummy.next;
    
      
    }
}