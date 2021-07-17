public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    private class Node<T> {
        public Node prev;
        public Node next;
        public T item;
        public Node(Node _prev,T _item,Node _next){
            prev = _prev;
            item = _item;
            next = _next;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(sentinel,3435,sentinel);
        size = 0;
    }

    public void addFirst(T item){
        this.sentinel.next = new Node(sentinel, item, sentinel.next);
        size ++;
    }

    public void addLast(T item){
        this.sentinel.prev = new Node(sentinel.prev, item, sentinel);
        size ++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = 0; i < size; i++){
            Node ptr = sentinel;
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        } else {
            Node first = sentinel.next;
            sentinel.next = sentinel.next.next;
            first.next.prev = sentinel;
            first.next = null;
            first.prev = null;
            return (T) first.item;
        }
    }

    public T removeLast(){
        if(size == 0){
            return null;
        } else {
            Node last = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            last.prev.next = sentinel;
            last.prev = null;
            last.next = null;
            return (T) last.item;
        }
    }

    public T get(int index){
        if(size == 0 || index < 0 || index >= size){
            return null;
        }
        Node ptr = sentinel;
        for(int i = 0; i <= index; i++){
            ptr = ptr.next;
        }
        return (T) ptr.item;
    }

    public T getRecursive(int index){
        return (T) _getRecursive(index).item;
    }

    public Node _getRecursive(int index){
        if(size == 0 || index < 0 || index >= size){
            return null;
        }
        if(index == 0){
            return sentinel.next;
        } else {
            return _getRecursive(index - 1).next;
        }
    }
}
