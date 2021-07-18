public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int nextFirst;
    private int nextLast;

    //Invariants:
    //The index of the last item will always be size-1;
    //The number of items in the deque will always be size;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int oneBehind(int index){
        if (index == array.length) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int oneBefore(int index){
        if (index == 0) {
            return array.length - 1;
        } else {
            return index - 1;
        }
    }

    private void resizeArray(int count) {
        T[] temp = (T[]) new Object[count];
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
        //d e f g h                                 a  b  c
        //nextFirst: 16, nextLast: 5
        //0 1 2 3 4 5 6 7 8 9
        //a b c d e f g h
        if (oneBefore(nextLast) < oneBehind(nextFirst)) {
            int len = array.length - oneBehind(nextFirst);
            System.arraycopy(array, oneBehind(nextFirst), temp, 0, len);
            System.arraycopy(array, 0, temp, len , size - len);
        } else {
            System.arraycopy(array, oneBehind(nextFirst), temp, 0, size);
        }
        array = temp;
        nextFirst = array.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resizeArray(size * 2);
        }
        array[nextFirst] = item;
        nextFirst = oneBefore(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resizeArray(size * 2);
        }
        array[nextLast] = item;
        nextLast = oneBehind(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst > nextLast) {
            for (int i = nextFirst + 1; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(array[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < size; i++) {
                System.out.print(array[i] + " ");
            }
        }
    }

    public T removeFirst() {
        T first = array[oneBehind(nextFirst)];
        array[oneBehind(nextFirst)] = null;
        size--;
        double usageRatio = (double) size/ array.length;
        if(usageRatio < 0.25 && array.length > 16){
            resizeArray(array.length/2);
        }
        nextFirst = oneBehind(nextFirst);
        return first;
    }

    public T removeLast(){
        T last = array[oneBefore(nextLast)];
        array[oneBefore(nextLast)] = null;
        size --;
        double usageRatio = (double) size/ array.length;
        if(usageRatio < 0.25 && array.length > 16){
            resizeArray(array.length/2);
        }
        nextLast = oneBefore(nextLast);
        return last;
    }

    public T get(int index){
        return array[oneBehind(nextFirst) + index - 1];
    }
}
