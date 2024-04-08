package collections.lists.array_list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class ArrayList<T extends Comparable<T>>{
    public static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }
    public ArrayList(int length) {
        this.array = new Object[length];
    }

    public ArrayList(Collection<? extends T> collection) {
        this.array = new Object[collection.size()];
        for (T item : collection) {
            this.array[size++] = item;
        }
    }

    public static <T extends Comparable<T>> void sort(Collection<T> collection) {
        insertionSort(collection);
    }

    private static <T extends Comparable<T>> void insertionSort(Collection<T> collection) {
        T[] array = (T[]) collection.toArray(new Comparable[0]);
        for (int i = 1; i < array.length; i++) {
            T current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(current) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        collection.clear();
        for (T item : array) {
            collection.add(item);
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = array.length;
        if (oldCapacity > 0 || array != DEFAULT_EMPTY_ARRAY) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            return array = Arrays.copyOf(array, newCapacity);
        } else {
            return array = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void add(T e, Object[] elements, int s) {
        if (s == elements.length)
            elements = grow(size + 1);
        elements[s] = e;
        size = s + 1;
    }

    public boolean add(T element){
        add(element, array, size);
        return true;
    }

    public boolean addAll(Collection<? extends T> collection){
        Object[] insertArray = collection.toArray();
        int insertSize = insertArray.length;
        if(insertSize == 0)
            return false;
        if(insertSize > (array.length - size))
            array = grow(insertSize + size);
        System.arraycopy(insertArray, 0, array, size, insertSize);

        size += insertSize;
        return true;
    }

    public T get(int index){
        checkIndex(index);
        return (T) this.array[index];
    }

    private void remove(Object[] rem, int i) {
        final int remSize;
        if ((remSize = size - 1) > i)
            System.arraycopy(rem, i + 1, rem, i, remSize - i);
        size = remSize;
        rem[size] = null;
    }

    public boolean remove(Object o) {
        final Object[] rem = array;
        final int size = this.size;
        int i;
        for(i = 0; i < size; i++){
            if((o == null && rem[i] == null) || (o != null && o.equals(rem[i]))){
                remove(rem, i);
                return true;
            }
        }
        return false;
    }


    public T remove(int index){
        checkIndex(index);
        T oldValue = (T) array[index];
        remove(array, index);
        return oldValue;
    }

    private void checkIndex(int index){
        if(index > this.array.length || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }

    public void sort(Comparator<? super T> comparator) {
        boolean isSorted = false;
        int i = 0;
        while (!isSorted) {
            isSorted = true;
            for (int j = i; j < size - 1; j++) {
                if (comparator.compare((T) array[j], (T) array[j + 1]) > 0) {
                    T temp = (T) array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                }
            }
            i++;
        }
    }

    public int size() {
        return this.size;
    }
}
