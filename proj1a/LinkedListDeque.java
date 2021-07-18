public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    private class Node<T> {
        private Node prev;
        private T item;
        private Node next;
        private Node(Node prev_, T item_, Node next_) {
            prev = prev_;
            item = item_;
            next = next_;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, 3435, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node first = sentinel.next;
            sentinel.next = first.next;
            first.next.prev = sentinel;
            size--;
            return (T) first.item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node last = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            last.prev.next = sentinel;
            size--;
            return (T) last.item;
        }
    }

    public T get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        Node ptr = sentinel;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return (T) ptr.item;
    }

    public T getRecursive(int index) {
        return (T) getRecursive_(index).item;
    }

    private Node getRecursive_(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return sentinel.next;
        } else {
            return getRecursive_(index - 1).next;
        }
    }
}
