public class ArrayDeque<T> {
    private int size;
    private T[] array;

    //Invariants:
    //The index of the last item will always be size-1;
    //The number of items in the deque will always be size;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
    }

    private void resizeArray(int count){
        T[] temp = (T[]) new Object[count];
        System.arraycopy(array,0,temp,0,size);
        array = temp;
    }

    private void moveItem(int pos, int step){
        T[] temp = (T[]) new Object[array.length];
        System.arraycopy(array,pos,temp,pos+step, size);
        array = temp;
    }

    public void addFirst(T item){
        if(size == array.length){
            resizeArray(size * 2);
        }
        moveItem(0,1);
        array[0] = item;
        size++;
    }

    public void addLast(T item){
        if(size == array.length){
            resizeArray(size * 2);
        }
        array[size] = item;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = 0; i < size; i++){
            System.out.print(array[i]+" ");
        }
    }

    public T removeFirst(){
        T first = array[0];
        moveItem(1,-1);
        size--;
        double usageRatio = size/ array.length;
        if(usageRatio < 0.25 && array.length > 16){
            resizeArray(array.length/2);
        }
        return first;
    }

    public T removeLast(){
        T last = array[size - 1];
        array[size - 1] = null;
        size --;
        double usageRatio = size/ array.length;
        if(usageRatio < 0.25 && array.length > 16){
            resizeArray(array.length/2);
        }
        return last;
    }

    public T get(int index){
        return array[index];
    }
}
