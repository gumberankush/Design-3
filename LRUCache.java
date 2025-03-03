// TimeComplexity: O(1) - Put and Get
// SpaceComplexity: O(n) - n is the capacity of the cache


import java.util.HashMap;
import java.util.Map;

class LRUCache {
    int capacity;
    ListNode head;
    ListNode tail;
    Map<Integer, ListNode> map;


    // Remember to handle orphan node pointers first
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            ListNode node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        ListNode node = null;
        if(!map.containsKey(key)){
            node = new ListNode(key, value);
            insert(node);
        }else{
            node = map.get(key);
            node.value = value;
            remove(node);
            insert(node);
        }

        if(map.size() > capacity){
            node = tail.prev;
            remove(node);
        }
    }

    public void remove(ListNode node){
        if(node != null){
            map.remove(node.key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void insert(ListNode node){
        if(node != null){
            map.put(node.key, node);
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            node.prev = head;
        }
    }
}

class ListNode{
    ListNode prev;
    ListNode next;
    int key, value;

    public ListNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
